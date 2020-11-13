package com.mmrx.health.activity;

import android.os.Bundle;
import android.view.View;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.mmrx.health.BaseActivity;
import com.mmrx.health.R;
import com.mmrx.health.adapter.NewsAdapter;
import com.mmrx.health.bean.News;
import com.mmrx.health.util.L;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends BaseActivity implements View.OnClickListener {

    SwipeListView slv;
	List<News> w_list;
	public NewsAdapter mAdapter;
	DbUtils dbUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight);
		init();
	}

	private void init(){
		slv = (SwipeListView)findViewById(R.id.weight_swipe_list);
		slv.setSwipeListViewListener(new NewsActivity.SwipeListViewListener());
		dbUtils = DbUtils.create(this);

		try {
			w_list = dbUtils.findAll(News.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (w_list == null) {
			w_list = new ArrayList<News>();
		}
		mAdapter = new NewsAdapter(w_list,getApplicationContext(),slv,null);
		slv.setAdapter(mAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//更新数据源
		try {
			w_list = dbUtils.findAll(News.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View view) {

	}

	class SwipeListViewListener extends BaseSwipeListViewListener {

        @Override
        public void onClickFrontView(int position) {
            super.onClickFrontView(position);
            L.i("WeightFragment--onClickFrontView");

        }
        @Override
        public void onDismiss(int[] reverseSortedPositions) {
            L.i("SwipeListViewListener-onDismiss");
            for (int position : reverseSortedPositions) {
                w_list.remove(position);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

	
	

}
