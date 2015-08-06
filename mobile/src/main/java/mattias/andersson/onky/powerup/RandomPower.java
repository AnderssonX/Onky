package mattias.andersson.onky.powerup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.R;

/**
 * Created by Alrik on 2015-07-28.
 */

public class RandomPower extends PowerUp {

    int defaultHealth, health = defaultHealth;
    Context context;
    // Drawable image;
    //  Bitmap bMap = BitmapFactory.decodeFile("main/assets/woodenBox.png");
    // Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.woodenBox.png);

    //  canvas.drawBitmap(bitmap, null, mRedPaddleRect, mPaint);
    public RandomPower(Context context, Point2D coord, Point2D Size) {
        super(coord, Size);
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.randomicon);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) size.x, (int) size.y, false);
        color.setARGB(255, 180, 140, 50);
    }


    public void update() {
        super.update();
    }

    public void display(){
        // c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
        super.display();
        //GameThread.c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);
    }


}

