package com.scene.scenelibdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.scene.mylib.view.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.scene.mylib.view.utils.NetworkUtil;
import com.scene.mylib.view.utils.RecyclerViewStateUtils;
import com.scene.mylib.view.weight.LoadingFooter;
import com.scene.scenelibdemo.base.BaseListActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends BaseListActivity {
    PreviewHandler preHandler = new PreviewHandler(this);

    @Override
    public void initAdapter() {
        mLists = new ArrayList<Object>();
        adapter = new MyAdapter(MainActivity.this, mLists, mRecyclerView);
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
    }

    @Override
    public void initView() {
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        refreshLayout.setOnRefreshListener(this);
        adapter.setOnRecyclerViewItemClickListener(this);
    }


    @Override
    public void reqData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (NetworkUtil.isNetWorkAvalible(MainActivity.this)) {
                    preHandler.sendEmptyMessage(-1);
                } else {
                    preHandler.sendEmptyMessage(-3);
                }

            }
        }, 1500);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(MainActivity.this, "你点击的是：" + position, Toast.LENGTH_SHORT).show();
    }

    private class PreviewHandler extends Handler {

        private WeakReference<MainActivity> ref;

        PreviewHandler(MainActivity activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MainActivity activity = ref.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            isRefresh = false;
            refreshLayout.setRefreshing(false);
            switch (msg.what) {
                case -1:
                    totalCount = 64;
                    if (page == 1) {
                        mLists.clear();
                    }
                    int currentSize = activity.adapter.getItemCount();
                    //activity.mDataAdapter.deleteAllItems();
                    //模拟组装10个数据
                    for (int i = 0; i < 10; i++) {
                        if (currentSize >= totalCount) {
                            break;
                        }
                        ItemBean item = new ItemBean();
                        item.title = "item" + ((10 * (page - 1)) + (i + 1));
                        mLists.add(item);
                    }
                    notifyDataSetChanged();
                    RecyclerViewStateUtils.setFooterViewState(activity.mRecyclerView, LoadingFooter.State.Normal);
                    break;
                case -2:
                    notifyDataSetChanged();
                    break;
                case -3:
                    RecyclerViewStateUtils.setFooterViewState(activity, activity.mRecyclerView, REQUEST_COUNT, LoadingFooter.State.NetWorkError, activity.mFooterClick);
                    break;
            }
        }
    }

}
