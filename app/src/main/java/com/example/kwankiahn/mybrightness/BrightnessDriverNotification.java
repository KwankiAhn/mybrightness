package com.example.kwankiahn.mybrightness;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by user on 2017-10-09.
 */

public class BrightnessDriverNotification {

    public void start(Context context) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.smile)
            .setContentTitle("My notification").setContentText("Hello World!").setOngoing(true);
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        // Sets an ID for the notification
        int mNotificationId = 999;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
    public void stop(Context context) {
        int mNotificationId = 999;
        NotificationManager mNotifyMgr =
                (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.cancel((mNotificationId));
    }
}
