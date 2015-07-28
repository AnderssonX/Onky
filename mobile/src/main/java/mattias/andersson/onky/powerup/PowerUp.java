package mattias.andersson.onky.powerup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import mattias.andersson.onky.Obstacle.Point2D;

/**
 * Created by Alrik on 2015-07-28.
 */
public abstract class PowerUp {
    public Point2D coord;
    public Point2D size;
    public Point2D velocity = new Point2D();
    public Point2D offset = new Point2D();
    public boolean instant = true, toggle, homing, gravity;
    public float angle, time, spawnTime;
    public Paint color = new Paint(Color.RED);
    public int upgradeLevel;

    public PowerUp() {

    }
    public PowerUp(Context context,Point2D coord) {

    }
    public void update() {

    }
    public void display(Canvas c) {
        c.drawCircle(coord.x, coord.y, size.x, color);
        //  c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);
    }


}
