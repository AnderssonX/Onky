package mattias.andersson.onky.Obstacle;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Alrik on 2015-07-23.
 */
public class IronBox extends  Obstacle {
   int defaultHealth,health=defaultHealth;
    Drawable image;
  //  Bitmap bMap = BitmapFactory.decodeFile("main/assets/woodenBox.png");
   // Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.woodenBox.png);
    public IronBox(Point2D coord, Point2D Size) {
       super(coord, Size);
    color.setARGB(255,200, 200, 200);
     //  image=C:\Users\Alrik\AndroidStudioProjects\Onky\mobile\src\main\assets\woodenBox.png

    //   Drawable d = Drawable.createFromStream(getAssets().open("woodenBox.png"), null);

    //  Drawable d=

      //// d.setBounds((int)coord.x, (int)coord.y,(int) (coord.x+size.x),(int) (coord.y+size.y));

      // d.draw(c);
   }

    public void update(){

    }

    public void display(Canvas c){
        c.drawRect(coord.x, coord.y, coord.x + size.x, coord.y + size.y, color);
       // c.drawBitmap(bMap, (int) coord.x, (int) coord.y, (int) (coord.x + size.x), (int) (coord.y + size.y), new Paint(Color.WHITE));
      //  c.drawBitmap(bMap,(int)coord.x,(int)coord.y,color);
    }



}
