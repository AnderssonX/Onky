package mattias.andersson.onky.Obstacle;


import java.util.Vector;

/**
 * Created by Alrik on 2015-07-20.
 */
public abstract class Obstacle {
    public Vector vectorXy;
    public Vector vectorWh;
    public String signText;
    boolean dead, regenerating;

    public Obstacle() {


    }

    public Obstacle(Vector vectorXy, Vector vectorWh) {

        this.vectorXy = vectorXy;
        this.vectorWh = vectorWh;

    }

    public Obstacle(Vector vectorXy, Vector vectorWh, String signText) {

        this.vectorXy = vectorXy;
        this.vectorWh = vectorWh;
        this.signText = signText;


    }

    public String getSignText() {
        return signText;
    }

    public void setSignText(String signText) {
        this.signText = signText;
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
