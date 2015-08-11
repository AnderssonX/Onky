package mattias.andersson.onky.player;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.GameView;
import mattias.andersson.onky.Particle.SpinParticle;
import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.powerup.PowerUp;

/**
 * Created by Alrik on 2015-07-30.
 */
public abstract class Player {
    long trailspawnTimer;
    public ArrayList<PowerUp> usedPowerup = new ArrayList<PowerUp>();
    // PImage ONKYSpriteSheet,Life, cell; //setup
    public float angle, decayFactor= .95f,punchTime, jumpTime, invis, toSlow;
    public Point2D coord= new Point2D(0,-1000), velocity= new Point2D(), acceleration= new Point2D(0,.9f), size= new Point2D();
    public final int MAX_LIFE = 3, MAX_JUMP = 2, PUNCH_MAX_CD = 20, SMASH_MAX_CD = 50, defaultSpeed = 10, MAX_POWERUP_SIZE = 16;
    public int cooldown, collectCooldown, jumpHeight = 20, jumpCount = MAX_JUMP, downDashSpeed = 35, lives = MAX_LIFE,punchCooldown = PUNCH_MAX_CD, punchRange = 100, attractRange, stompRange = 150;
    public float duckTime, duckCooldown, duckHeight = 45, tauntTime, smashTime, smashCooldown = SMASH_MAX_CD, smashRange = 100, attckSpeedReduction;
    public boolean taunt = true, dead, onGround, punching, stomping, ducking, invincible, respawning, tokenDroping;
    public int totalJumps, totalAttacks, totalDucks, tutorialCourseRetries;
    public float averageSpeed;
    public Paint color = new Paint(Color.WHITE);
    public Paint defaultWeaponColor = new Paint(Color.WHITE);
    public Paint weaponColor = defaultWeaponColor;
    //   int amountOfFrames=140;

