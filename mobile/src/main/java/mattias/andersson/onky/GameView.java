package mattias.andersson.onky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.Paralax.Paralax;
import mattias.andersson.onky.Particle.Particle;
import mattias.andersson.onky.Particle.PulseParticle;
import mattias.andersson.onky.Particle.TriangleParticle;
import mattias.andersson.onky.helper.CONSTANTS;
import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.helper.StockMarket;
import mattias.andersson.onky.player.Player;
import mattias.andersson.onky.player.Qwerty;
import mattias.andersson.onky.powerup.PowerUp;
import mattias.andersson.onky.projectile.LaserProjectile;
import mattias.andersson.onky.projectile.Projectile;

/**
 * Created by Alrik on 2015-07-15.
 */
public class GameView extends SurfaceView {
    public static int width, height;
    public static ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    public static ArrayList<Particle> particles = new ArrayList<Particle>();
    public static ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<Paralax> paralaxLayers = new ArrayList<Paralax>();
    public static ArrayList<Entity> entities = new ArrayList<Entity>(); // all objects
    public static GameThread gameLoopThread;
    public static Point2D offset = new Point2D(), transCoord = new Point2D(), shake = new Point2D(), shakeFactor = new Point2D(), scaleFactor = new Point2D(.5f, .5f), defaultPlayerOffset = new Point2D(200, 200), playerOffset =  new Point2D(200, 200);
    public float screenAngle = 0, flashOpacity;
    private SurfaceHolder holder;
    private int x = 0, xSpeed = 10, y = 0, ySpeed = 10;
    private Paint redP, flashColor = new Paint(Color.BLACK);
    private Bitmap bg = BitmapFactory.decodeResource(this.getResources(), R.drawable.backgroundfull);
    Random r = new Random();

    public GameView(Context context) {
        super(context);
        players.add(new Qwerty());

        this.setOnTouchListener(
                new OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent m) {

                        int pointerCount = m.getPointerCount();
                        for (int i = 0; i < pointerCount; i++) {
                            int x = (int) m.getX(i);
                            int y = (int) m.getY(i);
                            Point2D mouse = new Point2D(x, y);
                            int id = m.getPointerId(i);
                            int action = m.getActionMasked();
                            int actionIndex = m.getActionIndex();
                            String actionString;
                            switch (action) {
                                case MotionEvent.ACTION_DOWN:
                                    actionString = "DOWN";
                                    addparticle(mouse);
                                    shakeFactor.add(25);
                                    // addObstacle(mouse);
                                    break;
                                case MotionEvent.ACTION_UP:
                                    StockMarket.getGlobalPrices();
                                    actionString = "UP";
                                    addPulse(mouse);
                                    break;
                                case MotionEvent.ACTION_POINTER_DOWN:
                                    actionString = "PNTR DOWN";
                                    addparticle(mouse);
                                    screenAngle = r.nextFloat() * 20 - 10;
                                    //addObstacle(mouse);
                                    break;
                                case MotionEvent.ACTION_POINTER_UP:
                                    actionString = "PNTR UP";
                                    addPulse(mouse);
                                    flashOpacity = 200;
                                    playerOffset.x+=100;
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    actionString = "MOVE";
                                    addparticle(mouse);
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

                        Log.i("test", "ok");

                        return true;
                    }
                }
        );
        paralaxLayers.add(new Paralax(new Point2D(0, (int) -(height * 1.5) - 300), new Point2D(width * 3, (height * 3)), (float) 5, bg));

        gameLoopThread = new GameThread(this);   // !!!
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
                        Log.i("test", "canvasdraw");
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
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
        //  bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
    }

    void addObstacle(Point2D coord) {
        coord.div(scaleFactor);
        coord.sub(transCoord);
        // projectiles. add(new LaserProjectile(new Point2D(coord.x, coord.y), new Point2D(20, 0)));
        // obstacles. add(new Box(this.getContext(), new Point2D(coord.x, coord.y), new Point2D(20, 20)));
    }

    void addparticle(Point2D coord) {
        if (CONSTANTS.MAX_PARTICLES > particles.size()) {
            Random r = new Random();
            coord.div(scaleFactor);
            coord.sub(transCoord);
            projectiles.add(new LaserProjectile(new Point2D(coord.x, coord.y), new Point2D(30, 0)));
            particles.add(new TriangleParticle(new Point2D(coord.x, coord.y), new Point2D(r.nextInt(12) - 6, r.nextInt(12) - 6), new Point2D(10, 10), new Paint(Color.RED)));
        }
        // for(int i=0; i<5;i++)particles. add(new Particle(new Point2D(coord.x, coord.y), new Point2D(20, 20)));
    }

