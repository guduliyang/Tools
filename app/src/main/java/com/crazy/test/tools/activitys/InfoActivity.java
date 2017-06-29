package com.crazy.test.tools.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crazy.test.tools.R;
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

    public void load(Context context, ViewPager viewPager){
        this.viewPager = viewPager;
        this.context = context;
        setting = Setting.getSetting(context);
        initView();
        initEvent();
    }

    private void initView(){
        test = (Button)viewPager.findViewById(R.id.test);
    }

    private void initEvent(){
        test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test:
                Intent intent = new Intent("android.provider.Telephony.SMS_RECEIVED");
                context.sendBroadcast(intent);
                break;
        }
    }
}
