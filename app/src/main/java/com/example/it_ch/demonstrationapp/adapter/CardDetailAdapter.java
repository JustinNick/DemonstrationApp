package com.example.it_ch.demonstrationapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.it_ch.demonstrationapp.R;

import java.util.List;

/**
 * Created by ${ChengGuo} on 2017/12/10.
 */
public class CardDetailAdapter extends RecyclerView.Adapter<CardDetailAdapter.ViewHolder> {
    private List<String> mlistDatas;

    public CardDetailAdapter(List<String> mlistDatas) {
        this.mlistDatas = mlistDatas;
    }

    @Override
    public CardDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddetailitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardDetailAdapter.ViewHolder holder, int position) {
        holder.tvType.setText(mlistDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mlistDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvType;
        private TextView tvTime;
        private TextView tvPriceTrade;

        public ViewHolder(View view) {
            super(view);
            tvType = (TextView) view.findViewById(R.id.tv_type);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvPriceTrade = (TextView) view.findViewById(R.id.tv_price_trade);
        }
    }
}
