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
import android.widget.Toast;

import com.example.it_ch.demonstrationapp.R;

public class AccountRechargeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBack;
    private TextView mTvTitle;
    private Button mBtnGoBuy;
    private ImageView mIvWXPay;
    private ImageView mIvAliPay;
    private ImageView mIvUnionPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accoun_recharget);
        immersion();
        initViews();
        initEvents();
        mIvWXPay.performClick();
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
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void initViews() {
        mBack = (ImageView) findViewById(R.id.iv_left_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title_bar);
        mTvTitle.setText("账号充值");
        mBtnGoBuy = (Button) findViewById(R.id.btn_go_buy);
        mIvWXPay = (ImageView) findViewById(R.id.iv_wxpay);
        mIvAliPay = (ImageView) findViewById(R.id.iv_alipay);
        mIvUnionPay = (ImageView) findViewById(R.id.iv_unionpay);
    }

    private void initEvents() {
        mBack.setOnClickListener(this);
        mBtnGoBuy.setOnClickListener(this);
        mIvWXPay.setOnClickListener(this);
        mIvAliPay.setOnClickListener(this);
        mIvUnionPay.setOnClickListener(this);
    }

    private void setSelected() {
        mIvWXPay.setSelected(false);
        mIvAliPay.setSelected(false);
        mIvUnionPay.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_back:
                finish();
                break;
            case R.id.btn_go_buy:
                startActivity(new Intent(this, PayDialogActivity.class));
                finish();
                break;
            case R.id.iv_wxpay:
                setSelected();
                mIvWXPay.setSelected(true);
                Toast.makeText(this,"微信支付",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_alipay:
                setSelected();
                mIvAliPay.setSelected(true);
                Toast.makeText(this,"支付宝支付",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_unionpay:
                setSelected();
                mIvUnionPay.setSelected(true);
                Toast.makeText(this,"银联支付",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
