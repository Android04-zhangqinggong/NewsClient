package com.example.administrator.news.common;

import android.util.Log;

/**
 * 日志管理
 * Created by Administrator on 2016/8/8.
 */
public class LogUtil {
    //静态常量标识
    public static final String TAG = "新闻随意看";
    //调试日志的开关
    public static boolean isDebug = true;

    //重新对打印类的所有方法添加判别进行封装
    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }
    public static void d(String msg) {
        if (isDebug)
            Log.d(LogUtil.TAG,msg);
    }
}

