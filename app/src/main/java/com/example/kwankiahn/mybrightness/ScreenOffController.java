package com.example.kwankiahn.mybrightness;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by pray4 on 2017-12-25.
 */

public class ScreenOffController {
    private void setScreenOffTime(Context context, int militime) {
        android.provider.Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_OFF_TIMEOUT, militime);
    }
    public void toggleOffTime(Context context) {
        int time = 0;
        int nextTime = 0;
        try {
            time = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_OFF_TIMEOUT);
            switch (time) {
                case 30000:
                    nextTime = 60000;
                    break;
                case 60000:
                    nextTime = 600000;
                    break;
                case 600000:
                    nextTime = 30000;
                    break;
                default:
                    nextTime = 30000;
                    break;
            }
            setScreenOffTime(context, nextTime);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int getOffTime(Context context) {
        int time = 0;
        try {
            time = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return time;
    }
}
