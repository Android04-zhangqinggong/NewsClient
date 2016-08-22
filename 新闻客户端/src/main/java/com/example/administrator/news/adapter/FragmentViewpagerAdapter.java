package com.example.administrator.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/18.
 */
public class FragmentViewpagerAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> mArrayList;
    public FragmentViewpagerAdapter(FragmentManager fm ,ArrayList<Fragment> arrayList) {
        super(fm);
        mArrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public int getCount() {
        if (mArrayList != null){
            return mArrayList.size();
        }
        return 0;
    }
}
