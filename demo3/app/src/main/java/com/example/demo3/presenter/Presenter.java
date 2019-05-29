package com.example.demo3.presenter;

import com.example.demo3.ben.Bean;
import com.example.demo3.contract.Contract;
import com.example.demo3.model.Model;

import java.util.List;

public class Presenter implements Contract.Presenter {

    private final Model model;
    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void getDataList() {
        if (model != null){
            model.getRequestData(new Contract.CallBack() {
                @Override
                public void getSuccess(List<Bean.DataBean> list) {
                    view.onSuccess(list);
                }

                @Override
                public void getFailed(String error) {

                }
            });
        }
    }
}
