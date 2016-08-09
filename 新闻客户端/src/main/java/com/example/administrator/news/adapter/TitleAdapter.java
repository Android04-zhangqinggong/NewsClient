package com.example.administrator.news.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.news.R;

/**
 * Created by Administrator on 2016/8/9.
 */
public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.MyViewHolder> {
    String[] titles;
    Context mContent;
    private MyItemClickListener mListener;

    public TitleAdapter(String[] titles, Context content) {
        this.titles = titles;
        mContent = content;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContent).inflate(R.layout.item_recycle_title, parent, false);
        return new MyViewHolder(view,mListener);
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(titles[position].trim());
    }

    @Override
    public int getItemCount() {
        if(titles != null){
            return titles.length;
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public CardView mCardView;
        public TextView mTextView;
        private MyItemClickListener mListener;
        public MyViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cd_title);
            mTextView = (TextView) itemView.findViewById(R.id.tv_recycle_title);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }
}
