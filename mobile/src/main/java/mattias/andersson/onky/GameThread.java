package mattias.andersson.onky;

/**
 * Created by Alrik on 2015-07-15.
 */

import android.graphics.Canvas;


public class GameThread extends Thread {

    static final long FPS = 30;
    public static Canvas c = null;
    private GameView view;
    private boolean running = false;

    public GameThread(GameView view) {
        this.view = view;
    }


    public void setRunning(boolean run) {
        running = run;
    }


    @Override
    public void run() {
      //  long ticksPS = 1000 / FPS;
      //  long startTime;
      //  long sleepTime;
        while (running) {
            // Log.i("test", "loop");

            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    //view.onDraw(c);
                    view.draw(c);
                    GameView.width = c.getWidth();
                    GameView.height = c.getHeight();
                    //     Log.i("test", "canvaswidth= " + GameView.width);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
          //  view.draw(c);
            try {
                sleep(1);

            } catch (Exception e) {}
        /*    startTime = System.currentTimeMillis();
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
            } catch (Exception e) {}*/
        }
    }
}