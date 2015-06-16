class Player {
  float x, y, w=50, h=100, vx=5, vy, ax, ay=0.8;
  final int MAX_JUMP=2;
  int cooldown, invis=100, health, maxHealth=100, jumpCount;
  boolean dead, onGround;
  color playerColor= color(255, 0, 0);
  Player() {
  }

  void update() {
    x+=vx;
    y+=vy;
    vx+=ax;
    vy+=ay;
    /// playerColor = color(255,0,0);
    if (0<invis) {
        recover();
    } else {
      playerColor = color(255);
    }
    println(invis);
    checkIfGround();
  }
  void display() {
    fill(playerColor);
    rect(x, y, w, h*0.5);
    fill(255);
    rect(x, y+h*0.5, w, h*0.5);

  }
  void collision() {
   invis=100;
   vx=0;
    playerColor= color(0); // change color on hit to black
  }
  void jump() {
    if (jumpCount>0) {
      jumpCount--;
      vy=-20;
    }
  }
  void accel() {
    vx++;
  }
  void deAccel() {
    vx--;
  }
  void duck() {
    vy=20;
  }
    void checkIfObstacle(int top) {
    if (top<y+h) { 
      jumpCount=MAX_JUMP;
      onGround=true;
      y=top-h;
     vy=0;
    }
  }
  void checkIfGround() {
    if (floorHeight<y+h) { 
      jumpCount=MAX_JUMP;
      onGround=true;
      y=floorHeight-h;
      //vy=0;
    }
  }
  void recover() {
    invis--;
  }
  
  void punch(){
    fill(255,0,0);
    rect(x,y,w,h);
  }
}

