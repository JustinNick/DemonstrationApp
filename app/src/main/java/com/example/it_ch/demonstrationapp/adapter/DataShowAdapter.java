package com.example.it_ch.demonstrationapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

import java.util.List;

/**
 * Created by ${chengguo} on 2017/12/7.
 */
public class DataShowAdapter extends RecyclerView.Adapter<DataShowAdapter.MyViewHolder> {
    private List<String> mlistDatas;
    private View mHeaderView;
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_NORMAL = 1;  //说明是不带有header和footer的

    public DataShowAdapter(List<String> mlistDatas) {
        this.mlistDatas = mlistDatas;
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        return position == 0?TYPE_HEADER:TYPE_NORMAL;
    }

    @Override
    public DataShowAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(mHeaderView);
        }
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_show_fragment, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(DataShowAdapter.MyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL && holder != null) {
            holder.tv_content.setText(mlistDatas.get(position - 1));
        }

    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null) {
            return mlistDatas.size();
        } else {
            return mlistDatas.size() + 1;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_content;
        private TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
