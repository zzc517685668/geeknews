package com.example.demo3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo3.R;
import com.example.demo3.ben.Bean;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHodler> {

    private Context context;
    private ArrayList<Bean.DataBean> list;

    public MyAdapter(Context context, ArrayList<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {
        viewHodler.tv_title.setText(list.get(i).getTitle());
        viewHodler.tv_food_str.setText(list.get(i).getFood_str());
        Glide.with(context).load(list.get(i).getPic()).into(viewHodler.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv_title,tv_food_str;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_food_str = itemView.findViewById(R.id.tv_food_str);
        }
    }
}
