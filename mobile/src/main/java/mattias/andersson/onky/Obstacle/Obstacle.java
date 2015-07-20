package mattias.andersson.onky.Obstacle;


import java.util.Vector;

/**
 * Created by Alrik on 2015-07-20.
 */
public abstract class Obstacle {
    public Vector vectorXy;
    public Vector vectorWh;
    boolean dead, regenerating;

    public Obstacle() {


    }

    public Obstacle(Vector vectorXy, Vector vectorWh) {

        this.vectorXy = vectorXy;
        this.vectorWh = vectorWh;

    }

    public Vector getVectorXy() {
        return vectorXy;
    }

    public void setVectorXy(Vector vectorXy) {
        this.vectorXy = vectorXy;
    }

    public Vector getVectorWh() {
        return vectorWh;
    }

    public void setVectorWh(Vector vectorWh) {
        this.vectorWh = vectorWh;
    }
}
