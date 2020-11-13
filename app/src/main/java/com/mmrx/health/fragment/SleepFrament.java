package com.mmrx.health.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmrx.health.R;
import com.mmrx.health.activity.WeightActivity;
import com.mmrx.health.util.BitmapCache;
import com.mmrx.health.util.MyToast;
import com.mmrx.health.util.SPutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SleepFrament extends Fragment {

    private TextView mName;
	private TextView tv_time;
	private TextView tv_water;
	private TextView target_water;
	private Button btn_water;
	private Button set_water;

	int time=0;
	int water=0;
	AlarmManager alarmManager=null;
	int alarmtime=0;
    private boolean f=true;
    private float alpha=0.0f;
    private boolean isRun = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_sleep, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initview();
		SPutil putil=new SPutil(getActivity());
		int age = putil.ReadAge();
		if (age==-1) {
			MyToast.showShort(getActivity(), "没有您的年龄信息");
			
		}else if(age<18){
			time=9;
			alarmtime=20;
		}else if (age<59) {
			time=7;
			alarmtime=21;
		}else{
			time=5;
			alarmtime=22;
		}
		tv_time.setText(time+"小时");
//		tv_water.setText(water+"");
		boolean readAlarm = putil.ReadAlarm();
		if (readAlarm) {
			
		}
		
		super.onActivityCreated(savedInstanceState);
		
	}

	private void initview() {
		// TODO Auto-generated method stub
		tv_time = (TextView) getView().findViewById(R.id.sleep_tv_time);
		tv_water = (TextView) getView().findViewById(R.id.water_tv_time);
		set_water = (Button) getView().findViewById(R.id.water_set);
		btn_water = (Button) getView().findViewById(R.id.water_once);
		target_water = (TextView) getView().findViewById(R.id.water_tv_tar);
        mName = (TextView)getView().findViewById(R.id.sleep_fragment_name);
        final SPutil sp = new SPutil(getActivity());
        mName.setText(sp.ReadName());
        RelativeLayout back = (RelativeLayout)getView().findViewById(R.id.fragment_sleep_layou);
        back.setBackgroundDrawable(new BitmapDrawable(getResources(),
                BitmapCache.getInstance().getBitmapBlur(R.drawable.fragment_background_water,
                        getActivity(),15,false)));

		tv_water.setText(sp.ReadWater()+"");
		target_water.setText(sp.ReadLWater()+"");
		set_water.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				int set_wa = Integer.parseInt(tv_water.getText().toString());
				water = set_wa;
				sp.WriteWater(water);
				sp.WriteLWater(water);
				target_water.setText(set_wa+"");
				tv_water.setText(tv_water.getText().toString());
			}
		});
		btn_water.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (sp.ReadLWater() == 0){
					target_water.setText(sp.ReadLWater()+"");
				}else if(sp.ReadLWater() > 0){
					sp.WriteLWater(sp.ReadLWater()-1);
					System.out.println(sp.ReadLWater()+"3333333333");
					target_water.setText(sp.ReadLWater()+"");
				}
			}
		});
	}

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onPause() {
        super.onPause();
        isRun = false;
    }


	/*@Override
	public void onClick(View view) {
		if (water ==0){
			tv_water.setText(0+"杯");
		}else if(water > 0){
			water = water-1;
			tv_water.setText(water+"");
		}

	}*/
}
