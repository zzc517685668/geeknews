package com.example.geeknews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.geeknews.adapter.GoldDetailsAdapter;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.bean.GoldTabBean;
import com.example.geeknews.callback.SimpleItemTouchHelpCallBack;

import java.util.ArrayList;

import butterknife.BindView;

public class GoldDetailActivity extends BaseActivity {
    @BindView(R.id.gold_lv)
    RecyclerView gold_lv;
    private ArrayList<GoldTabBean> list;
    private GoldDetailsAdapter goldDetailsAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_gold_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        list = (ArrayList<GoldTabBean>) intent.getSerializableExtra("list");

        goldDetailsAdapter = new GoldDetailsAdapter(this, list);
        gold_lv.setAdapter(goldDetailsAdapter);
        gold_lv.setLayoutManager(new LinearLayoutManager(this));

        SimpleItemTouchHelpCallBack helpCallBack = new SimpleItemTouchHelpCallBack(goldDetailsAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(helpCallBack);
        touchHelper.attachToRecyclerView(gold_lv);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("list", list);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
