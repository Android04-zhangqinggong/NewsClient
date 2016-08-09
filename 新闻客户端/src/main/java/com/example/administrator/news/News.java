package com.example.administrator.news;

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

    public News( String title, String icon) {
        this.title = title;
        this.icon = icon;
    }




    public News() {
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
