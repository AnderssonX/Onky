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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import mattias.andersson.onky.helper.CONSTANTS;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    private Button createUser, play, playOffline,storeButton, backbutton;
    private TextView skip;
    private FragmentManager fm;
    private String pw;
    private String currentUser;
    private FragmentTransaction ft;
    private boolean match;
    private EditText eTUser;
    private EditText eTPW;
    public View.OnTouchListener hej = new View.OnTouchListener() {
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
                            case R.id.loginSignup:
                                createUser.setSelected(true);
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new SignupFragment());
                                ft.commit();
                                Log.i("knapp", "inside");

                             break;

                            case R.id.LoginBackButton:
                                backbutton.setSelected(true);
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.container, new MainMenu());
                                ft.commit();
                                Log.i("knapp", "inside");
                            break;
                            case R.id.loginLogin:
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
                                                currentUser = fbUC.getKey().toString();
                                                Log.i("login ", "datasnapshot is +" + fbUC.getValue());
                                                Log.i("login ", "pw is: " + pw);
                                                match = true;

                                                final Firebase fbCheckPw = CONSTANTS.fbRef.child("users/" + currentUser);
                                                fbCheckPw.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        pw = dataSnapshot.child("userInfo/password").getValue().toString();
                                                        Log.i("user" + dataSnapshot.toString() + "pw is", pw);
                                                    }

                                                    @Override
                                                    public void onCancelled(FirebaseError firebaseError) {

                                                    }
                                                });
                                                break;
                                            }
                                            Log.i("check", " no match!");
                                        }
                        /* If User is found in the Firebase database, call on the CheckPassword class
                        To see if the entered password is the same as saved in the db
                         */
                                        if (match == true) {
                                            CheckPassword cp = new CheckPassword();
                                            cp.checkPassword(getActivity(), v, currentUser, eTPW.getText().toString(), v.getContext());
                                            {
                                            }

//                            Firebase fb = new Firebase("https://blinding-inferno-6351.firebaseio.com/").child("users/" + newUser.getUsername().toString().toLowerCase());

                                            //  fm = getFragmentManager();
                                            //  ft = fm.beginTransaction();
                                            //   ft.replace(R.id.container, new Highscore());
                                            //    ft.commit();
                                        }
                                        if (match == false) {
                                            Toast toat = Toast.makeText(v.getContext(), "No such user", Toast.LENGTH_SHORT);
                                            toat.show();


                                        }


                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {

                                    }

                                });
                                play.setSelected(false);
                                break;
                        }



                    } else {
                        v.setSelected(false);
                        Log.i("knapp", "outside");
                        Log.i("knapp", "x  "+ (int) event.getX()+" Y   "+ (int) event.getY());

                       /* backbutton.setSelected(false);
                        fm = getFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.container, new MainMenu());
                        ft.commit();
                    */}
                    break;

            }
            return false;
        }
    };

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        createUser = (Button) view.findViewById(R.id.loginSignup);
        skip = (TextView) view.findViewById(R.id.HighSkipButton);
        backbutton = (Button) view.findViewById(R.id.LoginBackButton);
        //createUser.setOnClickListener(this);
        eTUser = (EditText) view.findViewById(R.id.loginUser);
        eTPW = (EditText) view.findViewById(R.id.loginPw);
        play = (Button) view.findViewById(R.id.loginLogin);

        skip.setOnClickListener(this);
        //backbutton.setOnClickListener(this);


        backbutton.setOnTouchListener(hej);
        createUser.setOnTouchListener(hej);
        play.setOnTouchListener(hej);



        backbutton.setSelected(false);
        createUser.setSelected(false);
        //playOffline = (Button) view.findViewById(R.id.Play);
        //playOffline.setOnClickListener(this);
        CONSTANTS.currentFragment = "loginFragment";
  //      storeButton = (Button) view.findViewById(R.id.Store);
//        storeButton.setOnClickListener(this);

        return view;

    }





    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            /*case R.id.Store:
                Log.i("offline", "Store");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new Store());
                ft.commit();

                break;*/
            /*case R.id.playOfflineButton:
                Log.i("offline", "we're in case switch");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new GameFragment());
                ft.commit();

                break;*/


            case R.id.loginSignup:

                createUser.setSelected(true);
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new SignupFragment());
                ft.commit();

                break;
            case R.id.LoginBackButton:
                backbutton.setSelected(true);

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new MainMenu());
                ft.commit();

                break;

            case R.id.HighSkipButton:


                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new Highscore());
                ft.commit();

                break;


        }
    }



}







