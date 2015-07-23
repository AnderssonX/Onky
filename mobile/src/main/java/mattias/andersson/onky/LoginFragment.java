package mattias.andersson.onky;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    private Button createUser;
    private FragmentManager fm;
    private Button play, playOffline;
    private String pw;
    private String currentUser;
    private FragmentTransaction ft;
    private boolean match;
    private EditText eTUser;
    private EditText eTPW;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        createUser = (Button) view.findViewById(R.id.loginSignup);
        createUser.setOnClickListener(this);
        eTUser = (EditText) view.findViewById(R.id.loginUser);
        eTPW = (EditText) view.findViewById(R.id.loginPw);
        play = (Button) view.findViewById(R.id.loginLogin);
        play.setOnClickListener(this);
        playOffline = (Button) view.findViewById(R.id.playOfflineButton);
        playOffline.setOnClickListener(this);


        return view;

    }


    @Override
    public void onClick(final View view) {
        switch (view.getId()) {

            case R.id.playOfflineButton:
                Log.i("offline", "we're in case switch");
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new GameFragment());
                ft.commit();

                break;


            case R.id.loginSignup:


                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new SignupFragment());
                ft.commit();

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
                            cp.checkPassword(getActivity(), view, currentUser, eTPW.getText().toString(), view.getContext());
                            {
                            }

//                            Firebase fb = new Firebase("https://blinding-inferno-6351.firebaseio.com/").child("users/" + newUser.getUsername().toString().toLowerCase());

                            //  fm = getFragmentManager();
                            //  ft = fm.beginTransaction();
                            //   ft.replace(R.id.container, new GameFragment());
                            //    ft.commit();
                        }
                        if (match == false) {
                            Toast toat = Toast.makeText(view.getContext(), "No such user", Toast.LENGTH_SHORT);
                            toat.show();


                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }

                });
                break;
        }
    }



}







