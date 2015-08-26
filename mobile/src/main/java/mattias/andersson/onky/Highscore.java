package mattias.andersson.onky;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import mattias.andersson.onky.helper.CONSTANTS;


/**
 * A simple {@link Fragment} subclass.
 */
public class Highscore extends Fragment implements View.OnClickListener {
    private Button backbutton;
    private FragmentManager fm;
    private FragmentTransaction ft;
    public View.OnTouchListener hej = new View.OnTouchListener() {
        Rect rect = new Rect();
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setSelected(true);
                    rect.set(v.getLeft()-10, v.getTop()-10, v.getRight()+10, v.getBottom()+10);
                    Log.i("knapp", "down"+" left   "+v.getLeft()+" top   "+ v.getTop()+" right   "+ v.getRight()+" bot   "+ v.getBottom());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("knapp", "up");
                    if (rect!=null  &&rect.contains(v.getLeft()+(int) event.getX(), v.getTop()+(int) event.getY())) {
                        Log.i("knapp", "x  "+ (int) event.getX()+" Y   "+ (int) event.getY());

                        switch (v.getId()) {
                            case R.id.HighBackButton:
                                backbutton.setSelected(true);
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new LoginFragment());
                                ft.commit();
                                Log.i("knapp", "inside");
                                break;

                        }

                    } else {
                        v.setSelected(false);
                        Log.i("knapp", "outside");
                        Log.i("knapp", "x  "+ (int) event.getX()+" Y   "+ (int) event.getY());}
                    break;

            }
            return false;
        }
    };

    public Highscore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_highscore, container, false);
        // Inflate the layout for this fragment
        backbutton = (Button) view.findViewById(R.id.HighBackButton);
        //backbutton.setOnClickListener(this);
        backbutton.setOnTouchListener(hej);
        backbutton.setSelected(false);








        return view;
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.HighBackButton:

                backbutton.setSelected(true);
                Log.i("offline", "Store");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new LoginFragment());
                ft.commit();

            break;



    }
}}
