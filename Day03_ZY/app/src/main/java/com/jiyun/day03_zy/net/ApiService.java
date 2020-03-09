package com.jiyun.day03_zy.net;


import com.jiyun.day03_zy.bean.HomeBean;
import com.jiyun.day03_zy.bean.TopicBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String sBaseUrl = "https://cdwan.cn/api/";

    //专题数据请求接口
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page, @Query("size") int size);

    @GET("index")
    Flowable<HomeBean> getHomeData();

}
