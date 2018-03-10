package com.ryan.testweb;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    WebView webView;
    ProgressDialog progDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.web_show)).setOnClickListener(this);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.web_show:
                progDailog = ProgressDialog.show(MainActivity.this, "Loading","Please wait...", true);
                progDailog.setCancelable(false);
                webView.setWebChromeClient(new WebChromeClient());
                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        Log.i("ryan", "shouldOverrideUrlLoading: startAnotherActivity"+url);
                        progDailog.show();
                        view.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        progDailog.dismiss();
                    }
                });
                webView.loadUrl("https://mall.flnet.com");
                break;

        }
    }
}
