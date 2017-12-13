package com.example.it_ch.demonstrationapp.ui.activity.homepage.phonesubwayticket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnDetail;
    private Button mBtnRecharge;
    private ImageView mIvOperation;
    private ImageView mBack;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        immersion();
        initViews();
        initEvents();
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

    private void initViews() {
        mBack = (ImageView) findViewById(R.id.iv_left_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title_bar);
        mTvTitle.setText("手机地铁票");
        mBtnDetail = (Button) findViewById(R.id.btn_card_detail);
        mBtnRecharge = (Button) findViewById(R.id.btn_card_recharge);
        mIvOperation = (ImageView) findViewById(R.id.iv_card_operation);
    }

    private void initEvents() {
        mBack.setOnClickListener(this);
        mBtnRecharge.setOnClickListener(this);
        mBtnDetail.setOnClickListener(this);
        mIvOperation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_back:
                finish();
                break;
            case R.id.btn_card_recharge:
                startActivity(new Intent(this, AccountRechargeActivity.class));
                break;
            case R.id.btn_card_detail:
                startActivity(new Intent(this, TradingRecordActivity.class));
                break;
            case R.id.iv_card_operation:
                startActivity(new Intent(this, AccountRechargeActivity.class));
                break;
        }
    }
}
