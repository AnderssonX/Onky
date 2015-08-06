package mattias.andersson.onky.Obstacle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.R;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-23.
 */
public class Water extends Obstacle {
    int defaultHealth, health = defaultHealth;
    Context context;
    int frameAmount= 4;
    float count;
    Bitmap[] animBitmap= new Bitmap[frameAmount];

    // Drawable image;
    //  Bitmap bMap = BitmapFactory.decodeFile("main/assets/woodenBox.png");
    // Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.woodenBox.png);
   // Bitmap bitmap = null;
    //nBitmap scaledBitmap;
    //  canvas.drawBitmap(bitmap, null, mRedPaddleRect, mPaint);

    public Water(Context context, Point2D coord, Point2D Size) {
        super(coord, Size);
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.watertile);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, 203, 50, false);
        color.setARGB(255,81, 104, 151);
        animBitmap= cutSprite(scaledBitmap,50,frameAmount,203,50);
        for(int i=0; i<frameAmount;i++){
            animBitmap[i]= Bitmap.createScaledBitmap(  animBitmap [i],  (int)size.x, (int)size.y, false);
        }
        //image=C:\Users\Alrik\AndroidStudioProjects\Onky\mobile\src\main\assets\woodenBox.png
        //Drawable d = Drawable.createFromStream(getAssets().open("woodenBox.png"), null);
        //image= BitmapFactory.decodeResource(getResources(), R.drawable.woondenBox);
        //d.setBounds((int)coord.x, (int)coord.y,(int) (coord.x+size.x),(int) (coord.y+size.y));
        //d.draw(c);
    }

    public void update() {
        count++;
    }

    public void display() {
        //c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
        //c.drawBitmap(bitmap, (int) coord.x, (int) coord.y, (int) (coord.x + size.x), (int) (coord.y + size.y), new Paint(Color.WHITE));
        GameThread.c.drawRect(coord.x, coord.y + 100, coord.x + size.x, coord.y + size.y + 100, color);
       // GameThread.c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);

        if (count%60<10)GameThread.c.drawBitmap(animBitmap[0], (int) coord.x, (int) coord.y, color);
        else if (count%60<20)GameThread.c.drawBitmap(animBitmap[1], (int) coord.x, (int) coord.y, color);
        else if (count%60<30)GameThread.c.drawBitmap(animBitmap[2], (int) coord.x, (int) coord.y, color);
        else if (count%60<40)GameThread.c.drawBitmap(animBitmap[3], (int) coord.x, (int) coord.y, color);
        else if (count%60<50)GameThread.c.drawBitmap(animBitmap[2], (int) coord.x, (int) coord.y, color);
        else GameThread.c.drawBitmap(animBitmap[1], (int) coord.x, (int) coord.y, color);
    }


}
