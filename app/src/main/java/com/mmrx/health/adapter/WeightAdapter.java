package com.mmrx.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.mmrx.health.R;
import com.mmrx.health.activity.WeightActivity;
import com.mmrx.health.bean.WeightBean;

import java.util.Date;
import java.util.List;

public class WeightAdapter extends BaseAdapter {


	private List<WeightBean>  list;
	private LayoutInflater inflater;
    private Context mContext;
    private SwipeListView mSwipeList;
    private WeightActivity weightActivity;


	public WeightAdapter() {
		super();
	}


	public WeightAdapter(List<WeightBean> list, Context context, SwipeListView swipeList) {
		super();
		this.list = list;
		this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSwipeList = swipeList;
	}


	public void setList(List<WeightBean> list){
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
            v=inflater.inflate(R.layout.weight_item_list, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) v.findViewById(R.id.item_weight_name);
            holder.tv_baozhiqi = (TextView) v.findViewById(R.id.item_weight_riqi);
            v.setTag(holder);
        }
        else{
            holder = (ViewHolder)v.getTag();
        }
        WeightBean weightBean = list.get(position);

        holder.tv_name.setText(weightBean.getWeight()+"kg");
        holder.tv_baozhiqi.setText("记录日期"+weightBean.getCtime());

		return v;
	}

    private class ViewHolder{
        TextView tv_name;
        TextView tv_baozhiqi;
    }
	
	
}
