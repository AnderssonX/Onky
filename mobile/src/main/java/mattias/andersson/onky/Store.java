package mattias.andersson.onky;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import mattias.andersson.onky.helper.CONSTANTS;
import mattias.andersson.onky.helper.StockMarket;
import mattias.andersson.onky.helper.Upgrade;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class Store extends Fragment implements View.OnClickListener {



    public Store() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_store, container, false);
        //ImageButton laserButton = (ImageButton)view.findViewById(R.id.imageButton1);
        CONSTANTS. upgrades.add(new Upgrade("slow", 1));
        CONSTANTS. upgrades.add(new Upgrade("laser",1));
        CONSTANTS. upgrades.add(new Upgrade("invis",4));
        CONSTANTS. upgrades.add(new Upgrade("token",2));
        CONSTANTS. upgrades.add(new Upgrade("skinGreen",1));
        CONSTANTS. upgrades.add(new Upgrade("ONKY",1));
        CONSTANTS. upgrades.add(new Upgrade("bonus",3));

        return view;
    }


    @Override
    public void onClick(View v) {

    }
}
