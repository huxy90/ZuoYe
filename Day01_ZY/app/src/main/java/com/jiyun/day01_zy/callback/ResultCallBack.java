package com.jiyun.day01_zy.callback;

public interface ResultCallBack<T> {
    void onSuccess(T t);
    void onFail(String msg);
}
