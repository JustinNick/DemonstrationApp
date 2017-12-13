package com.example.it_ch.demonstrationapp.ui.activity.homepage.phonesubwayticket;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;
import com.example.it_ch.demonstrationapp.adapter.CardDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class TradingRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvTitle;
    private RecyclerView mReCardDetail;
    private List<String> mListDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading_record);
        immersion();
        initViews();
        loadDatas();
        initEvents();
    }

    private void loadDatas() {
        mListDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i%2 == 0){
                mListDatas.add(i,"地铁乘车");
            }else {
                mListDatas.add(i,"账户充值");
            }

        }
    }

    private void initEvents() {
        mIvBack.setOnClickListener(this);
        mTvTitle.setText("交易记录");
        mReCardDetail.setLayoutManager(new LinearLayoutManager(this));
        mReCardDetail.setAdapter(new CardDetailAdapter(mListDatas));
    }

    private void initViews() {
        mIvBack = (ImageView) findViewById(R.id.iv_left_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title_bar);
        mReCardDetail = (RecyclerView) findViewById(R.id.re_card_detail_show);
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
