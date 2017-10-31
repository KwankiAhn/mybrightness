package com.example.kwankiahn.mybrightness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 2017-10-11.
 */

public class ButtonListener extends BroadcastReceiver {
    BrightnessController controller;
    public ButtonListener() {
        super();
        controller = new BrightnessController();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        BrightnessDriverNotification notification = new BrightnessDriverNotification(context);
        Log.d(MainActivity.TAG, "onReceive");
        if (intent.getStringExtra("LEVEL").equals("25")) {
            Log.d(MainActivity.TAG, "LEVEL 25");
            controller.setBrightness(context, 255 * 0.25);
            notification.setLevelIcons(BrightnessDriverNotification.Level.LEVEL_25);
        } else if (intent.getStringExtra("LEVEL").equals("50")) {
            Log.d(MainActivity.TAG, "LEVEL 50");
            controller.setBrightness(context, 255 * 0.5);
            notification.setLevelIcons(BrightnessDriverNotification.Level.LEVEL_50);
        } else if (intent.getStringExtra("LEVEL").equals("100")) {
            Log.d(MainActivity.TAG, "LEVEL 100");
            controller.setBrightness(context, 255);
            notification.setLevelIcons(BrightnessDriverNotification.Level.LEVEL_100);
        } else if (intent.getStringExtra("LEVEL").equals("AUTO")) {
            Log.d(MainActivity.TAG, "LEVEL AUTO");
            controller.toggleAuto(context);
        }
    }
}
