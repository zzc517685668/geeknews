package com.example.geeknews;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.geeknews.adapter.NodeAdapter;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.utils.XmlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NodeActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_node_title)
    TextView tvNodeTitle;
    private ArrayMap<String, ArrayMap<String, String>> stringArrayMapArrayMap;
    private int mTitleHeight=0;

    private static final String TAG = "NodeActivity";
    private int mCurrentPosition=0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        super.initView();


        try {
            stringArrayMapArrayMap = XmlUtils.parseNodes(this.getResources().getXml(R.xml.nodes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvContent.setLayoutManager(linearLayoutManager);

        NodeAdapter nodeAdapter = new NodeAdapter(this, stringArrayMapArrayMap);

        rvContent.setAdapter(nodeAdapter);


        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mTitleHeight = tvNodeTitle.getHeight(); // 获取固定头部局
                Log.d(TAG, "onScrollStateChanged: "+mTitleHeight);
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);// 因为第一个已经显示，因此获取下一个头部
                if (view != null) {
                    if (view.getTop() <= mTitleHeight) {// 当item 头部向上滚动，给固定title 也设置向上滚动
                        tvNodeTitle.setY(-(mTitleHeight - view.getTop()));
                        Log.d(TAG, "onScrolled: "+(mTitleHeight - view.getTop()));
                    } else {
                        Log.d(TAG, "onScrolled: setY(0);");
                        tvNodeTitle.setY(0);
                    }
                }
                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) { // 改变头布局的内容及设置 Y 轴位置
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    tvNodeTitle.setY(0);
                    if (stringArrayMapArrayMap != null) {
                        tvNodeTitle.setText(stringArrayMapArrayMap.keyAt(mCurrentPosition));

                        Log.d(TAG, "onScrolled:map.keyAt(mCurrentPosition)"+stringArrayMapArrayMap.keyAt(mCurrentPosition));
                    }
                }
            }
        });
        tvNodeTitle.setText(stringArrayMapArrayMap.keyAt(0));  // 设置默认 头部内容


    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_node;
    }

}
