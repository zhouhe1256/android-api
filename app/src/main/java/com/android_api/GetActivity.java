package com.android_api;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.android_api.model.WoQuListModel;
import com.android_api.util.DialogUtil;
import com.zh.android.async.Arguments;
import com.zh.android.async.ICallback;
import com.zh.android.view.ImageViewAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GetActivity extends Activity {
    private WoQuListModel woQuModel;

    @Bind(R.id.text_1)
    TextView textView;
    @Bind(R.id.image_1)
    ImageView imageVIew;
    @Bind(R.id.fresco_image)
    SimpleDraweeView frescoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        ButterKnife.bind(this);
        initView();
        setData();
    }

    private void initView() {

        //用了facebook的fresco框架
        Uri uri = Uri.parse("http://pic2.ooopic.com/01/03/51/25b1OOOPIC19.jpg");
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .build();
        frescoImage.setAspectRatio(2f);
        frescoImage.setController(controller);
       // frescoImage.setImageURI(uri);
    }

    private void setData() {

        /**
         * get请求的简例.
         */
        WoQuListModel.getWoQuList("1").done(new ICallback() {
            @Override
            public void call(Arguments arguments) {
                woQuModel = arguments.get(0);
                if (woQuModel.getSuccess()) {
                    textView.setText("请求成功");

                    /**
                     * get请求图片的简单方式:
                     * 参数含义:
                     * 1:控件,2:url,3:默认图片(可以忽略),4是否缓存
                     * 具体看框架中的方法
                     */
                    ImageViewAdapter.adapt(
                            imageVIew,
                            woQuModel.getActivities().get(0).getImageUrl(),
                            android.R.drawable.ic_delete,
                            true);


                } else {
                    textView.setText("请求失败");
                }
            }
        }).fail(new ICallback() {
            @Override
            public void call(Arguments arguments) {
                DialogUtil.showMessage(GetActivity.this, "网络异常");
                textView.setText("网络异常");
            }
        });

        //post请求跟以上同步.....
        // Http.instance().post(url)
        //如果是delete请求 使用Http.instance().post(url).param("_method","DELETE")
    }

}
