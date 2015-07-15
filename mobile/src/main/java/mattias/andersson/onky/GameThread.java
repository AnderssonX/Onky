package mattias.andersson.onky;

/**
 * Created by Alrik on 2015-07-15.
 */
import android.graphics.Canvas;



public class GameThread extends Thread {

    static final long FPS = 30;
    private GameView view;
    private boolean running = false;


    public GameThread(GameView view) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 1b7b20f... canvas cleanup

        this.view = view;

    }

>>>>>>> parent of 1b7b20f... canvas cleanup

        this.view = view;

<<<<<<< HEAD
=======
    public void setRunning(boolean run) {

        running = run;

<<<<<<< HEAD
>>>>>>> parent of 1b7b20f... canvas cleanup
=======
>>>>>>> parent of 1b7b20f... canvas cleanup
    }
    public void setRunning(boolean run) {

        running = run;

    }

    @Override

    public void run() {

        long ticksPS = 1000 / FPS;

        long startTime;

        long sleepTime;

        while (running) {

            Canvas c = null;

            startTime = System.currentTimeMillis();

            try {

                c = view.getHolder().lockCanvas();

                synchronized (view.getHolder()) {

                    view.draw(c);

                }

            } finally {

                if (c != null) {

                    view.getHolder().unlockCanvasAndPost(c);

                }

            }

            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);

            try {

                if (sleepTime > 0)

                    sleep(sleepTime);

                else

                    sleep(10);

            } catch (Exception e) {}

        }

    }

}