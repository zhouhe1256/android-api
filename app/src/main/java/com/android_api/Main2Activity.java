package com.android_api;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/***
 * 用Drawable 实现动画效果
 */
public class Main2Activity extends Activity {
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayout = new LinearLayout(this);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.my_image);
        ImageView i = new ImageView(this);
        //i.setImageBitmap(bitmap);
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mLinearLayout.addView(i);
        setContentView(mLinearLayout);
        Resources res = getResources();
        TransitionDrawable transition =
                (TransitionDrawable) res.getDrawable(R.drawable.expand_collapse);
        i.setImageDrawable(transition);
        transition.startTransition(10000);
    }

}
