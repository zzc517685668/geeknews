package com.example.geeknews.api;

import com.example.geeknews.bean.GankListGirlBean;
import com.example.geeknews.bean.ColumnBean;
import com.example.geeknews.bean.DailyBean;
import com.example.geeknews.bean.DailyBeforeBean;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.bean.HotBean;
import com.example.geeknews.bean.WeChatBean;
import com.example.geeknews.bean.ZhiHuDetailBean;

import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Path;

public interface ApiService {
    public String dailyUrl = "http://news-at.zhihu.com/api/4/";
    public String weChatUrl = "http://api.tianapi.com/";
    public String gankUrl = "http://gank.io/api/";

    /**
     * 干货数据复用
     * http://gank.io/api/data/{tech}/{num}/{page}
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<GankBean> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 干货福利数据
     * http://gank.io/api/data/福利/10/1
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankListGirlBean> getGirlList(@Path("num") int num, @Path("page") int page);

    /**
     * 干货随机妹子数据
     * http://gank.io/api/random/data/福利/20
     */
    @GET("random/data/福利/{page}")
    Observable<GankListGirlBean> getRandomGirl(@Path("num") int num);

    /**
     * 微信精选数据
     * http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1
     */
    @GET("wxnew?key=52b7ec3471ac3bec6846577e79f20e4c")
    Observable<WeChatBean> getWeChatData();

    /**
     * 日报详情数据
     * http://news-at.zhihu.com/api/4/news/{news_id}
     */
    @GET("news/{news_id}")
    Observable<ZhiHuDetailBean> getDetail(@Path("news_id") int newsId);

    /**
     * 日报今天数据
     * http://news-at.zhihu.com/api/4/news/latest
     */
    @GET("news/latest")
    Observable<DailyBean> getDailyData();

    /**
     * 日报过往数据
     * http://news-at.zhihu.com/api/4/news/before/20190710
     */
    @GET("news/before/{date}")
    Observable<DailyBeforeBean> getDailyBeforeList(@Path("date") String date);

    /**
     * 专栏数据
     * http://news-at.zhihu.com/api/4/sections
     */
    @GET("sections")
    Observable<ColumnBean> getColumnData();

    /**
     * 热门数据
     * http://news-at.zhihu.com/api/4/news/hot
     */
    @GET("news/hot")
    Observable<HotBean> getHotData();
}
