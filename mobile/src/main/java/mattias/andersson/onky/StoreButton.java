package mattias.andersson.onky;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import mattias.andersson.onky.helper.CONSTANTS;

/**
 * Created by Hussam on 7/30/2015.
 */
public class StoreButton extends ImageButton implements View.OnClickListener {

    public StoreButton(Context context) {
        super(context);
    }

    @Override
    public void onClick(View view) {

        Log.i("hej","hej");
    }
}