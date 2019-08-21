package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.MotionEvent;

public class LeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        char[] aa = new char[20] ;
        String bb = "welcom ";
        bb.toCharArray();

        Handler handler =  new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                System.out.println("test");
                return false;
            }
        }){

        };

        handler.sendEmptyMessageDelayed(0,300*1000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);

    }
}
