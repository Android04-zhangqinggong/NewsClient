package com.example.administrator.news.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ViewPagerAdapter extends PagerAdapter{
    ArrayList<ImageView> mViewArrayList;

    public ViewPagerAdapter(ArrayList<ImageView> viewArrayList) {
        mViewArrayList = viewArrayList;
    }

    @Override
    public int getCount() {
        if (mViewArrayList != null){
            mViewArrayList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewArrayList.get(position));
        return mViewArrayList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewArrayList.get(position));
    }
}
