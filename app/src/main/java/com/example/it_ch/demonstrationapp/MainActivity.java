package com.example.it_ch.demonstrationapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_ch.demonstrationapp.ui.fragment.ChangeFragment;
import com.example.it_ch.demonstrationapp.ui.fragment.HomepageFragment;
import com.example.it_ch.demonstrationapp.ui.fragment.LifeFragment;
import com.example.it_ch.demonstrationapp.ui.fragment.MyFragmentPagerAdapter;
import com.example.it_ch.demonstrationapp.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ly_one, ly_two, ly_three, ly_four;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4;
    private TextView mTextNum1, mTextNum2, mTextNum3;
    private ImageView mImageView;
    private ViewPager mViewpager;
    private static final long WAIT_TIME = 2000L;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        initEvents();
        ly_one.performClick();
    }

    private void bindView() {
        mViewpager = (ViewPager) findViewById(R.id.fragment_container);
        List<Fragment> lists = new ArrayList<>();
        HomepageFragment homepageFragment = new HomepageFragment();
        LifeFragment lifeFragment = new LifeFragment();
        UserFragment userFragment = new UserFragment();
        ChangeFragment changeFragment = new ChangeFragment();
        lists.add(homepageFragment);
        lists.add(changeFragment);
        lists.add(lifeFragment);
        lists.add(userFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentManager, lists);
        mViewpager.setAdapter(myFragmentPagerAdapter);
        mViewpager.setCurrentItem(0);

        ly_one = (LinearLayout) findViewById(R.id.ly_tab_menu_homepage);
        ly_two = (LinearLayout) findViewById(R.id.ly_tab_menu_change);
        ly_three = (LinearLayout) findViewById(R.id.ly_tab_menu_life);
        ly_four = (LinearLayout) findViewById(R.id.ly_tab_menu_user);

        mTextView1 = (TextView) findViewById(R.id.tab_menu_homepage);
        mTextView2 = (TextView) findViewById(R.id.tab_menu_change);
        mTextView3 = (TextView) findViewById(R.id.tab_menu_life);
        mTextView4 = (TextView) findViewById(R.id.tab_menu_user);

        mTextNum1 = (TextView) findViewById(R.id.tab_menu_homepage_num);
        mTextNum2 = (TextView) findViewById(R.id.tab_menu_change_num);
        mTextNum3 = (TextView) findViewById(R.id.tab_menu_more_life);

        mImageView = (ImageView) findViewById(R.id.tab_menu_setting_point);
    }

    private void initEvents() {
        ly_one.setOnClickListener(this);
        ly_two.setOnClickListener(this);
        ly_three.setOnClickListener(this);
        ly_four.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        mTextView1.setSelected(false);
        mTextView2.setSelected(false);
        mTextView3.setSelected(false);
        mTextView4.setSelected(false);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ly_tab_menu_homepage:
                setSelected();
                mViewpager.setCurrentItem(0);
                mTextView1.setSelected(true);
                mTextNum1.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_change:
                setSelected();
                mViewpager.setCurrentItem(1);
                mTextView2.setSelected(true);
                mTextNum2.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_life:
                setSelected();
                mViewpager.setCurrentItem(2);
                mTextView3.setSelected(true);
                mTextNum3.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_user:
                setSelected();
                mViewpager.setCurrentItem(3);
                mTextView4.setSelected(true);
                mImageView.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > WAIT_TIME) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
