package com.example.day11;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day11.bean.WanBean;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<WanBean.DataBean.DatasBean> list;

    public MyAdapter(Context context, ArrayList<WanBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<WanBean.DataBean.DatasBean> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 5) {
            return 2;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_one, viewGroup, false);
            return new oneViewHolder(view);
        } else if (i == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_two, viewGroup, false);
            return new twoViewHolder(view);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof oneViewHolder){
            ((oneViewHolder) viewHolder).one_title.setText(list.get(i).getTitle());
            Glide.with(context).load(list.get(i).getEnvelopePic()).into(((oneViewHolder) viewHolder).one_img);
        }else if (viewHolder instanceof twoViewHolder){
            Glide.with(context).load(list.get(i).getEnvelopePic()).into(((twoViewHolder) viewHolder).two_img);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null){
                    onItemClick.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class oneViewHolder extends RecyclerView.ViewHolder {

        private final ImageView one_img;
        private final TextView one_title;

        public oneViewHolder(@NonNull View itemView) {
            super(itemView);
            one_img = itemView.findViewById(R.id.one_img);
            one_title = itemView.findViewById(R.id.one_title);
        }
    }

    class twoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView two_img;

        public twoViewHolder(@NonNull View itemView) {
            super(itemView);
            two_img = itemView.findViewById(R.id.two_img);
        }
    }

    private onItemClick onItemClick;

    public void setOnItemClick(MyAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface onItemClick{
        void onItemClick(int position);
    }
}
