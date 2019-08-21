package com.example.myapplication;

import android.graphics.Color;
//import android.support.design.internal.FlowLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.myview.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class FlowLayoutActivity extends AppCompatActivity {


    private FlowLayout flowLayout;

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(this.getClass().getSimpleName());

        flowLayout = findViewById(R.id.flow);
        for (int i = 0; i < 10; i++) {
            list.add("Android");
            list.add("Java");
            list.add("IOS");
            list.add("python");
        }


//往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(28, 10, 28, 10);
            tv.setText(list.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
            tv.setBackgroundColor(Color.parseColor("#FFD5E2E1"));
            tv.setLayoutParams(layoutParams);
            flowLayout.addView(tv, layoutParams);
        }

    }
}

