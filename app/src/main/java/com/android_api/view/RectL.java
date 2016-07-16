package com.android_api.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhouh on 15-12-31.
 */
public class RectL extends View implements  Runnable{
    private Paint mPaint = null;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RectL(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
    }

    public RectL(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public RectL(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public RectL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RectL(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, Paint mPaint) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mPaint = mPaint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        {
            Rect rect1 = new Rect();
            /*设置矩形大小*/
            rect1.left = 5;
            rect1.top = 5;
            rect1.bottom = 25;
            rect1.right = 45;
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(rect1,mPaint);

            mPaint.setColor(Color.RED);
            canvas.drawRect(50,5,90,25,mPaint);

            mPaint.setColor(Color.YELLOW);
            canvas.drawCircle(40,70,30,mPaint);

            /*绘制椭圆*/
            RectF rectf1 = new RectF();
            rectf1.left = 80;
            rectf1.top = 30;
            rectf1.right = 120;
            rectf1.bottom = 70;
            mPaint.setColor(Color.LTGRAY);
            canvas.drawOval(rectf1,mPaint);

            /*绘制多边形*/
            Path path1 = new Path();

            /*设置多边形的点*/
            path1.moveTo(150+5,80-50);
            path1.lineTo(150+45,80-50);
            path1.lineTo(150+30,120-50);
            path1.lineTo(150+20,120-50);
            /*使用这些点构成封闭的多边形*/
            path1.close();
            mPaint.setColor(Color.GRAY);
            canvas.drawPath(path1,mPaint);

            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(3);
            /*绘制直线*/
            canvas.drawLine(5,110,315,110,mPaint);
        }
        mPaint.setStyle(Paint.Style.FILL);
        {
            Rect rect1 = new Rect();
            rect1.left = 5;
            rect1.top = 130+5;
            rect1.right = 45;
            rect1.bottom = 130+25;
            mPaint.setColor(Color.BLUE);
            /*绘制矩形*/
            canvas.drawRect(rect1,mPaint);

            mPaint.setColor(Color.RED);
            canvas.drawRect(50,130+5,90,130+25,mPaint);
            mPaint.setColor(Color.YELLOW);
            canvas.drawCircle(40,130+70,30,mPaint);

            RectF rectf1 = new RectF();
            rectf1.left = 80;
            rectf1.top = 130+30;
            rectf1.right = 120;
            rectf1.bottom = 130+70;
            mPaint.setColor(Color.LTGRAY);
            canvas.drawOval(rectf1,mPaint);
            /*绘制多边形*/
            Path path1 = new Path();

            /*设置多边形的点*/
            path1.moveTo(150+5,130+80-50);
            path1.lineTo(150+45,130+80-50);
            path1.lineTo(150+30,130+120-50);
            path1.lineTo(150+20,130+120-50);
            /*使用这些点构成封闭的多边形*/
            path1.close();
            mPaint.setColor(Color.GRAY);
            canvas.drawPath(path1,mPaint);

            canvas.drawLine(5,130+110,315,130+110,mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return true;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            postInvalidate();
        }
    }
}
