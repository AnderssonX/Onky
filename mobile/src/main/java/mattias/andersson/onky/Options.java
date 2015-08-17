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


/**
 * A simple {@link Fragment} subclass.
 */
public class Options extends Fragment {

    private FragmentManager fm;
    private FragmentTransaction ft;


    private Button optionsbackbutton;

    public View.OnTouchListener hej = new View.OnTouchListener() {
        Rect rect = new Rect();
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setSelected(true);
                    rect.set(v.getLeft()-10, v.getTop()-10, v.getRight()+10, v.getBottom()+10);
                    Log.i("knapp", "down" + " left   " + v.getLeft() + " top   " + v.getTop() + " right   " + v.getRight() + " bot   " + v.getBottom());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("knapp", "up");
                    if (rect!=null  &&rect.contains(v.getLeft()+(int) event.getX(), v.getTop()+(int) event.getY())) {
                        Log.i("knapp", "x  "+ (int) event.getX()+" Y   "+ (int) event.getY());

                        switch (v.getId()) {
                            case R.id.OptionsBackButton:
                                optionsbackbutton.setSelected(true);
                                Log.i("offline", "Store");
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new MainMenu());
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





    public Options() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_options, container, false);

        optionsbackbutton = (Button) v.findViewById(R.id.OptionsBackButton);


        optionsbackbutton.setOnTouchListener(hej);


        return v;
    }


}
