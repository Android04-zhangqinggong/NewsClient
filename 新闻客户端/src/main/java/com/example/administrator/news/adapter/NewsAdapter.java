package com.example.administrator.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.news.bean.News;
import com.example.administrator.news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/8.
 */
public class NewsAdapter extends BaseAdapter{
    Context mContext;
    ArrayList<News> mArrayList;

    public NewsAdapter(Context context, ArrayList<News> arrayList) {
        mContext = context;
        mArrayList = arrayList;
    }

    @Override
    public int getCount() {
        if(mArrayList!=null){
            return mArrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if(convertView == null){
            mViewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_list_news,null);
            mViewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_icon);
            mViewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.tv_title);
            mViewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.tv_authorname);
            mViewHolder.mTextView3 = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext)
                .load(mArrayList.get(position).getIcon())
                .into(mViewHolder.mImageView);
        mViewHolder.mTextView1.setText(mArrayList.get(position).getTitle());
        mViewHolder.mTextView2.setText(mArrayList.get(position).getAuthorName());
        mViewHolder.mTextView3.setText(mArrayList.get(position).getTime());

        return convertView;
    }
    private static class ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
    }
}
