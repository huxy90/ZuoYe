package com.jiyun.day03_zy.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.jiyun.day03_zy.R;
import com.jiyun.day03_zy.adapter.RlvTopicAdapter;
import com.jiyun.day03_zy.bean.TopicBean;
import com.jiyun.day03_zy.net.ApiService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopicFragment extends Fragment {

    private RecyclerView mRlv;
    private SmartRefreshLayout mSrl;
    public RlvTopicAdapter adapter;
    private int mPage = 1;
    private int mSize = 10;
    private int mTotalPages;

    public static TopicFragment getInstance() {
        TopicFragment topicFragment = new TopicFragment();
        return topicFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持rxjava
                .addConverterFactory(GsonConverterFactory.create())//数据转换工厂
                .build();
        retrofit.create(ApiService.class)
                .getTopic(mPage,mSize)
                .subscribeOn(Schedulers.io())//被观察者执行的子线程
                .observeOn(AndroidSchedulers.mainThread())//观察者运行的线程,主线程
                .subscribeWith(new ResourceSubscriber<TopicBean>() {
                    @Override
                    public void onNext(TopicBean topicBean) {

                        if (topicBean != null && topicBean.getErrno() == 0) {
                            mTotalPages = topicBean.getData().getTotalPages();
                            adapter.addData(topicBean.getData().getData());
                            hidheader();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                        hidheader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void hidheader(){
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
    }
    private void initView(View view) {
        mRlv = view.findViewById(R.id.rlv);
        mSrl = view.findViewById(R.id.srl);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<TopicBean.DataBeanX.DataBean> list = new ArrayList<>();
        adapter = new RlvTopicAdapter(getContext(), list);
        mRlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new RlvTopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(),"item： "+position,Toast.LENGTH_LONG).show();
            }
        });
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(mPage < mTotalPages){
                    mPage++;
                    initData();
                }else{
                    Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                 adapter.list.clear();
                 mPage = 1;
                 initData();
            }
        });
    }
}
