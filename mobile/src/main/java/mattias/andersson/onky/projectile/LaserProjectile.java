package mattias.andersson.onky.projectile;

import android.graphics.Paint;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.GameView;
import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.Particle.TriangleParticle;

/**
 * Created by Alrik on 2015-07-29.
 */

public class LaserProjectile extends Projectile {
    Paint coreColor = new Paint(255);

    public LaserProjectile(Point2D coord, Point2D vel) {
        super(coord, vel);
        // projectiles.add(this);
        size.x = 25;
        color.setARGB(255, 255, 0, 0);
        coreColor.setARGB(255, 255, 255, 255);
        // strokeWeight(10);
        // stroke(projectileColor);
        // fill(255);
        // ellipse(x+w, y, 75, 40);
        // entities.add(new SparkParticle(int(x+w), int(random(h)+y), 10, projectileColor));
        // playSound(laserSound);
    }

    public void display() {

        color.setStrokeWidth(12);
        GameThread.c.drawLine(coord.x, coord.y, (float) (coord.x - velocity.x * 3), (float) (coord.y - velocity.y * 3), color);

        coreColor.setStrokeWidth(5);
        GameThread.c.drawLine(coord.x, coord.y, (float) (coord.x - velocity.x * 2), (float) (coord.y - velocity.y * 2), coreColor);

    }

    void collision() {

        // if (!dead) {
        for (int i = GameView.obstacles.size() - 1; i >= 0; i--) {
            if (!GameView.obstacles.get(i).dead && GameView.obstacles.get(i).coord.x + GameView.obstacles.get(i).size.x > coord.x + velocity.x && GameView.obstacles.get(i).coord.x < coord.x + velocity.x && GameView.obstacles.get(i).coord.y + GameView.obstacles.get(i).size.y > coord.y + velocity.y && GameView.obstacles.get(i).coord.y < coord.y + size.y + velocity.y) {
                GameView.obstacles.get(i).damage(1);
                death();
            }
        }

        //  }
    }

    public void update() {
        // if(!dead) {
        coord.add(velocity);
        collision();
        //if ( x>p.x+width/scaleFactor) dead=true;  //off screen
        if (time <= 0) dead = true;  // timelimit
        else time--;
        //  }
    }

    public void death() {
        super.death();
        dead = true;
        // entities.add(new LineParticle(int(x+w*0.5), int(y+h), 30, 0));
        //for (int i=0; i<2; i++)
        GameView.particles.add(new TriangleParticle(coord, new Point2D((float) (r.nextFloat() * (velocity.x * 0.4) - (velocity.x * 0.20)), (float) (r.nextFloat() * (velocity.y * 0.4 + 4) - (velocity.y * 0.20 + 2))), new Point2D(10, 10), color));
        // strokeWeight(10);
        // stroke(projectileColor);
        // fill(255);
        // ellipse(int(x), int(y), 75, 75);
    }
}

