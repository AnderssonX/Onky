package mattias.andersson.onky.Obstacle;

/**
 * Created by Alrik on 2015-07-22.
 */
public class Point2D {
    public float x,y;

    public Point2D(){
    }
    public Point2D(float x,float y){
        this.x=x;
        this.y=y;
    }

    public void set(float x,float y){
        this.x=x;
        this.y=y;
    }
    public float getX(){
        return x;

    }
    public float getY(){
        return y;
    }
    public void add(Point2D point){
      this.x+=point.x;
      this.y+=point.y;
  }
}
