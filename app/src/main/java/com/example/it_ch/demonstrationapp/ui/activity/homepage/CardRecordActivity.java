package com.example.it_ch.demonstrationapp.ui.activity.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.citylink.tsm.blecitycard.bean.BleTradeRecord;
import com.citylink.tsm.citycard.bean.TradeRecord;
import com.example.it_ch.demonstrationapp.R;

import java.util.ArrayList;
import java.util.List;

public class CardRecordActivity extends AppCompatActivity {
    private TextView mTvCardNum;

    private List<TradeRecord> logList = new ArrayList<>();
    private List<BleTradeRecord> bleLogList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_record);
        initViews();
    }

    private void initViews() {
        hideActionBar();
        Intent intent = getIntent();
        String cardNum = intent.getStringExtra("Card_Num");
        String balance = intent.getStringExtra("Card_Balance");
        String extendDate=intent.getStringExtra("Card_ExtendDate");
        boolean isBle=intent.getBooleanExtra("IsBle",false);
        List<TradeRecord>  tradeRecords = intent.getParcelableArrayListExtra("Card_Logs");
        List<BleTradeRecord> bleTradeRecords= intent.getParcelableArrayListExtra("Card_Logs");

        mTvCardNum = (TextView) findViewById(R.id.tv_card_num);
        mTvCardNum.setText("卡号：" + cardNum);

        if(tradeRecords!=null||bleTradeRecords!=null) {
            if(isBle){
                bleLogList.addAll(bleTradeRecords);
            }else{
                logList.addAll(tradeRecords);
            }
        }
    }

    protected void hideActionBar() {
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

    public void onClick(View v){
        finish();
    }
}
