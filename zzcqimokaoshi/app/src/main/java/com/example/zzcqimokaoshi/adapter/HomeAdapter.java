package com.example.zzcqimokaoshi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zzcqimokaoshi.R;
import com.example.zzcqimokaoshi.bean.Bean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Bean.ResultBean> list;

    public HomeAdapter(Context context, ArrayList<Bean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 1;
        }
        if (position == 1){
            return 2;
        }
        if (position == 2){
            return 3;
        }
        if (position == 3){
            return 4;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.home_banner, viewGroup, false);
            return new BannerViewHolder(view);
        }
        if (i == 2){
            View view = LayoutInflater.from(context).inflate(R.layout.home_item_one, viewGroup, false);
            return new oneViewHolder(view);
        }
        if (i == 3){
            View view = LayoutInflater.from(context).inflate(R.layout.home_item_two, viewGroup, false);
            return new twoViewHolder(view);
        }
        if (i == 4){
            View view = LayoutInflater.from(context).inflate(R.layout.home_item_three, viewGroup, false);
            return new threeViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof BannerViewHolder){
            ArrayList<String> urls = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                urls.add(list.get(j).getHeader());
            }
            ((BannerViewHolder) viewHolder).home_banner.setImages(urls).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    String url = (String) path;
                    Glide.with(context).load(url).into(imageView);
                }
            }).setDelayTime(2000)
                    .start();
        }else if (viewHolder instanceof oneViewHolder){
            ((oneViewHolder) viewHolder).home_item_one_title.setText(list.get(i).getTop_comments_name());
            ((oneViewHolder) viewHolder).home_item_one_content.setText(list.get(i).getTop_comments_content());
            Glide.with(context).load(list.get(i).getHeader()).into(((oneViewHolder) viewHolder).home_item_one_img);
        }else if (viewHolder instanceof twoViewHolder){
            ((twoViewHolder) viewHolder).home_item_two_title.setText(list.get(i).getTop_comments_name());
            ((twoViewHolder) viewHolder).home_item_two_content.setText(list.get(i).getTop_comments_content());
            Glide.with(context).load(list.get(i).getHeader()).into(((twoViewHolder) viewHolder).home_item_two_img);
            Glide.with(context).load(list.get(i).getThumbnail()).into(((twoViewHolder) viewHolder).home_item_two_img2);
        }else if (viewHolder instanceof threeViewHolder){
            ((threeViewHolder) viewHolder).home_item_three_title.setText(list.get(i).getTop_comments_name());
            ((threeViewHolder) viewHolder).home_item_three_content.setText(list.get(i).getTop_comments_content());
            Glide.with(context).load(list.get(i).getHeader()).into(((threeViewHolder) viewHolder).home_item_three_img);
            Glide.with(context).load(list.get(i).getThumbnail()).into(((threeViewHolder) viewHolder).home_item_three_img2);
            Glide.with(context).load(list.get(i).getImages()).into(((threeViewHolder) viewHolder).home_item_three_img3);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Banner home_banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            home_banner = itemView.findViewById(R.id.home_banner);
        }
    }

    class oneViewHolder extends RecyclerView.ViewHolder {

        private final TextView home_item_one_title,home_item_one_content;
        private final ImageView home_item_one_img;

        public oneViewHolder(@NonNull View itemView) {
            super(itemView);
            home_item_one_title = itemView.findViewById(R.id.home_item_one_title);
            home_item_one_content = itemView.findViewById(R.id.home_item_one_content);
            home_item_one_img = itemView.findViewById(R.id.home_item_one_img);
        }
    } class twoViewHolder extends RecyclerView.ViewHolder {

        private final TextView home_item_two_title,home_item_two_content;
        private final ImageView home_item_two_img,home_item_two_img2;

        public twoViewHolder(@NonNull View itemView) {
            super(itemView);
            home_item_two_title = itemView.findViewById(R.id.home_item_two_title);
            home_item_two_content = itemView.findViewById(R.id.home_item_two_content);
            home_item_two_img = itemView.findViewById(R.id.home_item_two_img);
            home_item_two_img2 = itemView.findViewById(R.id.home_item_two_img2);
        }
    }

    class threeViewHolder extends RecyclerView.ViewHolder {

        private final TextView home_item_three_title,home_item_three_content;
        private final ImageView home_item_three_img,home_item_three_img2,home_item_three_img3;

        public threeViewHolder(@NonNull View itemView) {
            super(itemView);
            home_item_three_title = itemView.findViewById(R.id.home_item_three_title);
            home_item_three_content = itemView.findViewById(R.id.home_item_three_content);
            home_item_three_img = itemView.findViewById(R.id.home_item_three_img);
            home_item_three_img2 = itemView.findViewById(R.id.home_item_three_img2);
            home_item_three_img3 = itemView.findViewById(R.id.home_item_three_img3);
        }
    }
}