    void addPulse(Point2D coord) {
        if (CONSTANTS.MAX_PARTICLES > particles.size()) {
            coord.div(scaleFactor);
            coord.sub(transCoord);
            particles.add(new PulseParticle(new Point2D(coord.x, coord.y), new Point2D(200, 200), new Paint(Color.RED)));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {  // setup function
        super.onDraw(canvas);
     /*   canvas.drawColor(Color.WHITE);
        //  Log.i("test", "obstacle size:" + particles.size() + "   particle size:" + particles.size() + "   projectile size:" + projectiles.size() );

        transCoord.set(-players.get(0).coord.x, (float) (height*0.5));
      //--------------------background---------------------
        for (int i = paralaxLayers.size() - 1; i >= 0; i--) {
            paralaxLayers.get(i).update();
            paralaxLayers.get(i).display();
            //if(projectiles.get(i).dead)projectiles.remove(projectiles.get(i));
        }
        //------------------Transform-------------
        canvas.scale(scaleFactor.x, scaleFactor.y);
        canvas.rotate(screenAngle);
        canvas.translate(transCoord.x, transCoord.y);

     //--------------------obstacle---------------------

        for(int i=obstacles.size()-1 ; i>=0 ;i--){
            obstacles.get(i).update();
            obstacles.get(i).display();
            if(obstacles.get(i).dead)obstacles.remove(obstacles.get(i));
        }

        //--------------------player---------------------
        for(int i=players.size()-1 ; i>=0 ;i--){
            players.get(i).update();
            players.get(i).display();
            if(players.get(i).dead)players.remove(players.get(i));
        }
        //--------------------powerup---------------------
        for (int i = powerups.size() - 1; i >= 0; i--) {
            powerups.get(i).update();
            powerups.get(i).display();
            if(powerups.get(i).dead)powerups.remove(powerups.get(i));
        }
        //--------------------particle---------------------
        for(int i=particles.size()-1 ; i>=0 ;i--){
            particles.get(i).update();
            particles.get(i).display();
            if(particles.get(i).dead)particles.remove(particles.get(i));
        }
        //--------------------projectile---------------------
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            projectiles.get(i).update();
            projectiles.get(i).display();
            if(projectiles.get(i).dead)projectiles.remove(projectiles.get(i));
        }

*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(width, height);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        //  Log.i("test", "obstacle size:" + particles.size() + "   particle size:" + particles.size() + "   projectile size:" + projectiles.size() );

        transCoord.set(-players.get(0).coord.x + playerOffset.x, (float) (height * 0.5) + playerOffset.y);
        //--------------------background---------------------
        for (int i = paralaxLayers.size() - 1; i >= 0; i--) {
            paralaxLayers.get(i).update();
            paralaxLayers.get(i).display();
            //if(projectiles.get(i).dead)projectiles.remove(projectiles.get(i));
        }
        //------------------Transform-------------
        displayFlash();
        canvas.save();
        canvas.scale(scaleFactor.x, scaleFactor.y);
        canvas.rotate(screenAngle);
        canvas.translate(transCoord.x + shake.x, transCoord.y + shake.y);
        smoothAngle();
        smoothOffset();
        shake();
        //--------------------obstacle---------------------
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            obstacles.get(i).update();
            obstacles.get(i).display();
            if (obstacles.get(i).dead) obstacles.remove(obstacles.get(i));
        }
        //--------------------player---------------------
        for (int i = players.size() - 1; i >= 0; i--) {
            players.get(i).update();
            players.get(i).display();
            if (players.get(i).dead) players.remove(players.get(i));
        }
        //--------------------powerup---------------------
        for (int i = powerups.size() - 1; i >= 0; i--) {
            powerups.get(i).update();
            powerups.get(i).display();
            if (powerups.get(i).dead) powerups.remove(powerups.get(i));
        }
        //--------------------particle---------------------
        for (int i = particles.size() - 1; i >= 0; i--) {
            particles.get(i).update();
            particles.get(i).display();
            if (particles.get(i).dead) particles.remove(particles.get(i));
        }
        //--------------------projectile---------------------
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            projectiles.get(i).update();
            projectiles.get(i).display();
            if (projectiles.get(i).dead) projectiles.remove(projectiles.get(i));
        }
        canvas.restore();
        //------------restored
        displayFlash();
        flashOpacity *= .9f;

    }

    void displayFlash() {
        if (flashOpacity > 0) {
            flashColor.setAlpha((int) flashOpacity);
            //setARGB((int)flashOpacity,0,0,0);
            GameThread.c.drawRect(0, 0, width, height, flashColor);
        }
    }

    void shake() {
        if (shakeFactor.x > 0.5 && shakeFactor.y > 0.5) {
            Log.i("hej", " " + shakeFactor.x + " : " + shakeFactor.y);
            if (CONSTANTS.MAX_SHAKE < shakeFactor.x) shakeFactor.x = CONSTANTS.MAX_SHAKE;
            if (CONSTANTS.MAX_SHAKE < shakeFactor.y) shakeFactor.y = CONSTANTS.MAX_SHAKE;
            shakeFactor.multi(CONSTANTS.SHAKE_DECAY);
            shake.set((float) ((r.nextFloat() * shakeFactor.x) - shakeFactor.x * 0.5), (float) ((r.nextFloat() * shakeFactor.y) - shakeFactor.y * 0.5));
        }
    }

    void smoothAngle() {
        if (screenAngle != 0) {
            if (screenAngle > -1 && screenAngle < 1) screenAngle = 0;
            else screenAngle *= 0.9;
        }
    }

    void smoothOffset() {
        // if (defaultPlayerOffsetX != round(playerOffsetX)) {
       Point2D offsetDiff= new Point2D((float)((defaultPlayerOffset.x - playerOffset.x )* 0.02),(float)((defaultPlayerOffset.y - playerOffset.y)* 0.02));
        //float offsetXDiff = defaultPlayerOffset.x - playerOffset.x;
      //  float offsetYDiff = defaultPlayerOffset.y - playerOffset.y;
      //  playerOffset.x += offsetXDiff * 0.02;
      //  playerOffset.y += offsetYDiff * 0.02;
        playerOffset.add(offsetDiff);
        Log.i("offsetDiff", " " + offsetDiff.x + " : " + offsetDiff.y);

        Log.i("playerOffset", " " + playerOffset.x + " : " + playerOffset.y);

        //  }
    }
}

