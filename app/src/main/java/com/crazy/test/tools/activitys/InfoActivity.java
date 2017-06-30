package com.crazy.test.tools.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crazy.test.tools.R;
import com.crazy.test.tools.sms.action.SendMessage;
import com.crazy.test.tools.utils.Setting;

/**
 * Created by ADMIN on 2017/6/26.
 */

public class InfoActivity implements View.OnClickListener{
    private static final String TAG=InfoActivity.class.getSimpleName();
    private Context context;
    private Setting setting;
    private ViewPager viewPager;
    private Button test;
    private Button blanceOne;
    private Button blanceTwo;
    private SendMessage sendMessage;

    private EditText intervalBlance;
    private Button blanceButton;

    public void load(Context context, ViewPager viewPager){
        this.viewPager = viewPager;
        this.context = context;
        setting = Setting.getSetting(context);
        sendMessage = new SendMessage(context);
        initView();
        initEvent();
    }

    private void initView(){
        test = (Button)viewPager.findViewById(R.id.test);
        blanceOne = (Button)viewPager.findViewById(R.id.blanceOne);
        blanceTwo = (Button)viewPager.findViewById(R.id.blanceTwo);

        //频率设置
        intervalBlance = (EditText)viewPager.findViewById(R.id.intervalBlance);
        blanceButton = (Button)viewPager.findViewById(R.id.blanceButton);
        try{
            String temp=null;
            if((temp =  setting.get("intervalBlance"))!=null){
                intervalBlance.setText(temp);
                temp=null;
            }
        }catch (Exception e){
            Log.e(TAG, "initView: "+e.getMessage());
        }



    }

    private void initEvent(){
        test.setOnClickListener(this);
        blanceOne.setOnClickListener(this);
        blanceTwo.setOnClickListener(this);
        blanceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test:
                Intent intent = new Intent("android.provider.Telephony.SMS_RECEIVED");
                context.sendBroadcast(intent);
                break;
            case R.id.blanceOne:
                if(setting.get("to_one")!=""&&setting.get("to_one")!=null)
                sendMessage.getBlance(setting.get("to_one"),setting.get("order_one"),SendMessage.CDMA);
                break;
            case R.id.blanceTwo:
                if(setting.get("to_two")!=""&&setting.get("to_two")!=null)
                sendMessage.getBlance(setting.get("to_two"),setting.get("order_two"),SendMessage.GSM);
                break;
            case R.id.blanceButton:
                String time = intervalBlance.getText().toString().trim();
                setting.put("intervalBlance",time);
                break;
        }
    }

}