    public void Player() {

    }
    /*void update() {
        if (taunt) {
            if (tauntTime<80) {
                tauntTime++;
                vx=0;
            } else {
                vx=defaultSpeed;
                taunt=false;
                tauntTime=0;
                entities.add(new SparkParticle(int(x+w), int(y), 50, defaultWeaponColor));
            }
        } else {

            if (vx<1 && vx>-1) vx=1;
            if (vx<speedLevel && vx>0)vx*=1+0.08*speedFactor;
            if (vx<0)vx*= decayFactor*speedFactor;
            if (punchTime<=0 && punchCooldown>0)punchCooldown--;
            if (jumpCount<MAX_JUMP-1)angle+=15*speedFactor;

            if (invincible|| 0<invis)recover();
            if (0<collectCooldown)collectCooldown--; // cant collect

            // cutSprite(int(x*0.025));    //cutSprite(int(x/40));

            checkIfGround();

            checkDuck();

            checkIfStuck();

            spawnSpeedEffect();


            if (!onGround)jumpTime+=1*speedFactor;

            //if (respawning)respawn() ;

            for (int i=usedPowerup.size ()-1; i>=0; i--) {  // powerup handeling
                usedPowerup.get(i).use();
                if (usedPowerup.get(i).dead) {
                    usedPowerup.remove(usedPowerup.get(i));
                    UpdatePowerupGUILife();
                }
            }
     /* if (usedPowerup.size()>MAX_POWERUP_SIZE) {
        usedPowerup.remove(usedPowerup.size()-1);
        UpdatePowerupGUILife();
      }

            if (punching && punchCooldown<=0)punch();

            if (lives<0)gameOver();
        }
    }

    void display() {
        pushMatrix();
        translate(int(x+w*0.5), int(y+h*0.5));
        rotate(radians(angle));
        if (taunt) {
            cell=cutSpriteSheet(int(tauntTime));
            image(cell, -30, -40, 100, 80);
        } else {
            if (ducking && onGround) {
                cell=cutSpriteSheet(129);
                blink();
                image(cell, -30, -40, 100, 80);
            } else {
                if (jumpCount==MAX_JUMP-1) {
                    cell=cutSpriteSheet(130);
                    blink();
                    image(cell, -w*0.5, -h*0.5, 100, 80);
                } else   if (jumpCount==MAX_JUMP) {  // jump ability restored
                    cell=cutSpriteSheet(int(x*0.03%16));
                    blink();
                    image(cell, -w*0.5, -h*0.5, w, h);
                } else {
                    cell=cutSpriteSheet(131);
                    blink();
                    image(cell, -w*0.5, -h*0.5, 100, 80);
                }
            }
            if (millis() > trailspawnTimer+80/speedFactor) {
                if (ducking && onGround) entities.add(new TrailParticle(int(x), int(y-duckHeight*0.5), cell));
                else entities.add(new TrailParticle(int(x), int(y), cell));
                trailspawnTimer=millis();
            }
    if (taunt) {
      cell=animSprite[int(tauntTime)];
      image(cell, -30, -40, 100, 80);
    } else {
      if (ducking && onGround) {
        cell=animSprite[129];
        blink();
        image( displaySprite, -30, -40, 100, 80);
      } else {
        if (jumpCount==MAX_JUMP-1) { // jump
          cell=animSprite[130];
          blink();
          image(displaySprite, -w*0.5, -h*0.5, 100, 80);
        } else   if (jumpCount==MAX_JUMP) {  // jump ability restored
          cell=animSprite[int(x*0.03%16)];
          blink();

          image(displaySprite, -w*0.5, -h*0.5, w, h);
        } else {
          cell=animSprite[131];
          blink();

          image(displaySprite, -w*0.5, -h*0.5, 100, 80);
        }
      }
      if (millis() > trailspawnTimer+80/speedFactor) {
        if (ducking && onGround) entities.add(new TrailParticle(int(x), int(y-duckHeight*0.5), cell));
        else entities.add(new TrailParticle(int(x), int(y), cell));
        trailspawnTimer=millis();
      }
        }

        popMatrix();
        fill(0);
        // if (debug)text ("averageSpeed:"+averageSpeed +" :"+totalJumps +" totalducks:"+totalDucks + " totalAttack:"+totalAttacks, p.x+300, p.y-200, 500, -100);
        if (debug)text ("ducking:"+ducking +" onGround:"+onGround +" punching:"+punching + " invincible:"+invincible+" invis:"+invis+" tokenDroping"+tokenDroping, p.x+300, p.y-200, 500, -200);
    }*/
    public void update() {
        coord.add(velocity);
        velocity.add(acceleration);
        checkFallen();
        if (velocity.x<1 && velocity.x>-1) velocity.x=1;
        if (velocity.x<GameView.speedLevel && velocity.x>0)velocity.x*=1+0.08;
        if (velocity.x<0)velocity.x*= decayFactor;
        if (invincible|| 0<invis)recover();

        if (punchTime<=0 && punchCooldown>0)punchCooldown--;
        if (punching && punchCooldown<=0)checkAttack();
    }

    public void display() {
        GameThread.c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);

