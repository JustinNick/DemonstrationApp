package com.example.it_ch.demonstrationapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.it_ch.demonstrationapp.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ${chengguo} on 2017/12/7.
 */
public class HomepageFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRv_show;

    private TextView mTvPhone;
    private TextView mTvBuyTicket;
    private TextView mTvRealTime;
    private TextView TmvGuide;
    private TextView mTvRecord;
    private TextView mTvStationRim;
    private TextView mTvLost;
    private TextView mTvInform;
    private DataShowAdapter mDataShowAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_fragment,container,false);
        initViews(view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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
        mTvPhone.setOnClickListener(this);
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
        Banner banner = (Banner) header.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List images = Arrays.asList(R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3);
        banner.setImages(images);
        banner.setDelayTime(5000);
        banner.setBannerAnimation(Transformer.Accordion);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initHeaderView(View header) {
        mTvPhone = (TextView) header.findViewById(R.id.tv_phone);
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
