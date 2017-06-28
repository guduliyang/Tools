package com.crazy.test.tools.utils;

import android.content.Context;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by ADMIN on 2017/6/27.
 */

public class Config {
    private static final String TAG = Config.class.getSimpleName();
    private static Config config;
    private static final String fileName = "setting";
    private Context context;

    private Config(Context context){
        this.context = context;
    }

    public static Config getConfig(Context context){
        if (config == null) {
            synchronized (Config.class) {
                if (config == null) {
                    config = new Config(context);
                }
            }
        }
        return config;
    }

    public String get(String key){
        Properties properties = loadConfig();
        return properties.getProperty(key).toString();
    }

    public Set<String> keys(){
        return loadConfig().stringPropertyNames();
    }

    private Properties loadConfig() {
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            properties.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "loadConfig: 未找到配置文件(;" + fileName + "),创建新配置文件");
            writeConfig(new Properties());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * [保存配置文件]
     * @param properties
     * @return
     */
    private boolean writeConfig(Properties properties) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            properties.store(outputStream, null);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 【同步settings文件夹下配置信息】
     */
    public void ansySetting() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = context.getAssets().open("settings");
            properties.load(inputStream);
            Log.i(TAG, "ansySetting: 同步设置");
            for (String key : properties.stringPropertyNames()) {
                put(key, properties.getProperty(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 【添加或更新配置】
     *
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key, String value) {
        Properties properties = loadConfig();
        properties.put(key, value);
        return writeConfig(properties);
    }


}
