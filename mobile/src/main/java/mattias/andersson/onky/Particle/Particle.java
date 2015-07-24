package mattias.andersson.onky.Particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;

import java.util.Random;

import mattias.andersson.onky.GameView;
import mattias.andersson.onky.Obstacle.Point2D;

/**
 * Created by Alrik on 2015-07-23.
 */
public class Particle {
    Random r=new Random();
    Paint color=new Paint(Color.RED);
    public Point2D coord;
    public Point2D size;
    public Point2D velocity =new Point2D();
    public String signText;
    int opacity=255;
    public boolean dead;

    public Particle() {
    }

    public Particle(Point2D coord, Point2D Size) {
        color.setColor(Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        this.coord = coord;
        this.size = Size;
        velocity.x=r.nextInt(20)-10;
        velocity.y=r.nextInt(20)-10;
        opacity=255;
    }

    public Particle(Point2D coord, Point2D Size, String signText) {
        this.coord = coord;
        this.size = Size;
        this.signText = signText;
    }


    public void update(){
        if(opacity>0) {
            opacity--;
            color.setAlpha(opacity);
        }else{
            dead=true;
        }
        coord.add(velocity);
        if(coord.x > GameView.width|| coord.x<0) velocity.x*=-1;
        if(coord.y >GameView.height|| coord.y<0) velocity.y*=-1;
    }

    public void display(Canvas c){
        //opacity
        c.drawCircle(coord.x, coord.y, size.x, color );
    }
}
