package mattias.andersson.onky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Alrik on 2015-07-15.
 */
public class GameView extends SurfaceView {

    private SurfaceHolder holder;
    private GameThread gameLoopThread;
    private int x = 0,xSpeed = 10, y = 0,ySpeed = 10;
Paint redP= new Paint(Color.RED);
    public GameView(Context context) {
        super(context);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        x+=1*xSpeed;
        y+=1*ySpeed;
        if(x >getWidth()|| x<0) xSpeed*=-1;
        if(y >getHeight() || y<0) ySpeed*=-1;
       canvas.drawColor(Color.WHITE);
        canvas.drawCircle(x , y , 50, redP);

        //canvas.drawBitmap(bmp, x , 10, null);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
    }
}

