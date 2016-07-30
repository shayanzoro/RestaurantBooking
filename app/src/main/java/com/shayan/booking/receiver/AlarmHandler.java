package com.shayan.booking.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shayan.booking.application.App;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.util.ShayanLogger;

import javax.inject.Inject;

/**
 * Created by Shayan on 7/30/16.
 * This receiver is responsible for clearing all bookings of the user every 10 minutes
 */
public class AlarmHandler extends BroadcastReceiver {

    private static final int INTERVAL = 10 * 60 * 1000;

    @Inject
    DataBaseManager dataBaseManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        App.get(context).getServiceComponent().inject(this);
        dataBaseManager.clearAllBookings();
    }

    public static void init(Context context) {
        cancelAlarm(context);
        setAlarm(context);
    }

    private static void setAlarm(Context context) {
        ShayanLogger.w("AlarmHandler", "Set Alarm !!!!!!!!!!");

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmHandler.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + INTERVAL, INTERVAL, pi);
    }

    private static void cancelAlarm(Context context) {
        ShayanLogger.d("AlarmHandler", "Cancel Alarm !!!!!!!!!!");
        Intent intent = new Intent(context, AlarmHandler.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
