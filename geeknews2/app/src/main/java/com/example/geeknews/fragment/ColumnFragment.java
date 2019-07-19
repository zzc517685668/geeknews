package com.example.geeknews.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.geeknews.R;
import com.example.geeknews.adapter.ColumnAdapter;
import com.example.geeknews.base.BaseMvpFragment;

import com.example.geeknews.bean.ColumnBean;
import com.example.geeknews.model.ColumnModel;
import com.example.geeknews.presenter.ColumnPresenter;
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
public class ColumnFragment extends BaseMvpFragment<ColumnPresenter, ColumnModel, MyView> implements MyView<ColumnBean> {

    @BindView(R.id.column_recycler)
    RecyclerView columnRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ColumnAdapter columnAdapter;
    private ArrayList<ColumnBean.DataBean> list;
    private static final String TAG = "ColumnFragment";

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_column;
    }

    @Override
    protected void initView(View view) {
        list = new ArrayList<>();
        columnAdapter = new ColumnAdapter(getContext(), list);
        columnRecycler.setAdapter(columnAdapter);
        columnRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));


    }

    @Override
    public void onSuccess(final ColumnBean columnBean) {
        list.addAll(columnBean.getData());
        columnAdapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                mPresenter.getColumnContent();
                if (columnBean != null && columnBean.getData().size() > 0) {
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
    protected ColumnModel initMvpModel() {
        return new ColumnModel();
    }

    @Override
    protected ColumnPresenter initMvpPresenter() {
        return new ColumnPresenter();
    }

    @Override
    protected void initMvp() {
        super.initMvp();
        mPresenter.getColumnContent();
    }

}
