package mattias.andersson.onky;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Mattias on 15-07-15.
 */
public class CheckPassword {


    final Firebase fbCheck = CONSTANTS.fbRef;
    public boolean match;
    LoginFragment lf = new LoginFragment();

    public void checkPassword(String currentUser, final String enteredPassword, final Context context) {
        Log.i("we're in", "checkPassword");
        fbCheck.child("users/" + currentUser + "/userInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //  Iterable<DataSnapshot> fbUserCheck = dataSnapshot.getChildren();
                //  for (DataSnapshot fbUC : fbUserCheck) {
                //  Log.i("check", " current dataSnapshot is " + dataSnapshot.getRef());
                //      Log.i("gbuc get key ", fbUC.getKey());
                //      Log.i("gbuc get ref ", fbUC.getRef().toString());
                //       Log.i("gbuc get value ", fbUC.getValue().toString());
                Log.i("entered password", enteredPassword);
                Log.i("firebase password", dataSnapshot.child("password").getValue().toString());
                if (enteredPassword.equals(dataSnapshot.child("password").getValue().toString())) {

                    // currentUser = fbUC.getKey().toString();
                    //    Log.i("login ", "datasnapshot is +" + fbUC.getValue());
                    //     Log.i("login ", "pw is: " + pw);
                    match = true;
                    lf.doLogin(context);

//                    final Firebase fbCheckPw = CONSTANTS.fbRef.child("users/" + currentUser);
                    //                   fbCheckPw.addListenerForSingleValueEvent(new ValueEventListener() {
                    //                     @Override
                    //                    public void onDataChange(DataSnapshot dataSnapshot) {
                    //                       pw = dataSnapshot.child("userInfo/password").getValue().toString();
                    //                      Log.i("user" + dataSnapshot.toString() + "pw is", pw);
                    //                 }
//
                    //                       @Override
                    //                     public void onCancelled(FirebaseError firebaseError) {
//
                    //                      }
                    //                  });
                    //                   break;

                    {

                    }
                } else {
                    Toast toat = Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT);
                    toat.show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
