package mattias.andersson.onky;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
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

    int mouse,upgradeLvl,maxLvl;
    String toolTip;
    public StoreButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        System.out.println("Painting content");
        Paint paint  = new Paint(Paint.LINEAR_TEXT_FLAG);
        paint.setColor(0x0);
        paint.setTextSize(12.0F);
        System.out.println("Drawing text");
        canvas.drawText("Hello World in custom view", 100, 100, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Log.d("Hello Android", "Got a touch event: " + event.getAction());
        return super.onTouchEvent(event);

    }

    @Override
    public void onClick(View v) {

    }
}

