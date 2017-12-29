package com.example.it_ch.demonstrationapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.it_ch.demonstrationapp.R;
import com.example.it_ch.demonstrationapp.ui.activity.changecar.ChangeBuyTicketActivity;
import com.example.it_ch.demonstrationapp.ui.activity.changecar.ImgBgActivity;
import com.example.it_ch.demonstrationapp.ui.activity.changecar.QiDianActivity;

/**
 * Created by ${chengguo} on 2017/12/7.
 */
public class ChangeFragment extends Fragment implements View.OnClickListener {

    private View mView1,mView2,mView3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_fragment,container,false);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        mView1.setOnClickListener(this);
        mView2.setOnClickListener(this);
        mView3.setOnClickListener(this);
    }

    private void initView(View view) {
        mView1 = view.findViewById(R.id.view_1);
        mView1 = view.findViewById(R.id.view_2);
        mView1 = view.findViewById(R.id.view_3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_1:
                startActivity(new Intent(getActivity(), QiDianActivity.class));
                break;
            case R.id.view_2:
                startActivity(new Intent(getActivity(), ChangeBuyTicketActivity.class));
                break;
            case R.id.view_3:
                startActivity(new Intent(getActivity(), ImgBgActivity.class));
                break;
        }
    }
}
