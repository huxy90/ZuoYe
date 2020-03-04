package com.jiyun.day01_zy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.day01_zy.adapter.RlvAdapter;
import com.jiyun.day01_zy.bean.FuliBean;
import com.jiyun.day01_zy.presenter.MainPresenter;
import com.jiyun.day01_zy.view.MainView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment implements MainView {

    private RecyclerView mRlv;
    private RlvAdapter adapter;
    private MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mRlv = view.findViewById(R.id.mRlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<FuliBean.ResultsBean> list = new ArrayList<>();
        adapter = new RlvAdapter(getContext(),list);
        mRlv.setAdapter(adapter);

        presenter = new MainPresenter(this);
        initData();
        return view;
    }

    private void initData() {
        presenter.getData();
    }

    @Override
    public void setData(List<FuliBean.ResultsBean> list) {
        adapter.addData(list);
    }
}
