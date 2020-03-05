package com.jiyun.day03_zy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.day03_zy.R;
import com.jiyun.day03_zy.bean.TopicBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

public class RlvTopicAdapter extends RecyclerView.Adapter {

    private Context context;
    public  List<TopicBean.DataBeanX.DataBean> list;

    public RlvTopicAdapter(Context context, List<TopicBean.DataBeanX.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        vh.tv_title.setText(list.get(position).getTitle());
        vh.tv_subtitle.setText(list.get(position).getSubtitle());
        vh.tv_price.setText(list.get(position).getPrice_info()+"元起");
        Glide.with(context).load(list.get(position).getScene_pic_url()).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<TopicBean.DataBeanX.DataBean> result){
        list.addAll(result);
        notifyDataSetChanged();
    }
    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv_title,tv_subtitle,tv_price;

        public VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_subtitle = itemView.findViewById(R.id.tv_subtitle);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View v ,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
           mItemClickListener = listener;
    }

}
