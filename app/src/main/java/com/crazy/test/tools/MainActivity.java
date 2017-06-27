package com.crazy.test.tools;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crazy.test.tools.activitys.Card1Activity;
import com.crazy.test.tools.activitys.Card2Activity;
import com.crazy.test.tools.activitys.SettingActivity;
import com.crazy.test.tools.utils.Setting;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private Activity activity;
    //声明视图
    private ViewPager viewPager;
    //菜单栏中的四个按钮
    private static Button card1;
    private static Button card2;
    private static Button info;
    private static Button setting;
    //声明一个view List 用来存放viewPager中的布局文件
    private List<View> pageList = new ArrayList<>();
    //声明一个PagerAdapter
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initView();
        initViewPage();
        initEvent();
        Setting setting = new Setting(this);
    }

    //初始化控件
    private void initView(){
        viewPager = (ViewPager)findViewById(R.id.viewPage);
        card1 = (Button) findViewById(R.id.card1);
        card2 = (Button) findViewById(R.id.card2);
        info = (Button) findViewById(R.id.info);
        setting = (Button) findViewById(R.id.setting);
    }

    //初始化ViewPage
    private void initViewPage(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View card1 = inflater.inflate(R.layout.card1_layout,null);
        View card2 = inflater.inflate(R.layout.card2_layout,null);
        View info = inflater.inflate(R.layout.info_layout,null);
        View setting = inflater.inflate(R.layout.setting_layout,null);

        pageList.add(card1);
        pageList.add(card2);
        pageList.add(info);
        pageList.add(setting);

        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return pageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = pageList.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(pageList.get(position));
            }
        };

        viewPager.setAdapter(pagerAdapter);
    }

    //绑定事件
    private void initEvent(){
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        info.setOnClickListener(this);
        setting.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        resetButton();
                        activity.setTitle("CARD1");
                        card1.setTextColor(Color.BLUE);
                        new Card1Activity().load(viewPager);
                        break;
                    case 1:
                        resetButton();
                        activity.setTitle("CARD2");
                        card2.setTextColor(Color.BLUE);
                        new Card2Activity().load(viewPager);
                        break;
                    case 2:
                        resetButton();
                        activity.setTitle("INFO");
                        info.setTextColor(Color.BLUE);
                        break;
                    case 3:
                        resetButton();
                        activity.setTitle("SETTING");
                        setting.setTextColor(Color.BLUE);
                        new SettingActivity().load(viewPager);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //重置按钮字体颜色
    private void resetButton(){
        card1.setTextColor(Color.WHITE);
        card2.setTextColor(Color.WHITE);
        info.setTextColor(Color.WHITE);
        setting.setTextColor(Color.WHITE);
    }

    //点击响应事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card1:
                viewPager.setCurrentItem(0);
                activity.setTitle("CARD1");
                resetButton();
                card1.setTextColor(Color.BLUE);
                break;
            case R.id.card2:
                viewPager.setCurrentItem(1);
                activity.setTitle("CARD2");
                resetButton();
                card2.setTextColor(Color.BLUE);
                break;
            case R.id.info:
                viewPager.setCurrentItem(2);
                activity.setTitle("INFO");
                resetButton();
                info.setTextColor(Color.BLUE);
                break;
            case R.id.setting:
                viewPager.setCurrentItem(3);
                activity.setTitle("SETTING");
                resetButton();
                setting.setTextColor(Color.BLUE);
                break;
        }
    }
}
