package com.example.kwankiahn.mybrightness;

/**
 * Created by user on 2017-10-09.
 */

public class BrightnessDriverNotification {
    private enum NotificatoinStatus {
        OFF,
        ON,
        ERROR
    }
    private NotificatoinStatus notificationStatus;
    public BrightnessDriverNotification() {
        // TODO : load from non valotile
        notificationStatus = NotificatoinStatus.OFF;
    }
    public void toggle() {
        if (NotificatoinStatus.OFF == notificationStatus) {
            startUp();
        } else if (NotificatoinStatus.ON == notificationStatus) {
            stop();
        } else {
            throw new RuntimeException("Invalid notification status");
        }
    }
    private void startUp() {

    }
    private void stop() {

    }
}
