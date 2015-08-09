package mattias.andersson.onky.player;

import mattias.andersson.onky.GameThread;

/**
 * Created by Alrik on 2015-07-30.
 */
public class Qwerty extends Player{
    public Qwerty() {
        coord.set(0, 100);
        color.setARGB(255, 255, 200, 0);
        size.set(160, 130);
        velocity.set(1,0);
    }
    public void update() {
        super.update();
    }
    public void display() {
        super.display();
    }
}
