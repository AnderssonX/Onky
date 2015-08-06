package mattias.andersson.onky;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;

import mattias.andersson.onky.helper.CONSTANTS;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  image= BitmapFactory.decodeResource(getResources(), R.drawable.woondenBox);

        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        if (savedInstanceState == null) {
        /*{
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
            FragmentManager fm;
            fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, new LoginFragment(), "tagFrag");
            ft.commit();
        }
    }


    @Override
    public void onBackPressed() {
        if (CONSTANTS.currentFragment == "loginFragment") {

            Log.i("currentFragment is ", "" + CONSTANTS.currentFragment);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, new ExitFragment());
            ft.addToBackStack(null);
            ft.commit();
        }
        if (CONSTANTS.currentFragment == "gameFragment") {
            Log.i("currentFragment is ", "" + CONSTANTS.currentFragment);

            GameView.gameLoopThread.setRunning(!GameView.gameLoopThread.isAlive());
            if(!GameView.gameLoopThread.isAlive()) GameView.gameLoopThread.start();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            final Dialog dialog = new Dialog(this);
            final PauseFragment pauseFragment = new PauseFragment();
            pauseFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Transparent);
            pauseFragment.show(getFragmentManager(), "Dialog");
            /* ft.replace(R.id.container, new LoginFragment());
            ft.addToBackStack(null);
            ft.commit(); */
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
