package mattias.andersson.onky.powerup;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-28.
 */
public abstract class PowerUp {
    public Point2D coord;
    public Point2D size;
    public Point2D velocity = new Point2D();
    public Point2D offset = new Point2D();
    public boolean dead ,instant = true, toggle, homing, gravity;
    public float angle, time, spawnTime;
    public Paint color = new Paint(Color.RED);
    public int upgradeLevel;
    Bitmap bitmap = null;
    Bitmap scaledBitmap;
    public PowerUp() {

    }

    public PowerUp(Point2D coord, Point2D size) {
        this.coord = coord;
        this.size = size;
    }
    public void update() {
        angle+=4;
        offset.set((float)(Math.cos(Math.toRadians(angle))*12),(float)(Math.sin(Math.toRadians(angle))*12));
    }
    public void display() {
        //GameThread.c.drawCircle(coord.x+offset.x, coord.y+offset.y, size.x, color);
        GameThread.c.drawBitmap(scaledBitmap, (int) coord.x+offset.x, (int) coord.y+offset.y, color);
    }


}
