package mattias.andersson.onky;

import mattias.andersson.onky.helper.Point2D;

/**
 * Created by Alrik on 2015-08-04.
 */
public abstract class Entity {
    public Point2D coord;
    public Point2D size;
    public Point2D velocity = new Point2D();

    public void update() {
    }

    public void display() {

    }
}
