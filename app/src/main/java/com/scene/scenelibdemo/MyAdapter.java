package com.scene.scenelibdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scene.mylib.base.BaseRecyclerAdapter;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 16/01/09.
 */
public class MyAdapter<T> extends BaseRecyclerAdapter {
    private List<ItemBean> mLists;

    public MyAdapter(Context mContext, List<ItemBean> mList, RecyclerView mRecyclerView) {
        super(mContext, mList, mRecyclerView);
        this.mLists = mList;
    }

    /**
     * 设置item的样式id
     *
     * @return
     */
    @Override
    public int setItemLayoutResId() {
        return R.layout.sample_item_text;
    }

    /**
     * 处理数据展示
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemBean bean = mLists.get(position);
        ((TextView)holder.itemView.findViewById(R.id.info_text)).setText(bean.getTitle());


    }

}
