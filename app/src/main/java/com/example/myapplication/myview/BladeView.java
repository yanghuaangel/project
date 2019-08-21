package com.example.myapplication.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class BladeView extends View {
    private final static String TAG = "BladeView";
    private static final String[] mAlphabet = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private static final int allCount = 26;
    private Paint mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mSelectedLetterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCharHeight;
    private int choose = 0;

    private OnScollLister mOnScollLister;
    private String[] mSectionHeaders;
    //
    private TextView mPrimaryText;
    private ImageView mThumbImage;
    private boolean mShowLetterThumb = false;

    public BladeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BladeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BladeView(Context context) {
        super(context);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("yanghua","i  am onLayout");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        mCharHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / allCount;
//        Log.d("yanghua","getLeft() = " + getHeight());
    }

    private void init(Context context) {
        float letterSize = valueToDimen(TypedValue.COMPLEX_UNIT_SP, 16);
        mTxtPaint.setTextSize(letterSize);
        mTxtPaint.setColor(getResources().getColor(android.R.color.darker_gray));
        mTxtPaint.setStyle(Style.FILL);

        mSelectedLetterPaint.setColor(Color.parseColor("#fe0a5a"));
        mSelectedLetterPaint.setTextSize(letterSize);
    }

    public void setLetterThumb(ImageView thumb, TextView textView) {
        mThumbImage = thumb;
        mPrimaryText = textView;
    }

    public void setOnScollLister(OnScollLister listener) {
        mOnScollLister = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        //Log.d(TAG, "luotest onTouchEvent action:" + action);
        if (action == MotionEvent.ACTION_DOWN
                || action == MotionEvent.ACTION_MOVE) {
            getParent().requestDisallowInterceptTouchEvent(true);
            int item = (int) ((event.getY() - getPaddingTop()) / mCharHeight);
            if (item < 0 || item >= allCount) {
                return true;
            }
            Log.d(TAG, "onTouchEvent item:" + item);
            moveToSelection(item);
            choose = item;
            mShowLetterThumb = true;
            invalidate();
        } else {
            mShowLetterThumb = false;
            hideLetterThumb();
            Log.d(TAG, "onTouchEvent up");
        }

        return true;
    }

    public void updateSectionHeaders(String[] headers) {
        mSectionHeaders = headers;
    }

    public void setScrollItem(int item) {
        if (item > -1 && item < allCount) {
            boolean hasSetChoose = false;
            if (mSectionHeaders != null && mSectionHeaders.length >= 0 && item <= mSectionHeaders.length) {
                String name = mSectionHeaders[item];
                for (int kk = 0; kk < allCount; kk++) {
                    if (mAlphabet[kk].equals(name)) {
                        choose = kk;
                        Log.d(TAG, "setScrollItem choose set name--kk=" + name + "--" + kk);
                        hasSetChoose = true;
                        break;
                    }
                }
            }
            if (!hasSetChoose) {
                choose = item;
            }
            invalidate();
        }
    }

    private void moveToSelection(int item) {
        Log.d(TAG, "moveToSelection item:" + mAlphabet[item]);
        if (mOnScollLister != null) {
            mOnScollLister.onScoll(mAlphabet[item]);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "luotest initView onDraw=" + mCharHeight);
        mCharHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / allCount;
        for (int i = 0; i < allCount; i++) {
            float width = mSelectedLetterPaint.measureText(mAlphabet[i]);
            Paint currentPaint = mTxtPaint;
            if (choose == i) {
                currentPaint = mSelectedLetterPaint;
            }
            float startx = 0;
            float starty = getTop() + getPaddingTop() + (i + 1) * mCharHeight;
            // 字母水平方向居中，左下坐标
            startx = 0.5f * (getWidth() - width);

            /*Log.i(TAG, "luotest initView onDraw mCharHeight=" + mCharHeight
                    + "    startx=" + startx
                    + "    starty=" + starty
                    + "    getWidth()=" + getWidth());*/
            canvas.drawText(mAlphabet[i], startx, starty, currentPaint);
        }
        if (mShowLetterThumb) {
            if (mThumbImage != null && mPrimaryText != null) {
                mThumbImage.setVisibility(View.VISIBLE);
                mPrimaryText.setVisibility(View.VISIBLE);
                int thumbX = 0;
                int thumbY = 0;
                int thumbWidth = mThumbImage.getWidth();
                int thumbHeight = mThumbImage.getHeight();
                if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault())
                        == View.LAYOUT_DIRECTION_RTL) {
                    thumbX = getRight();
                } else {
                    thumbX = getLeft() - thumbWidth;
                }
                // 气泡图边上有阴影，所以注释掉下面部分，控件的右下角和字母的底部对齐
                thumbY = getTop() + getPaddingTop() + (choose + 1) * mCharHeight/* - mCharHeight / 2*/ - thumbHeight;
                mThumbImage.setX(thumbX);
                mThumbImage.setY(thumbY);
                //
                mPrimaryText.setText(mAlphabet[choose]);
                if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault())
                        == View.LAYOUT_DIRECTION_RTL) {
                    mPrimaryText.setX(thumbX + thumbWidth - thumbHeight);
                } else {
                    mPrimaryText.setX(thumbX);
                }
                mPrimaryText.setY(thumbY);
                Log.i(TAG, "yanghua  onDraw="
                        + "    thumbX=" + thumbX
                        + "    thumbY=" + thumbY
                        +"     getLeft()=" + getLeft()
                );
            }
        } else {
            hideLetterThumb();
        }
    }

    private void hideLetterThumb() {
        if (mThumbImage != null && mPrimaryText != null) {
            mThumbImage.setVisibility(View.GONE);
            mPrimaryText.setVisibility(View.GONE);
        }
    }

    private int valueToDimen(int unit, int value) {
        return (int) TypedValue.applyDimension(unit, value, getResources().getDisplayMetrics());
    }

    public interface OnScollLister {
        void onScoll(String item);
    }
}
