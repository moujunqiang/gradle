package com.mmrx.health.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.mmrx.health.R;
import com.mmrx.health.activity.NewsDetailActivity;
import com.mmrx.health.bean.News;
import com.mmrx.health.fragment.NewsFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class NewsAdapter extends BaseAdapter {


	private List<News>  list;
	private LayoutInflater inflater;
    private Context mContext;
    private SwipeListView mSwipeList;
    private ListView mList;
    private NewsFragment fragment;


	public NewsAdapter() {
		super();
	}


	public NewsAdapter(List<News> list, Context context, SwipeListView swipeList, NewsFragment fragment) {
		super();
		this.list = list;
		this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSwipeList = swipeList;
        this.fragment = fragment;
	}


	public NewsAdapter(List<News> list, Context context, ListView listView, NewsFragment fragment) {
		super();
		this.list = list;
		this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mList = listView;
        this.fragment = fragment;
	}


	public void setList(List<News> list){
		this.list=list;
		this.notifyDataSetChanged();
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(final int position, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;

        if(v == null) {
            v=inflater.inflate(R.layout.news_item_list, null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) v.findViewById(R.id.newsTitle);
            holder.tv_content = (TextView) v.findViewById(R.id.newsSubTitle);
            holder.tv_image = (ImageView) v.findViewById(R.id.newsImage);
            v.setTag(holder);
        }
        else{
            holder = (ViewHolder)v.getTag();
        }
		News news = list.get(position);

        holder.tv_title.setText(news.getTitle());
        holder.tv_content.setText(news.getSubtitle());
//		holder.tv_image.setImageURI();
		holder.tv_image.setImageResource(news.getImageID());
		holder.tv_content.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//开始传值
				Intent intent=new Intent(mContext, NewsDetailActivity.class);
				Bundle bundle=new Bundle();
				bundle.putSerializable("key",list.get(position));
				intent.putExtras(bundle);
				//利用上下文开启跳转
				mContext.startActivity(intent);
			}
		});

		return v;
	}

    private class ViewHolder{
        TextView tv_title;
        TextView tv_content;
        ImageView tv_image;
    }
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
