package com.example.it_ch.demonstrationapp.ui.activity.homepage.instationguide;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

public class StandSureActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvBaseMsg;
    private ImageView mIvBaseMsg1;
    private ImageView mIvStandImage;
    private ImageView mIvStandImage1;
    private ImageView mIvBack;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_sure);
        immersion();
        initViews();
        initEvents();
    }

    private void initEvents() {
        mIvBaseMsg.setOnClickListener(this);
        mIvStandImage.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mTvTitle.setText("明光路站");
    }

    private void initViews() {
        mIvBaseMsg = (ImageView) findViewById(R.id.iv_base_msg);
        mIvBaseMsg1 = (ImageView) findViewById(R.id.iv_base_msg1);
        mIvStandImage = (ImageView) findViewById(R.id.iv_stand_image);
        mIvStandImage1 = (ImageView) findViewById(R.id.iv_stand_image1);
        mIvBack = (ImageView) findViewById(R.id.iv_left_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title_bar);
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

    private void setImageVisible(){
        mIvBaseMsg1.setVisibility(View.GONE);
        mIvStandImage1.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_base_msg:
                setImageVisible();
                mIvBaseMsg1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_stand_image:
                setImageVisible();
                mIvStandImage1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_left_back:
                finish();
                break;
        }
    }
}
