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
import com.example.geeknews.bean.WeChatBean;

import java.util.ArrayList;

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.ViewHodler> {

    private Context context;
    private ArrayList<WeChatBean.NewslistBean> list;

    public WechatAdapter(Context context, ArrayList<WeChatBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_wechat_item, viewGroup, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, final int i) {
        viewHodler.wechat_title.setText(list.get(i).getTitle());
        viewHodler.wechat_description.setText(list.get(i).getDescription());
        viewHodler.wechat_ctime.setText(list.get(i).getCtime());
        Glide.with(context).load(list.get(i).getPicUrl()).into(viewHodler.wechat_img);

        viewHodler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null){
                    onClick.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        private final ImageView wechat_img;
        private final TextView wechat_title,wechat_description,wechat_ctime;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            wechat_img = itemView.findViewById(R.id.like_img);
            wechat_title = itemView.findViewById(R.id.wechat_title);
            wechat_description = itemView.findViewById(R.id.wechat_description);
            wechat_ctime = itemView.findViewById(R.id.wechat_ctime);
        }
    }

    public onClick onClick;

    public void setOnClick(onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick{
        void onClick(int position);
    }
}
