package mattias.andersson.onky;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TableRow tableRow;

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
        image4.setOnClickListener(this);
        image5 = (ImageButton) view.findViewById(R.id.imageButton5);
        image5.setOnClickListener(this);
        image6 = (ImageButton) view.findViewById(R.id.imageButton6);
        image6.setOnClickListener(this);*/


        tableRow = (TableRow) view.findViewById(R.id.Row);
        for (int i = 0; i < tableRow.getChildCount(); i++) {
            ImageButton temp = (ImageButton) tableRow.getChildAt(i);
            temp.setBackgroundResource(R.drawable.woodenbox);

            temp.setOnClickListener(this);
            CONSTANTS.upgrades.add(new Upgrade(temp.getTag().toString(), 3));
            Log.i("Store", " row " + CONSTANTS.upgrades.get(i).name + " : "+ CONSTANTS.upgrades.get(i).level);
        }



        //ImageButton laserButton = (ImageButton)view.findViewById(R.id.imageButton1);


        return view;
    }


    @Override
    public void onClick(View v) {
        ImageButton temp =(ImageButton)v;
        if(!temp.getTag().equals("divider"))temp.setBackgroundResource(R.drawable.achievementiconclicked);
       /* for (int i = 0; i < tableRow.getChildCount(); i++) {
            ImageButton temp = (ImageButton) tableRow.getChildAt(i);
            temp.setBackgroundResource(R.drawable.achievementiconclicked);
            Log.i("clicked", "Hope you)aasdw´re happy with your purchase");
        }

        switch (v.getId()) {
            case R.id.imageButton1:
                image1.setBackgroundResource(R.drawable.achievementiconclicked);
                slowUpgrade++;
                if (slowUpgrade == 3)
                    Log.i("clicked", "Hope you)aasdw´re happy with your purchase");
                break;

            case R.id.imageButton2:
                image2.setBackgroundResource(R.drawable.achievementiconclicked);
                Log.i("clicked", "Hope you´re happy with your purchase");


                break;
            case R.id.imageButton3:
                image3.setBackgroundResource(R.drawable.achievementiconclicked);
                Log.i("clicked", "Hope you´re happy with your purchase");


                break;
            case R.id.imageButton4:
                image4.setBackgroundResource(R.drawable.achievementiconclicked);
                Log.i("clicked", "Hope you´re happy with your purchase");


                break;
            case R.id.imageButton5:
                image5.setBackgroundResource(R.drawable.achievementiconclicked);
                Log.i("clicked", "Hope you´re happy with your purchase");


                break;
            case R.id.imageButton6:
                image6.setBackgroundResource(R.drawable.achievementiconclicked);
                Log.i("clicked", "Hope you´re happy with your purchase");


                break;

        }*/
    }
}
