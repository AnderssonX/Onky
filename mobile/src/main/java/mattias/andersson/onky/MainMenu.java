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
import android.widget.TextView;

import mattias.andersson.onky.helper.CONSTANTS;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenu extends Fragment implements View.OnClickListener {
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Button play;
    private Button highscore;
    private Button options;
    private Button store;

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
                            case R.id.Play:
                                play.setSelected(true);
                                Log.i("offline", "Store");
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new GameFragment());
                                ft.commit();

                                break;case R.id.Highscore:
                                highscore.setSelected(true);
                                Log.i("offline", "Highscore");
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new LoginFragment());
                                ft.commit();

                                break;
                            case R.id.Options:
                                options.setSelected(true);
                                Log.i("offline", "Highscore");
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new Options());
                                ft.commit();

                                break;
                            case R.id.Store:
                                store.setSelected(true);
                                Log.i("offline", "Highscore");
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new Store());
                                ft.commit();

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




            public MainMenu() {





        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main_menu, container, false);


        play = (Button) view.findViewById(R.id.Play);
        highscore = (Button) view.findViewById(R.id.Highscore);
        options = (Button) view.findViewById(R.id.Options);
        store = (Button) view.findViewById(R.id.Store);


        play.setOnTouchListener(hej);
        highscore.setOnTouchListener(hej);
        options.setOnTouchListener(hej);
        store.setOnTouchListener(hej);

        play.setSelected(false);
        highscore.setSelected(false);
        options.setSelected(false);
        store.setSelected(false);



        return view;
    }




    @Override
    public void onClick(View v) {



}}
