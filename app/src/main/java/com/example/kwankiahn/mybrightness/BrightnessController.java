package com.example.kwankiahn.mybrightness;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by pray4 on 2017-10-31.
 */

public class BrightnessController {
    public void setBrightness(Context context, double v) {
        android.provider.Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, (int)v);
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
