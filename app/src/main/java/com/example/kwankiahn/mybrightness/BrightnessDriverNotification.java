package com.example.kwankiahn.mybrightness;

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

    final int mNotificationId = 9766;
    private NotificationManager mNotifyMgr;
    private RemoteViews contentView;
    public BrightnessDriverNotification(Context context) {
        mNotifyMgr = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
    }
    public void start(Context context) {
        createContentView(context);
        initAllIcons();
        createNotification(context);
    }
    public void stop(Context context) {
        cancelNotification();
    }
    private void createNotification(Context context) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.smile).setContent(contentView).setOngoing(true);
        Intent level25 = new Intent(context, ButtonListener.class);
        level25.putExtra("LEVEL", "25");
        Intent level50 = new Intent(context, ButtonListener.class);
        level50.putExtra("LEVEL", "50");
        Intent level100 = new Intent(context, ButtonListener.class);
        level100.putExtra("LEVEL", "100");
        Intent levelAuto = new Intent(context, ButtonListener.class);
        levelAuto.putExtra("LEVEL", "AUTO");
        PendingIntent level25Intent = PendingIntent.getBroadcast(context, R.id.image_25, level25, 0);
        PendingIntent level50Intent = PendingIntent.getBroadcast(context, R.id.image_50, level50, 0);
        PendingIntent level100Intent = PendingIntent.getBroadcast(context, R.id.image_100, level100, 0);
        PendingIntent levelAutoIntent = PendingIntent.getBroadcast(context, R.id.image_auto, levelAuto, 0);
        contentView.setOnClickPendingIntent(R.id.image_25, level25Intent);
        contentView.setOnClickPendingIntent(R.id.image_50, level50Intent);
        contentView.setOnClickPendingIntent(R.id.image_100, level100Intent);
        contentView.setOnClickPendingIntent(R.id.image_auto, levelAutoIntent);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    private void createContentView(Context context) {
        contentView = new RemoteViews(context.getPackageName(), R.layout.notification);
    }
    private void cancelNotification() {
        mNotifyMgr.cancel((mNotificationId));
    }
    private void initAllIcons() {
        contentView.setImageViewResource(R.id.image_25, R.mipmap.ic_launcher);
        contentView.setImageViewResource(R.id.image_50, R.mipmap.ic_launcher);
        contentView.setImageViewResource(R.id.image_100, R.mipmap.ic_launcher);
        contentView.setImageViewResource(R.id.image_auto, R.drawable.unchecked);
    }

    public void setLevelIcons(Level level) {
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
}
