package com.crazy.test.tools.activitys;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crazy.test.tools.R;
import com.crazy.test.tools.okhttp3.Updata;
import com.crazy.test.tools.utils.Setting;
import java.util.Map;

/**
 * Created by ADMIN on 2017/6/26.
 */

public class Card1Activity implements View.OnClickListener{
    private static final String TAG=Card1Activity.class.getSimpleName();
    private Context context;
    private Setting setting;
    private ViewPager viewPager;
    private TextView operator;
    private TextView phonenumber;
    private TextView password;
    private TextView name;
    private TextView idcard;
    private EditText newPwd;
    private Button updataPwd;

    public void load(Context context, ViewPager viewPager){
        this.viewPager = viewPager;
        this.context = context;
        setting = Setting.getSetting(context);
        initView();
        initEvent();
    }

    private void initView(){
        operator = (TextView)viewPager.findViewById(R.id.operator_one);
        phonenumber = (TextView)viewPager.findViewById(R.id.phonenumber_one);
        password = (TextView)viewPager.findViewById(R.id.password_one);
        name = (TextView)viewPager.findViewById(R.id.name_one);
        idcard = (TextView)viewPager.findViewById(R.id.idcard_one);
        newPwd = (EditText)viewPager.findViewById(R.id.newPwd);
        updataPwd = (Button)viewPager.findViewById(R.id.updataPwd);

        Map<String,String> map;
        for(String key : (map = setting.getCardOne()).keySet()){
            try {
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
            }catch (Exception e){
                Log.e(TAG, "initView: 初始化界面失败！"+e.getMessage() );
            }

        }
    }

    private void initEvent(){
        updataPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String password;
        switch (v.getId()){
            case R.id.updataPwd:
                try {
                    if((password=newPwd.getText().toString().trim())!=null && password!=""){
                        Updata updata = new Updata(context);
                        updata.upataPwd(setting.get("CardOneNumber"),password);
                        setting.submit_one(setting.get("CardOneNumber"));
                    }
                }catch (Exception e){
                    Toast.makeText(context, "更新失败！", Toast.LENGTH_SHORT).show();
                }
                
                break;
            default:
                break;
        }
    }
}
