package com.example.geeknews.callback;

public interface TouchCallBack {

    //上下移动
    void onItemMove(int fromPosition, int toPosition);

    //左右删除
    void oItemDelete(int position);
}
