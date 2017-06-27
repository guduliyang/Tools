package com.crazy.test.tools.activitys;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.crazy.test.tools.R;

/**
 * Created by ADMIN on 2017/6/26.
 */

public class Card2Activity extends Activity implements View.OnClickListener{
    private static final String TAG=Card2Activity.class.getSimpleName();
    private ViewPager viewPager;
    private TextView operator;
    private TextView phonenumber;
    private TextView password;
    private TextView name;
    private TextView idcard;

    public void load(ViewPager viewPager){
        this.viewPager = viewPager;
        initView();
        initEvent();
    }

    private void initView(){
        operator = (TextView)viewPager.findViewById(R.id.operator_two);
        phonenumber = (TextView)viewPager.findViewById(R.id.phonenumber_two);
        password = (TextView)viewPager.findViewById(R.id.password_two);
        name = (TextView)viewPager.findViewById(R.id.name_two);
        idcard = (TextView)viewPager.findViewById(R.id.idcard_two);
    }

    private void initEvent(){

    }

    @Override
    public void onClick(View v) {

    }
}