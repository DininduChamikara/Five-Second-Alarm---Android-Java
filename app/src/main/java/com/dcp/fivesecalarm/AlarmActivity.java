package com.dcp.fivesecalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private Ringtone alarmRingtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Play default alarm sound
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            // If default alarm sound is not available, use notification sound
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        alarmRingtone = RingtoneManager.getRingtone(this, alarmUri);
        if (alarmRingtone != null) {
            alarmRingtone.play();
        }
    }

    public void stopAlarm(View view) {
        // Stop the alarm sound
        if (alarmRingtone != null && alarmRingtone.isPlaying()) {
            alarmRingtone.stop();
        }
        finish(); // Finish the activity
    }

    public void snoozeAlarm(View view) {
        // Snooze functionality
        // Implement snooze logic here
        // Stop the alarm sound
        if (alarmRingtone != null && alarmRingtone.isPlaying()) {
            alarmRingtone.stop();
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE);

        // Set the alarm to start after 5 seconds
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        finish(); // Finish the activity
    }
}