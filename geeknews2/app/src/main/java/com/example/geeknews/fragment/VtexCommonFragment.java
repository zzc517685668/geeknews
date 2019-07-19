package com.example.geeknews.fragment;

import android.util.Log;

import com.example.geeknews.R;
import com.example.geeknews.adapter.GankAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.BaseMvpFragment;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.presenter.GankPresenter;
import com.example.geeknews.view.GankView;


public class VtexCommonFragment extends BaseFragment {


    private static final String TAG = "GoldCommonFragment";
    private GankAdapter gankAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.view_common_list;
    }


}
