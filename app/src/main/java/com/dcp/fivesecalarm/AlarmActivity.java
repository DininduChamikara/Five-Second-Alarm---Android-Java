package com.dcp.fivesecalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

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
        // For example, you can schedule the alarm to ring again after a certain time
        // You can use AlarmManager to schedule the alarm again after a specific interval
        finish(); // Finish the activity
    }
}