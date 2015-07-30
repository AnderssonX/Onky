package mattias.andersson.onky.Obstacle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.R;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-23.
 */
public class Sign extends  Obstacle {
   int defaultHealth,health=defaultHealth;
    Drawable image;
    Context context;
    Bitmap bitmap = null;
    Bitmap scaledBitmap;
    Paint textColor=new Paint(Color.BLACK);
  //  Bitmap bMap = BitmapFactory.decodeFile("main/assets/woodenBox.png");
   // Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.woodenBox.png);
  public Sign(Context context, Point2D coord, Point2D Size) {
       super(coord, Size);

      this.context = context;
      bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sign);
      scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) size.x, (int) size.y, false);

        color.setARGB(255, 180, 140, 50);
        textColor.setColor(Color.BLACK);
        textColor.setColor(Color.WHITE);
        textColor.setStyle(Paint.Style.FILL);
        //  image=C:\Users\Alrik\AndroidStudioProjects\Onky\mobile\src\main\assets\woodenBox.png
        //  Drawable d = Drawable.createFromStream(getAssets().open("woodenBox.png"), null);
        //  Drawable d=//// d.setBounds((int)coord.x, (int)coord.y,(int) (coord.x+size.x),(int) (coord.y+size.y));
        //  d.draw(c);
    }

    public void update() {
    }

    public void display(){
        //c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
        GameThread.c.drawBitmap(scaledBitmap, (int) coord.x, (int) coord.y, color);
        //  c.drawPaint(textColor);
        textColor.setColor(Color.BLACK);
        textColor.setTextSize(20);
        GameThread.c.drawText(signText, coord.x + (size.x / 5), coord.y + (size.y / 3), textColor);


       // c.drawBitmap(bMap, (int) coord.x, (int) coord.y, (int) (coord.x + size.x), (int) (coord.y + size.y), new Paint(Color.WHITE));
      //  c.drawBitmap(bMap,(int)coord.x,(int)coord.y,color);
    }



}
