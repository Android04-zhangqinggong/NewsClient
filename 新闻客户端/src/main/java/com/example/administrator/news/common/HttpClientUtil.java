package com.example.administrator.news.common;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 联网模块--利用OkHttp或HttpUrlConnection获取网络数据
 * Created by Administrator on 2016/8/8.
 */
public class HttpClientUtil {
    public static final String TAG = "HttpClientUtil";
    private static OkHttpClient mhttpClient = new OkHttpClient();
    static Context mContext;
    private static HttpClientUtil mHttpClientUtil;
    private static String mString;

    /*
    有参构造私有,不能外部实例化
     */
    private HttpClientUtil(Context context) {
        mContext = context;
    }

    /*
    单例模式.只能调用getInstance方法返回一个本类对象
     */
    public static HttpClientUtil getInstance(Context context){
        if(mHttpClientUtil == null){
            mHttpClientUtil = new HttpClientUtil(context);
        }
        return mHttpClientUtil;
    }
    public String httpGet(URL url){
        //发送请求
        Request request = new Request.Builder().url(url).build();
        //响应请求
        try {
            Response response = mhttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                mString = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onResponse: " + mString);
        return mString;
    }
    /**
     * OkHttp异步get实现--String无法返回!!!!!!!!!!!!!!!!

    public String httpGet(URL url){
        //发送请求
        Request request = new Request.Builder().url(url).build();
        //响应请求
        mhttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mString = response.body().string();
                Log.d(TAG, "onResponse: " + mString);
            }
        });
        */

}
