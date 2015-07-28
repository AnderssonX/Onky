package mattias.andersson.onky.powerup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import mattias.andersson.onky.Obstacle.Point2D;
import mattias.andersson.onky.R;

/**
 * Created by Alrik on 2015-07-28.
 */

public class Laser extends PowerUp {

    int defaultHealth, health = defaultHealth;
    Context context;
    // Drawable image;
    //  Bitmap bMap = BitmapFactory.decodeFile("main/assets/woodenBox.png");
    // Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.woodenBox.png);
    Bitmap bitmap=null;
    Bitmap scaledBitmap;
    //  canvas.drawBitmap(bitmap, null, mRedPaddleRect, mPaint);
    public Laser(Context context, Point2D coord, Point2D Size) {
        super();
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.woodenbox);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) size.x, (int) size.y,false);
        color.setARGB(255, 180, 140, 50);
    }

    public void update() {
    }

    public void display(Canvas c) {
        c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
        c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);
    }









}

