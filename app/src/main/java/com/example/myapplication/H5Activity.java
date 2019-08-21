package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class H5Activity extends AppCompatActivity {

    public WebView mWebView;
    private WebAppInerface mWebApp;
    private Button mButton;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);

        mButton = findViewById(R.id.button11);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebApp.showName("我来自Native");
            }
        });

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/index.html");
        //1.设置webView允许执行JS脚本：
        mWebView.getSettings().setJavaScriptEnabled(true);
        //     2. 添加通信接口（注意：InterfaceName要与JS中的名字一致）
        mWebApp = new WebAppInerface(this);
        mWebView.addJavascriptInterface(mWebApp, "app");
    }

    //     2. 添加通信接口（注意：InterfaceName要与JS中的名字一致）
    class WebAppInerface {
        private Context mContext;

        public WebAppInerface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void sayHello(String name) {
            if (getMainLooper() == Looper.myLooper()) {
                Log.d("yanghua", "这个是主线程");
            }
            Toast.makeText(mContext, "传入的值是:" + name, Toast.LENGTH_SHORT).show();
            mButton.setText("改UI" + Math.random());
            mButton.setBackgroundResource(R.color.colorAccent);

        }

        public void showName(final String name) {
            mWebView.loadUrl("javascript:showName('" + name + "')");
        }
    }

}
