package com.crazy.test.tools.sms.model;

import com.crazy.test.tools.utils.DateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by ADMIN on 2016/12/26.
 */

public class SMS {

    /**
     * thread_id : 对话的序号
     * address : 发件人地址，手机号
     * date : 日期 long型
     * read : 是否阅读
     * status : 状态
     * type :  类型 1是收到的，2是发出的
     * body : 内容
     * service_center : 信服务中心号码编号
     * MODEL : 手机型号
     */

    private String thread_id;
    private String address;
    private long date;
    private int read;   //已读：1   未读：0
    private int status;
    private int type;
    private String body;
    private String service_center;
    private int sub_id;
    private int sim_id;
    private String MODEL; //手机型号

    public SMS (){}

    public SMS (String jsonString){
        try {
            JSONObject object = new JSONObject(jsonString);
            Iterator<String> stringIterator = object.keys();
            while (stringIterator.hasNext()){
                String key = stringIterator.next();
                switch (key){
                    case "thread_id":
                        setThread_id(object.getString(key));
                        break;
                    case "address":
                        setAddress(object.getString(key));
                        break;
                    case "date":
                        setDate(object.getLong(key));
                        break;
                    case "read":
                        setRead(object.getInt(key));
                        break;
                    case "status":
                        setStatus(object.getInt(key));
                        break;
                    case "type":
                        setType(object.getInt(key));
                        break;
                    case "body":
                        setBody(object.getString(key));
                        break;
                    case "service_center":
                        setService_center(object.getString(key));
                        break;
                    case "sub_id":
                        setSub_id(object.getInt(key));
                        break;
                    case "sim_id":
                        setSim_id(object.getInt(key));
                        break;
                    case "MODEL":
                        setMODEL(object.getString(key));
                        break;
                    default:
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getSim_id() {
        return sim_id;
    }

    public void setSim_id(int sim_id) {
        this.sim_id = sim_id;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getService_center() {
        return service_center;
    }

    public void setService_center(String service_center) {
        this.service_center = service_center;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String string(){
        return "From："+address+"\nTime："+ DateFormat.format(date)+"\nBody："+body;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        try {
            object.put("thread_id",thread_id);
            object.put("address",address);
            object.put("date",date);
            object.put("read",read);
            object.put("status",status);
            object.put("type",type);
            object.put("body",body);
            object.put("service_center",service_center);
            object.put("sub_id",sub_id);
            object.put("sim_id",sim_id);
            object.put("MODEL",MODEL);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object.toString();
    }
}
