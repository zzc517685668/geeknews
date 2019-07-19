package com.example.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.bean.ColumnBean;

import java.util.ArrayList;

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ColumnBean.DataBean> list;

    public ColumnAdapter(Context context, ArrayList<ColumnBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_column_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.column_name.setText(list.get(i).getName());
        viewHolder.column_description.setText(list.get(i).getDescription());
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHolder.column_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView column_img;
        private final TextView column_description,column_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            column_img = itemView.findViewById(R.id.column_img);
            column_description = itemView.findViewById(R.id.column_description);
            column_name = itemView.findViewById(R.id.column_name);
        }
    }
}
