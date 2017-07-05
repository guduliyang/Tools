package com.crazy.test.tools.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射机制
 * Created by admin on 2016/7/5.
 */
public class Reflection {
    private Class<?> pointClass;
    private static final String TAG = "Reflection";

    public Reflection(Class<?> pointClass){
        this.pointClass = pointClass;
    }

    /**
     * 反射类中所有公有属性
     */
    public void getField(){
        Log.i(TAG, pointClass.getName()+"类中所有公有的属性************************************************************");
        Field[] fb = pointClass.getFields();
        for (int i = 0; i < fb.length; i++) {
            Class c1 = fb[i].getType();
            Log.i(TAG, "类型：" + c1 + "：\t名称：" + fb[i].getName());
        }
    }

    /**
     * 反射类中所有属性
     */
    public void getDeclaredField(){
        Log.i(TAG, pointClass.getName()+"类中所有属性************************************************************");
        Field[] fb = pointClass.getDeclaredFields();
        for (int i = 0; i < fb.length; i++) {
            Class c1 = fb[i].getType();
            Log.i(TAG, "类型：" + c1 + "：\t名称：" + fb[i].getName());
        }
    }

    /**
     * 反射类中所有方法
     */
    public void getMethod(){
        Log.i(TAG, pointClass.getName()+"类中所有方法************************************************************");
        Method[] fm = pointClass.getMethods();
        for (int i = 0; i < fm.length; i++) {
            String parameter="";
            for (Class cl :fm[i].getParameterTypes()){
                parameter += cl.getName()+"\t";
            }
            Log.i(TAG, "方法名：" + fm[i].getName() + "\t返回值：" + fm[i].getReturnType()+"\t参数列表："+parameter);
        }
    }

    /**
     * 反射类中所有接口
     */
    public void getInterface(){
        Log.i(TAG, pointClass.getName()+"类中所有接口************************************************************");
        Class<?>[] fi = pointClass.getInterfaces();
        for (int i = 0; i < fi.length; i++) {
            Log.i(TAG, "接口名：" + fi[i].getName());
        }
    }



}
