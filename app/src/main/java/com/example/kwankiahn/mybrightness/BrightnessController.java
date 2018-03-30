package com.example.kwankiahn.mybrightness;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by pray4 on 2017-10-31.
 */

public class BrightnessController {
    public enum STEP {
        LOW,
        MID,
        HIGH
    }
    private int[] stepValue = { (int)(255 * 0.3), (int)(255 * 0.5), 255 };
    public int GetStepBrightness(STEP step) {
        return stepValue[step.ordinal()];
    }
    public void setBrightnessValue(Context context, double v) {
        android.provider.Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, (int)v);
    }
    public int getBrightnessValue(Context context) {
        int value = -1;
        try {
            value = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
    public void toggleAuto(Context context) {
        try {
            int isAuto = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, (isAuto + 1) % 2);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean isAutoMode(Context context) {
        boolean isAuto = false;
        try {
            isAuto = (Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) ? true : false;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        } finally {
            return isAuto;
        }
    }
}
