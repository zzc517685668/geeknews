package com.example.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.bean.GankListGirlBean;


import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    List<GankListGirlBean.ResultsBean> dataBeanList = new ArrayList<>();
    Context context;

    public SectionAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<GankListGirlBean.ResultsBean> dataBeanList) {

        if (dataBeanList != null) {

            this.dataBeanList.addAll(dataBeanList);
            notifyDataSetChanged();
        }
    }

    public void clear(){
        dataBeanList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(context).inflate(R.layout.item_section, viewGroup, false);
        return new SectionViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder sectionViewHolder, int i) {
        String url = dataBeanList.get(i).getUrl();
        Glide.with(context).load(url).into(sectionViewHolder.img);

    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}
