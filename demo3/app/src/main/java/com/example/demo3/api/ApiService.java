package com.example.demo3.api;

import com.example.demo3.ben.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    public String url = "http://www.qubaobei.com/";

    @GET("ios/cf/dish_list.php?stage_id=1&limit=20")
    Observable<Bean> getData();
}
