/*
 *
 *  ONKY the game
 *  av Alrik He
 *
 */
import ddf.minim.*;
//import javax.media.opengl.*;
//import processing.opengl.*;




int renderObject;
Minim minim;
AudioPlayer BGM;
AudioPlayer boxDestroySound, boxKnockSound;
AudioPlayer ironBoxDestroySound, ironBoxKnockSound, shatterSound;
AudioPlayer rubberSound;

AudioPlayer jumpSound, sliceSound, diceSound, ughSound, collectSound, laserSound;

PImage Block, laserIcon, superIcon, tokenIcon, lifeIcon, Bush, Tree, Grass, Leaf, BlockSad, Mountain;
int defaultSpeedLevel=12, speedLevel=defaultSpeedLevel; // default speed level
int score, tokens, objectsDestroyed;
ArrayList<Entity> entities = new ArrayList<Entity>(); // all objects
ArrayList<Paralax> paralaxLayers = new ArrayList<Paralax>();
ArrayList<Paralax> ForegroundParalaxLayers = new ArrayList<Paralax>();
ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
ArrayList<Debris> debris = new ArrayList<Debris>();
ArrayList<Projectile> projectiles= new ArrayList<Projectile>();
ArrayList<Particle> particles = new ArrayList<Particle>();
ArrayList<Powerup> powerups = new ArrayList<Powerup>();
Paralax paralax= new Paralax();
ParalaxObject paralaxObject=new ParalaxObject();
Player p = new Player();

boolean debug, mute;
int floorHeight=700, spawnHeight=250, playerOffsetX=100, playerOffsetY=200;
float screenAngle, scaleFactor=0.5, targetScaleFactor=scaleFactor, speedFactor=1, targetSpeedFactor=speedFactor, skakeFactor, shakeX, shakeY, shakeDecay=0.8;
final int MAX_SHAKE=200, MAX_SPEED=20;

void setup() {
  noSmooth();
  noClip();
  //size(720, 1080); // vertical
  size( 1080, 720,OPENGL); // horisontal
 // hint();
 hint(DISABLE_TEXTURE_MIPMAPS);
((PGraphicsOpenGL)g).textureSampling(2);
  
  p.SpriteSheetRunning = loadImage("onky_running3.png");
  p.FrontFlip = loadImage("frontFlip.png");
  p.Life = loadImage("extraLife.png");
  p.Jump = loadImage("jump.png");
  p.DownDash = loadImage("downDash.png");
  p.Slide = loadImage("slide.png");
  laserIcon = loadImage("laserpower.png");
  tokenIcon = loadImage("token.png");
  superIcon = loadImage("speedpower.png");
  lifeIcon = loadImage("oneup.png");

  Mountain= loadImage("backgroundfull.png");
  Grass= loadImage("grasstile.png");
  Bush = loadImage("bush.png");
  Tree =loadImage("treetile.png");
  Leaf  =loadImage("leaf.png");
  Block = loadImage("blockMad.png");
  BlockSad = loadImage("blockSad.png");
  // we pass this to Minim so that it can load files from the data directory
  minim = new Minim(this);
  // loadFile will look in all the same places as loadImage does.
  // this means you can find files that are in the data folder and the 
  // sketch folder. you can also pass an absolute path, or a URL.
  BGM = minim.loadFile("KillerBlood-The Black(Paroto).mp3");
  boxDestroySound = minim.loadFile("boxSmash.wav");
  boxKnockSound = minim.loadFile("boxKnock.wav");
  ironBoxDestroySound = minim.loadFile("ironBoxSmash.wav");
  ironBoxKnockSound = minim.loadFile("ironBoxKnock.wav");
  shatterSound = minim.loadFile("shatter.wav");
  jumpSound = minim.loadFile("jump.wav");
  sliceSound = minim.loadFile("slice.wav");
  diceSound= minim.loadFile("dice.wav");
  ughSound= minim.loadFile("ugh.wav");
  rubberSound= minim.loadFile("rubberBounce.mp3");
  collectSound= minim.loadFile("grab.wav");
  laserSound= minim.loadFile("laser2.wav");
  laserSound.setGain(-20);
  BGM.setGain(-10);
  BGM.play();
  BGM.loop();

  loadParalax();
  loadObstacle();

  //entities.add(new invisPowerup(1000, 600, 2000));
  entities.add(new LaserPowerup(2200, 600, 600));
  // entities.add(new SlowPowerup(2200, 400, 1000));
  // entities.add(new RandomPowerup(2000, 400, 500)); 
  //entities.add(new RandomPowerup(2000, 600, 500)); 
  entities.add(new RandomPowerup(2000, 200, 500));
}

