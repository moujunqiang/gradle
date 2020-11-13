package com.mmrx.health.fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.mmrx.health.R;
import com.mmrx.health.activity.BodyBuildingSettingActivity;
import com.mmrx.health.bean.BodyBuildingBean;
import com.mmrx.health.util.BitmapCache;
import com.mmrx.health.util.Constant;
import com.mmrx.health.util.L;
import com.mmrx.health.util.SPutil;

import java.util.List;

import mmrx.com.metrolayout.MetroView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BodyBuildingFragment extends Fragment {

    View mInflater;
//    LinearLayout mLinearLayout;
    List<BodyBuildingBean> mlist;
//    MetroAdapter mMetroAdapter;
    DbUtils mDb;
    Button adf;

    MetroView[] metroArr = new MetroView[7];
    final int mertoViewID[] = new int[]{R.id.body_metor_1_1,R.id.body_metor_1_2,R.id.body_metor_2_1,
            R.id.body_metor_2_2,R.id.body_metor_2_3,R.id.body_metor_3_1,R.id.body_metor_3_2,
            };
    //应该显示的metro的数量，应该小于等于7
    int metroViewShowNum;
    //刷新线程
    ReflashMetroLayoutThread mInitThread;

    Handler mHandler = new Handler(){
        @SuppressLint("NewApi")
        public void handleMessage(android.os.Message msg) {
            if(msg.what == Constant.MESSAGE_UPDATE_BODY_FRAGMENT){
                //隐藏所有控件
                hideAllMetroViews();
                for(int i=0;i<metroViewShowNum&&i<7;i++){
                    metroArr[i].setVisibility(View.VISIBLE);
                    metroArr[i].invalidate();
                }
                }
            }

        };

    public BodyBuildingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflater = inflater.inflate(R.layout.fragment_body_building, container, false);
        L.i("BodyBuildingFragment---onCreateView");
        init();
        return mInflater;
    }
    private void init(){
        mDb = DbUtils.create(getActivity());
        SPutil sp = new SPutil(BodyBuildingFragment.this.getActivity());
        sp.WriteBuildUpdate(true);
        //填充metro数组
        for(int i=0;i<7;i++){
            metroArr[i] = (MetroView)mInflater.findViewById(mertoViewID[i]);

        }
        /**
         * 测试按钮
         * */
        adf = (Button)mInflater.findViewById(R.id.body_but_dismiss);
        adf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodyBuildingSettingActivity.class);
                intent.putExtra(Constant.FRAGMENT_TAG,Constant.FRAGMENT_TAG_BUILD);
                startActivity(intent);
            }
        });
        RelativeLayout back = (RelativeLayout)mInflater.findViewById(R.id.fragment_build_layout);
//        back.setBackground(new BitmapDrawable(getResources(),
//                BitmapCache.getInstance().getBitmapBlur(R.drawable.fragment_background_build,
//                        getActivity(),30,false)));
        back.setBackgroundDrawable(new BitmapDrawable(getResources(),
                BitmapCache.getInstance().getBitmapBlur(R.drawable.fragment_background_build,
                        getActivity(),30,false)));


    }

    @Override
    public void onResume() {
        super.onResume();
        SPutil sp = new SPutil(getActivity());
        mInitThread = new ReflashMetroLayoutThread();
        mInitThread.setNeedReflash(sp.ReadBuildUpdate());
        mInitThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //打断线程
        if(mInitThread.isAlive())
            mInitThread.interrupt();

    }
    /**
     * 隐藏所有的metro控件
     * */
    private void hideAllMetroViews(){
        for(MetroView m: metroArr){
            if(m !=null)
                m.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        L.i("BodyBuildingFragment----onStop");
//        SPutil sp = new SPutil(BodyBuildingFragment.this.getActivity());
//        sp.WriteBuildUpdate(true);
        //更新所有的metroView
        if(mlist !=null) {
            try {
                mDb.updateAll(mlist);
            }catch (DbException dbe){
                dbe.printStackTrace();
            }
        }
    }

    private class ReflashMetroLayoutThread extends Thread{
        /**
         * 这个是否刷新需不需要在xml里面保存一下，只要没有修改过db里面的相关内容，就还是
         * 用原来的东西，修改后再重新刷新
         * */
        private boolean needReflash;
        @Override
        public void run() {
            if(needReflash) {
                try {
                    List<BodyBuildingBean> listFromDb = mDb.findAll(BodyBuildingBean.class);

                    if (listFromDb != null ) {
                        mlist = listFromDb;
                        if(listFromDb.size()>7)
                            L.e("健身计划数量大于7个...");
                        //将bean中的数据填充到数组里面去
                        metroViewShowNum = 0;
                        for(int i=0;i<listFromDb.size() && i<7;i++){
                            BodyBuildingBean temp_bean = listFromDb.get(i);
                            L.i("ReflashMetroLayoutThread--style"+temp_bean.getmStyle());
                            metroArr[i].setAttribute(temp_bean,BodyBuildingFragment.this.getActivity());
                            temp_bean.setThisNode(temp_bean);
                            temp_bean.setThisView(metroArr[i]);
                            //判断是否需要打卡，需要打卡就给添加上点击事件
                            if(temp_bean.ismCheckSetting())
                                metroArr[i].setOnClickListener(listFromDb.get(i));
                            metroViewShowNum++;
                        }
                        //通知刷新
                        Message message = new Message();
                        message.what = Constant.MESSAGE_UPDATE_BODY_FRAGMENT;
                        mHandler.sendMessage(message);
                    }
                } catch (DbException dbe) {
                    dbe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.needReflash = false;
                SPutil sp = new SPutil(BodyBuildingFragment.this.getActivity());
                sp.WriteBuildUpdate(this.needReflash);
            }
        }//end run

        public void setNeedReflash(boolean isNeed){
            this.needReflash = isNeed;
        }
    };
}
