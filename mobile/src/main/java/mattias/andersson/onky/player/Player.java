package mattias.andersson.onky.player;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.GameView;
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
    public Point2D coord= new Point2D(), velocity= new Point2D(), acceleration= new Point2D(), size= new Point2D();
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
    }

    public void display() {
        GameThread.c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);

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
            //   if (jumpCount<MAX_JUMP) entities.add( new SpinParticle( true));
            jumpCount--;
            velocity.y=-jumpHeight;
        }
    }
    public void checkDuck(){



    }
    public void duck(){



    }
    public void checkAttack(){



    }
    public void attack(){



    }

    void checkIfObstacle(int top) {
        if (top<coord.y+size.y) {
          //  if (punching && ducking && !onGround && jumpCount<MAX_JUMP) stomp(); // stomp attack
            jumpCount=MAX_JUMP;
            onGround=true;
            jumpTime=0;
            coord.y=top-size.y;
            velocity.y=0;
            angle=0;
        }
    }
    void collision() {
        if (invis==0) {
            reduceLife();
        }
       // if (!tutorial) invis=100;
      // vx*= -0.5;
    }
    void reduceLife() {
     //   if (tutorial) {
        //    tutorialRespawn();
     //   } else {
            lives--;
            //playSound(ughSound);
          //  GameView.screenAngle=-10;
         //   background(255, 0, 0);
          //  UpdateGUILife(); // updateGUI
      //  }
    }

}
