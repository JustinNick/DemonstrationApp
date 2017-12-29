package com.example.it_ch.demonstrationapp.ui.activity.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.citylink.tsm.blecitycard.bean.BleTradeRecord;
import com.citylink.tsm.citycard.bean.TradeRecord;
import com.example.it_ch.demonstrationapp.R;
import com.example.it_ch.demonstrationapp.adapter.BleTradeRecordAdapter;
import com.example.it_ch.demonstrationapp.adapter.TradeRecordAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardRecordActivity extends AppCompatActivity {
    private ListView listView;
    private TextView mTvCardNum,mTvCardBalance,mNoRecordNotice,mTvCardExtendDate;

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
        mTvCardBalance = (TextView) findViewById(R.id.tv_balance);
        mNoRecordNotice = (TextView) findViewById(R.id.tv_no_record);
        mTvCardExtendDate = (TextView)findViewById(R.id.tv_extend_date);
        mTvCardNum.setText("卡号：" + cardNum);
        mTvCardBalance.setText("余额：" + balance);
        mTvCardExtendDate.setText("有效期：" + extendDate);

        if(tradeRecords!=null||bleTradeRecords!=null) {
            if(isBle){
                bleLogList.addAll(bleTradeRecords);
            }else{
                logList.addAll(tradeRecords);
            }
            mNoRecordNotice.setVisibility(View.GONE);
        }else{
            mNoRecordNotice.setVisibility(View.VISIBLE);
        }

        listView = (ListView) findViewById(R.id.listview);
        if(logList!=null) {
            if(isBle){
                listView.setAdapter(new BleTradeRecordAdapter(this,bleLogList));
            }
            else {
                listView.setAdapter(new TradeRecordAdapter(this, logList));
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
