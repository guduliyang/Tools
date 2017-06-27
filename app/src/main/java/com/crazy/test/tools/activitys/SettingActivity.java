package com.crazy.test.tools.activitys;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.crazy.test.tools.R;

/**
 * Created by ADMIN on 2017/6/26.
 */

public class SettingActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG=SettingActivity.class.getSimpleName();
    private ViewPager viewPager;
    private ToggleButton smsListen;
    private ToggleButton autoStart;
    private ToggleButton autoBlance;
    private static EditText CardOneNumber;
    private static EditText CardTwoNumber;
    private static Button submit_one;
    private static Button submit_two;

    public void load(ViewPager viewPager){
        this.viewPager = viewPager;
        initView();
        initEvent();
    }

    //初始化控件
    private void initView(){
        CardOneNumber = (EditText)viewPager.findViewById(R.id.CardOneNumber);
        CardTwoNumber = (EditText)viewPager.findViewById(R.id.CardTwoNumber);
        submit_one = (Button)viewPager.findViewById(R.id.submit_one);
        submit_two = (Button)viewPager.findViewById(R.id.submit_two);
        smsListen = (ToggleButton)viewPager.findViewById(R.id.smsListen);
        autoStart = (ToggleButton)viewPager.findViewById(R.id.autoStart);
        autoBlance = (ToggleButton)viewPager.findViewById(R.id.autoBlance);
    }

    //初始化事件
    private void initEvent(){
        submit_one.setOnClickListener(this);
        submit_two.setOnClickListener(this);
        smsListen.setOnCheckedChangeListener(this);
        autoStart.setOnCheckedChangeListener(this);
        autoBlance.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_one:
                break;
            case R.id.submit_two:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.smsListen:
                if(isChecked){
                    Log.i(TAG, "onCheckedChanged: 开");
                }else {
                    Log.i(TAG, "onCheckedChanged: 关");
                }
                break;
            case R.id.autoStart:
                if(isChecked){
                    Log.i(TAG, "onCheckedChanged: 开");
                }else {
                    Log.i(TAG, "onCheckedChanged: 关");
                }
                break;
            case R.id.autoBlance:
                if(isChecked){
                    Log.i(TAG, "onCheckedChanged: 开");
                }else {
                    Log.i(TAG, "onCheckedChanged: 关");
                }
                break;
        }
    }
}
