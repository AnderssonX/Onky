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

import mattias.andersson.onky.helper.CONSTANTS;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenu extends Fragment implements View.OnClickListener {
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Button play;
    private Button highscore;
    private Button options;
    private Button store;


    public MainMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main_menu, container, false);


        play = (Button) view.findViewById(R.id.Play);
        highscore = (Button) view.findViewById(R.id.Highscore);
        options = (Button) view.findViewById(R.id.Options);
        store = (Button) view.findViewById(R.id.Store);

        play.setOnClickListener(this);
        highscore.setOnClickListener(this);
        options.setOnClickListener(this);
        store.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Play:
                Log.i("offline", "Store");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new GameFragment());
                ft.commit();

                break;
            case R.id.Highscore:
                Log.i("offline", "Highscore");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new Highscore());
                ft.commit();

                break;
            case R.id.Options:
                Log.i("offline", "Highscore");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new Options());
                ft.commit();

                break;
            case R.id.Store:
                Log.i("offline", "Highscore");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new Store());
                ft.commit();

                break;
    }



}}