        if (punching) GameThread.c.drawRect(coord.x + punchRange, coord.y, coord.x + size.x+punchRange, coord.y + size.y, color);

    }
    public void checkJump(){


    }
    public void jump(){
        if (jumpCount>0) {
            totalJumps++;
            onGround=false;
            ducking=false;
            if (jumpCount==2) {
                //   entities.add(new LineParticle(int(x+w*0.5), int(y+h), 15, 0));
            }
            //  playSound(jumpSound);
               if (jumpCount<MAX_JUMP) GameView.particles.add( new SpinParticle( coord, new Point2D(100,100), new Paint(Color.WHITE)));
            jumpCount--;
            velocity.y=-jumpHeight;
        }
    }
    public void checkDuck(){

    }
    public void duck(){
        if (!onGround) {
            velocity.y=downDashSpeed;

          //  if (punching) entities.add(new slashParticle(int(x), int(y), 4)); // downdash Attack
        }
        if (attckSpeedReduction<30)attckSpeedReduction=30;  // redused attack cooldown when ducking

        //if (jumpCount<MAX_JUMP && !ducking)entities.add(new LineParticle(int(x+w), int(y+h*2), 60, 80));

        if (!ducking) {
            duckTime=50;
            totalDucks++;
            ducking=true;
           //( coord.y+=duckHeight;
        } else if (ducking && duckTime>0) {
            duckTime=50;
        }
    }
    void checkFallen(){
        if(coord.y>GameView.height){
            respawn();
        }
    }
    void recover() {
        invis-=1;
        if (invis<1) {
            invis=0;
            if (invincible) { // super
                velocity.x=GameView.speedLevel;
                //changeMusic(regularSong);
            }
            invincible=false;
        }
    }
    void respawn() {
        usedPowerup.clear();
        angle=0;
        invis=100;
        jumpCount=MAX_JUMP;
        velocity.x*= -0.5;
        //scaleFactor=0.1;
        coord.x-=400;
        coord.y=-50-size.x;
       /* for (Obstacle o : obstacles) {
            if (o.y+o.h > p.y && p.y +p.h > o.y &&  o.x > p.x-400 && o.x+o.w < p.x+p.w+800 ) {
                o.impactForce=60;
                o.health=0;
                o.death();
            }
        }
        entities.add(new slashParticle(int(x+400), int(y+h), 5, 400));
        entities.add(new Lumber(int(x), int(floorHeight-700), 400, 25, true) );
        */
        respawning=false;
        //UpdateGUILife(); // updateGUI
    }

    public void checkAttack(){
        if (punchTime<=0) {
            punching=false;
            if (invincible)  punchCooldown=(int)(PUNCH_MAX_CD-attckSpeedReduction);
            else punchCooldown=PUNCH_MAX_CD;
        } else {
            punchTime-= 1;
            if (ducking) {
            } else {
                if ((int)(punchTime)==15 ) {
                    //  entities.add(new slashParticle(int(x), int(y), 1));
                    //  playSound(diceSound);
                }
                if (invincible && (int)(punchTime)==20) {
                    //  entities.add(new slashParticle(int(x+120), int(y), 4));
                    //playSound(sliceSound);
                }
            }
        }
    }
    public void attack(){
        if (punchCooldown<=0 && !punching) {
            totalAttacks++;
            //  playSound(sliceSound);
            if (ducking && jumpCount<MAX_JUMP) {      // down dash attack
             //   entities.add(new slashParticle(int(p.x), int(p.y), 4));
                punchTime=30;
            } else if (ducking) {    // slide attack
              //  entities.add(new slashParticle(int(p.x), int(p.y), 2));
                punchTime=20;
            } else if ( jumpCount==0 ) {   // jump attack
              //  entities.add(new slashParticle(int(p.x), int(p.y), 3));
                punchTime=40;
            } else {
               /* if (jumpTime!=0 && jumpTime<=8) { // uppercut
                    background(255);
                    entities.add(new slashParticle(int(p.x), int(p.y), 6));
                    for (Obstacle o : obstacles) {
                        if (o.y+o.h > p.y-200 && p.y +p.h > o.y &&  o.x+o.w > p.x && o.x < p.x+p.w+200 ) {
                            o.impactForce=60;
                            o.hit();
                            o.death();
                        }
                    }
                } else {*/
                  //  entities.add(new slashParticle(int(p.x), int(p.y), 0)); // normal attack
            //    }
                punchTime=30;
            }
            punching=true;
        }
        //fill(255, 0, 0);  // hitbox
        // rect(x+w, y, punchRange, 75);


    }
  public   void checkIfObstacle(int top) {
        if (top<coord.y+size.y) {
          //if (punching && ducking && !onGround && jumpCount<MAX_JUMP) stomp(); // stomp attack
            Log.i("player","surface!!!");
            jumpCount=MAX_JUMP;
            onGround=true;
            jumpTime=0;
            coord.y=top-size.y;
            velocity.y=0;
            angle=0;
        }
    }
    public void collision() {
        if (invis==0) {
            reduceLife();
        }
       // if (!tutorial) invis=100;
      velocity.x*=-0.5f;
    }

    public void reduceLife() {
     //   if (tutorial) {
        //    tutorialRespawn();
     //   } else {
            lives--;
            //playSound(ughSound);
            GameView.screenAngle=-10;
        GameView.flashColor.setARGB(255,255,0,0);
        GameView.flashOpacity=200;
          //  UpdateGUILife(); // updateGUI
      //  }
    }

}
