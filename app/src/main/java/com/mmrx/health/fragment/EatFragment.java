package com.mmrx.health.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmrx.health.R;
import com.mmrx.health.activity.ManageActivity;
import com.mmrx.health.activity.ResultActivity;
import com.mmrx.health.activity.WeightActivity;
import com.mmrx.health.util.BitmapCache;
import com.mmrx.health.util.Constant;
import com.mmrx.health.util.MyToast;
import com.mmrx.health.util.SPutil;

public class EatFragment extends Fragment {
	
	private EditText et_weight;
	private Button but_dismiss;
	private Button but_save;
	private TextView weight1;
	private TextView weight2;
	StringBuffer targetWeight;
	SPutil putil;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_eat, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		initview();
		super.onActivityCreated(savedInstanceState);
		
	}

	private void initview() {
		putil=new SPutil(getContext());
		int w1 = putil.ReadWeight();
		// TODO Auto-generated method stub
		et_weight = (EditText) getView().findViewById(R.id.target_et_weight);
		but_dismiss = (Button) getView().findViewById(R.id.eat_but_dismiss);
		but_save = (Button) getView().findViewById(R.id.eat_but_save);
		weight1 = (TextView) getView().findViewById(R.id.notice_weight1);
		weight2 = (TextView) getView().findViewById(R.id.notice_weight2);
		weight1.setText(w1+"");
		weight2.setText(putil.ReadTargetWeight()+"");
		but_dismiss.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), WeightActivity.class);
				startActivity(intent);
			}
		});
		but_save.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String string = et_weight.getText().toString();
				if ("".equals(string)) {
					MyToast.showShort(getActivity(), "请填写体重");
					return;
				}
				putil.WriteTargetWeight(Integer.parseInt(et_weight.getText().toString()));
				weight2.setText(putil.ReadTargetWeight()+"");
			}
		});

        RelativeLayout back = (RelativeLayout)getView().findViewById(R.id.fragment_eat_layout);
        back.setBackgroundDrawable(new BitmapDrawable(getResources(),
                BitmapCache.getInstance().getBitmapBlur(R.drawable.fragment_background_food,
                        getActivity(),10,false)));

	}
	

}
