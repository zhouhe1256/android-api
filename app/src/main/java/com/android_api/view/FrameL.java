package com.android_api.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by zhouh on 16-1-7.
 * 实现Frame动画效果
 */
public class FrameL extends View {
    private AnimationDrawable frameAnimation;
    private Context mContext;
    private Drawable mBitAnimation;
    public FrameL(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FrameL(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public FrameL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FrameL(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }
    private void init(){
        /*实例化AnimationDrawable对象*/
        frameAnimation = new AnimationDrawable();
        /*装载资源*/
        //用循环装载所有类似名字的资源
        //a1到a3的图片名字
        for(int i=1;i<=3;i++){
            int id = getResources().getIdentifier("a"+i,
                    "drawable", mContext.getPackageName());
            Log.i("resourcesId","id="+id);
            mBitAnimation = getResources().getDrawable(id);
            frameAnimation.addFrame(mBitAnimation,500);


        }
        /*设置动画为循环播放*/
        frameAnimation.setOneShot(false);
        this.setBackgroundDrawable(frameAnimation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        frameAnimation.start();
    }


}
