package com.example.geeknews.callback;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleItemTouchHelpCallBack extends ItemTouchHelper.Callback {

    private TouchCallBack touchCallBack;

    public SimpleItemTouchHelpCallBack(TouchCallBack touchCallBack) {
        this.touchCallBack = touchCallBack;
    }

    //确定可以滑动的方向
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView
            .ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP| ItemTouchHelper.DOWN;

        int swipeFlags = ItemTouchHelper.LEFT;

        return makeMovementFlags(dragFlags,swipeFlags);
    }

    //上下移动
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder
            viewHolder, @NonNull RecyclerView.ViewHolder target) {

        //调用adapter中实现上下移动
        touchCallBack.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    //左右滑动
    @Override

    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        //调用adapter中实现左右滑动
        touchCallBack.oItemDelete(viewHolder.getAdapterPosition());
    }
}
