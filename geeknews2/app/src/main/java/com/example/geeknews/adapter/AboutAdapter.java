package com.example.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geeknews.R;

import java.util.ArrayList;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> Tv = new ArrayList<>();

    public AboutAdapter(Context context, ArrayList<Integer> tv) {
        this.context = context;
        Tv = tv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_about, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.introduce.setText(Tv.get(i));
    }

    @Override
    public int getItemCount() {
        return Tv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView introduce;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            introduce = itemView.findViewById(R.id.introduce);
        }
    }
}
