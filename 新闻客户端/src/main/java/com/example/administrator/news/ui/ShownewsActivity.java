package com.example.administrator.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.example.administrator.news.R;

public class ShownewsActivity extends AppCompatActivity {
    private static final String TAG = "ShownewsActivity";
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownews);
        mWebView = (WebView) findViewById(R.id.wv_shownews);
        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        Log.d(TAG, "传过来了!!!!!!!!!!!!!!!!!!!!: " + url);
        mWebView.loadUrl(url);
    }
}
