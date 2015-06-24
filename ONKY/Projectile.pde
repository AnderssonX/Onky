abstract class Projectile extends Entity {

  Projectile(int _x, int  _y, float _vx, float _vy) {
    super( _x, _y, _vx, _vy);
    projectiles.add(this);
    vx=_vx;
    vy=_vy;
  }



  void collision() {
  /*  for (Obstacle o : obstacles) {
      if (o.x+o.w > x && o.x < x + w + vx && o.y+o.h > y&&  o.y < y + h) {
        o.damage(1);
        o.hit();
        death();
      }
    }*/
  }




}
class LaserProjectile extends Projectile {

  LaserProjectile(int _x, int  _y, float _vx, float _vy) {
    super( _x, _y, _vx, _vy);
    projectiles.add(this);
    w=50;
    fill(255, 0, 0);
    ellipse(x+w*0.5, y, 100, 50);
    fill(255);
    ellipse(x+w*0.5, y, 75, 30);
  playSound(laserSound);
  }

  void display() {
    super.display();

    stroke(255, 0, 0);
    strokeWeight(10);
    line(x, y, x+w, y);

    stroke(255);
    strokeWeight(5);
    line(x, y, x+w, y);
  }

  void collision() {
    for (Obstacle o : obstacles) {
      if (!dead && o.x+o.w > x+vx && o.x < x + w + vx && o.y+o.h > y+vy &&  o.y < y + h+vy) {
        o.damage(1);
        death();
      }
    }
  }

  void update() {
    super.update();
    x+=vx;
    y+=vy;
    collision();
    if (x>p.x+width/scaleFactor) dead=true;
  }

  void death() {
    super.death();
    entities.add(new LineParticle(int(x+w*0.5), int(y+h), 30, 0));
    fill(255, 0, 0);
    ellipse(x+w*0.5, y, 100, 100);
    fill(255);
    ellipse(x+w*0.5, y, 75, 75);
  }
}

