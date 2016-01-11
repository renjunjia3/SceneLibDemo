package com.scene.scenelibdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scene.mylib.view.recyclerview.OnRecyclerViewItemClickListener;
import com.scene.mylib.view.recyclerview.RecyclerViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by scene on 16/01/09.
 * 自己界面的ViewHolder 必须继承自BaseViewHolder
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.info_text)
    TextView infoText;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private RecyclerView recyclerView;

    public MyViewHolder(View itemView, RecyclerView mRecyclerView, OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
        this.recyclerView = mRecyclerView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onRecyclerViewItemClick(RecyclerViewUtils.getLayoutPosition(recyclerView, MyViewHolder.this));
            }
        });
    }

}