void draw() {

  //if (!p.invincible) background(80);
 // else background(255, 150, 0);
  shake();
  smoothScale();
  smoothSlow();
  smoothAngle();
  if (!debug)adjustZoomLevel();
  //-----------------------------         Paralax   / Entity            -----------------------------------------------------------
  for (Paralax plx : paralaxLayers) {
    plx.update();
    if ( plx.x < width) plx.display(); // onscreen
  }
  if (debug) displaySign();
  pushMatrix();
  scale(scaleFactor);
  rotate(radians(screenAngle));
  translate(-p.x+playerOffsetX+shakeX, (-p.y+(height*0.5)/scaleFactor)*0.3+ shakeY);
  renderObject=0;

  displayFloor();
  p.update();
  p.display();
  //-----------------------------         Obstacle   / Entity         -----------------------------------------------------------
  for (Obstacle o : obstacles) {
    //if (o.x+shakeX*2<(p.x+width/(scaleFactor)) && (o.x+o.w-shakeX*2)/(scaleFactor)>(p.x -playerOffsetX)) {// old renderBound
    if (o.x+o.w+shakeX>p.x-p.vx-playerOffsetX-shakeX  && o.x-shakeX<p.x-p.vx-playerOffsetX-shakeX+(width)/scaleFactor) { // onscreen
      renderObject++;
      o.update();
      // o.gravity();
      o.display();
    }
  }
  for (int i=obstacles.size () -1; i>=0; i--) {
    if (obstacles.get(i).dead)obstacles.remove(obstacles.get(i));
  }
  if (debug) {
    fill(0, 255, 0, 100);
    rect(p.x-p.vx-playerOffsetX-shakeX+50/scaleFactor, (p.y-(height*0.3)/scaleFactor)*0.3-shakeY, (width-100+shakeX)/scaleFactor, height/scaleFactor-100+shakeY);
  }
  //-----------------------------         Powerup   / Entity         -----------------------------------------------------------

  for (Powerup pow : powerups) {
    if (pow.x+pow.w+shakeX>p.x-p.vx-playerOffsetX-shakeX  && pow.x-shakeX<p.x-p.vx-playerOffsetX-shakeX+(width)/scaleFactor) { // onscreen
      renderObject++;
      pow.update();
      pow.display();
    }
  }
  for (int i=powerups.size () -1; i>=0; i--) {
    if (powerups.get(i).dead)powerups.remove(powerups.get(i));
  }

  //-----------------------------         Debris    / Entity       -----------------------------------------------------------

  for (Debris d : debris) {
    d.update();
    d.display();
  }
  for (int i=debris.size () -1; i>=0; i--) {
    if (debris.get(i).dead)debris.remove(debris.get(i));
  }

  //-----------------------------         Particle     / Entity       -----------------------------------------------------------

  for (Particle par : particles) {
    par.update();
    par.display();
  }
  for (int i=particles.size () -1; i>=0; i--) {
    if (particles.get(i).dead)particles.remove(particles.get(i));
  }
  //-----------------------------         Projectile     / Entity       -----------------------------------------------------------

  for (Projectile pro : projectiles) {
    pro.update();
    pro.display();
  }
  for (int i=projectiles.size () -1; i>=0; i--) {
    if (projectiles.get(i).dead)projectiles.remove(projectiles.get(i));
  }

  //-----------------------------         Entities           -----------------------------------------------------------


  /* for (Entity e : entities) {
  /* if(!(e instanceof Paralax)){
   }*/

  /*if (e.getClass() == Paralax.class) {
   }*/

  /* if( !paralaxLayers.contains( e)){   // works
   }*/



  /*  if (! e.getClass().isInstance(paralax) && ! e.getClass().isInstance(paralaxObject)) {   // works
   e.display();
   e.update();
   }
   }*/
  /* for (int i=entities.size () -1; i>=0; i--) {
   if (entities.get(i).dead)entities.remove(entities.get(i));
   }*/

  //----------------------------¨-------------------------------------------------------------------------------------

  popMatrix();

  //-----------------------------         Paralax     / Entity       -----------------------------------------------------------

  for (Paralax plx : ForegroundParalaxLayers) {
    plx.update();
    if (plx.x<width)plx.display(); // onscreen
  }
  for ( Powerup pow : p.usedPowerup) { 
     pow.displayIcon();
  }

  displayLife();
  calcDispScore();
  if (debug)debugScreen();
  if (p.lives<0)gameReset();
}

