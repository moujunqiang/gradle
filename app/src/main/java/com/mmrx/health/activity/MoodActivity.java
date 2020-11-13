package com.mmrx.health.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.mmrx.health.BaseActivity;
import com.mmrx.health.R;
import com.mmrx.health.adapter.MoodAdapter;
import com.mmrx.health.bean.Mood;
import com.mmrx.health.util.BitmapCache;
import com.mmrx.health.util.Constant;
import com.mmrx.health.util.L;
import com.mmrx.health.util.MyToast;
import com.mmrx.health.util.SPutil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoodActivity extends BaseActivity implements OnClickListener{

	//title bar 相关设置
	ImageButton mBackBut;
	TextView mTitleTv;
	Button but_more;

	String mFragmentTag;

	DbUtils dbUtils;
	private TextView tv_bminum;
	private TextView tv_bmi;
	private float weight;
	private float readTall;
	boolean isRun=true;
	boolean f=true;
	String str_bmi="";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mood);
		init();
		SPutil sPutil=new SPutil(this);
		/*//如果没有填写个人信息跳转到填写个人信息页面
		if(!sPutil.ReadLogin()){
			Intent intent = new Intent();
			intent.setClass(this,LogActivity.class);
			startActivity(intent);
		}*/

		weight = sPutil.ReadWeight();
		readTall = sPutil.ReadHeight();

		float we = weight;
		float tall = readTall;
		tall=tall/100;
		float bmi=we/(tall*tall);
		if (bmi<18.5) {
			str_bmi="您的体型为偏瘦型，脂肪在人体的营养中有着极其重要的功用，是人体代谢的主要能源，可以适量摄入富含易分解的不饱和脂肪类的食物，如坚果、大豆、花生等，补充足够的水分、少食多餐，在忙碌的日子做一些简单的运动。一段时间后，你一定能收获更好的自己。";
		}else if (bmi<24) {
			str_bmi="您的体型在标准区间，请注意保持，如果你想身材变得更加完美，可以通过增加运动来达到。一段时间后，你一定可以达到穿衣显瘦，脱衣有肉的完美身材。";


		}else if (bmi<28) {
			str_bmi="您的体型过重，想要达到标准体型努努力就马上可以实现哦，要想瘦，你得严格控制脂肪，但也需补充优质蛋白质，一定要严格限制动物性脂肪，油炸食品，甜食的摄入，这样才能既限制脂肪的摄入，又保证了营养的均衡。相信经过一段时间，你一定会达到目标体重。";
		}else {
			str_bmi="您的体型为肥胖，体脂和体重都超标啦，需要引起重视和控制哦，缺乏运动会导致身体虚胖和免疫力下降从而引起脂肪堆积。从现在开始，坚持力量和有氧运动，适当的节食有助于减肥，而且尽量少吃高盐、高脂肪的食物，多吃蔬果，这样平衡身体的营养。坚持，你将收获更好的自己。";
		}

		DecimalFormat decimalFormat=new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(1);
		String format = decimalFormat.format(bmi);

		sPutil.WriteBMI(format);
		tv_bminum.setText(format);
		tv_bmi.setText(str_bmi);

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
		tv_bminum = (TextView) findViewById(R.id.bmi_tv);
		tv_bmi = (TextView) findViewById(R.id.bmi_tv2);

		mBackBut = (ImageButton)findViewById(R.id.title_bar_back);
		mBackBut.setOnClickListener(this);
		mTitleTv = (TextView)findViewById(R.id.title_bar_title);
		mTitleTv.setText("健康报告");
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
