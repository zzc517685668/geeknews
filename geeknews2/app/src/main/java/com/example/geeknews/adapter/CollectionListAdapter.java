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
import com.example.geeknews.bean.CollectionDbBean;
import com.example.geeknews.utils.DbUtils;


import java.util.ArrayList;
import java.util.List;

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.ViewHolder> {

    List<CollectionDbBean> collectionDbBeanList = new ArrayList<>();
    Context context;

    public CollectionListAdapter(Context context) {

        this.context = context;
    }

    public void initData(List<CollectionDbBean> collectionDbBeanList) {

        this.collectionDbBeanList.clear();
        this.collectionDbBeanList.addAll(collectionDbBeanList);
        notifyDataSetChanged();

    }

    public void refresh(){
        collectionDbBeanList.clear();
        List<CollectionDbBean> collectionDbBeans = DbUtils.queryAll();
        collectionDbBeanList.addAll(collectionDbBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_like_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final CollectionDbBean dbBean = collectionDbBeanList.get(i);

        Glide.with(context).load(dbBean.getImgUrl()).into(viewHolder.like_img);
        viewHolder.like_form.setText(getTypeName(dbBean.getFromType()));

        viewHolder.like_title.setText(dbBean.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(dbBean.getFromType(), dbBean.getId());
                }
            }
        });
    }

    public String getTypeName(int type) {

        switch (type) {
            case 1:
                return "来自知乎";

            case 2:
                return "来自微信";

        }
        return "";
    }


    @Override
    public int getItemCount() {
        return collectionDbBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView like_img;
        TextView like_title, like_form;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            like_img = itemView.findViewById(R.id.like_img);
            like_title = itemView.findViewById(R.id.like_title);
            like_form = itemView.findViewById(R.id.like_form);
        }
    }

    private onClick onClick;

    public void setOnClick(onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick {
        void onClick(int showType, int newsId);
    }
}
