package com.example.geeknews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CheckBox;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.utils.Constants;

import butterknife.BindView;
import butterknife.Unbinder;


public class SettingFragment extends BaseFragment {

    @BindView(R.id.img_checkbox)
    CheckBox imgCheckbox;
    @BindView(R.id.night_checkbox)
    CheckBox nightCheckbox;
    Unbinder unbinder;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        if (Constants.isNoIMg) {
            imgCheckbox.setChecked(true);
        } else {
            imgCheckbox.setChecked(false);
        }
    }

    @Override
    protected void initLastener() {
        imgCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 如果是无图模式
                if (Constants.isNoIMg) {
                    Constants.isNoIMg = false;
                } else {
                    Constants.isNoIMg = true;
                }
            }
        });
        nightCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.isNightMode) {
                    Constants.isNightMode = false;
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    Constants.isNightMode = true;
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO);
                }
                getActivity().recreate();

            }
        });
    }

}
