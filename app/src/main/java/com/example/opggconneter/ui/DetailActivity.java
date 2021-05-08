package com.example.opggconneter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.opggconneter.R;
import com.example.opggconneter.databinding.ActivityDetailBinding;
import com.example.opggconneter.util.Util;

public class DetailActivity extends AppCompatActivity {
    private WebView webView;
    //https://www.op.gg/summoner/userName=
    private String url = "https://www.op.gg/summoner/userName=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding detailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(detailBinding.getRoot());



        String id = getIntent().getStringExtra("detailId");
        String name = getIntent().getStringExtra("detailName");
        setTitle(name);
        webView = detailBinding.webview;

        webView.getSettings().setJavaScriptEnabled(true);
        String realUrl = url + id;

        webView.loadUrl(realUrl);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == event.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}