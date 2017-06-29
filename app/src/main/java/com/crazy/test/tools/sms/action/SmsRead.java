package com.crazy.test.tools.sms.action;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import com.crazy.test.tools.sms.model.SMS;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2017/6/20.
 */

public class SmsRead {
    private static final String TAG = SmsRead.class.getSimpleName();
    private static final String SMS_URI_ALL = "content://sms/"; // 所有短信
    private static final String SMS_URI_INBOX = "content://sms/inbox"; // 收件箱
    private static final String SMS_URI_SEND = "content://sms/sent"; // 已发送
    private static final String SMS_URI_DRAFT = "content://sms/draft"; // 草稿
    private static final String SMS_URI_OUTBOX = "content://sms/outbox"; // 发件箱
    private static final String SMS_URI_FAILED = "content://sms/failed"; // 发送失败
    private static final String SMS_URI_QUEUED = "content://sms/queued"; // 待发送列表

    public static SMS getLastOne(Context context){
        Cursor cursor = getCursor(context);
        cursor.moveToFirst();
        SMS sms = saveToSMS(cursor);
        if(cursor!=null){
            cursor.close();
        }
        return  sms;
    }

    public static List<SMS> getALL(Context context) {
        Cursor cursor = getCursor(context);
        List<SMS> messages = new ArrayList<>();
        while (cursor.moveToNext()){
            messages.add(saveToSMS(cursor));
        }
        if(cursor!=null){
            cursor.close();
        }
        return messages;
    }

    private static Cursor getCursor(Context context){
        Uri uri = Uri.parse(SMS_URI_INBOX);
        String[] projection = null;                    //返回的列
        String selection = null;// "read=?";                  //查询条件（相当于where后边的值）
        String[] selectionArgs = null;//new String[] {"0"};  //查询条件的值
        String sortOrder = "date desc";              //排序条件
        Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        return cursor;
    }

    private static SMS saveToSMS(Cursor cursor){
        SMS sms = new SMS();
        sms.setMODEL(Build.MODEL);
        if(cursor!=null){
            boolean over = false;
            int i=0;
            while (!over){
                try {
                    String s = cursor.getColumnName(i);
                    switch (s) {
                        case "thread_id":
                            sms.setThread_id(cursor.getString(i++));
                            break;
                        case "address":
                            sms.setAddress(cursor.getString(i++));
                            break;
                        case "date":
                            sms.setDate(cursor.getLong(i++));
                            break;
                        case "read":
                            sms.setRead(cursor.getInt(i++));
                            break;
                        case "status":
                            sms.setStatus(cursor.getInt(i++));
                            break;
                        case "type":
                            sms.setType(cursor.getInt(i++));
                            break;
                        case "body":
                            sms.setBody(cursor.getString(i++));
                            break;
                        case "service_center":
                            sms.setService_center(cursor.getString(i++));
                            break;
                        case "sub_id":
                            sms.setSub_id(cursor.getInt(i++));
                            break;
                        case "sim_id":
                            sms.setSim_id(cursor.getInt(i++));
                            break;
                        default:
                            i++;
//                        Log.i(TAG, cursor.getColumnName(i) + ":" + cursor.getString(i++));
                    }
                } catch (Exception e) {
                    over = true;
                }
            }
        }
        return sms;
    }
}
