package com.example.kwankiahn.mybrightness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 2017-10-11.
 */

public class ButtonListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MYBRIGHTNESS", "onReceive");
        if (intent.getStringExtra("LEVEL").equals("25")) {
            Log.d("MYBRIGHTNESS", "LEVEL 25");
        } else if (intent.getStringExtra("LEVEL").equals("50")) {
            Log.d("MYBRIGHTNESS", "LEVEL 50");
        } else if (intent.getStringExtra("LEVEL").equals("100")) {
            Log.d("MYBRIGHTNESS", "LEVEL 100");
        } else if (intent.getStringExtra("LEVEL").equals("AUTO")) {
            Log.d("MYBRIGHTNESS", "LEVEL AUTO");
        }
    }
}
