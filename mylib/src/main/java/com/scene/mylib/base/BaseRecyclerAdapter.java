package com.scene.mylib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.scene.mylib.view.recyclerview.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by scene on 16/01/09.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    List<T> mList;
    Context mContext;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private RecyclerView mRecyclerView;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener) {
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
    }

    public abstract int setItemLayoutResId();

    public BaseRecyclerAdapter(Context mContext, List<T> mList, RecyclerView mRecyclerView) {
        this.mList = mList;
        this.mContext = mContext;
        this.mRecyclerView = mRecyclerView;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(mLayoutInflater.inflate(setItemLayoutResId(), parent, false), mRecyclerView, mOnRecyclerViewItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    /**
//     * 动态增加一条数据
//     *
//     * @param itemDataType 数据实体类对象
//     */
//    public void add(ItemDataType itemDataType) {
//        if (itemDataType != null) {
//            mList.add(itemDataType);
//            notifyDataSetChanged();
//        }
//    }
//
//    /**
//     * 动态增加一组数据集合
//     *
//     * @param ? 数据实体类集合
//     */
//    public void add(List<ItemDataType> itemDataTypes) {
//        if (itemDataTypes.size() > 0) {
//            for (ItemDataType itemDataType : itemDataTypes) {
//                mList.add(itemDataType);
//            }
//            notifyDataSetChanged();
//        }
//    }
//
//    /**
//     * 替换全部数据
//     *
//     * @param itemDataTypes 数据实体类集合
//     */
//    public void replace(List<ItemDataType> itemDataTypes) {
//        mList.clear();
//        if (itemDataTypes.size() > 0) {
//            mList.addAll(itemDataTypes);
//            notifyDataSetChanged();
//        }
//    }
//
//    /**
//     * 移除一条数据集合
//     *
//     * @param position
//     */
//    public void remove(int position) {
//        mList.remove(position);
//        notifyDataSetChanged();
//    }
//
//    /**
//     * 移除所有数据
//     */
//    public void removeAll() {
//        mList.clear();
//        notifyDataSetChanged();
//    }

}
