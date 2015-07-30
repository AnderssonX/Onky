package mattias.andersson.onky;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExitFragment extends Fragment {
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    private Button createUser, play, playOffline;
    private FragmentManager fm;
    private String pw;
    private String currentUser;
    private FragmentTransaction ft;
    private boolean match;
    private EditText eTUser;
    private EditText eTPW;


    public ExitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_exit, container, false);
        killOnky();

        return view;


    }


    private void killOnky() {
        Log.i("killOnky called, ", "exiting app...");
        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.i("seconds remaining: ", "" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Log.i("seconds remaining: ", "done!");
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            }
        };

    }

}







