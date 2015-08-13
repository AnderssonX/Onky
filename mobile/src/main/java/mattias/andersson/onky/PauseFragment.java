package mattias.andersson.onky;


import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PauseFragment extends DialogFragment implements View.OnClickListener {



    public TextView quit;
    View thisView;

    public PauseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pause, null);

        quit = (TextView) view.findViewById(R.id.TwQuit);

        quit.setOnClickListener(this);











        return view;

    }

    @Override
        public void onClick(View view) {
            Log.i("click", "before switch");
            switch (view.getId()) {

                case R.id.TwQuit:
                    Log.i("click", "quitButton");
                    FragmentManager fm;
                    fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.container, new MainMenu(), "tagFrag");
                    ft.commit();
                    GameView.gameLoopThread.setRunning(false);

                    this.dismiss();

                    break;
            }
        }}
