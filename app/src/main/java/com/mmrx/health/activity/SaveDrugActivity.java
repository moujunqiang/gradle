package com.mmrx.health.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.mmrx.health.BaseActivity;
import com.mmrx.health.R;
import com.mmrx.health.bean.Drug;
import com.mmrx.health.fragment.DrugFragment;
import com.mmrx.health.util.BitmapCache;
import com.mmrx.health.util.Constant;
import com.mmrx.health.util.MyToast;

import java.util.Date;

public class SaveDrugActivity extends BaseActivity implements OnClickListener{

	Button but_dismiss;
	EditText et_baozhiqi;
	EditText et_name;
	EditText et_year;
	TextView tv_state;
	DbUtils dbUtils;

    //title bar 相关设置
    ImageButton mBackBut;
    TextView mTitleTv;
    Button but_more;

    String mFragmentTag;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_drug);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		dbUtils=DbUtils.create(this);
		but_dismiss = (Button) findViewById(R.id.sd_but_submit);
		et_baozhiqi = (EditText) findViewById(R.id.sd_et_baozhiqi);
		et_name = (EditText) findViewById(R.id.sd_et_name);
		et_year = (EditText) findViewById(R.id.sd_et_year);
		tv_state = (TextView) findViewById(R.id.item_tv_state);

        mBackBut = (ImageButton)findViewById(R.id.title_bar_back);
        mBackBut.setOnClickListener(this);

        LinearLayout back = (LinearLayout)findViewById(R.id.save_drug_layout);
//        back.setBackground(new BitmapDrawable(getResources(),
//                BitmapCache.getInstance().getBitmapBlur(R.drawable.background_save_drug,
//                        this,5,false)));
        back.setBackgroundDrawable(new BitmapDrawable(getResources(),
                BitmapCache.getInstance().getBitmapBlur(R.drawable.background_save_drug,
                        this,5,false)));

        mTitleTv = (TextView)findViewById(R.id.title_bar_title);
        mTitleTv.setText("药品录入");
        but_more = (Button)findViewById(R.id.title_bar_menu);
        but_more.setVisibility(View.GONE);

        mFragmentTag = getIntent().getStringExtra(Constant.FRAGMENT_TAG);
		
		but_dismiss.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name = et_name.getText().toString();
				if ("".equals(name)) {
					MyToast.showShort(getApplicationContext(), "请输入完整信息");
					return;
				}
				
			/*	String string = et_month.getText().toString();
				if ("".equals(string)) {
					MyToast.showShort(getApplicationContext(), "请输入完整信息");
					return;
				}
				int month = Integer.parseInt(string);
				*/
				
				String year = et_year.getText().toString();
				if ("".equals(year)) {
					MyToast.showShort(getApplicationContext(), "请输入完整信息");
					return;
				}


				String string2 = et_baozhiqi.getText().toString();
				if ("".equals(string2)) {
					MyToast.showShort(getApplicationContext(), "请输入完整信息");
					return;
				}
				int baozhiqi = Integer.parseInt(string2);


				try {
					dbUtils.save(new Drug(name, year, baozhiqi));
					MyToast.showShort(getApplicationContext(), "录入成功");
					Intent intent=new Intent();
					intent.setAction(DrugFragment.ACTION);
					sendBroadcast(intent);
					finish();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_bar_back:
                Intent intent = new Intent();
                intent.setClass(this,ManageActivity.class);
                intent.putExtra(Constant.FRAGMENT_TAG,mFragmentTag);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
