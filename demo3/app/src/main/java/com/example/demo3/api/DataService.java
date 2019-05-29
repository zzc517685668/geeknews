package com.example.demo3.api;

import com.example.demo3.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {
    public static volatile ApiService apiService;
    private static final long DEFAULT_TIMEOUT = 20000;
    public static ApiService getApiService(){
        if (apiService == null){
            synchronized (DataService.class){
                if (apiService == null){
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    if (BuildConfig.DEBUG){
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    }else {
                        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl(ApiService.url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    apiService = retrofit.create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
