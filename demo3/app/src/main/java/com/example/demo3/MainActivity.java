package com.example.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demo3.adapter.MyAdapter;
import com.example.demo3.ben.Bean;
import com.example.demo3.contract.Contract;
import com.example.demo3.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

//张振川-H1810A
public class MainActivity extends AppCompatActivity implements Contract.View {

    private RecyclerView mRecycler;
    private ArrayList<Bean.DataBean> arrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Presenter presenter = new Presenter(this);
        presenter.getDataList();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        arrayList = new ArrayList<>();
        myAdapter = new MyAdapter(this, arrayList);
        mRecycler.setAdapter(myAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSuccess(List<Bean.DataBean> list) {
        arrayList.addAll(list);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String error) {

    }
}
