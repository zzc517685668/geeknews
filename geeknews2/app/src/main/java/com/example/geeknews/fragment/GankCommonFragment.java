package com.example.geeknews.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.adapter.GankAdapter;
import com.example.geeknews.base.BaseMvpFragment;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.bean.GankListGirlBean;
import com.example.geeknews.model.GankModel;
import com.example.geeknews.presenter.GankPresenter;
import com.example.geeknews.utils.Constants;
import com.example.geeknews.view.GankView;
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
public class GankCommonFragment extends BaseMvpFragment<GankPresenter, GankModel, GankView> implements GankView {

    @BindView(R.id.iv_tech_blur)
    ImageView ivTechBlur;
    @BindView(R.id.tv_tech_copyright)
    TextView tvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String key;
    private GankAdapter gankAdapter;
    private static final String TAG = "GankCommonFragment";

    @Override
    protected int getLayoutID() {
        return R.layout.gank__fragment;
    }

    @Override
    protected void initView(final View view) {
        Bundle arguments = getArguments();
        key = arguments.getString(Constants.IT_GANK_TYPE);

        viewMain.setLayoutManager(new LinearLayoutManager(getContext()));
        gankAdapter = new GankAdapter(getContext());
        viewMain.setAdapter(gankAdapter);

        mPresenter.getGankContent(key);
        mPresenter.getGankGirlContent();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                gankAdapter.clear();
                mPresenter.getGankContent(key);
                mPresenter.getGankGirlContent();
                if (viewMain != null) {
                    refreshlayout.finishRefresh(true);
                }
            }
        });
    }

    @Override
    public void onFail(String error) {
        Log.e(TAG, "onFail: " + error);
    }


    @Override
    protected GankView initMvpView() {
        return this;
    }

    @Override
    protected GankModel initMvpModel() {
        return new GankModel();
    }

    @Override
    protected GankPresenter initMvpPresenter() {
        return new GankPresenter();
    }

    @Override
    public void onGankData(GankBean gankBean) {
        if (gankAdapter != null) {
            gankAdapter.initData(gankBean.getResults());
        }
    }

    @Override
    public void onGankGirl(GankListGirlBean gankListGirlBean) {
        List<GankListGirlBean.ResultsBean> results = gankListGirlBean.getResults();
        int i = (int) (Math.random() * results.size()-1);
        String url = results.get(i).getUrl();
        String who = results.get(i).getWho();
        Glide.with(getContext()).load(url).into(ivTechBlur);
        tvTechCopyright.setText(who);
    }

}
