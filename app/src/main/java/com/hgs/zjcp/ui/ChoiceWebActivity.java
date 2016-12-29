package com.hgs.zjcp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.hgs.zjcp.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hgs on 2016/12/21.
 */

public class ChoiceWebActivity extends BaseActivity {
    @BindView(R.id.main_bar_title)
    TextView choiceWebTitle;
    @BindView(R.id.choice_webView)
    WebView choiceWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_web);
        ButterKnife.bind(this);
        choiceWebTitle.setText("指尖精选");
        initWebView();
    }

    private void initWebView() {
        String uri = getIntent().getStringExtra("uri");

        WebSettings webSetting = choiceWeb.getSettings();
        webSetting.setJavaScriptEnabled(true);
        //仍然用本地的WebView打开
        choiceWeb.setWebViewClient(new WebViewClient());
        choiceWeb.loadUrl(uri);
    }
}
