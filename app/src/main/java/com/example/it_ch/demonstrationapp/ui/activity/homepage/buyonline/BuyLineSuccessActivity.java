package com.example.it_ch.demonstrationapp.ui.activity.homepage.buyonline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.it_ch.demonstrationapp.R;

public class BuyLineSuccessActivity extends AppCompatActivity implements View.OnClickListener{

    private View mIvBack;
    private View mTvPaySuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_line_success);
        immersion();
        initViews();
        initEvents();
    }

    private void initEvents() {
        mIvBack.setOnClickListener(this);
        mTvPaySuccess.setOnClickListener(this);
    }

    private void initViews() {
        mIvBack = findViewById(R.id.iv_left_back);
        mTvPaySuccess = findViewById(R.id.complete_buy);
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
            case R.id.complete_buy:
                startActivity(new Intent(this,TicketCodeActivity.class));
                finish();
                break;
            case R.id.iv_left_back:
                finish();
                break;
        }
    }
}
