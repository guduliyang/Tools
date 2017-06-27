package com.crazy.test.tools.okhttp3;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 【包装OkHttp3】
 * Created by iyYang on 2017/6/6.
 */

public class HttpClient {
    private final String TAG = this.getClass().getSimpleName();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 【异步get请求】
     * @param callback
     * @param url
     */
    public void getAsyn(Callback callback,String url){
        //step 1: 创建 OkHttpClient 对象
        OkHttpClient httpClient = new OkHttpClient();

        //step 2： 创建一个请求，不指定请求方法时默认是GET。
        Request.Builder builder = new Request.Builder().url(url);

        //可以省略，默认是GET请求
        builder.method("GET",null);

        //step 3：创建 Call 对象
        Call call = httpClient.newCall(builder.build());

        //step 4: 开始异步请求
        call.enqueue(callback);
    }

    /**
     * 【同步get请求】
     * @param url
     * @return
     */
    public Response get(String url){
        //接收返回的数据
        Response response = null;

        //step 1: 创建 OkHttpClient 对象
        OkHttpClient httpClient = new OkHttpClient();

        //step 2： 创建一个请求，不指定请求方法时默认是GET。
        Request.Builder builder = new Request.Builder().url(url);

        //可以省略，默认是GET请求
        builder.method("GET",null);

        //step 3：创建 Call 对象
        Call call = httpClient.newCall(builder.build());

        try {
            response = call.execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 【异步post请求】
     * @param callback
     * @param url
     * @param map
     */
    public void postAsyn(Callback callback, String url, Map<String,String> map){

        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient httpClient = new OkHttpClient();

        //step 2: 创建FormBody.Builder
        FormBody.Builder builder = new FormBody.Builder();

        if(map!=null && !map.isEmpty()){
            for (String key:map.keySet()){
                builder.add(key,map.get(key));
            }
        }

        //step 3: 创建  FormBody.Builder
        RequestBody requestBody = builder.build();

        //step 4: 创建请求
        Request request = new Request.Builder().url(url).post(requestBody).build();

        //step 5： 建立联系 创建Call对象
        httpClient.newCall(request).enqueue(callback);
    }

    /**
     * 【同步的post请求】
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public Response post(String url, Map<String,String> map) throws IOException {
        Response response = null;

        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient httpClient = new OkHttpClient();

        //step 2: 创建FormBody.Builder
        FormBody.Builder builder = new FormBody.Builder();

        if(map!=null && !map.isEmpty()){
            for (String key:map.keySet()){
                builder.add(key,map.get(key));
            }
        }

        //step 3: 创建  RequestBody
        RequestBody requestBody = builder.build();

        //step 4: 创建请求
        Request request = new Request.Builder().url(url).post(requestBody).build();

        //step 5： 建立联系 创建Call对象
        response = httpClient.newCall(request).execute();

        return response;
    }

    /**
     * 【同步post请求】
     * @param url   访问地址
     * @param json  JSON格式参数
     * @return
     * @throws IOException
     */
    public Response post(String url,String json) throws IOException {
        Response response = null;

        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient httpClient = new OkHttpClient();

        //step 2: 创建  RequestBody
        RequestBody requestBody = RequestBody.create(JSON,json);

        //step 3: 创建请求
        Request request = new Request.Builder().url(url).post(requestBody).build();

        //step 4： 建立联系 创建Call对象
        response = httpClient.newCall(request).execute();

        return response;
    }

    /**
     * 【异步post请求】
     * @param callback
     * @param url
     * @param json
     */
    public void postAsyn(Callback callback, String url, String json){

        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient httpClient = new OkHttpClient();

        //step 3: 创建  RequestBody
        RequestBody requestBody = RequestBody.create(JSON,json);

        //step 4: 创建请求
        Request request = new Request.Builder().url(url).post(requestBody).build();

        //step 5： 建立联系 创建Call对象
        httpClient.newCall(request).enqueue(callback);
    }
}
