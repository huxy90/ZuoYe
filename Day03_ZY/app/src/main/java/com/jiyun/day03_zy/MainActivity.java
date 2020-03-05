package com.jiyun.day03_zy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jiyun.day03_zy.adapter.VPFrgmentAdapter;
import com.jiyun.day03_zy.adapter.VpImageAdapter;
import com.jiyun.day03_zy.ui.fragment.CartFragment;
import com.jiyun.day03_zy.ui.fragment.MainPageFragment;
import com.jiyun.day03_zy.ui.fragment.MeFragment;
import com.jiyun.day03_zy.ui.fragment.SortFragment;
import com.jiyun.day03_zy.ui.fragment.TopicFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private ViewPager mVp;
    private TabLayout mTabLayout;
    private ArrayList<String> mTitles;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initTitles();
        initFragments();
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        toolbar();
        vp();
        //关联tablayout和viewpager
        mTabLayout.setupWithViewPager(mVp);
        for(int i =0; i < mFragments.size();i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(tabView(i));
        }
    }

    private void vp() {
        VPFrgmentAdapter adapter = new VPFrgmentAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mVp.setAdapter(adapter);
    }

    private void toolbar() {
        mToolBar.setTitle(R.string.title);
        setSupportActionBar(mToolBar);
    }


    private void initTitles() {
        //ctrl+alt+f 变成员变量
        mTitles = new ArrayList<>();
        //R.string.main_page
        String str = getResources().getString(R.string.main_page);
        mTitles.add(str);
        mTitles.add(getResources().getString(R.string.section));
        mTitles.add(getResources().getString(R.string.category));
        mTitles.add(getResources().getString(R.string.cart));
        mTitles.add(getResources().getString(R.string.me));

    }
    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new MainPageFragment());
        mFragments.add(new TopicFragment());
        mFragments.add(new SortFragment());
        mFragments.add(new CartFragment());
        mFragments.add(new MeFragment());
    }

    //对外提供tab的view
    public View tabView(int position){
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
        ImageView iv = inflate.findViewById(R.id.iv);
        TextView tv = inflate.findViewById(R.id.tv);
        switch (position){
            case 0:
                iv.setImageResource(R.drawable.se_main_page);
                break;
            case 1:
                iv.setImageResource(R.drawable.se_section);
                break;
            case 2:
                iv.setImageResource(R.drawable.se_category);
                break;
            case 3:
                iv.setImageResource(R.drawable.se_cart);
                break;
            case 4:
                iv.setImageResource(R.drawable.se_me);
                break;
        }
        tv.setText(mTitles.get(position));
        return inflate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
