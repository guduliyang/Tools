package com.crazy.test.tools.timeTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.crazy.test.tools.sms.action.SendMessage;
import com.crazy.test.tools.utils.DateFormat;
import com.crazy.test.tools.utils.Setting;

public class TimeTaskService extends Service {
    private static final String TAG = TimeTaskService.class.getSimpleName();
    private Setting setting = Setting.getSetting(this);

    public TimeTaskService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: start TimeTaskService");
        Toast.makeText(this, DateFormat.format(System.currentTimeMillis()), Toast.LENGTH_SHORT).show();
        SendMessage sendMessage = new SendMessage(this);
        if(setting.get("to_one")!=""&&setting.get("to_one")!=null)
            sendMessage.getBlance(setting.get("to_one"),setting.get("order_one"),SendMessage.CDMA);
        if(setting.get("to_two")!=""&&setting.get("to_two")!=null)
            sendMessage.getBlance(setting.get("to_two"),setting.get("order_two"),SendMessage.GSM);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
