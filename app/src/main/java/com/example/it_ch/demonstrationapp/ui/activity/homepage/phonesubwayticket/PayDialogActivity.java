package com.example.it_ch.demonstrationapp.ui.activity.homepage.phonesubwayticket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.it_ch.demonstrationapp.R;

public class PayDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvPayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_dialog);
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
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void initEvents() {
        mIvPayDialog.setOnClickListener(this);
    }

    private void initViews() {
        mIvPayDialog = (ImageView) findViewById(R.id.iv_pay_dialog);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,RechargeSuccessActivity.class));
        finish();
    }
}
