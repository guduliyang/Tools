package com.crazy.test.tools.activitys;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.crazy.test.tools.R;
import com.crazy.test.tools.utils.Setting;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of App Widget functionality.
 */
public class ToolsWidget extends AppWidgetProvider {
    private static final String TAG = ToolsWidget.class.getSimpleName();
    private static Setting setting;
    private int[] appwidgeId;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tools_widget);

        Map<String,String> map = setting.getCardOne();
        for(String key : map.keySet()){
            switch (key){
                case "Operators":
                    views.setTextViewText(R.id.Operators_one,map.get(key));
                    break;
                case "PhoneNumber":
                    views.setTextViewText(R.id.PhoneNumber_one,map.get(key));
                    break;
                case "pwd":
                    views.setTextViewText(R.id.pwd_one,map.get(key));
                    break;
                case "name":
                    views.setTextViewText(R.id.name_one,map.get(key));
                    break;
                case "idcard":
                    views.setTextViewText(R.id.idcard_one,map.get(key));
                    break;
            }
        }

        map = setting.getCardTwo();
        for(String key : map.keySet()){
            switch (key){
                case "Operators":
                    views.setTextViewText(R.id.Operators_two,map.get(key));
                    break;
                case "PhoneNumber":
                    views.setTextViewText(R.id.PhoneNumber_two,map.get(key));
                    break;
                case "pwd":
                    views.setTextViewText(R.id.pwd_two,map.get(key));
                    break;
                case "name":
                    views.setTextViewText(R.id.name_two,map.get(key));
                    break;
                case "idcard":
                    views.setTextViewText(R.id.idcard_two,map.get(key));
                    break;
            }
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.i(TAG, "onUpdate: onUpdate");
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Log.i(TAG, "onEnabled: onEnabled");;
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        Log.i(TAG, "onDisabled: onDisabled");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: "+intent.getAction());
        setting = Setting.getSetting(context);
        if(intent==null)return;
        String action = intent.getAction();
        if(action.equals("android.appwidget.action.APPWIDGET_UPDATE")){
            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.tools_widget);
            Map<String,String> map = setting.getCardOne();
            for(String key : map.keySet()){
                switch (key){
                    case "Operators":
                        views.setTextViewText(R.id.Operators_one,map.get(key));
                        break;
                    case "PhoneNumber":
                        views.setTextViewText(R.id.PhoneNumber_one,map.get(key));
                        break;
                    case "pwd":
                        views.setTextViewText(R.id.pwd_one,map.get(key));
                        break;
                    case "name":
                        views.setTextViewText(R.id.name_one,map.get(key));
                        break;
                    case "idcard":
                        views.setTextViewText(R.id.idcard_one,map.get(key));
                        break;
                }
            }

            map = setting.getCardTwo();
            for(String key : map.keySet()){
                switch (key){
                    case "Operators":
                        views.setTextViewText(R.id.Operators_two,map.get(key));
                        break;
                    case "PhoneNumber":
                        views.setTextViewText(R.id.PhoneNumber_two,map.get(key));
                        break;
                    case "pwd":
                        views.setTextViewText(R.id.pwd_two,map.get(key));
                        break;
                    case "name":
                        views.setTextViewText(R.id.name_two,map.get(key));
                        break;
                    case "idcard":
                        views.setTextViewText(R.id.idcard_two,map.get(key));
                        break;
                }
            }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context,ToolsWidget.class);
            appWidgetManager.updateAppWidget(componentName,views);
        }


        super.onReceive(context, intent);
    }

}

