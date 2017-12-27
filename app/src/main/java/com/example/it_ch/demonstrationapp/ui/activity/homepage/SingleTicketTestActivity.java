package com.example.it_ch.demonstrationapp.ui.activity.homepage;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

public class SingleTicketTestActivity extends AppCompatActivity {
    private ImageView mIvNfcSinga,mIvMoveCard;
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ticket_test);
        immersion();
        initViews();
        initEvents();
    }

    private void initViews(){
        ((TextView)findViewById(R.id.tv_title_bar)).setText("单程票验票");
        mIvBack = (ImageView) findViewById(R.id.iv_left_back);
        mIvNfcSinga = (ImageView) findViewById(R.id.nfc_signal_imageview);
        mIvMoveCard = (ImageView) findViewById(R.id.move_card_imageview);
        startCardAnimal();
        startNfcSingalAnimal();
    }

    private void initEvents(){
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 开启nfc搜索信号动画  帧动画
     */
    private void startNfcSingalAnimal() {
        AnimationDrawable nfcSingaAnimDrawble = (AnimationDrawable) mIvNfcSinga.getDrawable();
        nfcSingaAnimDrawble.start();
    }


    /**
     * 开启充值卡移动动画
     */
    private void startCardAnimal() {
        final AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.translate);

        mIvMoveCard.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvMoveCard.startAnimation(animationSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void immersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
}
