package com.example.administrator.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.TitleAdapter;
import com.example.administrator.news.fragment.CaijingFragment;
import com.example.administrator.news.fragment.GuojiFragment;
import com.example.administrator.news.fragment.GuoneiFragment;
import com.example.administrator.news.fragment.JunshiFragment;
import com.example.administrator.news.fragment.KejiFragment;
import com.example.administrator.news.fragment.ShehuiFragment;
import com.example.administrator.news.fragment.ShishangFragment;
import com.example.administrator.news.fragment.TopFragment;
import com.example.administrator.news.fragment.YuleFragment;

public class MainActivity extends AppCompatActivity implements TitleAdapter.MyItemClickListener{
    private RecyclerView mRecyclerView;
    String[] titles = {"头条","社会","国内","国际","娱乐","军事","科技","财经","时尚"};
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    ImageView mImageView;
    private Intent mIntent;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content_header);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_left);
        mRecyclerView.setHasFixedSize(true);

        //设置recycleView 以什么形式展示出来
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        TitleAdapter adapter = new TitleAdapter(titles,this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        //默认显示TopFragment内容
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.replace(R.id.ll_insertfragment,new TopFragment());
        mTransaction.commit();
        //NavigationDrawer
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar, R.string.start,R.string.end);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //navigation里的header里的imageView设置点击事件
        View headerView = mNavigationView.getHeaderView(0);
        mImageView = (ImageView) headerView.findViewById(R.id.navigation_header_login);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时判断是否已经登录,若已登录,跳转至账号显示界面(可退出登录),没有登录,则跳转至登录界面
                SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
                boolean islogin = isLogin.getBoolean("islogin", false);
                if (islogin){
                    mIntent = new Intent(MainActivity.this,AccountShowActivity.class);
                }else{
                    mIntent = new Intent(MainActivity.this,LoginActivity.class);
                }
                startActivity(mIntent);
                //跳转后关闭navigationView
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        //navigation里的item设置点击事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_item_collect:
                        Toast.makeText(MainActivity.this, "点击了我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_item_address:
                        Toast.makeText(MainActivity.this, "点击了我的位置", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_item_like:
                        Toast.makeText(MainActivity.this, "点击了我的喜欢", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }
    //设置左上角小菜单键.点击展开或关闭抽屉
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    //返回键设置,点击返回键,若抽屉打开,先关闭抽屉,再次点击退出程序
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        switch (postion){
            case 0:
                mTransaction.replace(R.id.ll_insertfragment,new TopFragment());
                mTransaction.commit();
                break;
            case 1:
                mTransaction.replace(R.id.ll_insertfragment,new ShehuiFragment());
                mTransaction.commit();
                break;
            case 2:
                mTransaction.replace(R.id.ll_insertfragment,new GuoneiFragment());
                mTransaction.commit();
                break;
            case 3:
                mTransaction.replace(R.id.ll_insertfragment,new GuojiFragment());
                mTransaction.commit();
                break;
            case 4:
                mTransaction.replace(R.id.ll_insertfragment,new YuleFragment());
                mTransaction.commit();
                break;
            case 5:
                mTransaction.replace(R.id.ll_insertfragment,new JunshiFragment());
                mTransaction.commit();
                break;
            case 6:
                mTransaction.replace(R.id.ll_insertfragment,new KejiFragment());
                mTransaction.commit();
                break;
            case 7:
                mTransaction.replace(R.id.ll_insertfragment,new CaijingFragment());
                mTransaction.commit();
                break;
            case 8:
                mTransaction.replace(R.id.ll_insertfragment,new ShishangFragment());
                mTransaction.commit();
                break;
            default:
                break;
        }
    }
}
