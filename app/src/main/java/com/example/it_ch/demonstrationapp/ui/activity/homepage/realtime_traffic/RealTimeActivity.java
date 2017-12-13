package com.example.it_ch.demonstrationapp.ui.activity.homepage.realtime_traffic;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

public class RealTimeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvGuestNum;
    private ImageView mIvGuestNum1;
    private ImageView mIvBack;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);
        immersion();
        initViews();
        initEvents();
    }

    private void initEvents() {
        mIvBack.setOnClickListener(this);
        mIvGuestNum.setOnClickListener(this);
        mIvGuestNum1.setOnClickListener(this);
        mTvTitle.setText("实时客流");
    }

    private void initViews() {
        mIvGuestNum = (ImageView) findViewById(R.id.iv_guest_num);
        mIvGuestNum1 = (ImageView) findViewById(R.id.iv_guest_num1);
        mIvBack = (ImageView) findViewById(R.id.iv_left_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title_bar);
    }
    private void setImageVisible(){
        mIvGuestNum.setVisibility(View.GONE);
        mIvGuestNum1.setVisibility(View.GONE);
    }

    private void immersion() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_guest_num:
                setImageVisible();
                mIvGuestNum1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_guest_num1:
                setImageVisible();
                mIvGuestNum.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_left_back:
                finish();
                break;
        }
    }
}
