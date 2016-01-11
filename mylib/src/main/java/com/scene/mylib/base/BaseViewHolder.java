package com.scene.mylib.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scene.mylib.view.recyclerview.OnRecyclerViewItemClickListener;
import com.scene.mylib.view.recyclerview.RecyclerViewUtils;


/**
 * Created by scene on 16/01/09.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private RecyclerView mRecyclerView;

    public BaseViewHolder(View itemView, RecyclerView mRecyclerView, OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener) {
        super(itemView);
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
        this.mRecyclerView = mRecyclerView;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mOnRecyclerViewItemClickListener.onRecyclerViewItemClick(RecyclerViewUtils.getLayoutPosition(mRecyclerView, this));
    }
}
