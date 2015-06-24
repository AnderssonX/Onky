


class Paralax extends Entity {
  float angle, factor;
  PImage bg;
  Paralax() { // DUMMY
    super( 0, 0, 0, 0);
  }
  Paralax(int _x, int _y, int _w, int _h, float _factor) {
    super( _x, _y, _w, _h);
    paralaxLayers.add( this);
    w=_w;
    h=_h;
    factor=_factor;
  }

  void update() {
    x+=vx*speedFactor;
    y+=vy*speedFactor;
    if (x+w<width)x=0;
    //if(x+w<0)x=0;
  }

  void display() {
    noStroke();
    if(!p.invincible) fill(0, 0, 255);
    else  fill(100, 100, 255);
    rect(x, y, w, h);
  }
}



class ParalaxObject extends Paralax {

  int repeatDistance=1;
  
  ParalaxObject() { // DUMMY
    super();
  }
  ParalaxObject(int _x, int _y, int _w, int _h, float _factor) {
    super(_x, _y, _w, _h, _factor);
  }
  ParalaxObject(int _x, int _y, int _w, int _h, float _factor, int _repeatDistance) {
    super(_x, _y, _w, _h, _factor);
    repeatDistance=_repeatDistance;
  }
  void update() {
    x-=int(p.vx*factor*speedFactor);
    if (x+w<0)x=width*repeatDistance;
  }

  void display() {
    noStroke();
    fill(0, 100, 100);
    rect(x, y, w, h);
  }
}

