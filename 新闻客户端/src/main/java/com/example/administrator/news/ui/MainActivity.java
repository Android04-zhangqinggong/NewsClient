package com.example.administrator.news.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        TitleAdapter adapter = new TitleAdapter(titles,this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_insertfragment,new TopFragment());
        transaction.commit();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content_header);
        mRecyclerView.setHasFixedSize(true);
        //设置recycleView 以什么形式展示出来
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.replace(R.id.ll_insertfragment,new TopFragment());
        mTransaction.commit();
    }


    @Override
    public void onItemClick(View view, int postion) {
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        switch (postion){
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
