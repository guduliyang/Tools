package com.crazy.test.tools.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;
import com.crazy.test.tools.R;
import com.crazy.test.tools.sms.service.SmsService;
import com.crazy.test.tools.utils.CheckService;
import com.crazy.test.tools.utils.Setting;
import java.util.Map;

/**
 * Created by ADMIN on 2017/6/26.
 */

public class SettingActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG=SettingActivity.class.getSimpleName();
    private Context context;
    private Setting setting;

    private ViewPager viewPager;
    private ToggleButton smsListen;
    private ToggleButton autoStart;
    private ToggleButton autoBlance;
    private static EditText CardOneNumber;
    private static EditText CardTwoNumber;
    private static Button submit_one;
    private static Button submit_two;
    private static EditText to_one;
    private static EditText order_one;
    private static EditText to_two;
    private static EditText order_two;
    private static Button orderOne;
    private static Button orderTwo;


    public void load(Context context, ViewPager viewPager){
        this.viewPager = viewPager;
        this.context = context;
        setting = Setting.getSetting(context);
        initView();
        initEvent();


    }

    //初始化控件
    private void initView(){
        //同步手机信息
        CardOneNumber = (EditText)viewPager.findViewById(R.id.CardOneNumber);
        CardTwoNumber = (EditText)viewPager.findViewById(R.id.CardTwoNumber);
        submit_one = (Button)viewPager.findViewById(R.id.submit_one);
        submit_two = (Button)viewPager.findViewById(R.id.submit_two);
        //监控开关
        smsListen = (ToggleButton)viewPager.findViewById(R.id.smsListen);
        autoStart = (ToggleButton)viewPager.findViewById(R.id.autoStart);
        autoBlance = (ToggleButton)viewPager.findViewById(R.id.autoBlance);
        //余额查询设置
        to_one = (EditText)viewPager.findViewById(R.id.to_one);
        order_one = (EditText)viewPager.findViewById(R.id.order_one);
        to_two = (EditText)viewPager.findViewById(R.id.to_two);
        order_two = (EditText)viewPager.findViewById(R.id.order_two);
        orderOne = (Button)viewPager.findViewById(R.id.orderOne);
        orderTwo = (Button)viewPager.findViewById(R.id.orderTwo);

        Map<String,String> map;
        for (String key : (map = setting.getInfo()).keySet()){
            switch (key){
                case "smsListen":
                    if(CheckService.isWork(context,"SmsService")){
                        setting.put("smsListen","true");
                    }else {
                        setting.put("smsListen","false");
                    }
                    if(map.get(key).equals("true")){
                        smsListen.setChecked(true);
                    }else {
                        smsListen.setChecked(false);
                    }
                    break;
                case "autoStart":
                    if(map.get(key).equals("true")){
                        autoStart.setChecked(true);
                    }else {
                        autoStart.setChecked(false);
                    }
                    break;
                case "autoBlance":
                    if(map.get(key).equals("true")){
                        autoBlance.setChecked(true);
                    }else {
                        autoBlance.setChecked(false);
                    }
                    break;
                case "CardOneNumber":
                    try {
                        CardOneNumber.setText(map.get(key));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "CardTwoNumber":
                    try {
                        CardTwoNumber.setText(map.get(key));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "to_one":
                    try {
                        to_one.setText(map.get(key));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "to_two":
                    try {
                        to_two.setText(map.get(key));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "order_one":
                    try {
                        order_one.setText(map.get(key));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "order_two":
                    try {
                        order_two.setText(map.get(key));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    //初始化事件
    private void initEvent(){
        submit_one.setOnClickListener(this);
        submit_two.setOnClickListener(this);
        smsListen.setOnCheckedChangeListener(this);
        autoStart.setOnCheckedChangeListener(this);
        autoBlance.setOnCheckedChangeListener(this);
        orderOne.setOnClickListener(this);
        orderTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_one:
                setting.submit_one(CardOneNumber.getText().toString().trim());
                break;
            case R.id.submit_two:
                setting.submit_two(CardTwoNumber.getText().toString().trim());
                break;
            case R.id.orderOne:
                String one = to_one.getText().toString().trim();
                String order =order_one.getText().toString().trim();
                setting.orderOne(one,order);
                break;
            case R.id.orderTwo:
                String two = to_two.getText().toString().trim();
                String orderTwo =order_two.getText().toString().trim();
                setting.orderTwo(two,orderTwo);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.smsListen:
                Intent intent = new Intent(context, SmsService.class);
                if(isChecked){
                    context.startService(intent);
                    Log.i(TAG, "onCheckedChanged: smsListen 开");
                    setting.put("smsListen","true");
                }else {
                    context.stopService(intent);
                    Log.i(TAG, "onCheckedChanged: smsListen 关");
                    setting.put("smsListen","false");
                }
                break;
            case R.id.autoStart:
                if(isChecked){
                    Log.i(TAG, "onCheckedChanged: autoStart 开");
                    setting.put("autoStart","true");
                }else {
                    Log.i(TAG, "onCheckedChanged: autoStart 关");
                    setting.put("autoStart","false");
                }
                break;
            case R.id.autoBlance:
                if(isChecked){
                    Log.i(TAG, "onCheckedChanged: autoBlance 开");
                    setting.put("autoBlance","true");
                }else {
                    Log.i(TAG, "onCheckedChanged: autoBlance 关");
                    setting.put("autoBlance","false");
                }
                break;
        }
    }
}
