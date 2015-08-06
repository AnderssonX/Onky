package mattias.andersson.onky.helper;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by Mattias on 2015-06-16.
 */
public class CONSTANTS {
    public static final Firebase fbRef = new Firebase("https://blinding-inferno-6351.firebaseio.com/");
    public static final int MAX_PARTICLES=500;
    public static final float SHAKE_DECAY=.85f,MAX_SHAKE=200;
    public static String currentFragment;
    public static int screenWidth=0;
    public static int screenHeight=0;
    public static    ArrayList<Upgrade> upgrades= new ArrayList<Upgrade>();

}
