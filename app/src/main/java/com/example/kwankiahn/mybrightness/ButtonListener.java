package com.example.kwankiahn.mybrightness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 2017-10-11.
 */

public class ButtonListener extends BroadcastReceiver {

    BrightnessController brightnessController;
    ScreenOffController screenOffController;
    public ButtonListener() {
        super();
        brightnessController = new BrightnessController();
        screenOffController = new ScreenOffController();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        BrightnessDriverNotification driverNotification = new BrightnessDriverNotification(context);
        Log.d(MainActivity.TAG, "onReceive");
        String level = intent.getStringExtra("LEVEL");
        String powerOffTime = intent.getStringExtra("POWEROFFTIME");
        if (null != level) {
            if (level.equals("25")) {
                Log.d(MainActivity.TAG, "LEVEL 25");
                brightnessController.setBrightnessValue(context, brightnessController.GetStepBrightness(BrightnessController.STEP.LOW));
            } else if (level.equals("50")) {
                Log.d(MainActivity.TAG, "LEVEL 50");
                brightnessController.setBrightnessValue(context, brightnessController.GetStepBrightness(BrightnessController.STEP.MID));
            } else if (level.equals("100")) {
                Log.d(MainActivity.TAG, "LEVEL 100");
                brightnessController.setBrightnessValue(context, brightnessController.GetStepBrightness(BrightnessController.STEP.HIGH));
            } else if (level.equals("AUTO")) {
                Log.d(MainActivity.TAG, "LEVEL AUTO");
                brightnessController.toggleAuto(context);
            }
        }
        if (null != powerOffTime) {
            if (powerOffTime.equals("TOGGLE")) {
                Log.d(MainActivity.TAG, "POWEROFFTIME TOGGLE");
                screenOffController.toggleOffTime(context);
            }
        }
        int levelValue = brightnessController.getBrightnessValue(context);
        driverNotification.updateButtonIconsAccordingToLevel(context, getBrightnessLevelbByValue(levelValue), brightnessController.isAutoMode(context), screenOffController.getOffTime(context));
    }

    public BrightnessDriverNotification.Level getBrightnessLevelbByValue(int value) {
        if (value == brightnessController.GetStepBrightness(BrightnessController.STEP.LOW)) {
            return BrightnessDriverNotification.Level.LEVEL_25;
        } else if (value == brightnessController.GetStepBrightness(BrightnessController.STEP.MID)) {
            return BrightnessDriverNotification.Level.LEVEL_50;
        } else if (value == brightnessController.GetStepBrightness(BrightnessController.STEP.HIGH)) {
            return BrightnessDriverNotification.Level.LEVEL_100;
        }
        return BrightnessDriverNotification.Level.LEVEL_25;
    }
}
