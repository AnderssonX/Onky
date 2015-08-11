package mattias.andersson.onky.Particle;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.GameView;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-29.
 */
public class SpinParticle extends Particle {
    Paint color = new Paint(Color.RED);
    float tSize, angle;
    boolean onOnky;

    public SpinParticle() {


    }

    public SpinParticle(Point2D coord, Point2D size, Paint _particleColor) {
        super(coord, size);
        color = _particleColor;
        color.setColor(Color.rgb(255, 255, 255));
        color.setStyle(Paint.Style.STROKE);
        opacity=255;
    }

    public SpinParticle(Point2D coord, Point2D _velocity, Point2D size, Paint _particleColor) {
        this(coord, size, _particleColor);
        velocity = _velocity;
    }

    public void update() {
        //super.update();
        color.setStrokeWidth( 30);
        color.setAlpha(opacity);
        // size.add(15);
        angle += 16;
        if (onOnky) {
            coord = GameView.players.get(0).coord;
        } else coord.add(velocity);

        if (opacity < 8) death();
        else opacity -= 8;


//        if (size.x > tSize) death();
    }

    public void display() {
        //   color.setStrokeWidth((float) (size.x * 0.3));
        RectF oval=new RectF(coord.x, coord.y, coord.x+size.x, coord.y+size.y);
        //GameThread.c.drawArc(coord.x, coord.y, size.x, size.y, angle, 180, true, color);
        GameThread.c.drawArc(oval,angle,180,false,color);
      // GameThread.c.drawCircle(coord.x, coord.y, size.x, color);

    }
}
