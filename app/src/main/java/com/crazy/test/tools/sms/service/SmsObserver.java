package com.crazy.test.tools.sms.service;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.crazy.test.tools.sms.action.SmsRead;
import com.crazy.test.tools.sms.model.SMS;

/**
 * Created by ADMIN on 2017/6/28.
 */

public class SmsObserver extends ContentObserver{
    private static final String TAG = SmsObserver.class.getSimpleName();
    private Context context;
    private Handler handler;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        SMS sms = SmsRead.getLastOne(context);
        if(sms.getRead()==0){
            Log.i(TAG, "onChange: 收到一条新短信！");

            Message msg = handler.obtainMessage();
            msg.obj = sms;
            handler.sendMessage(msg);
        }
    }
}
