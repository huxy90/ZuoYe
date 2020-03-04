package com.jiyun.day01_zy.model;

import com.jiyun.day01_zy.ApiService;
import com.jiyun.day01_zy.bean.FuliBean;
import com.jiyun.day01_zy.callback.ResultCallBack;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel {
    public void getData(final ResultCallBack<FuliBean> callBack){
        //android 9.0,不支持非加密的请求,只支持https,如果非要请求http,需要在清单文件中配置
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.sBaseUrl)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                         //发生订阅时被调用的
                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        //请求成功的回调
                        callBack.onSuccess(fuliBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //失败的回调
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //事件完成的回调,这个方法和onError互斥
                    }
                });

    }
}
