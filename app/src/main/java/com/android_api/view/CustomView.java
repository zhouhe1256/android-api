package com.android_api.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android_api.R;

/**
 * Created by zhouh on 15-12-24.
 */
public class CustomView extends View{
    private Paint paint; //画笔
    
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init() {



    }
    @Override
    protected void onDraw(Canvas canvas) {
        //canvas 相当于一张白纸
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        //canvas.clipRect(10, 10, 280, 260);
        paint = new Paint();
        paint.setColor(Color.RED);//设置画笔颜色
        paint.setStyle(Paint.Style.FILL);//画笔填充
        canvas.drawCircle(300,300,200,paint);//在画布上画一个圆
        canvas.rotate(10f);
        paint.setColor(Color.YELLOW);
        paint.setTextSize(200);
        canvas.drawText("一个圆",50,50,paint);

        Bitmap iconbit = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        paint.setColor(Color.RED);
        canvas.drawBitmap(iconbit,20,20,paint);

    }

    /**
     * 计算位置
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = measureHeight(heightMeasureSpec);
        int measuredWidth = measureWidth(widthMeasureSpec);
        setMeasuredDimension(measuredWidth,measuredHeight);
    }
    private int measureHeight(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        int result = 500;
        if(specMode == MeasureSpec.AT_MOST){
            result = specSize;
        }
        else if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }
        return result;
    }
    private int measureWidth(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        int result = 500;
        if(specMode == MeasureSpec.AT_MOST){
            result = specSize;
        }
        else if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }
        return result;
    }

}
