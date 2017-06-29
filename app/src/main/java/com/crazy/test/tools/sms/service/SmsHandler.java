package com.crazy.test.tools.sms.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.crazy.test.tools.sms.model.SMS;
import com.crazy.test.tools.utils.Setting;

/**
 * Created by ADMIN on 2017/6/28.
 */

public class SmsHandler extends Handler {
    private static final String TAG=SmsHandler.class.getSimpleName();
    private SMS sms;
    private Context context;
    private Setting setting;

    public SmsHandler(Context context) {
        this.context = context;
        setting = Setting.getSetting(context);
    }

    @Override
    public void handleMessage(Message msg) {
        //短信处理
        sms = (SMS)msg.obj;
        Log.i(TAG, "handleMessage: 收到短信：\n"+sms.toString());
    }
}
