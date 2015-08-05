package mattias.andersson.onky.helper;

import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alrik on 2015-08-04.
 */
final public class StockMarket {
public static ArrayList<Integer> prices = new ArrayList<Integer>();
    public static HashMap<String,Float> globalPrices= new  HashMap<String,Float>();

    private StockMarket() {
        globalPrices.put("discount",0.5f);
    }
    public static void getGlobalPrices(){
        globalPrices.put("test",888f);
        globalPrices.put("helo world",69f);
        Log.i("StockPrices", "hashMap" + globalPrices.keySet());
        Log.i("StockPrices","hashMap"+globalPrices.values());
    }
}
