package com.example.demo3.contract;

import com.example.demo3.ben.Bean;

import java.util.List;

public interface Contract {
    interface CallBack{
        void getSuccess(List<Bean.DataBean> list);
        void getFailed(String error);
    }

    interface Model {
        void getRequestData(CallBack callBack);
    }

    interface View {
        void onSuccess(List<Bean.DataBean> list);
        void onFailed(String error);
    }

    interface Presenter {
        void getDataList();
    }
}
