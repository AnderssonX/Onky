package mattias.andersson.onky;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Iterator;
import java.util.Map;

import mattias.andersson.onky.Obstacle.Obstacle;
import mattias.andersson.onky.Obstacle.Point2D;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    public Firebase fb = CONSTANTS.fbRef.child("levels/box");
    public String jsonArray;
    boolean first;
    public GameFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        //  GameView gameView=new GameView(this.getActivity());
        first=true;
        GameView gameView = new GameView(view.getContext());


        fb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
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


             /*   Iterator it = value.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    it.remove(); // avoids a ConcurrentModificationException
                    GameView.obstacles.add(new Obstacle(new Point2D(200, 200), new Point2D(50, 50)));
                }*/

              /* for (int i = 0; i < value.size(); i++) {
                    value.keySet();
                    System.out.println("datasnapshot " + value.keySet());
                    GameView.obstacles.add(new Obstacle(new Point2D(200, 200), new Point2D(50, 50)));
                }*/

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
        });
        Log.i(jsonArray, "");
        return gameView;
    }


}
