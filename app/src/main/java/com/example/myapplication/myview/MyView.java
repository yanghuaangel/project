package com.example.myapplication.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

public class MyView extends android.support.v7.widget.AppCompatTextView {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("MyView","onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d("MyView","onLayout");
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("MyView","onDraw");
        super.onDraw(canvas);

    }


}
