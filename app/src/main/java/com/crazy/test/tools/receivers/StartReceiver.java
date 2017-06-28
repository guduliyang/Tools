package com.crazy.test.tools.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.crazy.test.tools.sms.service.SmsService;
import com.crazy.test.tools.utils.Setting;

public class StartReceiver extends BroadcastReceiver {
    private static final String TAG = StartReceiver.class.getSimpleName();
    private static final String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
    private Setting setting;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent smsIntent = new Intent(context, SmsService.class);
        setting = Setting.getSetting(context);
        String autoStart = setting.get("autoStart");
        if(intent.getAction().equals(BOOT_COMPLETED)&& "true".equals(autoStart)){
            context.startService(smsIntent);
            Setting.getSetting(context).put("smsListen","true");
        }
    }
}
