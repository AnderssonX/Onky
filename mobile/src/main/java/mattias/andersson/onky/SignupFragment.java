package mattias.andersson.onky;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import mattias.andersson.onky.helper.CONSTANTS;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {
    private Button createUser;
    private EditText eTUser;
    private EditText eTPW;
    private boolean isTaken;
    private boolean go;
    private Button backButton;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private boolean match = false; public View.OnTouchListener hej = new View.OnTouchListener() {
        Rect rect = new Rect();
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setSelected(true);
                    rect.set(v.getLeft()-10, v.getTop()-10, v.getRight()+10, v.getBottom()+10);
                    Log.i("knapp", "down"+" left   "+v.getLeft()+" top   "+ v.getTop()+" right   "+ v.getRight()+" bot   "+ v.getBottom());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("knapp", "up");
                    if (rect!=null  &&rect.contains(v.getLeft()+(int) event.getX(), v.getTop()+(int) event.getY())) {
                        Log.i("knapp", "x  "+ (int) event.getX()+" Y   "+ (int) event.getY());

                        switch (v.getId()) {
                            case R.id.SignupBackButton:
                                backButton.setSelected(true);
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new LoginFragment());
                                ft.commit();
                                Log.i("knapp", "inside");
                                break;
                            case R.id.signupCreate:
                                createUser.setSelected(true);
                                final Firebase fbCheck = CONSTANTS.fbRef;

                                fbCheck.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Iterable<DataSnapshot> fbUserCheck = dataSnapshot.getChildren();
                                        for (DataSnapshot fbUC : fbUserCheck) {
                                            //  Log.i("check", " current dataSnapshot is " + dataSnapshot.getRef());
                                            Log.i("gbuc get key ", fbUC.getKey());
                                            Log.i("gbuc get ref ", fbUC.getRef().toString());
                                            Log.i("gbuc get value ", fbUC.getValue().toString());


                                            if (eTUser.getText().toString().toLowerCase().equals(fbUC.getKey().toString())) {
                                                Log.i("check", " Match? = " + fbUC.getKey());

                                                isTaken = true;
                                                match = true;
                                                break;
                                            }
                                            Log.i("check", " no match!");
                                        }
                                        if (match == true) {
                                            Toast toat = Toast.makeText(v.getContext(), "Sorry, username unavailable", Toast.LENGTH_SHORT);
                                            toat.show();
                                        }
                                        if (match == false) {
                                            Toast toat = Toast.makeText(v.getContext(), "user created", Toast.LENGTH_SHORT);
                                            toat.show();
                                            createUser newUser = new createUser(eTUser.getText().toString(), eTPW.getText().toString());
                                            Firebase fb = new Firebase("https://blinding-inferno-6351.firebaseio.com/").child("users/" + newUser.getUsername().toString().toLowerCase());

                                            Map<String, createUser> addNewUser = new HashMap<String, createUser>();
                                            addNewUser.put("userInfo", newUser);
                                            fb.setValue(addNewUser);

                                            FragmentManager fm;
                                            fm = getFragmentManager();
                                            FragmentTransaction ft = fm.beginTransaction();
                                            ft.replace(R.id.container, new LoginFragment());
                                            ft.commit();
                                        }
                                        match = false;
                                    }


                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {

                                    }
                                });


                        }

                    } else {
                        v.setSelected(false);
                        Log.i("knapp", "outside");
                        Log.i("knapp", "x  "+ (int) event.getX()+" Y   "+ (int) event.getY());}
                    break;

            }
            return false;
        }
    };
    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signup, container, false);
        createUser = (Button) view.findViewById(R.id.signupCreate);
        eTUser = (EditText) view.findViewById(R.id.signupUser);
        eTPW = (EditText) view.findViewById(R.id.signupPw);
        backButton = (Button) view.findViewById(R.id.SignupBackButton);
        backButton.setOnTouchListener(hej);
        backButton.setSelected(false);
        createUser.setOnTouchListener(hej);
        createUser.setSelected(false);
        Firebase.setAndroidContext(view.getContext());
        return view;
    }


    @Override
    public void onClick(final View view) {





    }
}