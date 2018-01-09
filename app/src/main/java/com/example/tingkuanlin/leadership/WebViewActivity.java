package com.example.tingkuanlin.leadership;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.tingkuanlin.leadership.Controllers.NoteAdapter;

/**
 * Created by tingkuanlin on 2018/1/7.
 */

public class WebViewActivity extends Activity {


    WebView webview = null;
    WebViewClient webViewClient = null;

    ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webview = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //setContentView(webview);
        //webViewClient = new WebViewClient();

        webview.setWebChromeClient(new WebChromeClient(){


            public void onProgressChanged(WebView view, int progress)
            {
                progressBar.setProgress(progress);

                if(progress == 100) progressBar.setVisibility(View.GONE);
            }


        });
        webview.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);


            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onLoadResource(WebView view, String url) {

                super.onLoadResource(view, url);

            }
        });
        webview.loadUrl("https://www.facebook.com/ncculeadership/");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }




}
