package com.crazy.test.tools.sms.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

import com.crazy.test.tools.okhttp3.Updata;
import com.crazy.test.tools.sms.model.SMS;
import com.crazy.test.tools.utils.Setting;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsAnalytic extends Service {
    private static final String TAG = SmsAnalytic.class.getSimpleName();
    private static final String Coolpad_5217 = "Coolpad 5217";
    private static final String Coolpad_5200 = "Coolpad 5200";
    private static final String Coolpad_5200S = "Coolpad 5200S";
    private static final String Lenovo_A355e = "Lenovo A355e";
    private static final int BLANCE = 1; //余额
    private static final int CAPTCHA = 2;//验证码
    private static String Smskey;
    private static SMS sms;
    private static String phoneNumber;
    private static Updata updata;

    public SmsAnalytic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String smsString = intent.getStringExtra("sms");
        updata = new Updata(this);
        sms = new SMS(smsString);
        long time;
        if (((time = new Date().getTime() - sms.getDate()) < 180000) && (sms.getRead() != 1)) {
            getPhoneNumber();
            Log.i(TAG, "onStartCommand: "+phoneNumber+" 新短信:"+sms.string());
            switch (getSmsType()){
                case BLANCE:
                    String blance = analyticBlance();
                    Toast.makeText(this, phoneNumber+"、余额为："+blance, Toast.LENGTH_SHORT).show();
                    break;
                case CAPTCHA:
                    String captcha = analyticCaptcha();
                    Toast.makeText(this, phoneNumber+"、验证码为："+captcha, Toast.LENGTH_SHORT).show();
                    updata.smsreciver(phoneNumber,captcha);
                    break;
                default:
                    break;
            }

        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    //获取手机号码
    private void getPhoneNumber(){
        switch (sms.getMODEL()){
            case Coolpad_5200:
                phoneNumber = sms.getSub_id()==1? Setting.getSetting(this).get("CardTwoNumber"):Setting.getSetting(this).get("CardOneNumber");
                break;
            case Coolpad_5200S:
                break;
            case Coolpad_5217:
                break;
            case Lenovo_A355e:
                break;
            default:
                break;
        }
    }

    //获取短信类型
    private int getSmsType(){
        for (String key : blanceKey()){
            if(sms.getBody().contains(key)){
                Smskey = key;
                return BLANCE;
            }
        }
        for(String key : captchaKey()){
            if(sms.getBody().contains(key)){
                Smskey = key;
                return CAPTCHA;
            }
        }
        return -1;
    }

    private String[] blanceKey(){
        String[] keys = new String[]{"现金余额为","实时余额为","的账户余额为","普通话费余额","账户余额","可用余额","当前余额","话费余额","可用金额为","当前结余","普通余额","余额为","当前的余额为","余额","时为"};
        return keys;
    }

    private String[] captchaKey(){
        String[] keys = new String[]{"验证码","随机码","随机密码","动态密码"};
        return keys;
    }

    private String analyticBlance(){
        String balance = "";
        Pattern pattern = Pattern.compile(Smskey+".{0,6}\\d+\\.*\\d*元");
        Matcher matcher = pattern.matcher(sms.getBody());
        while(matcher.find()){
            balance =  matcher.group();
        }
        return balance;
    }

    private String analyticCaptcha(){
        String temp = "";
        String captcha="";
        Pattern pattern = Pattern.compile(Smskey+".{0,6}[a-z0-9A-Z]{4,6}");
        Matcher matcher = pattern.matcher(sms.getBody());
        while(matcher.find()){
            temp = matcher.group();
        }
        pattern = Pattern.compile("[a-z0-9A-Z]{4,6}");
        matcher = pattern.matcher(temp);
        while(matcher.find()){
            captcha =  matcher.group();
        }

        //为江苏移动短信验证码做适配
        if(captcha.equals("")){
            pattern = Pattern.compile("[0-9]{6}");
            matcher = pattern.matcher(sms.getBody());
            while (matcher.find()){
                captcha = matcher.group();
            }
        }
        return captcha;
    }

}
