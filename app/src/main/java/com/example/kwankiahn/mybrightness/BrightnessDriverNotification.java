package com.example.kwankiahn.mybrightness;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by user on 2017-10-09.
 */

public class BrightnessDriverNotification implements DialogInterface.OnClickListener{
    enum Level {
        LEVEL_25,
        LEVEL_50,
        LEVEL_100,
    }

    private static String TAG = "DriverNotification";
    final int mNotificationId = 9766;
    private NotificationManager mNotifyMgr;
    private RemoteViews contentView;
    public BrightnessDriverNotification(Context context) {
        mNotifyMgr = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
    }
    public void start(Context context) {
        contentView = new RemoteViews(context.getPackageName(), R.layout.notification);
        initIcons();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.smile).setContent(contentView).setOngoing(true);
//        Intent resultIntent = new Intent(context, MainActivity.class);
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.setContentIntent(resultPendingIntent);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
    public void stop(Context context) {
        mNotifyMgr.cancel((mNotificationId));
    }

    private void initIcons() {
        contentView.setImageViewResource(R.id.image_25, R.mipmap.ic_launcher);
        contentView.setImageViewResource(R.id.image_50, R.mipmap.ic_launcher);
        contentView.setImageViewResource(R.id.image_100, R.mipmap.ic_launcher);
        contentView.setImageViewResource(R.id.image_auto, R.drawable.unchecked);
    }
    private void initClickEvent(Context context) {

    }
    private void setLevelIcons(Level level) {
        if (level == Level.LEVEL_25) {
            contentView.setImageViewResource(R.id.image_25, R.drawable.red_dot);
        } else if (level == Level.LEVEL_50) {
            contentView.setImageViewResource(R.id.image_25, R.drawable.red_dot);
            contentView.setImageViewResource(R.id.image_50, R.drawable.red_dot);
        } else if (level == Level.LEVEL_100) {
            contentView.setImageViewResource(R.id.image_25, R.drawable.red_dot);
            contentView.setImageViewResource(R.id.image_50, R.drawable.red_dot);
            contentView.setImageViewResource(R.id.image_100, R.drawable.red_dot);
        }
    }
    private void setBrighenessLevel(Level level) {
        if (level == Level.LEVEL_25) {

        } else if (level == Level.LEVEL_50) {

        } else if (level == Level.LEVEL_100) {

        }
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Log.d(TAG, "onClick()");
        switch(i) {
            case R.id.image_25:
                initIcons();
                setLevelIcons(Level.LEVEL_25);
                setBrighenessLevel(Level.LEVEL_25);
                break;
            case R.id.image_50:
                initIcons();
                setLevelIcons(Level.LEVEL_50);
                setBrighenessLevel(Level.LEVEL_50);
                break;
            case R.id.image_100:
                initIcons();
                setLevelIcons(Level.LEVEL_100);
                setBrighenessLevel(Level.LEVEL_100);
                break;
            case R.id.image_auto:

                break;
        }
    }
}
