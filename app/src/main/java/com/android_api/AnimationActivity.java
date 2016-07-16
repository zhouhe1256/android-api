package com.android_api;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimationActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.scale)
    Button scaleButton;
    @Bind(R.id.translate)
    Button translateButton;
    @Bind(R.id.alpha)
    Button alphaButton;
    @Bind(R.id.rotate)
    Button rotateButton;
    private Animation myAnimation_Alpha;
    private Animation myAnimation_Translate;
    private Animation myAnimation_Scale;
    private Animation myAnimation_Rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        scaleButton.setOnClickListener(this);
        translateButton.setOnClickListener(this);
        alphaButton.setOnClickListener(this);
        rotateButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scale:
                myAnimation_Scale = AnimationUtils.loadAnimation(this,R.anim.my_scale_action);
                scaleButton.startAnimation(myAnimation_Scale);
                break;
            case R.id.translate:
                myAnimation_Translate = AnimationUtils.loadAnimation(this,R.anim.my_translate_action);
                translateButton.startAnimation(myAnimation_Translate);
                break;
            case R.id.rotate:
                myAnimation_Rotate = AnimationUtils.loadAnimation(this,R.anim.my_rotate_action);
                rotateButton.startAnimation(myAnimation_Rotate);
                break;
            case R.id.alpha:
                myAnimation_Alpha = AnimationUtils.loadAnimation(this,R.anim.my_alpha_action);
                alphaButton.startAnimation(myAnimation_Alpha);
                break;
        }
    }
}
