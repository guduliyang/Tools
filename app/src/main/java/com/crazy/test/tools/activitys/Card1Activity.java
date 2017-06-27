package com.crazy.test.tools.activitys;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.crazy.test.tools.R;
import com.crazy.test.tools.utils.Setting;

import java.util.Map;

/**
 * Created by ADMIN on 2017/6/26.
 */

public class Card1Activity extends Activity implements View.OnClickListener{
    private static final String TAG=Card1Activity.class.getSimpleName();
    private ViewPager viewPager;
    private TextView operator;
    private TextView phonenumber;
    private TextView password;
    private TextView name;
    private TextView idcard;

    public void load(ViewPager viewPager){
        this.viewPager = viewPager;
        initView();
    }

    private void initView(){
        operator = (TextView)viewPager.findViewById(R.id.operator_one);
        phonenumber = (TextView)viewPager.findViewById(R.id.phonenumber_one);
        password = (TextView)viewPager.findViewById(R.id.password_one);
        name = (TextView)viewPager.findViewById(R.id.name_one);
        idcard = (TextView)viewPager.findViewById(R.id.idcard_one);

        Map<String,String> map;
        for(String key : (map = Setting.getCardOne()).keySet()){
            switch (key){
                case "Operators":
                    operator.setText(map.get(key));
                    break;
                case "PhoneNumber":
                    phonenumber.setText(map.get(key));
                    break;
                case "pwd":
                    password.setText(map.get(key));
                    break;
                case "name":
                    name.setText(map.get(key));
                    break;
                case "idcard":
                    idcard.setText(map.get(key));
                    break;
            }
        }
    }

    private void initEvent(){

    }

    @Override
    public void onClick(View v) {

    }
}
