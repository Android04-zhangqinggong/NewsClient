package com.example.administrator.news.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.news.bean.News;
import com.example.administrator.news.common.ParseNews;
import com.example.administrator.news.R;
import com.example.administrator.news.adapter.NewsAdapter;
import com.example.administrator.news.common.HttpClientUtil;
import com.example.administrator.news.ui.ShownewsActivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuojiFragment extends Fragment implements AdapterView.OnItemClickListener{


    private static final String TAG = "TopFragment";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mListView.setAdapter(new NewsAdapter(getContext(), (ArrayList<News>) msg.obj));
        }
    };
    ListView mListView;
    public ArrayList<News> mNewsArrayList;

    public GuojiFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_fragment);
        mNewsArrayList = new ArrayList<>();
        initData();
        mListView.setOnItemClickListener(this);
        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = mNewsArrayList.get(position).getUrl();
        Intent intent = new Intent(getContext(),ShownewsActivity.class);
        intent.putExtra("URL",url);
        Log.d(TAG, "数据: " + url);
        startActivity(intent);

    }
    private void initData() {
        //必须开启一个线程进行联网操作
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://v.juhe.cn/toutiao/index?type=guoji&key=e5a12c17184566014f12b71199624fcf");
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
