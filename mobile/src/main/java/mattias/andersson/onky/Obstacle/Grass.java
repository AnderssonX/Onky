package mattias.andersson.onky.Obstacle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.R;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-23.
 */
public class Grass extends Obstacle {
    int defaultHealth, health = defaultHealth;
    Context context;
    // Drawable image;
    //  Bitmap bMap = BitmapFactory.decodeFile("main/assets/woodenBox.png");
    // Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.woodenBox.png);
    Bitmap bitmap = null;
    int margin = 25;

    Bitmap scaledBitmap;
    DisplayMetrics dm = new DisplayMetrics();
    int screenWidth = dm.widthPixels;
    int screenHeight = dm.heightPixels;
    //  canvas.drawBitmap(bitmap, null, mRedPaddleRect, mPaint);

    public Grass(Context context, Point2D coord, Point2D Size) {
        super(coord, Size);
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.grasstile);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) size.x, (int) size.y, false);
        color.setARGB(255, 128, 181, 113);

        //image=C:\Users\Alrik\AndroidStudioProjects\Onky\mobile\src\main\assets\woodenBox.png
        //Drawable d = Drawable.createFromStream(getAssets().open("woodenBox.png"), null);
        //image= BitmapFactory.decodeResource(getResources(), R.drawable.woondenBox);
        //d.setBounds((int)coord.x, (int)coord.y,(int) (coord.x+size.x),(int) (coord.y+size.y));
        //d.draw(c);
    }

    public void update() {

    }

    public void display() {
        //   c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
        //c.drawBitmap(bitmap, (int) coord.x, (int) coord.y, (int) (coord.x + size.x), (int) (coord.y + size.y), new Paint(Color.WHITE));
        GameThread.c.drawRect(coord.x, coord.y + 100 - margin, coord.x + size.x, coord.y + size.y + 100, color);
        GameThread.c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);


    }


}
