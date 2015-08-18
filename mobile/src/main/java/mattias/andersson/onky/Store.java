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
import android.widget.ImageButton;
import android.widget.TableRow;

import java.util.ArrayList;

import mattias.andersson.onky.helper.CONSTANTS;
import mattias.andersson.onky.helper.StockMarket;
import mattias.andersson.onky.helper.Upgrade;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class Store extends Fragment implements View.OnClickListener {

    ImageButton image1, image2, image3, image4, image5, image6;
    int slowUpgrade, teleportUpgrade, shootUpgrade, invinceUpgrade, coinUpgrade, magnetUpgarde;
    private Button backbutton;
    private FragmentManager fm;
    private FragmentTransaction ft;

    TableRow tableRow;


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


                            case R.id.BackButton:
                                backbutton.setSelected(true);
                                Log.i("offline", "Store");
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new MainMenu());
                                ft.commit();
                                break;
                            default:
                                ImageButton temp = (ImageButton) v;
                                if (!temp.getTag().equals("divider"))
                                    temp.setBackgroundResource(R.drawable.achievementiconclicked);



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



    public Store() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_store, container, false);
       /* image1 = (ImageButton) view.findViewById(R.id.imageButton1);
        image1.setOnClickListener(this);
        image2 = (ImageButton) view.findViewById(R.id.imageButton2);
        image2.setOnClickListener(this);
        image3 = (ImageButton) view.findViewById(R.id.imageButton3);
        image3.setOnClickListener(this);
        image4 = (ImageButton) view.findViewById(R.id.imageButton4);
        image4.setOnClickListener(this)
        image5 = (ImageButton) view.findViewById(R.id.imageButton5);
        image5.setOnClickListener(this);
        image6 = (ImageButton) view.findViewById(R.id.imageButton6);
        image6.setOnClickListener(this);*/
        backbutton = (Button) view.findViewById(R.id.BackButton);

        backbutton.setOnTouchListener(hej);
        backbutton.setSelected(false);


        tableRow = (TableRow) view.findViewById(R.id.Row);
        for (int i = 0; i < tableRow.getChildCount(); i++) {
            ImageButton temp = (ImageButton) tableRow.getChildAt(i);
            temp.setBackgroundResource(R.drawable.woodenbox);

            temp.setOnTouchListener(hej);
            CONSTANTS.upgrades.add(new Upgrade(temp.getTag().toString(), 3));
            Log.i("Store", " row " + CONSTANTS.upgrades.get(i).name + " : " + CONSTANTS.upgrades.get(i).level);
        }


        //ImageButton laserButton = (ImageButton)view.findViewById(R.id.imageButton1);


        return view;
    }


    @Override
    public void onClick(View v) {


    }
}