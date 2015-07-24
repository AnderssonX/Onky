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

import mattias.andersson.onky.Obstacle.Box;
import mattias.andersson.onky.Obstacle.IronBox;
import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.Obstacle.Point2D;
import mattias.andersson.onky.Obstacle.Sign;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_game, container, false);
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
                        GameView.obstacles.add(new Box(new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;
                    case "IronBox":
                        GameView.obstacles.add(new IronBox(new Point2D(x, y), new Point2D(xSize, ySize)));
                        break;

                    case "Tire":

                        break;

                    case "Lumber":
                        break;

                    case "Glass":
                        break;

                    case "Block":
                        break;

                    case "Bush":
                        break;

                    case "Grass":
                        break;

                    case "Water":
                        break;

                    case "Sign":
                        text = value.get("text").toString();
                        Log.i("levels", "text: " + text);
                        Obstacle temp = new Sign(new Point2D(x, y), new Point2D(xSize, ySize));
                        temp.signText = text;
                        Log.i("levels", "text: " + temp.getSignText());
                        GameView.obstacles.add(temp);
                        break;

                    case "Snake":
                        break;

                    case "Barrel":
                        break;

                    case "Rock":
                        break;

                    case "StoneSign":
                        break;


                }


                Log.i("Added ball! ", "obstacles.size is now " + GameView.obstacles.size());
            }

        }


    }
}



