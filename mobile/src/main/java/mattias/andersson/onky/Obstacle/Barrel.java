package mattias.andersson.onky.Obstacle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.R;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-23.
 */
public class Barrel extends Obstacle {
    int defaultHealth, health = defaultHealth;
    Context context;
    float angle;
    // Drawable image;
    //  Bitmap bMap = BitmapFactory.decodeFile("main/assets/woodenBox.png");
    // Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.woodenBox.png);
   // Bitmap bitmap = null;
  //  Bitmap scaledBitmap;
    //  canvas.drawBitmap(bitmap, null, mRedPaddleRect, mPaint);

    public Barrel(Context context, Point2D coord, Point2D Size) {
        super(coord, Size);
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.barrel);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) size.x, (int) size.y, false);
        color.setARGB(255, 180, 140, 50);

        //image=C:\Users\Alrik\AndroidStudioProjects\Onky\mobile\src\main\assets\woodenBox.png
        //Drawable d = Drawable.createFromStream(getAssets().open("woodenBox.png"), null);
        //image= BitmapFactory.decodeResource(getResources(), R.drawable.woondenBox);
        //d.setBounds((int)coord.x, (int)coord.y,(int) (coord.x+size.x),(int) (coord.y+size.y));
        //d.draw(c);
    }

    public void update() {
        super.update();
      //  coord.add(velocity);
        angle+=velocity.x*2;
    }

    public void display() {
        //c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
        //c.drawBitmap(bitmap, (int) coord.x, (int) coord.y, (int) (coord.x + size.x), (int) (coord.y + size.y), new Paint(Color.WHITE));
        GameThread.c.save();
        GameThread.c.translate((float)(coord.x + size.x * 0.5),(float)( coord.y + size.y * 0.5));
        GameThread.c.rotate(angle);
        //GameThread.c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);
        GameThread.c.drawBitmap(scaledBitmap, (float) (-size.x * 0.5), (float) (-size.y * 0.5), color);
        GameThread.c.restore();


    }


}
