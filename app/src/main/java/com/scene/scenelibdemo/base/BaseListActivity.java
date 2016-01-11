package com.scene.scenelibdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scene.mylib.base.BaseRecyclerAdapter;
import com.scene.mylib.view.recyclerview.EndlessRecyclerOnScrollListener;
import com.scene.mylib.view.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.scene.mylib.view.recyclerview.OnRecyclerViewItemClickListener;
import com.scene.mylib.view.utils.RecyclerViewStateUtils;
import com.scene.mylib.view.weight.LoadingFooter;
import com.scene.scenelibdemo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by scene on 16/01/09.
 */
public abstract class BaseListActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, OnRecyclerViewItemClickListener {

    @Bind(R.id.mRecyclerView)
    public RecyclerView mRecyclerView;
    @Bind(R.id.refreshLayout)
    public SwipeRefreshLayout refreshLayout;
    public int page = 1;
    public int totalCount = 0;
    public static final int REQUEST_COUNT = 10;

    public List<Object> mLists;
    public HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter = null;
    public BaseRecyclerAdapter adapter;

    public boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_list_layout);
        ButterKnife.bind(this);
        initAdapter();
        initView();
        isRefresh=true;
        reqData();
    }

    /**
     * 初始化adapter
     */
    public abstract void initAdapter();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 获取数据
     */
    public abstract void reqData();

    public abstract void onItemClick(int position);

    @Override
    public void onRefresh() {
        LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
        if (state == LoadingFooter.State.Loading||isRefresh) {
            Log.d("@Cundong", "the state is Loading, just wait..");
            refreshLayout.setRefreshing(false);
            return;
        }
        isRefresh = true;
        page = 1;
        reqData();
    }

    public void notifyDataSetChanged() {
        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
    }

    public View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(BaseListActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
            reqData();
        }
    };

    @Override
    public void onRecyclerViewItemClick(int position) {
        onItemClick(position);
    }


    public EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);

            if (state == LoadingFooter.State.Loading||isRefresh) {
                Log.d("@Cundong", "the state is Loading, just wait..");
                return;
            }
            if (mLists.size() < totalCount) {
                // loading more
                RecyclerViewStateUtils.setFooterViewState(BaseListActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                page++;
                reqData();
            } else {
                //the end
                RecyclerViewStateUtils.setFooterViewState(BaseListActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
            }
        }
    };


}
