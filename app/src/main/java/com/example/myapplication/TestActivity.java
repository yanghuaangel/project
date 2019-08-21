package com.example.myapplication;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(this.getClass().getSimpleName());

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //流畅率
                getSM();
            }
        });
    }

    private void getSM() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
//                Log.d("sfaf", "yanghua frameTimeNanos" + frameTimeNanos);
                //这里就是记录时间进行判断的地方
                //如果现在的时间与上一帧的时间差大于16.6ms就认为是丢帧了，并+1
                getSM();
            }
        });
    }
}
