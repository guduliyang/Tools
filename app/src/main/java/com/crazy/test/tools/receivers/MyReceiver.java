package com.crazy.test.tools.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;

import com.crazy.test.tools.sms.action.SmsRead;
import com.crazy.test.tools.sms.service.SmsAnalytic;
import com.crazy.test.tools.sms.service.SmsService;
import com.crazy.test.tools.utils.Setting;

import java.security.Provider;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = MyReceiver.class.getSimpleName();
    private static final String ACTION_BOOT_COMPLETED = Intent.ACTION_BOOT_COMPLETED;
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private static final String SMS_DELIVER_ACTION = "android.provider.Telephony.SMS_DELIVER";
    private Setting setting;
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        setting = Setting.getSetting(context);
        Log.i(TAG, "onReceive: "+intent.getAction());
        switch (intent.getAction()){
            case ACTION_BOOT_COMPLETED: //开机广播
                Intent smsIntent = new Intent(context, SmsService.class);
                String autoStart = setting.get("autoStart");
                if(intent.getAction().equals(ACTION_BOOT_COMPLETED)&& "true".equals(autoStart)){
                    context.startService(smsIntent);
                    Setting.getSetting(context).put("smsListen","true");
                }
                break;
            case SMS_RECEIVED_ACTION: //收到短信广播
                Intent intentSms  = new Intent(context,SmsAnalytic.class);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intentSms.putExtra("sms",SmsRead.getLastOne(context).toString());
                context.startService(intentSms);
                break;
            case SMS_DELIVER_ACTION:
                break;
        }


    }
}
