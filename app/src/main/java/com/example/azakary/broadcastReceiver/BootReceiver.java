package com.example.azakary.broadcastReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.azakary.utils.Constance;
import com.example.azakary.utils.PrefManager;

public class BootReceiver extends BroadcastReceiver {
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        PrefManager.initPrefs(context);
        Constance.startAlarm(context);
        if (PrefManager.getBoolean("notify", true)){
            Constance.notify(context,true);
        }
        /**Schedule your Alarm Here**/
        Toast.makeText(context, "Booting Completed", Toast.LENGTH_LONG).show();
    }
}
