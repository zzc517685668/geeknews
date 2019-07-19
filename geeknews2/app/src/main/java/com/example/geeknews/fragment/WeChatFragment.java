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
import com.example.geeknews.adapter.WechatAdapter;
import com.example.geeknews.base.BaseMvpFragment;
import com.example.geeknews.bean.WeChatBean;
import com.example.geeknews.model.WeChatModel;
import com.example.geeknews.presenter.WeChatPresenter;
import com.example.geeknews.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends BaseMvpFragment<WeChatPresenter, WeChatModel, MyView> implements MyView<WeChatBean> {


    @BindView(R.id.wechat_recycler)
    RecyclerView wechatRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<WeChatBean.NewslistBean> list;
    private WechatAdapter wechatAdapter;
    private static final String TAG = "WeChatFragment";

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        list = new ArrayList<>();
        wechatAdapter = new WechatAdapter(getContext(), list);
        wechatRecycler.setAdapter(wechatAdapter);
        wechatRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void onSuccess(final WeChatBean weChatBean) {
        list.addAll(weChatBean.getNewslist());
        wechatAdapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                mPresenter.getWeChatContent();
                if (weChatBean != null && weChatBean.getNewslist().size() > 0) {
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

    @Override
    protected MyView initMvpView() {
        return this;
    }

    @Override
    protected WeChatModel initMvpModel() {
        return new WeChatModel();
    }

    @Override
    protected WeChatPresenter initMvpPresenter() {
        return new WeChatPresenter();
    }

    @Override
    protected void initMvp() {
        super.initMvp();
        mPresenter.getWeChatContent();
    }
}
