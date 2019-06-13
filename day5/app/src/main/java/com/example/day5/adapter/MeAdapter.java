package com.example.day5.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day5.R;
import com.example.day5.ben.HotBean;
import com.example.day5.ben.RecentBean;

import java.util.ArrayList;

public class MeAdapter extends RecyclerView.Adapter<MeAdapter.ViewHodler> {

    private Context context;
    private ArrayList<RecentBean> list;

    public MeAdapter(Context context, ArrayList<RecentBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.me_fragment_item_layout, viewGroup, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, final int i) {
        viewHodler.me_title.setText(list.get(i).getTitle());
        viewHodler.me_newsid.setText(list.get(i).getNews_id()+"");
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHodler.me_img);
        viewHodler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick!=null){
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

        private final ImageView me_img;
        private final TextView me_title,me_newsid;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            me_img = itemView.findViewById(R.id.me_img);
            me_title = itemView.findViewById(R.id.me_title);
            me_newsid = itemView.findViewById(R.id.me_newsid);

        }
    }

    public onClick onClick;

    public void setOnClick(MeAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick{
        void onClick(int position);
    }
}
