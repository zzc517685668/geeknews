package com.example.demo3two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ArrayList<Dog> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        List<Dog> dogs = Utils.queryAll();
        list = new ArrayList<>();
        list.addAll(dogs);
        adapter = new MyAdapter(this, list);
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        for (int i = 0; i < 20; i++) {
            Dog dog = new Dog(null, "小白" + i, "" + i);
            Utils.insert(dog);
        }

    }
}