void shake() {
  if (MAX_SHAKE<skakeFactor) skakeFactor=MAX_SHAKE;
  skakeFactor*=shakeDecay;
  shakeX=random(skakeFactor)-skakeFactor*0.5;
  shakeY=random(skakeFactor)-skakeFactor*0.5;
}
void smoothScale() {
  float scaleDiff=targetScaleFactor-scaleFactor;
  scaleFactor+=scaleDiff*0.1;
  // targetScaleFactor=1;
  // scaleFactor=1;
}
void smoothSlow() {
  float speedDiff=targetSpeedFactor-speedFactor;
  speedFactor+=speedDiff*0.05;
}
void smoothAngle() {
  if (screenAngle!=0) {
    if (screenAngle>-1 && screenAngle<1)screenAngle=0 ;
    else screenAngle*=0.9;
  }
}
void adjustZoomLevel() {
  targetScaleFactor= map(p.vx, 0, 50, 1, 0.2);
}
void displayFloor() {
  int offset = -200;
  if (p.invincible)fill(255, 50, 0);
  else fill(0);
  // image(Grass ,p.x-playerOffsetX-MAX_SHAKE, floorHeight+offset, width+playerOffsetX+MAX_SHAKE*2, 1000*scaleFactor);
  rect(p.x-playerOffsetX-MAX_SHAKE, floorHeight, width/(scaleFactor)+playerOffsetX+MAX_SHAKE*2, 1000);
}

void displaySign() {
  stroke(255);
  strokeWeight(1);
  int interval=100;
  for (int i=0; i< width/scaleFactor; i+=interval)
    line((i+playerOffsetX-p.x%interval)*(scaleFactor), 0, (i+playerOffsetX-p.x%interval)*(scaleFactor), height);
}

void gameReset() {
  particles.clear();
  entities.clear();
  obstacles.clear();
  powerups.clear();
  debris.clear();
  background(0);
  BGM.rewind();
 difficultyRange=10;
 minDifficulty=0;
 maxDifficulty=difficultyRange;

  loadObstacle();
  p.reset();
  p.y=floorHeight;
  p.invis=0;
  speedFactor=1;
  targetSpeedFactor=1;
  score=0;
  tokens=0;
  objectsDestroyed=0;
}

void calcDispScore() {
  if (MAX_SPEED>speedLevel)speedLevel=int(score*0.00005+defaultSpeedLevel);
  score=int(p.x);
  fill(0);
  textSize(30);
  //+" + "+ int(p.vx-speedLevel);
  text( String.format( "%.1f", speedFactor)+"X"+" velocity:"+(speedLevel-defaultSpeedLevel) +"  m: "+int(score*0.01)+"  killed: "+objectsDestroyed +"  tokens: "+tokens, width-850, 50);
 // textSize(18);
}
void debugScreen() {
  fill(100, 255, 0);
  textSize(20);
  text("renderO "+renderObject+" Entities: "+ entities.size()+" particles: "+particles.size()+" obstacles: "+obstacles.size() +" debris:"+debris.size()+" powerups:"+powerups.size(), 50, height-50);
}
void playSound(AudioPlayer sound) {
  if (!mute) {
    sound.rewind();
    sound.play();
  }
}
void displayLife() {
  for (int i=0; i<p.lives; i++)
    image(p.Life, 0+50+i*50, 0+60, 40, 40);
}
void loadParalax() {

  entities.add(new Paralax(0, -int(height*1.5)-300, width*3, int( height*3), 0.01, Mountain)); // bakgrund
  entities.add(new ParalaxObject(0, 400, 50, 50, 0.3)); 
  entities.add(new ParalaxObject(255, 400, 50, 50, 0.3)); 
  entities.add(new ParalaxObject(0, 420, 100, 100, 0.5)); 
  entities.add(new ParalaxObject(300, 420, 100, 100, 0.5)); 
  entities.add(new ParalaxObject(0, 290, 250, 250, 0.7)); 
  entities.add(new ParalaxObject(0, 150, 500, 500, 0.9));

  ForegroundParalaxLayers.add(new ParalaxObject(300, 250-400, 800, 800, 1.2, 18, 150)); 
  ForegroundParalaxLayers.add(new ParalaxObject(500, 50-1200, 2000, 2000, 1.4, 20, 150));
}

