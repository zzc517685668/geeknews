package com.example.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.bean.GoldTabBean;
import com.example.geeknews.callback.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

public class GoldDetailsAdapter extends RecyclerView.Adapter<GoldDetailsAdapter.ViewHolder> implements TouchCallBack {

    private Context context;
    private ArrayList<GoldTabBean> list;

    public GoldDetailsAdapter(Context context, ArrayList<GoldTabBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gold_detail, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final GoldTabBean goldTabBean = list.get(i);

        viewHolder.name.setText(goldTabBean.getTitles());
        viewHolder.sw.setChecked(goldTabBean.isSelected());

        viewHolder.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goldTabBean.setSelected(isChecked);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     *  上下移动
     * @param fromPosition  移动前的位置
     * @param toPosition   移动后的位置
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //集合数据交换
        Collections.swap(list,fromPosition,toPosition);
        //局部交换刷新
        notifyItemMoved(fromPosition,toPosition);
    }

    /**
     *  左右删除
     * @param position 删除的位置
     */
    @Override
    public void oItemDelete(int position) {
        //集合数据删除
        list.remove(position);
        //局部刷新
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final SwitchCompat sw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            sw = itemView.findViewById(R.id.sw);
        }
    }
}
