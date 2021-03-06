package mattias.andersson.onky;


import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;
import java.util.Random;

import mattias.andersson.onky.Obstacle.Barrel;
import mattias.andersson.onky.Obstacle.Block;
import mattias.andersson.onky.Obstacle.Box;
import mattias.andersson.onky.Obstacle.Bush;
import mattias.andersson.onky.Obstacle.Glass;
import mattias.andersson.onky.Obstacle.Grass;
import mattias.andersson.onky.Obstacle.IronBox;
import mattias.andersson.onky.Obstacle.Lumber;
import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.Obstacle.Rock;
import mattias.andersson.onky.Obstacle.Sign;
import mattias.andersson.onky.Obstacle.Snake;
import mattias.andersson.onky.Obstacle.StoneSign;
import mattias.andersson.onky.Obstacle.Tire;
import mattias.andersson.onky.Obstacle.Water;
import mattias.andersson.onky.helper.CONSTANTS;
import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.powerup.Invisible;
import mattias.andersson.onky.powerup.Laser;
import mattias.andersson.onky.powerup.Life;
import mattias.andersson.onky.powerup.Magnet;
import mattias.andersson.onky.powerup.Poison;
import mattias.andersson.onky.powerup.RandomPower;
import mattias.andersson.onky.powerup.Slow;
import mattias.andersson.onky.powerup.Teleport;
import mattias.andersson.onky.powerup.Token;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    static View view;
    boolean first;
    Random r = new Random();
    private int randomPower;
    private String classId, type, text;
    private Long x, y, id, xSize, ySize,xVel,yVel;

    private Firebase fb = CONSTANTS.fbRef;
    private String jsonArray;
    private int obstacleCourses;

    public GameFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);
        //  GameView gameView=new GameView(this.getActivity());
        first = true;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        float scale = getResources().getDisplayMetrics().density;

        Point size = new Point();
        display.getSize(size);

        CONSTANTS.screenWidth = size.x;
        CONSTANTS.screenHeight = size.y;
        CONSTANTS.density =(int)getResources().getDisplayMetrics().scaledDensity;
        CONSTANTS.dpi =getResources().getDisplayMetrics().densityDpi;

        GameView gameView = new GameView(view.getContext());
        gameView.scaleFactor.set((float) (CONSTANTS.dpi / 480));
         Log.i("density", ""+    CONSTANTS.density +" firstdpi: "+ CONSTANTS.dpi +"   scale " +CONSTANTS.dpi / 480);
        CONSTANTS.currentFragment = "gameFragment";

        fb.child("prices/powerups").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                obstacleCourses = (int) dataSnapshot.getChildrenCount();
                Log.i("levels ", "Box0 has" + obstacleCourses + " children");
                Iterable<DataSnapshot> prices = dataSnapshot.getChildren();
                for (DataSnapshot fbp : prices) {
                    Log.i("prices","cost :" + fbp.getKey()+"  : " + fbp.getValue());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        fb.child("levels").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                obstacleCourses = (int) dataSnapshot.getChildrenCount();
                Log.i("levels ", "Box0 has" + obstacleCourses + " children");
                Iterable<DataSnapshot> firebaseLevels = dataSnapshot.getChildren();
                for (DataSnapshot FBlevels : firebaseLevels) {

                    addLevel(FBlevels);
                }
           /*     Iterable<DataSnapshot> firebaseLevels = dataSnapshot.getChildren();
                for (DataSnapshot FBlevels : firebaseLevels) {
                    if (FBlevels.getKey() == "courseProperties") {
                        break;
                    } else {
                        Log.i("looping, ref is:", "" + FBlevels);

                        Log.i("levels", " current dataSnapshot is " + dataSnapshot);
                        //Map<String, Object> value = (Map<String, Object>) dataSnapshot.child("Box0").getValue();
                        Map<String, Object> value = (Map<String, Object>) FBlevels.getValue();
                        classId = value.get("class").toString();
                        type = value.get("type").toString();
                        text = value.get("text").toString();
                        x = (Long) value.get("xCoord");
                        y = (Long) value.get("yCoord");
                        id = (Long) value.get("id");

                        Log.i("levels", "class: " + classId);
                        Log.i("levels", "type: " + type);
                        Log.i("levels", "text: " + text);
                        Log.i("levels", "xcoord: " + x);
                        Log.i("levels", "ycoord: " + y);
                        Log.i("levels", "id: " + id);
                    }
                }/*
*/
            }


            //  }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        Log.i(jsonArray, "");
        return gameView;
    }

    private void addLevel(DataSnapshot dataSnapshot) {
        Log.i("levels", "Adding level: " + dataSnapshot.getKey());
        Iterable<DataSnapshot> firebaseLevels = dataSnapshot.getChildren();
        for (DataSnapshot FBlevels : firebaseLevels) {
            if (FBlevels.getKey() == "courseProperties") {
                break;
            } else {
                Map<String, Object> value = (Map<String, Object>) FBlevels.getValue();

                Log.i("looping, ref is:", "" + FBlevels);
                Log.i("levels", " current dataSnapshot is " + dataSnapshot);

                classId = value.get("class").toString();
                type = value.get("type").toString();

                x = (Long) value.get("xCoord");
                y = (Long) value.get("yCoord");
                id = (Long) value.get("id");
                xSize = (Long) value.get("xSize");
                ySize = (Long) value.get("ySize");
                try {
                    Log.i("try","success");
                    xVel = (Long) value.get("xVel");
                    yVel = (Long) value.get("yVel");
                }catch(Exception e){
                    Log.i("try","fail");

                }
                Log.i("levels", "class: " + classId);
                Log.i("levels", "type: " + type);

                Log.i("levels", "xcoord: " + x);
                Log.i("levels", "ycoord: " + y);
                Log.i("levels", "id: " + id);
                Log.i("levels", "size " + xSize + "*" + ySize);

                switch (classId) {


                    case "Box":
                        GameView.obstacles.add(new Box(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "IronBox":
                        GameView.obstacles.add(new IronBox(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Tire":
                        GameView.obstacles.add(new Tire(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Lumber":
                        GameView.obstacles.add(new Lumber(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Glass":
                        GameView.obstacles.add(new Glass(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Block":

                        Obstacle temp0=new Block(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize));
                        if(xVel!=null)  temp0.velocity.x=xVel.intValue();
                        if(yVel!=null)   temp0.velocity.y=yVel.intValue();
                        GameView.obstacles.add(temp0);

                       // GameView.obstacles.add(new Block(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Bush":
                        GameView.obstacles.add(new Bush(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));

                        break;

                    case "Grass":
                        GameView.obstacles.add(new Grass(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Water":
                        GameView.obstacles.add(new Water(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Sign":
                        text = value.get("text").toString();
                        Log.i("levels", "text: " + text);
                        Obstacle temp = new Sign(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize));
                        temp.signText = text;
                        Log.i("levels", "text: " + temp.getSignText());
                        GameView.obstacles.add(temp);
                        break;

                    case "Snake":
                        Obstacle temp1=new Snake(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize));
                      if(xVel!=null)  temp1.velocity.x=xVel.intValue();
                        if(yVel!=null)   temp1.velocity.y=yVel.intValue();
                         GameView.obstacles.add(temp1);
                       // GameView.obstacles.add(new Snake(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Barrel":
                        Obstacle temp2=new Barrel(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize));
                        if(xVel!=null)  temp2.velocity.x=xVel.intValue();
                        if(yVel!=null)   temp2.velocity.y=yVel.intValue();
                        GameView.obstacles.add(temp2);
                       // GameView.obstacles.add(new Barrel(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Rock":
                        GameView.obstacles.add(new Rock(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "StoneSign":
                        text = value.get("text").toString();
                        Log.i("levels", "text: " + text);
                        Obstacle tempStoneSign = new StoneSign(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize));
                        tempStoneSign.signText = text;
                        Log.i("levels", "text: " + tempStoneSign.getSignText());
                        GameView.obstacles.add(tempStoneSign);
                        break;


                    // PowerUps below
                    case "TokenPowerup":

                        GameView.powerups.add(new Token(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "InvisPowerup":
                        GameView.powerups.add(new Invisible(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "LaserPowerup":
                        GameView.powerups.add(new Laser(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "SlowPowerup":
                        GameView.powerups.add(new Slow(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "LifePowerup":
                        GameView.powerups.add(new Life(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "TeleportPowerup":
                        GameView.powerups.add(new Teleport(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "MagnetPowerup":
                        GameView.powerups.add(new Magnet(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "RandomPowerup":
                        GameView.powerups.add(new RandomPower(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        /*for (int i=0; i<1; i++){
                            randomPower = r.nextInt(5);
                            switch (randomPower) {
                                case 0:
                        }*/
                        break;
                    case "PoisonPowerdown":
                        GameView.powerups.add(new Poison(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                }


                Log.i("Added ball! ", "obstacles.size is now " + GameView.obstacles.size());

            }
                GameView.levelLoaded=true;
        }


    }
}



