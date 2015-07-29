package mattias.andersson.onky.Particle;

import android.graphics.Color;
import android.graphics.Paint;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.Obstacle.Point2D;

/**
 * Created by Alrik on 2015-07-29.
 */
public class TriangleParticle extends Particle {
    float  angle, vAngle;
    Paint color=new Paint(Color.RED);
    public TriangleParticle(){


    }
    public TriangleParticle(Point2D coord,Point2D size, Paint _particleColor) {
        super(coord, size);
        color=_particleColor;

        vAngle=r.nextInt(12) -6;
    }
    public TriangleParticle(Point2D coord, Point2D _velocity,Point2D size,Paint _particleColor) {

        this( coord, size,  _particleColor);
        color.setColor(Color.rgb(255, 0, 0));
        velocity=_velocity;
    }
    public void update() {
        //super.update();
        angle+=vAngle;
        coord.add(velocity);
        size.multi(.9f);
        if (size.x<2)death();
    }

    public void display() {
        /*pushMatrix();
        translate(coord.x + size.x * 0.5, coord.y + size.x * 0.5);
        rotate(radians(angle));
        strokeWeight(int(size.x*0.3));
        stroke(particleColor);
        fill(255);
        beginShape();
        vertex(0, -size.x);
        vertex(size.x*0.5, 0 );
        vertex(-size.x * 0.5, 0);
        endShape(CLOSE);
        popMatrix();*/
    //    float vertexes[] = {coord.x+0, coord.y-size.x,(float)(coord.x+size.x*0.5), coord.y+0,(float)(coord.x-size.x * 0.5), coord.y+0};

        GameThread.c.translate(coord.x, coord.y);
        GameThread.c.rotate(angle);
       // float vertexes[] = {(float)(coord.x-size.x * 0.5), coord.y+0,coord.x+0, coord.y-size.x,coord.x+0, coord.y-size.x,(float)(coord.x+size.x*0.5), coord.y+0,coord.y-size.x,(float)(coord.x+size.x*0.5), coord.y+0,(float)(coord.x-size.x * 0.5), coord.y+0};
        float vertexes[] = {0, -size.x,(float)(size.x*0.5), 0,(float)(size.x*0.5), 0,(float)(-size.x * 0.5), 0,(float)(-size.x * 0.5), 0,0, -size.x};
        color.setStrokeWidth((float) (size.x * 0.3));
        GameThread.c.drawLines(vertexes, color);

        GameThread.c.rotate(-angle);
        GameThread.c.translate(-coord.x, -coord.y);
    //    GameThread.c.restore();
    }
}
