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

import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.Obstacle.Point2D;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    boolean first;
    private String classId, type, text;
    private Long x, y, id;
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
        first=true;
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

        /*

      fb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    System.out.println("datasnapshot looped times !!!");

                    Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();

                    Iterable<DataSnapshot> data = dataSnapshot.getChildren();

                    for (DataSnapshot fc : data) {
                        System.out.println("datasnapshot " + fc.getRef().toString() + " " + value);
                        GameView.obstacles.add(new Obstacle(new Point2D(200, 200), new Point2D(50, 50)));
                    }




            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        }); */
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

                GameView.obstacles.add(new Obstacle(new Point2D(200, 200), new Point2D(50, 50)));
                Log.i("Added ball! ", "obstacles.size is now " + GameView.obstacles.size());
            }

        }


    }
}



