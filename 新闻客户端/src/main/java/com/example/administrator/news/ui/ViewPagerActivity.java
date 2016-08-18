package com.example.administrator.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {
    public static final String REMENBER = "remenber";
    public static final String ISFIRSTRUN = "IsFirstRun";
    private static final String TAG = "ViewPagerActivity";
    ViewPager mViewPager;
    ArrayList<View> mViewArrayList;
    int[] pictures = {R.drawable.small,R.drawable.wy,R.drawable.welcome,R.drawable.bd};
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //生成shared Preferences文件用于保存用户是否浏览了viewPager
        //MODE_PRIVATE 私有模式
        mSharedPreferences = getSharedPreferences(REMENBER, MODE_PRIVATE);
        //从sp文件中读取key,是否为true
        boolean isFirstRun = mSharedPreferences.getBoolean(ISFIRSTRUN,true);
        Log.d(TAG, "onCreate: " + isFirstRun);
        //根据isFirstRun的值判断是否为第一次运行,如果不是第一次运行则直接开启intent跳转到帧动画欢迎界面
        if (!isFirstRun){
            startActivity(new Intent(ViewPagerActivity.this,MainActivity.class));
            Log.d(TAG, "onCreate: " + "非第一次运行");
            //非第一次运行跳转后也得finish()!
            finish();
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
            mViewPager.setAdapter(new ViewPagerAdapter(mViewArrayList));
            Log.d(TAG, "onCreate: " + "arraylist的长度" + mViewArrayList.size());

            mViewPager.setPageTransformer(true,new ZoomOutPageTransformer() );
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position >= 3){
                        Log.d(TAG, "onPageSelected: " +"图片显示4");
                        //第四张图片后自动跳转
                        startActivity(new Intent(ViewPagerActivity.this,MainActivity.class));
                        //同时停止该activity
                        finish();
                        SharedPreferences.Editor edit = mSharedPreferences.edit();
                        //记忆不是第一次运行
                        edit.putBoolean(ISFIRSTRUN,false);
                        //editor必须提交
                        edit.commit();
                    }else{
                        Log.d(TAG, "onPageSelected: " + "图片显示123");
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }
    /*
    (类)动画实现
     */
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
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
