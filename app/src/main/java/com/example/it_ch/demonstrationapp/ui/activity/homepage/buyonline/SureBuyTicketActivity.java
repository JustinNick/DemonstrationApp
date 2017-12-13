package com.example.it_ch.demonstrationapp.ui.activity.homepage.buyonline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.it_ch.demonstrationapp.R;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.buyonline.BuyLineSuccessActivity;

public class SureBuyTicketActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvPayInstant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_buy_ticket);
        immersion();
        initViews();
        initEvents();
    }

    private void initEvents() {
        mIvPayInstant.setOnClickListener(this);
    }

    private void initViews() {
        mIvPayInstant = (ImageView) findViewById(R.id.iv_pay_instant);
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
            case R.id.iv_pay_instant:
                startActivity(new Intent(this,BuyLineSuccessActivity.class));
                finish();
                break;
        }
    }
}
