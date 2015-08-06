package mattias.andersson.onky.Obstacle;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.util.Log;

import java.util.Random;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.GameView;
import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.helper.StockMarket;

/**
 * Created by Alrik on 2015-07-20.
 */
public abstract class Obstacle {
    Image image;
    Random r = new Random();
    public Paint color = new Paint(Color.RED);
    public Point2D coord;
    public Point2D size;
    public Point2D velocity = new Point2D();
    public String signText;
    public Bitmap bitmap = null;
    public Bitmap scaledBitmap;
    int opacity;
    public boolean dead, regenerating;

    public Obstacle() {
        // super();

    }

    public Obstacle(Point2D coord, Point2D Size) {
        color.setColor(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        this.coord = coord;
        this.size = Size;
     //   velocity.x = r.nextInt(20) - 10;
     //   velocity.y = r.nextInt(20) - 10;
    }

    public Obstacle(Point2D coord, Point2D Size, String signText) {
        this.coord = coord;
        this.size = Size;
        this.signText = signText;
    }


    public void update() {
        coord.add(velocity);
        if (coord.x > GameView.width || coord.x < 0) velocity.x *= -1;
        if (coord.y > GameView.height || coord.y < 0) velocity.y *= -1;
    }

    public void display() {
        //opacity
        // GameThread.c.drawRect(coord.x,coord.y,size.x,size.y,color);
    }

    public Bitmap[] cutSprite(Bitmap bitmap, int amount, int width, int height) {

        // final int imageWidth=width, imageheight=height;
        Bitmap[] sprites = new Bitmap[amount];
       /*for(int i=0; amount>i ; i++) {    full Image
           Log.i(" forSprite"," frame" + i+ "  "+  (int)((width/amount)+i-1)*i +"  to "+ (int)((width/amount)+i-1)*(i+1));
           sprites[i]=Bitmap.createBitmap(bitmap,  (int)((width/amount)+i-1)*i,0, (int)((width/amount)+i-1)*(i+1), height);
       }*/
        for (int i = 0; amount > i; i++) {  // interval
            Log.i(" forSprite", " frame" + i + "  " + (int) ((width / amount) + i - 1) * i + "  to " + (int) ((width / amount) + i - 1) * (i + 1));
            sprites[i] = Bitmap.createBitmap(bitmap, (int) ((width / amount) + i - 1) * i, 0, (int) ((width / amount) + i - 1) * (i + 1), height);
        }
        return sprites;
    }

    public Bitmap[] cutSprite(Bitmap bitmap, int interval, int amount, int width, int height) {
        // final int imageWidth=width, imageheight=height;
        Bitmap[] sprites = new Bitmap[amount];

       /*for(int i=0; amount>i ; i++) {    full Image
           Log.i(" forSprite"," frame" + i+ "  "+  (int)((width/amount)+i-1)*i +"  to "+ (int)((width/amount)+i-1)*(i+1));
           sprites[i]=Bitmap.createBitmap(bitmap,  (int)((width/amount)+i-1)*i,0, (int)((width/amount)+i-1)*(i+1), height);
       }*/

        for (int i = 0; amount > i; i++) {  // interval

            if (amount - 1 == i) {
                Log.i(" forSprite", " frame" + i + "  " + (interval * i + i) + "  to " + ((interval + 1) * i + interval ));
                sprites[i] = Bitmap.createBitmap(bitmap, (interval * i + i), 0, (interval)
                        , height);
            } else {
                Log.i(" forSprite", " frame" + i + "  " + (interval * i + i) + "  to " + ((interval + 1) * i + interval ));
                sprites[i] = Bitmap.createBitmap(bitmap, (interval * i + i), 0, (interval)
                        , height);

            }
            Log.i(" forSprite", " sprite " + sprites[i].getWidth() + " : " + sprites[i].getHeight());


        }

        return sprites;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public String getSignText() {
        return signText;
    }

    public void setSignText(String signText) {
        this.signText = signText;
    }

    public Point2D getCoord() {
        return coord;
    }

    public void setCoord(Point2D coord) {
        this.coord = coord;
    }

    public Point2D getSize() {
        return size;
    }

    public void setSize(Point2D size) {
        this.size = size;
    }

    public void damage(int i) {

    }
}
