package com.example.geeknews.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.ZhiHuDetailsActivity;
import com.example.geeknews.adapter.CollectionListAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.CollectionDbBean;
import com.example.geeknews.utils.DbUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class LikeFragment extends BaseFragment {

    @BindView(R.id.like_recycler)
    RecyclerView likeRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_like;
    }
    String action ="com.like.search";

    private static final String TAG = "CollectionFragment";
    private CollectionListAdapter collectionListAdapter;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, "onReceive: data="+intent.getStringExtra("data"));
            // TODO 查询数据库 by title  注意：like
            // TODO 刷新UI
        }
    };

    @Override
    protected void initView(View view) {
        super.initView(view);
        IntentFilter intentFilter = new IntentFilter(action);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver,intentFilter);

        likeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        collectionListAdapter = new CollectionListAdapter(getActivity());
        likeRecycler.setAdapter(collectionListAdapter);


        List<CollectionDbBean> collectionDbBeans = DbUtils.queryAll();

        collectionListAdapter.initData(collectionDbBeans);
        collectionListAdapter.setOnClick(new CollectionListAdapter.onClick() {
            @Override
            public void onClick(int showType, int newsId) {
                if (showType == 1) {
                    Intent intent = new Intent(getActivity(), ZhiHuDetailsActivity.class);
                    intent.putExtra("newsId", newsId);
                    startActivity(intent);
                } else if (showType == 2) {

                }
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                collectionListAdapter.refresh();
                refreshlayout.finishRefresh(true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (collectionListAdapter != null) {
            List<CollectionDbBean> collectionDbBeans = DbUtils.queryAll();
            collectionListAdapter.initData(collectionDbBeans);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        Log.d(TAG, "onHiddenChanged: " + hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }
}
