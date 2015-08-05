package mattias.andersson.onky.Particle;

import android.graphics.Color;
import android.graphics.Paint;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-29.
 */
public class TriangleParticle extends Particle {
    float angle, vAngle;
    float vertexes[] = new float[12];
    Paint color = new Paint(Color.RED);

    public TriangleParticle() {


    }

    public TriangleParticle(Point2D coord, Point2D size, Paint _particleColor) {
        super(coord, size);
        color = _particleColor;

        vAngle = r.nextInt(12) - 6;
    }

    public TriangleParticle(Point2D coord, Point2D _velocity, Point2D size, Paint _particleColor) {

        this(coord, size, _particleColor);
        color.setColor(Color.rgb(255, 0, 0));
        velocity = _velocity;
    }

    public void update() {
        //super.update();
        angle += vAngle;
        coord.add(velocity);
        size.multi(.9f);
        if (size.x < 2) death();
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
        GameThread.c.save();

        GameThread.c.translate(coord.x, coord.y);
        GameThread.c.rotate(angle);
        // float vertexes[] = {(float)(coord.x-size.x * 0.5), coord.y+0,coord.x+0, coord.y-size.x,coord.x+0, coord.y-size.x,(float)(coord.x+size.x*0.5), coord.y+0,coord.y-size.x,(float)(coord.x+size.x*0.5), coord.y+0,(float)(coord.x-size.x * 0.5), coord.y+0};
        //float vertexes[] = {0, -size.x,   (float)(size.x*0.5), 0,   (float)(size.x*0.5), 0,    (float)(-size.x * 0.5), 0,   (float)(-size.x * 0.5), 0,    0, -size.x};
        vertexes[0] = 0;
        vertexes[1] = -size.x;
        vertexes[2] =(float) (size.x * 0.5);
        vertexes[3] = 0;

        vertexes[4] =(float) (size.x * 0.5);
        vertexes[5] = 0;
        vertexes[6] = (float) (-size.x * 0.5);
        vertexes[7] = 0;

        vertexes[8] = (float) (-size.x * 0.5);
        vertexes[9] = 0;
        vertexes[10] = 0;
        vertexes[11] = -size.x;



       color.setStrokeWidth((float) (size.x * 0.3));
        GameThread.c.drawLines(vertexes, color);
        GameThread.c.restore();

       // GameThread.c.rotate(-angle);
      //  GameThread.c.translate(-coord.x, -coord.y);
        //    GameThread.c.restore();
    }
}
