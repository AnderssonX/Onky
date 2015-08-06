package mattias.andersson.onky.Paralax;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.GameView;
import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.helper.CONSTANTS;
import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-07-30.
 */

public class Paralax extends Obstacle {
    float angle, factor, heightLevel, opacity = 255;
    Bitmap bg;

    Paralax() { // DUMMY
        //super( 0, 0, 0, 0);
    }

    public Paralax(Point2D coord, Point2D size, float _factor) {
        super(coord, size);
        GameView.paralaxLayers.add(this);
        color.setAntiAlias(false);
        color.setFilterBitmap(false);
        factor = _factor;
    }

    public Paralax(Point2D coord, Point2D size, float _factor, Bitmap _bg) {
        this(coord, size, _factor);
        GameView.paralaxLayers.add(this);
        DisplayMetrics displaymetrics = new DisplayMetrics();

        //bg=_bg;
        bg = Bitmap.createScaledBitmap(_bg, CONSTANTS.screenWidth * 3, (int) (CONSTANTS.screenHeight * 1.5), false);
        //Log.i("paralax gameview X Y", " "+CONSTANTS.screenWidth+" " + GameView.height);
        //bg = Bitmap.createScaledBitmap(_bg, (int) size.x, (int) size.y,false);
    }


    public void update() {
        coord.x -= GameView.players.get(0).velocity.x * factor;
        //x+=vx*speedFactor;
        // if (coord.x+(size.getX()/3)*2<GameView.players.get(0).coord.x)coord.x=GameView.players.get(0).coord.x;
        if (coord.x < (-CONSTANTS.screenWidth) * 2) coord.x = 0;
        //Log.i("bgDisplay", "we're in update() "+coord.x+(size.getX()/3)*2+" : "+GameView.players.get(0).coord.x);
    }


    public void display() {
        //noStroke();
     /* if (!p.) fill(0, 0, 255);
      else  fill(100, 100, 255);
      if (bg!=null)image(bg,  int(x), int(y), int(w), int(h));
      else rect(x, y, w, h);*/
        // Log.i("bgDisplay", "we're in display()");
        GameThread.c.drawBitmap(bg, coord.getX(), coord.getY(), color);
    }
}

/*

class ParalaxObject extends Paralax {
    float x, y ;
    int repeatDistance=1;

    ParalaxObject() { // DUMMY
        super();
    }
    ParalaxObject(Bitmap _image,int _x, int _y, int _w, int _h, float _factor) {
        super(_x, _y, _w, _h, _factor);
        x=_x;
        y=_y;
        heightLevel=_y;
        bg=_image;
    }
    ParalaxObject(Bitmap _image,int _x, int _y, int _w, int _h, float _factor, int _repeatDistance) {
        this(_image,_x, _y, _w, _h, _factor);
        repeatDistance=_repeatDistance;
    }
    ParalaxObject(Bitmap _image,int _x, int _y, int _w, int _h, float _factor, int _repeatDistance, int _opacity) {
        this(_image,_x, _y, _w, _h, _factor, _repeatDistance);
        opacity=_opacity;
    }
    void update() {
        x-=GameView.players.get(0).velocity.x*factor;
        y =-(GameView.players.get(0).velocity.y-(GameView.height)*0.15*factor+heightLevel);
        if (x+w<0)x=width*repeatDistance;
    }

    void display() {

        tint(255, opacity);
        Bitmap(bg,int(x), int(y), int(w), int(h));
        noTint();
        //}
    }
 */