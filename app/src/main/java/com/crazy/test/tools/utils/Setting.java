package com.crazy.test.tools.utils;

import android.content.Context;
import android.util.Log;

import com.crazy.test.tools.okhttp3.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ADMIN on 2017/6/27.
 */

public class Setting{
    private static final String TAG = Setting.class.getSimpleName();
    private static Config config;

    public Setting(Context context){
        config = Config.getConfig(context);
        try {
            config.get("ansy");
        }catch (Exception e){
            config.ansySetting();
        }
    }

    //获取info界面配置信息
    public static Map<String,String> getInfo(){
        Map<String,String> objectMap = new HashMap<>();
        objectMap.put("smsListen",config.get("smsListen"));
        objectMap.put("autoStart",config.get("autoStart"));
        objectMap.put("autoBlance",config.get("autoBlance"));
        objectMap.put("CardOneNumber",config.get("CardOneNumber"));
        objectMap.put("CardTwoNumber",config.get("CardTwoNumber"));
        objectMap.put("to_one",config.get("to_one"));
        objectMap.put("to_two",config.get("to_two"));
        objectMap.put("order_one",config.get("order_one"));
        objectMap.put("order_two",config.get("order_two"));
        return objectMap;
    }

    //更新配置文件
    public static boolean put(String key,String value){
        return config.put(key,value);
    }

    //查询卡一信息
    public static boolean submit_one(String phoneNumber){
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String url = call.request().url().toString();
                String method = call.request().method();
                Log.e(TAG, "onFailure: 获取卡1信息失败！"+"\n method:"+method+"\n url:"+url+"\n"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                config.put("cardOneData",body);
            }
        };
        HttpClient client = new HttpClient();
        Map map = new HashMap();
        map.put("PhoneNumber",phoneNumber);
        client.postAsyn(callback,config.get("getPhoneTask"), map);
        config.put("CardOneNumber",phoneNumber);
        return true;
    }

    //查询卡二信息
    public static boolean submit_two(String phoneNumber){
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String url = call.request().url().toString();
                String method = call.request().method();
                String RequestBody = call.request().body().toString();
                Log.e(TAG, "onFailure: 获取卡2信息失败！"+"\n method:"+method+"\n url"+url+"\n RequestBody"+ RequestBody);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                config.put("cardTwoData",body);
            }
        };
        HttpClient client = new HttpClient();
        Map map = new HashMap();
        map.put("PhoneNumber",phoneNumber);
        client.postAsyn(callback,config.get("getPhoneTask"), map);
        config.put("CardTwoNumber",phoneNumber);
        return true;
    }

    //获取卡一信息
    public static Map<String,String> getCardOne(){
        Map<String,String> map = new HashMap<>();
        try {
            JSONObject object = new JSONObject(config.get("cardOneData"));
            Iterator iterator = object.keys();
            String key;
            String value;
            while (iterator.hasNext()){
                key = (String) iterator.next();
                value = (String) object.get(key);
                map.put(key,value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    //获取卡二信息
    public static Map<String,String> getCardTwo(){
        Map<String,String> map = new HashMap<>();
        try {
            JSONObject object = new JSONObject(config.get("cardTwoData"));
            Iterator iterator = object.keys();
            String key;
            String value;
            while (iterator.hasNext()){
                key = (String) iterator.next();
                value = (String) object.get(key);
                map.put(key,value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

}
