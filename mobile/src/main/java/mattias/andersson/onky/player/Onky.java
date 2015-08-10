package mattias.andersson.onky.player;

import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;
import mattias.andersson.onky.GameThread;
import mattias.andersson.onky.GameView;
import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.powerup.PowerUp;

/**
 * Created by Alrik on 2015-07-30.
 */
public class Onky extends Player {
    public Onky() {
        color.setARGB(255, 255, 0, 0);
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
