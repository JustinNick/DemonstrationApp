package com.example.it_ch.demonstrationapp.ui.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.LinearLayoutManager;
import com.example.it_ch.demonstrationapp.R;
import com.example.it_ch.demonstrationapp.adapter.DataShowAdapter;
import com.example.it_ch.demonstrationapp.adapter.MyReDecoration;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.around_station.StationRimActivity;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.buy_record.RecordActivity;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.buyonline.BuyTicketActivity;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.instationguide.GuideActivity;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.lost_found.LostActivity;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.operation_notice.InformActivity;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.phonesubwayticket.PhoneActivity;
import com.example.it_ch.demonstrationapp.ui.activity.homepage.realtime_traffic.RealTimeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chengguo} on 2017/12/7.
 */
public class HomepageFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRv_show;

    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 是否正在放大
    private Boolean mScaling = false;

    private DisplayMetrics metric;


    private TextView mTvphone;
    private TextView mTvBuyTicket;
    private TextView mTvRealTime;
    private TextView TmvGuide;
    private TextView mTvRecord;
    private TextView mTvStationRim;
    private TextView mTvLost;
    private TextView mTvInform;
    private DataShowAdapter mDataShowAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageView mBannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment,container,false);
        initViews(view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRv_show.setLayoutManager(linearLayoutManager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("合肥轨道交通近期建设规划出炉 ( 2017-2025 ) 规划有9条线路");
        }
        mDataShowAdapter = new DataShowAdapter(list);
        mRv_show.setAdapter(mDataShowAdapter);
        mRv_show.addItemDecoration(new MyReDecoration(getActivity(),MyReDecoration.VERTICAL_LIST));
        setHeaderView(mRv_show);
        return view;
    }

    private void initViews(View view){
        mRv_show = (RecyclerView) view.findViewById(R.id.rv_show);
    }

    private void initHeaderEvents(){
        mTvphone.setOnClickListener(this);
        mTvBuyTicket.setOnClickListener(this);
        TmvGuide.setOnClickListener(this);
        mTvRealTime.setOnClickListener(this);
        mTvRecord.setOnClickListener(this);
        mTvStationRim.setOnClickListener(this);
        mTvLost.setOnClickListener(this);
        mTvInform.setOnClickListener(this);
    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.headerview, view, false);
        mDataShowAdapter.setHeaderView(header);
        initHeaderView(header);
        initHeaderEvents();
        mBannerView = (ImageView) header.findViewById(R.id.iv_banner);
        metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mBannerView.getLayoutParams();
        lp.width = metric.widthPixels;
        lp.height = metric.widthPixels * 9 / 16;
        mBannerView.setLayoutParams(lp);
        initzoomimage();
    }

    /**
     * 图片缩放的处理
     */
    private void initzoomimage() {
        mRv_show.setOnTouchListener(new MyTouchListener());
    }

private class MyTouchListener implements View.OnTouchListener{
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mBannerView.getLayoutParams();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                mScaling = false;
                replyImage();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScaling) {
//                            当图片也就是第一个item完全可见的时候，记录触摸屏幕的位置
                    int [] position = new int[2];
                    mBannerView.getLocationOnScreen(position);
                    if (position[1]> 0) {
                        mFirstPosition = event.getY();
                    } else {
                        break;
                    }
                }
                int distance = (int) ((event.getY() - mFirstPosition)* 0.6); // 滚动距离乘以一个系数
                if (distance < 0) {
                    break;
                }
                // 处理放大
                mScaling = true;
                lp.width = metric.widthPixels + distance;
                lp.height = (metric.widthPixels + distance) * 9 / 16;
                mBannerView.setLayoutParams(lp);
                return true; // 返回true表示已经完成触摸事件，不再处理
        }
        return false;
    }
}

    private void replyImage() {
        final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mBannerView.getLayoutParams();
        final float w = mBannerView.getLayoutParams().width;// 图片当前宽度
        final float h = mBannerView.getLayoutParams().height;// 图片当前高度
        final float newW = metric.widthPixels;// 图片原宽度
        final float newH = metric.widthPixels * 9 / 16;// 图片原高度

        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration(200);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.width = (int) (w - (w - newW) * cVal);
                lp.height = (int) (h - (h - newH) * cVal);
                mBannerView.setLayoutParams(lp);
            }
        });
        anim.start();

    }


    private void initHeaderView(View header) {
        mTvphone = (TextView) header.findViewById(R.id.tv_phone);
        mTvBuyTicket = (TextView) header.findViewById(R.id.tv_buy_ticket);
        TmvGuide = (TextView) header.findViewById(R.id.tv_guide);
        mTvRealTime = (TextView) header.findViewById(R.id.tv_real_time);
        mTvRecord = (TextView) header.findViewById(R.id.tv_record);
        mTvStationRim = (TextView) header.findViewById(R.id.tv_station_rim);
        mTvLost = (TextView) header.findViewById(R.id.tv_lost);
        mTvInform = (TextView) header.findViewById(R.id.tv_inform);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_phone:
                startActivity(new Intent(getActivity(),PhoneActivity.class));
                break;
            case R.id.tv_buy_ticket:
                startActivity(new Intent(getActivity(),BuyTicketActivity.class));
                break;
            case R.id.tv_guide:
                startActivity(new Intent(getActivity(),GuideActivity.class));
                break;
            case R.id.tv_real_time:
                startActivity(new Intent(getActivity(),RealTimeActivity.class));
                break;
            case R.id.tv_record:
                startActivity(new Intent(getActivity(),RecordActivity.class));
                break;
            case R.id.tv_station_rim:
                startActivity(new Intent(getActivity(),StationRimActivity.class));
                break;
            case R.id.tv_lost:
                startActivity(new Intent(getActivity(),LostActivity.class));
                break;
            case R.id.tv_inform:
                startActivity(new Intent(getActivity(),InformActivity.class));
                break;
        }
    }
}
