package com.crazy.test.tools.okhttp3;

import android.content.Context;
import android.util.Log;

import com.crazy.test.tools.utils.Setting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by ADMIN on 2017/6/29.
 */

public class Updata {
    private static final String TAG= Updata.class.getSimpleName();
    private static Setting setting;
    private static Context context;
    public Updata(Context context){
        this.context = context;
        setting = Setting.getSetting(context);
    }

    public void smsreciver(String phone,String code){
        HttpClient httpClient = new HttpClient();
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);
        try {
            Response response = httpClient.post(setting.get("smsreciver"),map);
            Log.i(TAG, "smsreciver: "+phone+"上传短信验证码返回："+response.body().string());
        } catch (IOException e) {
            Log.e(TAG, "smsreciver: "+phone+"上传短信验证码失败！"+ e.getMessage());
        }
    }

    public void updataBlance(String phone,String blance){
        HttpClient httpClient = new HttpClient();
        Map<String,String> map = new HashMap<>();
        map.put("phoneNumber",phone);
        map.put("balance",blance);
        try {
            Response response = httpClient.post(setting.get("updata"),map);
            Log.i(TAG, "smsreciver: "+phone+"更新余额信息返回："+response.body().string());
        } catch (IOException e) {
            Log.e(TAG, "smsreciver: "+phone+"更新余额失败！"+ e.getMessage());
        }
    }
}
