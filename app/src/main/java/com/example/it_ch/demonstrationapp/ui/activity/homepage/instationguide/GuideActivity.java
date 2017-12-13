package com.example.it_ch.demonstrationapp.ui.activity.homepage.instationguide;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnSure;
    private ImageView mIvBack;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        immersion();
        initViews();
        initEvents();
    }

    private void initEvents() {
        mIvBack.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
        mTvTitle.setText("站点信息");
    }

    private void initViews() {
        mBtnSure = (Button) findViewById(R.id.btn_sure);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sure:
                startActivity(new Intent(this,StandSureActivity.class));
                break;
            case R.id.iv_left_back:
                finish();
                break;
        }
    }
}
