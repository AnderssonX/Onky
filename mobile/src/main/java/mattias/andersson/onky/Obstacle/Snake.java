package mattias.andersson.onky.Obstacle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.R;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-23.
 */
public class Snake extends Obstacle {
    int defaultHealth, health = defaultHealth;
    Context context;
    int frameAmount= 3;
    float count;
    Bitmap[] animBitmap= new Bitmap[frameAmount];

    public Snake(Context context, Point2D coord, Point2D Size) {
        super(coord, Size);
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake);

        //scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) size.x, (int) size.y, false); // default size
        scaledBitmap = Bitmap.createScaledBitmap(bitmap,  248,  35, false); // editor size

        color.setARGB(255, 180, 140, 50);
        Log.i("Imagesize", (int) size.x+" : "+(int) size.y);
        Log.i("bitmapeSize", bitmap.getWidth() + " : " + bitmap.getHeight());
        Log.i("scaledBitmapSize", scaledBitmap.getWidth()+" : "+scaledBitmap.getHeight());
       // animBitmap= cutSprite(bitmap,frameAmount,bitmap.getWidth(),bitmap.getHeight());
        animBitmap= cutSprite(scaledBitmap,82,frameAmount,248,35);
       for(int i=0; i<frameAmount;i++){
            animBitmap[i]= Bitmap.createScaledBitmap(  animBitmap [i],  (int)size.x, (int)size.y, false);
        }

    }

    public void update() {
        super.update();
        count++;
       // coord.add(velocity);
    }

    public void display() {
        //  c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
        //c.drawBitmap(bitmap, (int) coord.x, (int) coord.y, (int) (coord.x + size.x), (int) (coord.y + size.y), new Paint(Color.WHITE));
        //GameThread.c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);
        if (count%30<10) GameThread.c.drawBitmap(animBitmap[0], (int) coord.x, (int) coord.y, color);
        else if (count%30<20)  GameThread.c.drawBitmap(animBitmap[1], (int) coord.x, (int) coord.y, color);
        else   GameThread.c.drawBitmap(animBitmap[2], (int) coord.x, (int) coord.y, color);
    }


}
