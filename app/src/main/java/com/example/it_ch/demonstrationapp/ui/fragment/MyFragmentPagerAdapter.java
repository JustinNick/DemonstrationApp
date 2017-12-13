package com.example.it_ch.demonstrationapp.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${chengguo} on 2017/12/7.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager mFragmentManager;
    private List<Fragment> lists;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm);
        this.mFragmentManager = fm;
        this.lists = lists;
    }



    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
