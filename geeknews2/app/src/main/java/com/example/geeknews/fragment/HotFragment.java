package com.example.geeknews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapter.HotAdapter;
import com.example.geeknews.base.BaseMvpFragment;
import com.example.geeknews.bean.HotBean;
import com.example.geeknews.model.HotModel;
import com.example.geeknews.presenter.HotPresenter;
import com.example.geeknews.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseMvpFragment<HotPresenter, HotModel, MyView<HotBean>> implements MyView<HotBean> {

    @BindView(R.id.hot_recycler)
    RecyclerView hotRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ArrayList<HotBean.RecentBean> list;
    private HotAdapter hotAdapter;
    private static final String TAG = "HotFragment";

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        list = new ArrayList<>();
        hotAdapter = new HotAdapter(getContext(), list);
        hotRecycler.setAdapter(hotAdapter);
        hotRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onSuccess(final HotBean hotBean) {
        list.addAll(hotBean.getRecent());
        hotAdapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                mPresenter.getHotContent();
                if (hotBean.getRecent().size() > 0 && hotBean != null){
                    refreshlayout.finishRefresh(true);
                }else {
                    refreshlayout.finishRefresh(false);
                }
            }
        });
    }

    @Override
    public void onFail(String error) {
        Log.e(TAG, "onFail: "+error);
    }

    @Override
    protected MyView<HotBean> initMvpView() {
        return this;
    }

    @Override
    protected HotModel initMvpModel() {
        return new HotModel();
    }

    @Override
    protected HotPresenter initMvpPresenter() {
        return new HotPresenter();
    }

    @Override
    protected void initMvp() {
        super.initMvp();
        mPresenter.getHotContent();
    }

}
