package com.mmrx.health.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.DbUtils;
import com.mmrx.health.BaseActivity;
import com.mmrx.health.R;
import com.mmrx.health.bean.News;
import com.mmrx.health.util.Constant;
import com.mmrx.health.util.SPutil;

import java.text.DecimalFormat;

public class NewsDetailActivity extends BaseActivity implements OnClickListener{

	private News news;

	//title bar 相关设置
	ImageButton mBackBut;
	TextView mTitleTv;
	Button but_more;

	String mFragmentTag;

	DbUtils dbUtils;
	private TextView news_title;
	private TextView news_content;
	private ImageView news_image;
	private float weight;
	private float readTall;
	boolean isRun=true;
	boolean f=true;
	String str_bmi="";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		init();
		SPutil sPutil=new SPutil(this);
		/*//如果没有填写个人信息跳转到填写个人信息页面
		if(!sPutil.ReadLogin()){
			Intent intent = new Intent();
			intent.setClass(this,LogActivity.class);
			startActivity(intent);
		}*/
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		if (bundle!=null){
			news =(News) bundle.getSerializable("key");
		}
		news_title.setText(news.getSubtitle());
		news_content.setMovementMethod(new ScrollingMovementMethod());
		news_content.setText(news.getContent());
		news_image.setImageResource(news.getImageID());
//		news_title.setText(format);
//		news_content.setText(str_bmi);
//		news_image.setImageResource(R.drawable.icon1);
		/*new Thread(){
			public void run() {
				while (isRun) {
//					handler.sendEmptyMessage(0);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			};
		}.start();*/
	}

	private void init() {
		// TODO Auto-generated method stub

		dbUtils=DbUtils.create(this);
		news_title = (TextView) findViewById(R.id.news_title);
		news_image = (ImageView) findViewById(R.id.news_image);
		news_content = (TextView) findViewById(R.id.news_content);

		mBackBut = (ImageButton)findViewById(R.id.title_bar_back);
		mBackBut.setOnClickListener(this);
		mTitleTv = (TextView)findViewById(R.id.title_bar_title);
		mTitleTv.setText("科普详情");
		but_more = (Button)findViewById(R.id.title_bar_menu);
		but_more.setVisibility(View.GONE);

		mFragmentTag = getIntent().getStringExtra(Constant.FRAGMENT_TAG);


	}

	@Override
	public void onClick(View view) {

	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}
}
