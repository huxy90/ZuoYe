package com.jiyun.day03_zy.adapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class VPFrgmentAdapter extends FragmentPagerAdapter {

    ArrayList<String> mTitles;
    ArrayList<Fragment> mFragments;

    public VPFrgmentAdapter(@NonNull FragmentManager fm, ArrayList<String> mTitles,ArrayList<Fragment> mFragments) {
        super(fm);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    //viewpager配合TabLayout+framgnet使用的时候,可以委托adapter去帮我们参加tabLayout的tab
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
