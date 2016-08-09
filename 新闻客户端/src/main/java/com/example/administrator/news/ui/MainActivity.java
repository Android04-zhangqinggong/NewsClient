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
import com.example.administrator.news.fragment.CarFragment;

public class MainActivity extends AppCompatActivity implements TitleAdapter.MyItemClickListener{
    private RecyclerView mRecyclerView;
    String[] titles = {"头条","汽车","笑话","NBA","图片","娱乐","财经","科技","时尚","IT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        TitleAdapter adapter = new TitleAdapter(titles,this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content_header);
        mRecyclerView.setHasFixedSize(true);
        //设置recycleView 以什么形式展示出来
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onItemClick(View view, int postion) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (titles[postion]){
            case "汽车":
                transaction.replace(R.id.ll_insertfragment,new CarFragment());
        }
    }
}
