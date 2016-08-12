package com.example.administrator.news.bean;

/**
 * Created by Administrator on 2016/8/8.
 */
public class News {
    //标题
    private String title;
    //图标
    private String icon;
    //链接
    private String url;
    //出处
    private String authorName;
    //时间
    private String time;

    public News(String title, String icon , String url,String authorName ,String time) {
        this.title = title;
        this.icon = icon;
        this.url = url;
        this.authorName = authorName;
        this.time = time;
    }


    public News() {
    }
    public void setTime(String time) {
        this.time = time;
    }public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTime() {
        return time;
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
