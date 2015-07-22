package mattias.andersson.onky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.Obstacle.Point2D;

/**
 * Created by Alrik on 2015-07-15.
 */
public class GameView extends SurfaceView {
    static ArrayList<Obstacle>  obstacles = new ArrayList<Obstacle>();
    private SurfaceHolder holder;
    private GameThread gameLoopThread;
    private int x = 0,xSpeed = 10, y = 0,ySpeed = 10;
    public static int width,height;
    private Paint redP;


    public GameView(Context context) {
        super(context);
        this.setOnTouchListener(
                new RelativeLayout.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent m) {

                        int pointerCount = m.getPointerCount();
                        for (int i = 0; i < pointerCount; i++) {
                            int x = (int) m.getX(i);
                            int y = (int) m.getY(i);
                            int id = m.getPointerId(i);
                            int action = m.getActionMasked();
                            int actionIndex = m.getActionIndex();
                            String actionString;


                            switch (action) {
                                case MotionEvent.ACTION_DOWN:
                                    actionString = "DOWN";
                                    addObstacle(new Point2D(x,y));
                                    break;
                                case MotionEvent.ACTION_UP:
                                    actionString = "UP";
                                    break;
                                case MotionEvent.ACTION_POINTER_DOWN:
                                    actionString = "PNTR DOWN";
                                    break;
                                case MotionEvent.ACTION_POINTER_UP:
                                    actionString = "PNTR UP";
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    actionString = "MOVE";
                                    break;
                                default:
                                    actionString = "";
                            }

                            String touchStatus = "Action: " + actionString + " Index: " + actionIndex + " ID: " + id + " X: " + x + " Y: " + y;

                            if (id == 0)
                                Log.i("test", "touchStatus" + touchStatus);
                            else
                                Log.i("test", "nottouch" + touchStatus);
                        }




                            Log.i("test","ok");

                        return true;
                    }
                }
        );




        Log.i("test","canvasdraw11");

        gameLoopThread = new GameThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                        Log.i("test","canvasdraw");
                    } catch (InterruptedException e) {
                    }
                }
            }



            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
                Log.i("test", "canvas start");

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
      //  bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    public  void handleTouch(MotionEvent m)
    {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++)
        {
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            int id = m.getPointerId(i);
            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;


            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }

            String touchStatus = "Action: " + actionString + " Index: " + actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
            if (id == 0)
                Log.i("test","touchStatus"+touchStatus);  else
                Log.i("test", "nottouch"+touchStatus);        }
    }

    void addObstacle(Point2D coord){
        for(int i=0; i<50;i++)obstacles. add(new Obstacle(new Point2D(200, 200), new Point2D(40, 40)));
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

     /*   x+=1*xSpeed;
        y+=1*ySpeed;
        if(x >getWidth()|| x<0) xSpeed*=-1;
        if(y >getHeight() || y<0) ySpeed*=-1;*/

        canvas.drawColor(Color.WHITE);

       // barrel.display(canvas);

        Log.i("test", "size"+obstacles.size() );

        for(int i=0; i<obstacles.size()-1;i++){
            obstacles.get(i).update();
            obstacles.get(i).display(canvas);
        }

       // redP.setColor(Color.RED);
       // canvas.drawCircle(x , y , 10, redP);

        //canvas.drawBitmap(bmp, x , 10, null);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
    }
}

