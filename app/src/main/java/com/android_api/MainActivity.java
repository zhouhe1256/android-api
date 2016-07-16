package com.android_api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    float x = 0;
    float y = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttou_1)
    public void httpGetButton(){
        Intent intent = new Intent(MainActivity.this,GetActivity.class);
        startActivity(intent);
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                Toast.makeText(this,(event.getX()-x)+","+y,Toast.LENGTH_LONG).show();
                break;
        }
        return super.onTouchEvent(event);
    }*/

}
