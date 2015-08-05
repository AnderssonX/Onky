package mattias.andersson.onky.Particle;

import android.graphics.Color;
import android.graphics.Paint;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-29.
 */
public class PulseParticle extends Particle {
    Paint color = new Paint(Color.RED);
    public PulseParticle() {


    }

    public PulseParticle(Point2D coord, Point2D size, Paint _particleColor) {
        super(coord, size);
        color = _particleColor;
        color.setColor(Color.rgb(255, 0, 0));
        color.setStrokeWidth(10);
        color.setStyle(Paint.Style.STROKE);
        color.setColor(Color.rgb(255, 0, 0));
    }

    public PulseParticle(Point2D coord, Point2D _velocity, Point2D size, Paint _particleColor) {

        this(coord, size, _particleColor);

        velocity = _velocity;
    }

    public void update() {
        //super.update();
        coord.add(velocity);
        size.multi(1.1f);
        if (size.x > 300) death();
    }

    public void display() {
    //   color.setStrokeWidth((float) (size.x * 0.3));

        GameThread.c.drawCircle(coord.x, coord.y, size.x, color);

    }
}
