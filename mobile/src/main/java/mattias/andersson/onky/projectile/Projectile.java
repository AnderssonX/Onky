package mattias.andersson.onky.projectile;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import java.util.Random;
import mattias.andersson.onky.helper.Point2D;
/**
 * Created by Alrik on 2015-07-29.
 */
public abstract class Projectile {
    Image image;
    Random r = new Random();
    Paint color = new Paint(Color.RED);
    public Point2D coord;
    public Point2D size = new Point2D();
    public Point2D velocity = new Point2D();
    public int opacity, time = 80;
   public  boolean dead;
    Projectile(Point2D coord, Point2D velocity) {
        this.coord = coord;
        this.velocity = velocity;
    }
    void collision() { }
    public void death() {
        dead = true;
    }

    public void update() { }

    public void display() {
        dead = true;
    }

}


