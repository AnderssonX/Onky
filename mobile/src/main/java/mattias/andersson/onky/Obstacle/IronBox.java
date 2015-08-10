package mattias.andersson.onky.Obstacle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.R;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-23.
 */
public class IronBox extends  Obstacle {
   int defaultHealth,health=defaultHealth;
    Drawable image;
    Context context;
   // Bitmap bitmap = null;
   // Bitmap scaledBitmap;

    public IronBox(Context context, Point2D coord, Point2D Size) {
       super(coord, Size);

        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.metalbox1);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) size.x, (int) size.y, false);

        color.setARGB(255, 200, 200, 200);
        //  image=C:\Users\Alrik\AndroidStudioProjects\Onky\mobile\src\main\assets\woodenBox.png

    //   Drawable d = Drawable.createFromStream(getAssets().open("woodenBox.png"), null);

    //  Drawable d=

      //// d.setBounds((int)coord.x, (int)coord.y,(int) (coord.x+size.x),(int) (coord.y+size.y));

      // d.draw(c);
   }

    public void update(){
        super.update();
    }

    public void display(){
        //  c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
       // c.drawBitmap(bMap, (int) coord.x, (int) coord.y, (int) (coord.x + size.x), (int) (coord.y + size.y), new Paint(Color.WHITE));
      //  c.drawBitmap(bMap,(int)coord.x,(int)coord.y,color);
        GameThread.c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);
    }



}
