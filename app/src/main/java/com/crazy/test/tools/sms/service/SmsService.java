package com.crazy.test.tools.sms.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.crazy.test.tools.sms.handlers.SmsHandler;
import com.crazy.test.tools.utils.CheckService;
import com.crazy.test.tools.utils.Setting;

import org.json.JSONArray;

public class SmsService extends Service {
    private static final String TAG = SmsService.class.getSimpleName();
    private SmsObserver observer;
    private Handler handler;
    public SmsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "SmsService 短信监控启动了！", Toast.LENGTH_SHORT).show();
        ContentResolver resolver = getContentResolver();
        observer = new SmsObserver(this,new SmsHandler());
        resolver.registerContentObserver(Uri.parse("content://sms"),true,observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getContentResolver().unregisterContentObserver(observer);
        Toast.makeText(this, "SmsService 已关闭短信监控！", Toast.LENGTH_SHORT).show();
    }
}
