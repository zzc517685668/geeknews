package com.example.geeknews.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapter.SectionAdapter;
import com.example.geeknews.base.BaseMvpFragment;
import com.example.geeknews.bean.GankListGirlBean;
import com.example.geeknews.model.GankFuliModel;
import com.example.geeknews.presenter.GankFuliPresenter;
import com.example.geeknews.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GankFuliFragment extends BaseMvpFragment<GankFuliPresenter, GankFuliModel, MyView<GankListGirlBean>> implements MyView<GankListGirlBean> {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private SectionAdapter sectionAdapter;
    private static final String TAG = "GankFuliFragment";

    @Override
    protected MyView<GankListGirlBean> initMvpView() {
        return this;
    }

    @Override
    protected GankFuliModel initMvpModel() {
        return new GankFuliModel();
    }

    @Override
    protected GankFuliPresenter initMvpPresenter() {
        return new GankFuliPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_common_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        viewMain.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        sectionAdapter = new SectionAdapter(getContext());

        viewMain.setAdapter(sectionAdapter);
        mPresenter.getGankFuliContent();


    }

    @Override
    public void onSuccess(final GankListGirlBean gankListGirlBean) {
        if (sectionAdapter != null) {
            sectionAdapter.initData(gankListGirlBean.getResults());
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                sectionAdapter.clear();
                mPresenter.getGankFuliContent();
                if (gankListGirlBean != null && gankListGirlBean.getResults().size() >0) {
                    refreshlayout.finishRefresh(true);
                }else {
                    refreshlayout.finishRefresh(false);
                }
            }
        });
    }

    @Override
    public void onFail(String error) {
        Log.e(TAG, "onFail: " + error);
    }

}
