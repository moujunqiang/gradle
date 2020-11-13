package com.mmrx.health.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mmrx.health.BaseActivity;
import com.mmrx.health.R;

public class WebActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton mTitleBarBack;
    /**
     * 标题
     */
    private TextView mTitleBarTitle;
    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        mTitleBarBack = (ImageButton) findViewById(R.id.title_bar_back);
        mTitleBarTitle = (TextView) findViewById(R.id.title_bar_title);
        mWeb = (WebView) findViewById(R.id.web);
        mTitleBarBack.setOnClickListener(this);
        WebSettings settings = mWeb.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.setWebChromeClient(new WebChromeClient());
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        mTitleBarTitle.setText(title);
        //加载url
        mWeb.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_back:
                finish();
                break;

        }
    }
}