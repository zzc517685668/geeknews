package com.example.day4demo.contract;


public interface Contract {
    interface CallBack{
        void onLoginData(String name,String pass);
        void onRegister(String name,String pass,String phone,String verify);
        void onVerify(String verify);
    }

    interface Model {
        void getLoginData(CallBack callBack,String name,String pass);
        void getRegister(CallBack callback,String name,String pass,String phone,String verify);
        void getVerify(CallBack callback);
    }

    interface View {
        void onLoginData(String name,String pass);
        void onRegister(String name,String pass,String phone,String verify);
        void onVerify(String verify);
    }

    interface Presenter {
        void getData();
    }
}
