package com.example.kwankiahn.mybrightness;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class BrightnessDriverService extends Service {
    public BrightnessDriverService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("MYBRIGHTNESS", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYBRIGHTNESS", "onStartCommand : " + intent.toString());
        String level = intent.getStringExtra("LEVEL");
        Log.d("MYBRIGHTNESS", "level : " + level);
        if (null != level && false == level.isEmpty()) {
            if (level.equals("25")) {
                Log.d("MYBRIGHTNESS","LEVEL 25");
            } else if (level.equals("50")) {
                Log.d("MYBRIGHTNESS","LEVEL 50");
            } else if (level.equals("100")) {
                Log.d("MYBRIGHTNESS","LEVEL 100");
            } else if (level.equals("AUTO")) {
                Log.d("MYBRIGHTNESS","LEVEL AUTO");
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("MYBRIGHTNESS", "onDestroy");
        super.onDestroy();
    }
}
