package com.crazy.test.tools.timeTask;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ADMIN on 2017/6/30.
 */

public class TimeTask {
    private static Context context;
    private static Intent intent;
    private static PendingIntent pendingIntent;
    private static AlarmManager alarmManager;

    public static void start(Context context,long intervalTime,Class cls){
        intent = new Intent(context,cls);
        pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),intervalTime,pendingIntent);
        Toast.makeText(context, "定时任务已开启", Toast.LENGTH_SHORT).show();
    }

    public static void stop(Context context,Class cls){
        intent = new Intent(context,cls);
        pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(context, "定时任务已关闭", Toast.LENGTH_SHORT).show();
    }
}
