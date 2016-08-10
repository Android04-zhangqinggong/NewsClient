package com.example.administrator.news.bean;

/**
 * Created by Administrator on 2016/8/8.
 */
public class News {
    //标题
    private String title;
    //副标题
    private String summary;
    //图标
    private String icon;
    //链接
    private String url;

    public News(String title, String icon ,String url) {
        this.title = title;
        this.icon = icon;
        this.url = url;
    }


    public News() {
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }




}
