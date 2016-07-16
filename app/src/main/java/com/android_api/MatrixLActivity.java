package com.android_api;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixLActivity extends Activity {

    @Bind(R.id.myImageView)
    ImageView mImageView;
    @Bind(R.id.layout1)
    AbsoluteLayout layout1;
    @Bind(R.id.myButton_1)
    Button mButton_1;
    @Bind(R.id.myButton_2)
    Button mButton_2;
    @Bind(R.id.text)
    TextView mTextView;
    private int displayWidth;
    private int displayHeight;
    private Bitmap bmp;
    private float scaleWidth = 1;
    private float scaleHeight = 1;
    private int id = 0;
    private int scaleTimes = 1;
    private int scaleAngle = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_l);
        ButterKnife.bind(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        displayWidth = dm.widthPixels;
        displayHeight = dm.heightPixels - 80;
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        setListeners();
    }

    private void setListeners() {
        mImageView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(Menu.NONE,1,0,R.string.str_context1);
                menu.add(Menu.NONE,2,0,R.string.str_context2);
                menu.add(Menu.NONE,3,0,R.string.str_context3);
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        switch (item.getItemId())
        {
            case 1:
                String strOpt =
                        getResources().getString(R.string.str_context1)
                        +"="+bmpWidth;
                mTextView.setText(strOpt);
                break;
            case 2:
                String strOpt2 =
                        getResources().getString(R.string.str_context2)
                                +"="+bmpHeight;
                mTextView.setText(strOpt2);
                break;
            case 3:
                String strOpt3 =
                        getResources().getString(R.string.str_context3)
                                +"="+bmpWidth+"和"+bmpHeight;
                mTextView.setText(strOpt3);
                break;

        }
        return super.onContextItemSelected(item);
    }

    @OnClick(R.id.myButton_1)
    public void smallButton(){
       // small();
        left();
    }
    @OnClick(R.id.myButton_2)
    public void bigButton(){
       // big();
        right();
    }
    private void small(){
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        double scale = 0.8;
        scaleWidth = (float) (scaleWidth*scale);
        scaleHeight = (float) (scaleHeight*scale);

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);
        if(id == 0){
            layout1.removeView(mImageView);
        }else{
            layout1.removeView((ImageView)findViewById(id));
        }
        id++;
        ImageView imageView = new ImageView(this);
        imageView.setId(id);
        imageView.setImageBitmap(resizeBmp);
        layout1.addView(imageView);
        //setContentView(layout1);
        mButton_2.setEnabled(true);
    }
    private void big(){
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        double scale = 1.25;
        scaleWidth = (float) (scaleWidth*scale);
        scaleHeight = (float) (scaleHeight*scale);

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);
        if(id == 0){
            layout1.removeView(mImageView);
        }else{
            layout1.removeView((ImageView)findViewById(id));
        }
        id++;
        ImageView imageView = new ImageView(this);
        imageView.setId(id);
        imageView.setImageBitmap(resizeBmp);
        layout1.addView(imageView);
        //setContentView(layout1);

        if (scaleWidth*scale*bmpWidth>displayWidth||
                scaleHeight*scale*bmpHeight>displayHeight){
            mButton_2.setEnabled(false);
        }
    }
    /*向左旋转*/
    private void left(){
        int widthOrig = bmp.getWidth();
        int heightOrig = bmp.getHeight();
        scaleAngle--;
        if (scaleAngle < -5){
            scaleAngle = -5;
        }
        int newWidth = widthOrig * scaleTimes;
        int newHeight = heightOrig * scaleTimes;

        float scaleWidth = ((float)newWidth) / widthOrig;
        float scaleHeight = ((float)newHeight) / heightOrig;

        Matrix matrix = new Matrix();
        /*使用Marix.postScale设置维度*/
        matrix.postScale(scaleWidth,scaleHeight);
        matrix.setRotate(5*scaleAngle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bmp,0,0,widthOrig,heightOrig,matrix,true);
        mImageView.setImageBitmap(resizedBitmap);
    }
    /*向右旋转*/
    private void right(){
        int widthOrig = bmp.getWidth();
        int heightOrig = bmp.getHeight();
        scaleAngle++;
        if (scaleAngle > 5){
            scaleAngle = 5;
        }
        int newWidth = widthOrig * scaleTimes;
        int newHeight = heightOrig * scaleTimes;

        float scaleWidth = ((float)newWidth) / widthOrig;
        float scaleHeight = ((float)newHeight) / heightOrig;

        Matrix matrix = new Matrix();
        /*使用Marix.postScale设置维度*/
        matrix.postScale(scaleWidth,scaleHeight);
        matrix.setRotate(5*scaleAngle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bmp,0,0,widthOrig,heightOrig,matrix,true);
        mImageView.setImageBitmap(resizedBitmap);
    }
    
}
