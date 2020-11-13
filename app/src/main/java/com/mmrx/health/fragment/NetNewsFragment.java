package com.mmrx.health.fragment;

import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mmrx.health.R;
import com.mmrx.health.adapter.NetNewsAdapter;
import com.mmrx.health.bean.NetNewsBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetNewsFragment extends Fragment {
    private RecyclerView rvNews;
    private NetNewsAdapter adapter;
    private List<NetNewsBean.NewslistBean> newslistBeans = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.fragment_net_news, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        init();
        getData();
        super.onActivityCreated(savedInstanceState);
    }

    public void init() {
        rvNews = getView().findViewById(R.id.rv_net_news);
        adapter = new NetNewsAdapter();
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setList(newslistBeans);
        rvNews.setAdapter(adapter);
    }

    /**
     * 通过网络请求获取数据
     */
    public void getData() {
        String url = "https://api.tianapi.com/health/index?key= 1206b4de87aa23cc915baefe88887c48&num=10";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Request", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //通过json解析数据
                        Gson gson = new Gson();
                        NetNewsBean netNewsBean = null;
                        try {
                            netNewsBean = gson.fromJson(response.body().string(), NetNewsBean.class);
                            newslistBeans.clear();
                            newslistBeans.addAll(netNewsBean.getNewslist());
                            adapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }

}
