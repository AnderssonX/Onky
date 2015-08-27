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
import android.widget.TextView;

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

    private int slowPowerUpgrade = 0;
    private int oneUpUpgrade = 0;
    private int slashPowerUpgrade = 0;
    private int laserPowerUpgrade = 0;
    private int speedPowerUpgrade = 0;
    private int tokenUpgrade = 0;
    private int magnetPowerUpgrade = 0;


    private TextView descriptivetext;
    private ImageButton slowpower;
    private ImageButton oneup;
    private ImageButton slashpower;
    private ImageButton laserpower;
    private ImageButton speedpower;
    private ImageButton token;
    private ImageButton magnetpower;
    TableRow tableRow;


    public View.OnTouchListener hej = new View.OnTouchListener() {
        Rect rect = new Rect();

        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setSelected(true);
                    rect.set(v.getLeft() - 10, v.getTop() - 10, v.getRight() + 10, v.getBottom() + 10);
                    Log.i("knapp", "down" + " left   " + v.getLeft() + " top   " + v.getTop() + " right   " + v.getRight() + " bot   " + v.getBottom());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("knapp", "up");
                    if (rect != null && rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                        Log.i("knapp", "x  " + (int) event.getX() + " Y   " + (int) event.getY());

                        switch (v.getId()) {


                            case R.id.BackButton:
                                backbutton.setSelected(true);
                                Log.i("offline", "Store");
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new MainMenu());
                                ft.commit();
                                break;
                            case R.id.slowpower:

                                slowPowerUpgrade++;


                                if (slowPowerUpgrade == 0) {
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slowPowerUpgrade == 1) {

                                    slowpower.setBackgroundResource(R.drawable.woodenboxupgradeslow1);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slowPowerUpgrade == 2) {

                                    slowpower.setBackgroundResource(R.drawable.woodenboxupgradeslow2);

                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slowPowerUpgrade == 3) {

                                    slowpower.setBackgroundResource(R.drawable.woodenboxupgradeslow3);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slowPowerUpgrade == 4) {

                                    slowpower.setBackgroundResource(R.drawable.woodenboxupgradeslow4);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slowPowerUpgrade >= 4) slowPowerUpgrade = 4;

                                break;


                            case R.id.oneup:

                                oneUpUpgrade++;


                                if (oneUpUpgrade == 0) {
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");
                                }


                                if (oneUpUpgrade == 1) {

                                    oneup.setBackgroundResource(R.drawable.woodenboxupgradegreen1);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (oneUpUpgrade == 2) {

                                    oneup.setBackgroundResource(R.drawable.woodenboxupgradegreen2);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (oneUpUpgrade == 3) {

                                    oneup.setBackgroundResource(R.drawable.woodenboxupgradegreen3);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (oneUpUpgrade == 4) {

                                    oneup.setBackgroundResource(R.drawable.woodenboxupgradegreen4);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (oneUpUpgrade >= 4) oneUpUpgrade = 4;

                                break;
                            case R.id.slashpower:

                                slashPowerUpgrade++;


                                if (slashPowerUpgrade == 0) {
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slashPowerUpgrade == 1) {

                                    slashpower.setBackgroundResource(R.drawable.woodenboxupgradeblue1);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slashPowerUpgrade == 2) {

                                    slashpower.setBackgroundResource(R.drawable.woodenboxupgradeblue2);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slashPowerUpgrade == 3) {

                                    slashpower.setBackgroundResource(R.drawable.woodenboxupgradeblue3);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slashPowerUpgrade == 4) {

                                    slashpower.setBackgroundResource(R.drawable.woodenboxupgradeblue4);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (slashPowerUpgrade >= 4) slashPowerUpgrade = 4;

                                break;
                            case R.id.laserpower:

                                laserPowerUpgrade++;


                                if (laserPowerUpgrade == 0) {
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (laserPowerUpgrade == 1) {

                                    laserpower.setBackgroundResource(R.drawable.woodenboxupgradered1);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (laserPowerUpgrade == 2) {

                                    laserpower.setBackgroundResource(R.drawable.woodenboxupgradered2);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (laserPowerUpgrade == 3) {

                                    laserpower.setBackgroundResource(R.drawable.woodenboxupgradered3);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (laserPowerUpgrade == 4) {

                                    laserpower.setBackgroundResource(R.drawable.woodenboxupgradered4);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (laserPowerUpgrade >= 4) laserPowerUpgrade = 4;

                                break;
                            case R.id.speedpower:

                                speedPowerUpgrade++;


                                if (speedPowerUpgrade == 0) {
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (speedPowerUpgrade == 1) {

                                    speedpower.setBackgroundResource(R.drawable.woodenboxupgrade1);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (speedPowerUpgrade == 2) {

                                    speedpower.setBackgroundResource(R.drawable.woodenboxupgrade2);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }

                                if (speedPowerUpgrade == 3) {

                                    speedpower.setBackgroundResource(R.drawable.woodenboxupgrade3);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (speedPowerUpgrade == 4) {

                                    speedpower.setBackgroundResource(R.drawable.woodenboxupgrade4);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (speedPowerUpgrade >= 4) speedPowerUpgrade = 4;

                                break;
                            case R.id.token:

                                tokenUpgrade++;


                                if (tokenUpgrade == 0) {
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (tokenUpgrade == 1) {

                                    token.setBackgroundResource(R.drawable.woodenboxupgradetoken1);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (tokenUpgrade == 2) {

                                    token.setBackgroundResource(R.drawable.woodenboxupgradetoken2);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (tokenUpgrade == 3) {

                                    token.setBackgroundResource(R.drawable.woodenboxupgradetoken3);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (tokenUpgrade == 4) {

                                    token.setBackgroundResource(R.drawable.woodenboxupgradetoken4);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (tokenUpgrade >= 4) tokenUpgrade = 4;

                                break;

                            case R.id.magnetpower:

                                magnetPowerUpgrade++;


                                if (magnetPowerUpgrade == 0) {
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (magnetPowerUpgrade == 1) {

                                    magnetpower.setBackgroundResource(R.drawable.woodenboxupgradepurple1);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (magnetPowerUpgrade == 2) {

                                    magnetpower.setBackgroundResource(R.drawable.woodenboxupgradepurple2);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (magnetPowerUpgrade == 3) {

                                    magnetpower.setBackgroundResource(R.drawable.woodenboxupgradepurple3);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }
                                if (magnetPowerUpgrade == 4) {

                                    magnetpower.setBackgroundResource(R.drawable.woodenboxupgradepurple4);
                                    descriptivetext.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius dictum felis, sit amet bibendum nisl tristique ut. Aenean quis ipsum dignissim, rhoncus justo eget, feugiat odio.");


                                }

                                if (tokenUpgrade >= 4) tokenUpgrade = 4;

                                break;


                            default:
                                ImageButton temp = (ImageButton) v;
                                if (!temp.getTag().equals("divider")) {
                                    temp.setBackgroundResource(R.drawable.woodenboxupgrade1);


                                }


                        }

                    } else {
                        v.setSelected(false);
                        Log.i("knapp", "outside");
                        Log.i("knapp", "x  " + (int) event.getX() + " Y   " + (int) event.getY());
                    }
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
        descriptivetext = (TextView) view.findViewById(R.id.descriptivetext);
        slowpower = (ImageButton) view.findViewById(R.id.slowpower);
        oneup = (ImageButton) view.findViewById(R.id.oneup);
        slashpower = (ImageButton) view.findViewById(R.id.slashpower);
        laserpower = (ImageButton) view.findViewById(R.id.laserpower);
        speedpower = (ImageButton) view.findViewById(R.id.speedpower);
        magnetpower = (ImageButton) view.findViewById(R.id.magnetpower);
        token = (ImageButton) view.findViewById(R.id.token);


        tableRow = (TableRow) view.findViewById(R.id.Row);
        for (int i = 0; i < tableRow.getChildCount(); i++) {
            ImageButton temp = (ImageButton) tableRow.getChildAt(i);
            temp.setBackgroundResource(R.drawable.woodenboxstore);

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