package com.example.myapplication.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TouchRegionView extends View {

    Paint paint = new Paint();
    //主要是Region ,region存储了圆的Path
    Region circleRegion;//圆的Region
    Path circlePath;//圆的path

    public TouchRegionView(Context context) {
        super(context);
    }

    public TouchRegionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchRegionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circlePath = new Path();
        circlePath.addCircle(300, 300, 100, Path.Direction.CW);
        Region region = new Region(0, 0, getWidth(), getHeight());
        circleRegion = new Region();
//把圆的path 添加到圆的Region中
        circleRegion.setPath(circlePath, region);
        paint.setColor(Color.GREEN);
        canvas.drawPath(circlePath, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            boolean contains = circleRegion.contains((int) event.getX(), (int) event.getY());
            if (contains) {
                Toast.makeText(getContext(), "点击了圆", Toast.LENGTH_LONG).show();
            }
        }
        return super.onTouchEvent(event);
    }
}
