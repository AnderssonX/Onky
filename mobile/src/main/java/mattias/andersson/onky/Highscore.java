package mattias.andersson.onky;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 */
public class Highscore extends Fragment implements View.OnClickListener {
    private Button backbutton;
    private FragmentManager fm;
    private FragmentTransaction ft;


    public Highscore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_highscore, container, false);
        // Inflate the layout for this fragment
        backbutton = (Button) view.findViewById(R.id.HighBackButton);
        backbutton.setOnClickListener(this);







        return view;
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.HighBackButton:
                Log.i("offline", "Store");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new LoginFragment());
                ft.commit();

                break;



    }
}}
