abstract class Projectile extends Entity {
  int time=80;
  Projectile(int _x, int  _y, float _vx, float _vy) {
    super( _x, _y, _vx, _vy);
    projectiles.add(this);

    vx=_vx*speedFactor;
    vy=_vy*speedFactor;
  }

  void collision() {
  }
}

class LaserProjectile extends Projectile {

  LaserProjectile(int _x, int  _y, float _vx, float _vy) {
    super( _x, _y, _vx, _vy);
    //projectiles.add(this);
    w=25;
    strokeWeight(10);
    stroke(255, 0, 0);
    fill(255);
    ellipse(x+w, y, 75, 30);
    playSound(laserSound);
  }

  void display() {
    super.display();

    stroke(255, 0, 0);
    strokeWeight(10);
    line(x, y, x-vx, y-vy);

    stroke(255);
    strokeWeight(5);
    line(x, y, x-vx, y-vy);
  }

  void collision() {
    if (!dead) {
      for (Obstacle o : obstacles) {
        if ( o.x+o.w > x+vx && o.x < x  + vx && o.y+o.h > y+vy &&  o.y < y + h+vy) {
          o.damage(1);
          death();
        }
      }
      if ( floorHeight < y+vy ) {
        death();
      }
    }
  }

  void update() {
    x+=vx;
    y+=vy;
    collision();
    //if ( x>p.x+width/scaleFactor) dead=true;  //off screen
    if (time<=0) dead=true;  // timelimit
    else time--;
  }

  void death() {
    super.death();
    entities.add(new LineParticle(int(x+w*0.5), int(y+h), 30, 0));
    strokeWeight(10);
    stroke(255, 0, 0);
    fill(255);
    ellipse(int(x),int(y), 75, 75);
  }
}

