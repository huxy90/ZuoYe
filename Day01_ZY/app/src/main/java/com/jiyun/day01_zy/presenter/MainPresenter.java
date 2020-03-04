package com.jiyun.day01_zy.presenter;

import com.jiyun.day01_zy.bean.FuliBean;
import com.jiyun.day01_zy.callback.ResultCallBack;
import com.jiyun.day01_zy.model.MainModel;
import com.jiyun.day01_zy.view.MainView;

public class MainPresenter {
    private MainView mView;
    private MainModel mModel;

    public MainPresenter(MainView mView) {
        this.mView = mView;
        mModel = new MainModel();
    }

    public void getData(){
        //调用Model的getData方法
        mModel.getData(new ResultCallBack<FuliBean>() {
            @Override
            public void onSuccess(FuliBean fuliBean) {
                //调用View的setData方法
                if(fuliBean != null && fuliBean.getResults() != null && fuliBean.getResults().size() > 0){
                    mView.setData(fuliBean.getResults());
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
