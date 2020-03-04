package com.jiyun.day01_zy;

import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.jiyun.day01_zy.adapter.RlvAdapter;
import com.jiyun.day01_zy.bean.FuliBean;
import com.jiyun.day01_zy.presenter.MainPresenter;
import com.jiyun.day01_zy.view.MainView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private OwnFragment ownFragment;
    private FragmentManager fm;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        ownFragment = new OwnFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();//开启事务
        ft.add(R.id.frame_main,homeFragment);
        ft.add(R.id.frame_main,ownFragment);
        ft.hide(ownFragment);
        ft.commit();//提交事务
    }

    private void initView() {
        tab = findViewById(R.id.tab_main);
        //添加两个标签页
        tab.addTab(tab.newTab().setText("首页").setIcon(R.mipmap.ic_launcher));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.mipmap.ic_launcher));
        //添加tab的切换功能
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction ft = fm.beginTransaction();
                if(tab.getPosition() == 0){
                    ft.hide(ownFragment).show(homeFragment).commit();
                }else{
                    ft.hide(homeFragment).show(ownFragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
//public class MainActivity extends AppCompatActivity implements MainView {
//
//    private RecyclerView mRlv;
//    private RlvAdapter adapter;
//    private MainPresenter presenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        presenter = new MainPresenter(this);
//        initView();
//        initData();
//    }
//
//    private void initData() {
//        presenter.getData();
//    }
//
//    private void initView() {
//        mRlv = findViewById(R.id.mRlv);
//        mRlv.setLayoutManager(new LinearLayoutManager(this));
//        List<FuliBean.ResultsBean> list = new ArrayList<>();
//        adapter = new RlvAdapter(this,list);
//        mRlv.setAdapter(adapter);
//    }
//
//
//    @Override
//    public void setData(List<FuliBean.ResultsBean> list) {
//        adapter.addData(list);
//    }
//}
