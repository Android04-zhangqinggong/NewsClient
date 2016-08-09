package com.example.administrator.news.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.news.News;
import com.example.administrator.news.ParseNews;
import com.example.administrator.news.R;
import com.example.administrator.news.adapter.NewsAdapter;
import com.example.administrator.news.common.HttpClientUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends Fragment {


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mListView.setAdapter(new NewsAdapter(getContext(), (ArrayList<News>) msg.obj));
        }
    };
    ListView mListView;
    public ArrayList<News> mNewsArrayList;

    public CarFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_fragment_car);
        mNewsArrayList = new ArrayList<>();
        initData();
        return view;
    }

    private void initData() {
        //必须开启一个线程进行联网操作
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://c.m.163.com/nc/article/list/T1348654060988/0-20.html");
                    //调用方法,传入URL,返回一个Json字符串
                    HttpClientUtil httpClientUtil = HttpClientUtil.getInstance(getContext());
                    String JsonString = httpClientUtil.httpGet(url);
                    //调用方法.传入Json字符串,返回一个数据集合
                    mNewsArrayList = ParseNews.parser(JsonString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = mNewsArrayList;
                mHandler.sendMessage(message);
            }
        }.start();
    }

}
