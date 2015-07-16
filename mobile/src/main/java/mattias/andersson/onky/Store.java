package mattias.andersson.onky;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


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





        return view;
    }

    @Override
    public void onClick(View v) {

        Log.i("hej","hej");


    }



}
