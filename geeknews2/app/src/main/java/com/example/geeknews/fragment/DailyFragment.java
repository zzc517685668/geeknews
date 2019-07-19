package com.example.geeknews.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.CalenderActivity;
import com.example.geeknews.R;
import com.example.geeknews.ZhiHuDetailsActivity;
import com.example.geeknews.adapter.DailyAdapter;
import com.example.geeknews.base.BaseMvpFragment;
import com.example.geeknews.bean.DailyBean;
import com.example.geeknews.bean.DailyBeforeBean;
import com.example.geeknews.model.DailyModel;
import com.example.geeknews.presenter.DailyPresenter;
import com.example.geeknews.utils.DateUtils;
import com.example.geeknews.view.DailyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DailyFragment extends BaseMvpFragment<DailyPresenter, DailyModel, DailyView> implements DailyView<DailyBean> {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private DailyAdapter dailyAdapter;
    private static final String TAG = "DailyFragment";

    @BindView(R.id.fab_calender)
    FloatingActionButton floatingActionButton;
    private String date;
    boolean isBefore;
    BroadcastReceiver myBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            date = intent.getStringExtra("date");


            // TODO 是否是当天
            String yyyymmdd = DateUtils.getYYYYMMDD();
            if (date.equals(yyyymmdd)) {
                isBefore = false;
                dailyAdapter.setIsBefore(isBefore, "今日新闻");

                mPresenter.getDailyContent();
            } else {
                isBefore = true;
                dailyAdapter.setIsBefore(isBefore, date);
                mPresenter.getDailyBeforeData(date);
            }


            Log.d(TAG, "onReceive: 日期为=" + date + "---" + isBefore);
        }
    };

    @Override
    protected void initView(View view) {
        super.initView(view);
        initBroadcastManager();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        dailyAdapter = new DailyAdapter(getActivity());

        recyclerview.setAdapter(dailyAdapter);

        mPresenter.getDailyContent();

        dailyAdapter.setOnClick(new DailyAdapter.onClick() {
            @Override
            public void onClick(int newsId) {
                Intent intent = new Intent(getActivity(), ZhiHuDetailsActivity.class);
                intent.putExtra("newsId", newsId);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                dailyAdapter.clear();
                mPresenter.getDailyBeforeData(date);
                mPresenter.getDailyContent();
                if (dailyAdapter.isData()){
                    refreshlayout.finishRefresh(true);
                }else {
                    refreshlayout.finishRefresh(false);
                }
            }
        });
    }

    public void initBroadcastManager() {

        IntentFilter intentFilter = new IntentFilter("com.geekdemo.date");

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(myBroadCastReceiver, intentFilter);

    }

    public void unRegisterBroadcast() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(myBroadCastReceiver);

    }



    @Override
    protected void initLastener() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalenderActivity.class));
            }
        });
    }

    /**
     * 执行无图模式
     */
    public void doNoImgMode(){

        if (dailyAdapter!=null){
            dailyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_daily;
    }

    @Override
    protected DailyView initMvpView() {
        return this;
    }

    @Override
    protected DailyModel initMvpModel() {
        return new DailyModel();
    }

    @Override
    protected DailyPresenter initMvpPresenter() {
        return new DailyPresenter();
    }

    @Override
    public void onSuccess(final DailyBean dailyBean) {
        dailyAdapter.initBanner(dailyBean.getTop_stories());
        dailyAdapter.refreshDate("今日新闻");
        dailyAdapter.initDailyList(dailyBean.getStories());
    }

    @Override
    public void getBeforeData(DailyBeforeBean dailyBeforeBean) {
        dailyAdapter.initBeforeData(dailyBeforeBean.getStories());
    }

    @Override
    public void onFail(String error) {
        Log.e(TAG, "onFail: " + error);
    }

}
