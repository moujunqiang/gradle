package com.mmrx.health.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.mmrx.health.R;
import com.mmrx.health.activity.NewsDetailActivity;
import com.mmrx.health.activity.WebActivity;
import com.mmrx.health.bean.NetNewsBean;
import com.mmrx.health.bean.News;
import com.mmrx.health.fragment.NewsFragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class NetNewsAdapter extends RecyclerView.Adapter<NetNewsAdapter.ViewHolder> {


    private List<NetNewsBean.NewslistBean> list;
    private Context mContext;

    public void setList(List<NetNewsBean.NewslistBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NetNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.net_news_item_list, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NetNewsBean.NewslistBean netNewsBean = list.get(position);
        holder.tv_title.setText(netNewsBean.getTitle());
        holder.tv_content.setText(netNewsBean.getDescription());
        holder.tv_time.setText(netNewsBean.getCtime());
        //通过glide加载图片
        Glide.with(mContext).load(netNewsBean.getPicUrl()).into(holder.tv_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", netNewsBean.getUrl());
                intent.putExtra("title", netNewsBean.getTitle());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        ImageView tv_image;
        TextView tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_news_title);
            tv_content = itemView.findViewById(R.id.tv_news_desc);
            tv_image = itemView.findViewById(R.id.iv_news);
            tv_time = itemView.findViewById(R.id.tv_news_time);

        }
    }


}
