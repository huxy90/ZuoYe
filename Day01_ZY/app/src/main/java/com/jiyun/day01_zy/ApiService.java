package com.jiyun.day01_zy;


import io.reactivex.Observable;
import com.jiyun.day01_zy.bean.FuliBean;

import retrofit2.http.GET;

public interface ApiService {
    //基础连接必须是以"/"结尾
    String sBaseUrl = "http://gank.io/api/data/";

    @GET("福利/10/3")
    Observable<FuliBean> getData();
}
