package com.crazy.test.tools.sms.action;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by ADMIN on 2017/6/29.
 */

public class SendMessage {
    private static final String TAG = SendMessage.class.getSimpleName();
    private static final String Coolpad_5217 = "Coolpad 5217";
    private static final String Coolpad_5200 = "Coolpad 5200";
    private static final String Coolpad_5200S = "Coolpad 5200S";
    private static final String ACTION_SENT = "com.examples.sms.SENT";
    private static final String ACTION_DELIVERED = "com.examples.sms.DELIVERED";
    public static final int Coolpad_5200_CDMA = 0;
    public static final int Coolpad_5200_GSM = 1;
    public static final boolean CDMA = true;
    public static final boolean GSM = false;
    private Context context;
    private PendingIntent sIntent;
    private PendingIntent dIntent;

    public SendMessage(Context context){
        this.context = context;
    }

    public boolean Coolpad_5200_send(String number,String smsBody,int witch){
        sIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_SENT), 0);
        dIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_DELIVERED), 0);
        SmsManager smsManager = SmsManager.getDefault();
        Class[] smsManagerPamas = {String.class, String.class, String.class, PendingIntent.class, PendingIntent.class, int.class};
        try {
            Method method = smsManager.getClass().getDeclaredMethod("sendTextMessage", smsManagerPamas);
            method.setAccessible(true);
            if(Coolpad_5200_CDMA==witch){
                method.invoke(smsManager, number, null, smsBody, sIntent, dIntent, Coolpad_5200_CDMA);
            }else if(Coolpad_5200_GSM==witch){
                method.invoke(smsManager, number, null, smsBody, sIntent, dIntent, Coolpad_5200_GSM);
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Coolpad_5200: 发送短信失败！"+e.getMessage() );
            return false;
        }
    }

    public boolean getBlance(String number,String smsBody,boolean CDMA){
        int temp;
        switch (Build.MODEL){
            case Coolpad_5200:
                temp = CDMA?Coolpad_5200_CDMA:Coolpad_5200_GSM;
                Coolpad_5200_send(number,smsBody,temp);
                break;
            case Coolpad_5217:
                temp = CDMA?Coolpad_5200_CDMA:Coolpad_5200_GSM;
                Coolpad_5200_send(number,smsBody,temp);
                break;
        }
        return true;
    }
}
