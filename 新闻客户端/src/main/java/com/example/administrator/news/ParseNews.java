package com.example.administrator.news;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析json
 * Created by Administrator on 2016/8/8.
 */
public class ParseNews {

    public static ArrayList<News> parser(String s){
        ArrayList<News> newsArrayList = new ArrayList<>();
        Gson mGson = new Gson();
        GsonBean gsonBean = mGson.fromJson(s, GsonBean.class);
        List<GsonBean.T1350383429665Bean> data = gsonBean.getT1350383429665();
        if(data != null){
            for (int i = 0; i <data.size() ; i++) {
                GsonBean.T1350383429665Bean dataBean = data.get(i);
                String title = dataBean.getTitle();
                String icon = dataBean.getImgsrc();
                News news = new News(title,icon);
                newsArrayList.add(news);
            }
        }
        return newsArrayList;
    }
}
/*Gson mGson = new Gson();
        GsonBeanProject gsonBean = mGson.fromJson(s, GsonBeanProject.class);
        List<GsonBeanProject.DataBean> dataBeanArrayList = gsonBean.getData();
        ArrayList<News> newsArrayList = new ArrayList<>();
        for (int i = 0; i <dataBeanArrayList.size() ; i++) {
            GsonBeanProject.DataBean dataBean = dataBeanArrayList.get(i);
            //新闻id
            int nid = dataBean.getNid();
            //标题
            String title = dataBean.getTitle();
            //摘要
            String summary = dataBean.getSummary();
            //图标
            String icon = dataBean.getIcon();
            //网页链接
            String link = dataBean.getLink();
            //新闻类型
            int type = dataBean.getType();
            //利用JavaBean储存数据到集合中
            News news=new News(nid,title,icon,summary,link,type);
            newsArrayList.add(news);
        }*/

