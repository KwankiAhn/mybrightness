package com.example.kwankiahn.mybrightness;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
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

public class BrightnessDriverNotification{
    enum Level {
        LEVEL_25,
        LEVEL_50,
        LEVEL_100,
    }
    enum OffTime {
        _30SEC,
        _1MIN,
        _10MIN
    }
    final int mNotificationId = 9766;
    private RemoteViews contentView;
    private NotificationManager mNotifyMgr;
    private NotificationCompat.Builder mBuilder;
    public BrightnessDriverNotification(Context context) {
        mNotifyMgr = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
    }
    public void start(Context context) {
        createContentView(context);
        setButtonIconsAsDefault();
        createNotification(context);
        showNotification();
    }
    public void stop(Context context) {
        cancelNotification();
    }
    private void createNotification(Context context) {
        mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_stat_brightness_6)
                .setContent(contentView).setOngoing(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setCategory(Notification.CATEGORY_EVENT)
                    .setPriority(Notification.PRIORITY_MAX);
        }
        Intent level25 = new Intent(context, ButtonListener.class);
        level25.putExtra("LEVEL", "25");
        Intent level50 = new Intent(context, ButtonListener.class);
        level50.putExtra("LEVEL", "50");
        Intent level100 = new Intent(context, ButtonListener.class);
        level100.putExtra("LEVEL", "100");
        Intent levelAuto = new Intent(context, ButtonListener.class);
        levelAuto.putExtra("LEVEL", "AUTO");
        Intent powerOffTime = new Intent(context, ButtonListener.class);
        powerOffTime.putExtra("POWEROFFTIME", "TOGGLE");
        PendingIntent level25Intent = PendingIntent.getBroadcast(context, R.id.image_25, level25, 0);
        PendingIntent level50Intent = PendingIntent.getBroadcast(context, R.id.image_50, level50, 0);
        PendingIntent level100Intent = PendingIntent.getBroadcast(context, R.id.image_100, level100, 0);
        PendingIntent levelAutoIntent = PendingIntent.getBroadcast(context, R.id.image_auto, levelAuto, 0);
        PendingIntent powerOffTimeIntent = PendingIntent.getBroadcast(context, R.id.image_timeout, powerOffTime,0);
        contentView.setOnClickPendingIntent(R.id.image_25, level25Intent);
        contentView.setOnClickPendingIntent(R.id.image_50, level50Intent);
        contentView.setOnClickPendingIntent(R.id.image_100, level100Intent);
        contentView.setOnClickPendingIntent(R.id.image_auto, levelAutoIntent);
        contentView.setOnClickPendingIntent(R.id.image_timeout, powerOffTimeIntent);
    }
    private void showNotification() {
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    private void createContentView(Context context) {
        contentView = new RemoteViews(context.getPackageName(), R.layout.notification);
    }
    private void cancelNotification() {
        mNotifyMgr.cancel((mNotificationId));
    }
    private void setButtonIconsAsDefault() {
        contentView.setImageViewResource(R.id.image_25, R.mipmap.button_off);
        contentView.setImageViewResource(R.id.image_50, R.mipmap.button_off);
        contentView.setImageViewResource(R.id.image_100, R.mipmap.button_off);
        contentView.setImageViewResource(R.id.image_auto, R.drawable.unchecked);
        contentView.setImageViewResource(R.id.image_timeout, R.mipmap.ic_launcher);
    }

    public void updateButtonIconsAccordingToLevel(Context context, Level level, boolean isAuto, int timeout) {
        createContentView(context);
        setButtonIconsAsDefault();
        if (level == Level.LEVEL_25) {
            contentView.setImageViewResource(R.id.image_25, R.mipmap.button_on);
        } else if (level == Level.LEVEL_50) {
            contentView.setImageViewResource(R.id.image_25, R.mipmap.button_on);
            contentView.setImageViewResource(R.id.image_50, R.mipmap.button_on);
        } else if (level == Level.LEVEL_100) {
            contentView.setImageViewResource(R.id.image_25, R.mipmap.button_on);
            contentView.setImageViewResource(R.id.image_50, R.mipmap.button_on);
            contentView.setImageViewResource(R.id.image_100, R.mipmap.button_on);
        }
        if (isAuto)
            contentView.setImageViewResource(R.id.image_auto, R.drawable.checked);
        else
            contentView.setImageViewResource(R.id.image_auto, R.drawable.unchecked);

        switch(timeout) {
            case 30000:
                contentView.setImageViewResource(R.id.image_timeout, R.mipmap.button_time_1);
                break;
            case 60000:
                contentView.setImageViewResource(R.id.image_timeout, R.mipmap.button_time_2);
                break;
            case 600000:
                contentView.setImageViewResource(R.id.image_timeout, R.mipmap.button_time_3);
                break;
            default:
                contentView.setImageViewResource(R.id.image_timeout, R.mipmap.button_time_1);
                break;
        }
        createNotification(context);
        showNotification();
    }
}
