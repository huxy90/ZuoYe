package com.jiyun.day01_zy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.day01_zy.R;
import com.jiyun.day01_zy.bean.FuliBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RlvAdapter extends RecyclerView.Adapter{

    Context mContext;
    List<FuliBean.ResultsBean> list;
    public RlvAdapter(Context context,List<FuliBean.ResultsBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item,null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        vh.tv.setText(list.get(position).getWho());
        Glide.with(mContext).load(list.get(position).getUrl()).into(vh.iv);
    } //d 


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<FuliBean.ResultsBean> results){
        list.addAll(results);
        notifyDataSetChanged();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;
        public VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
