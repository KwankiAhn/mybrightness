package com.example.kwankiahn.mybrightness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 2017-10-11.
 */

public class ButtonListener extends BroadcastReceiver {
    private static int[] stepValue = { (int)(255 * 0.3), (int)(255 * 0.5), 255 };
    BrightnessController controller;
    public ButtonListener() {
        super();
        controller = new BrightnessController();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        BrightnessDriverNotification driverNotification = new BrightnessDriverNotification(context);
        Log.d(MainActivity.TAG, "onReceive");
        if (intent.getStringExtra("LEVEL").equals("25")) {
            Log.d(MainActivity.TAG, "LEVEL 25");
            controller.setBrightnessValue(context, stepValue[0]);
            driverNotification.updateButtonIconsAccordingToLevel(context, BrightnessDriverNotification.Level.LEVEL_25, controller.isAutoMode(context));
        } else if (intent.getStringExtra("LEVEL").equals("50")) {
            Log.d(MainActivity.TAG, "LEVEL 50");
            controller.setBrightnessValue(context, stepValue[1]);
            driverNotification.updateButtonIconsAccordingToLevel(context, BrightnessDriverNotification.Level.LEVEL_50, controller.isAutoMode(context));
        } else if (intent.getStringExtra("LEVEL").equals("100")) {
            Log.d(MainActivity.TAG, "LEVEL 100");
            controller.setBrightnessValue(context, stepValue[2]);
            driverNotification.updateButtonIconsAccordingToLevel(context, BrightnessDriverNotification.Level.LEVEL_100, controller.isAutoMode(context));
        } else if (intent.getStringExtra("LEVEL").equals("AUTO")) {
            Log.d(MainActivity.TAG, "LEVEL AUTO");
            controller.toggleAuto(context);
            int level = controller.getBrightnessValue(context);
            driverNotification.updateButtonIconsAccordingToLevel(context, getBrightnessLevelbByValue(level), controller.isAutoMode(context));
        }
    }

    private BrightnessDriverNotification.Level getBrightnessLevelbByValue(int value) {
        if (value == stepValue[0]) {
            return BrightnessDriverNotification.Level.LEVEL_25;
        } else if (value == stepValue[1]) {
            return BrightnessDriverNotification.Level.LEVEL_50;
        } else if (value == stepValue[2]) {
            return BrightnessDriverNotification.Level.LEVEL_100;
        }
        return BrightnessDriverNotification.Level.LEVEL_25;
    }
}
