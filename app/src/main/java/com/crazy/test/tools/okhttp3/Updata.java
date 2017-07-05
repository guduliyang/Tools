package com.crazy.test.tools.okhttp3;

import android.content.Context;
import android.util.Log;

import com.crazy.test.tools.utils.DateFormat;
import com.crazy.test.tools.utils.Setting;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ADMIN on 2017/6/29.
 */

public class Updata {
    private static final String TAG = Updata.class.getSimpleName();
    private static Setting setting;
    private static Context context;

    public Updata(Context context) {
        this.context = context;
        setting = Setting.getSetting(context);
    }

    public void smsreciver(String phone, String code) {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: 请求地址：" + call.request().url());
                Log.e(TAG, "onFailure: 请求参数：" + call.request().body().toString());
                Log.e(TAG, "smsreciver: 上传短信验证码失败！" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "smsreciver: 上传短信验证码返回：" + response.body().string());
            }
        };
        HttpClient httpClient = new HttpClient();
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", code);
        httpClient.postAsyn(callback, setting.get("smsreciver"), map);
    }

    public void updataBlance(String phone, String blance) {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: 请求地址：" + call.request().url());
                Log.e(TAG, "onFailure: 请求参数：" + call.request().body().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "smsreciver:更新余额信息返回：" + response.body().string());
            }
        };
        HttpClient httpClient = new HttpClient();
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phone);
        map.put("balance", blance);
        map.put("balanceTime", DateFormat.format(new Date().getTime()));
        httpClient.postAsyn(callback, setting.get("updata"), map);

    }

    public void upataPwd(String phone, String password) {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: 请求地址：" + call.request().url());
                Log.e(TAG, "onFailure: 请求参数：" + call.request().body().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "upataPwd:更新密码返回：" + response.body().string());
            }
        };
        HttpClient httpClient = new HttpClient();
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phone);
        map.put("pwd", password);
        httpClient.postAsyn(callback,setting.get("updata"), map);

    }
}
