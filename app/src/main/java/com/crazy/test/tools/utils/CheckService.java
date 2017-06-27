package com.crazy.test.tools.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * 【服务检测】
 * Created by ADMIN on 2017/6/20.
 */
public class CheckService {

    /**
     * 【判断服务是否启动】
     * @param context
     * @param ServiceName 服务名称
     * @return
     */
    public static boolean isWork(Context context,String ServiceName){
        boolean working = false;
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list = manager.getRunningServices(40);
        if(list.size()<=0){
            return false;
        }else {
            for (int i=0;i<list.size();i++){
                String name = list.get(i).service.getClassName().toString();
                if(name.equals(ServiceName)){
                    working = true;
                }
            }
        }
        return working;
    }
}
