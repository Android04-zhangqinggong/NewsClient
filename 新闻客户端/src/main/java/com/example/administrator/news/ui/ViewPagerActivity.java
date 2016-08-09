package com.example.administrator.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {
    public static final String REMENBER = "remenber";
    public static final String ISFIRSTRUN = "IsFirstRun";
    private static final String TAG = "ViewPagerActivity";
    ViewPager mViewPager;
    ArrayList<ImageView> mViewArrayList;
    int[] pictures = {R.drawable.welcome,R.drawable.wy,R.drawable.bd,R.drawable.small};
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //生成shared Preferences文件用于保存用户是否浏览了viewPager
        //MODE_PRIVATE 私有模式
        mSharedPreferences = getSharedPreferences(REMENBER, MODE_PRIVATE);
        //从sp文件中读取key,是否为true
        boolean isFirstRun = mSharedPreferences.getBoolean(ISFIRSTRUN,true);

        //根据isFirstRun的值判断是否为第一次运行,如果不是第一次运行则直接开启intent跳转到帧动画欢迎界面
        if (!isFirstRun){
            startActivity(new Intent(ViewPagerActivity.this,MainActivity.class));
            //非第一次运行跳转后也得finish()!!!!!!!!!! !!!!!!!!!!!
            finish();
            Log.d(TAG, "onCreate: " + "非第一次运行");
        }else{
            setContentView(R.layout.activity_view_pager);
            mViewPager = (ViewPager) findViewById(R.id.vp);
            mViewArrayList = new ArrayList<>();

            for (int i = 0; i < pictures.length; i++) {
                ImageView iv = new ImageView(this);
                iv.setImageResource(pictures[i]);
                mViewArrayList.add(iv);
            }
            Log.d(TAG, "onCreate: " + "第一次运行");
            Log.d(TAG, "onCreate: " + "arraylist的长度" + mViewArrayList.size());
            mViewPager.setAdapter(new ViewPagerAdapter(mViewArrayList));
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position >= 3){
                        //第四张图片后自动跳转
                        startActivity(new Intent(ViewPagerActivity.this,MainActivity.class));
                        //同时停止该activity
                        finish();
                        mSharedPreferences = getSharedPreferences(REMENBER, MODE_PRIVATE);
                        SharedPreferences.Editor edit = mSharedPreferences.edit();
                        //记忆不是第一次运行
                        edit.putBoolean(ISFIRSTRUN,false);
                        //editor必须提交
                        edit.commit();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + "start!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + "onResume!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + "onDestroy!!!!!!!!!!!!!!!!!!!!!");
    }
}
