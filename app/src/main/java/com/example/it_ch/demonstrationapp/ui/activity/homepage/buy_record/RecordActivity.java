package com.example.it_ch.demonstrationapp.ui.activity.homepage.buy_record;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mIvTicketNoGet;
    private ImageView mIvTicketNoGet1;
    private ImageView mIvAll;
    private ImageView mIvAll1;
    private ImageView mIvBack;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        immersion();
        initViews();
        initEvents();
    }

    private void initEvents() {
        mIvTicketNoGet.setOnClickListener(this);
        mIvAll.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mTvTitle.setText("明光路站");
    }

    private void initViews() {
        mIvTicketNoGet = (ImageView) findViewById(R.id.iv_ticket_no_get);
        mIvTicketNoGet1 = (ImageView) findViewById(R.id.iv_ticket_no_get1);
        mIvAll = (ImageView) findViewById(R.id.iv_all);
        mIvAll1 = (ImageView) findViewById(R.id.iv_all1);
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
        mIvTicketNoGet1.setVisibility(View.GONE);
        mIvAll1.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_ticket_no_get:
                setImageVisible();
                mIvTicketNoGet1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_all:
                setImageVisible();
                mIvAll1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_left_back:
                finish();
                break;
        }
    }
}
