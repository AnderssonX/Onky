package mattias.andersson.onky;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import mattias.andersson.onky.Obstacle.Barrel;
import mattias.andersson.onky.Obstacle.Block;
import mattias.andersson.onky.Obstacle.Box;
import mattias.andersson.onky.Obstacle.Bush;
import mattias.andersson.onky.Obstacle.Glass;
import mattias.andersson.onky.Obstacle.Grass;
import mattias.andersson.onky.Obstacle.IronBox;
import mattias.andersson.onky.Obstacle.Lumber;
import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.Obstacle.Point2D;
import mattias.andersson.onky.Obstacle.Rock;
import mattias.andersson.onky.Obstacle.Sign;
import mattias.andersson.onky.Obstacle.Snake;
import mattias.andersson.onky.Obstacle.StoneSign;
import mattias.andersson.onky.Obstacle.Tire;
import mattias.andersson.onky.Obstacle.Water;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
   static View view;
    boolean first;
    private String classId, type, text;
    private Long x, y, id, xSize, ySize;

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
        GameView gameView = new GameView(view.getContext());

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

                Log.i("levels", "class: " + classId);
                Log.i("levels", "type: " + type);

                Log.i("levels", "xcoord: " + x);
                Log.i("levels", "ycoord: " + y);
                Log.i("levels", "id: " + id);
                Log.i("levels", "size " + xSize + "*" + ySize);

                switch (classId) {


                    case "Box":
                        GameView.obstacles.add(new Box(view.getContext(),new Point2D(x, y), new Point2D(xSize, ySize)));
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
                        GameView.obstacles.add(new Block(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
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
                        GameView.obstacles.add(new Snake(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Barrel":
                        GameView.obstacles.add(new Barrel(view.getContext(), new Point2D(x, y), new Point2D(xSize, ySize)));
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


                }


                Log.i("Added ball! ", "obstacles.size is now " + GameView.obstacles.size());
            }

        }


    }
}



