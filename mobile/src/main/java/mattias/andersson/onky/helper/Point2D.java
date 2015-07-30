package mattias.andersson.onky.helper;

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
    public float getX(){return x; }
    public float getY(){
        return y;
    }
    public void add(Point2D point){
      this.x+=point.x;
      this.y+=point.y;
  }
    public void multi(Point2D point){
        this.x*=point.x;
        this.y*=point.y;
    }
    public void multi(float factor){
        this.x*=factor;
        this.y*=factor;
    }
    public void sub(Point2D point){
        this.x-=point.x;
        this.y-=point.y;
    }
    public void sub(float factor) {
        this.x-=factor;
        this.y-=factor;
    }

    public void div(Point2D point) {
        this.x/=point.x;
        this.y/=point.y;
    }
    public void div(float factor) {
        this.x/=factor;
        this.y/=factor;
    }
}
