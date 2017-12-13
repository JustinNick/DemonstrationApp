package com.example.it_ch.demonstrationapp.ui.activity.homepage.buyonline;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

public class TicketCodeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView mTvPaySuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_code);
        immersion();
        initViews();
        initEvents();
    }


    private void initEvents() {
        mIvBack.setOnClickListener(this);
        mTvTitle.setText("在线购票");
    }

    private void initViews() {
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
            case R.id.iv_left_back:
                finish();
                break;
        }
    }
}
