package com.example.geeknews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.utils.SystemUtil;
import com.example.geeknews.view.FlowLayout;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/12/29.
 */

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayMap<String, ArrayMap<String, String>> mMap;

    public NodeAdapter(Context context, ArrayMap<String, ArrayMap<String, String>> mMap) {
        this.mContext = context;
        this.mMap = mMap;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_node, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mMap.keyAt(position));
        holder.flContent.removeAllViews();
        ArrayMap<String, String> mNodeBlock = mMap.valueAt(position);
        for (ArrayMap.Entry<String, String> node : mNodeBlock.entrySet()) {
            TextView tvNode = new TextView(mContext);
            tvNode.setText(node.getValue());
            tvNode.setTextColor(ContextCompat.getColor(mContext, R.color.colorText));
            tvNode.setPadding(SystemUtil.dp2px(6f), SystemUtil.dp2px(6f), SystemUtil.dp2px(6f), SystemUtil.dp2px(6f));
//            tvNode.setOnClickListener(new OnNodeClickListener(node.getKey()));
            holder.flContent.addView(tvNode);
        }
    }

    @Override
    public int getItemCount() {
        return mMap.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_node_title)
        TextView tvTitle;
        @BindView(R.id.fl_node_content)
        FlowLayout flContent;

        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);

            tvTitle = itemView.findViewById(R.id.tv_node_title);
            flContent = itemView.findViewById(R.id.fl_node_content);
        }

    }

    private class OnNodeClickListener implements View.OnClickListener {

        private String nodeName;

        public OnNodeClickListener(String nodeName) {
            this.nodeName = nodeName;
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent();
//            intent.setClass(mContext, NodeListActivity.class);
//            intent.putExtra(Constants.IT_VTEX_NODE_NAME, nodeName);
//            mContext.startActivity(intent);
        }
    }
}
