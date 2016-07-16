package com.android_api.applical;



import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.android_api.constant.ApiUrl;
import com.android_api.util.PreferencesConstant;
import com.android_api.util.PreferencesUtils;
import com.zh.android.async.Arguments;
import com.zh.android.async.ICallback;
import com.zh.android.async.LooperCallbackExecutor;
import com.zh.android.cache.ChainedCache;
import com.zh.android.cache.DiskCache;
import com.zh.android.cache.MemoryCache;
import com.zh.android.remote.Http;
import com.zh.android.remote.HttpOption;
import com.zh.android.remote.IContentDecoder;
import com.zh.android.util.Logger;
import com.facebook.drawee.backends.pipeline.Fresco;


import org.json.JSONObject;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;


public class WApplication extends Application {
    private static WApplication wApplication;
    private static volatile boolean httpInited;
    private static File baseDir;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        wApplication=this;
        initHttp(this);
        Logger.setDebug(true);
    }



    public static WApplication getInstance() {
        return wApplication;
    }
    public void updateApiToken() {
        String token = PreferencesUtils.getString(wApplication, PreferencesConstant.API_TOKEN);
        Http.instance().param("t", token).option(HttpOption.X_Token, token);
    }
    public synchronized void initHttp(Context context) {
        if (httpInited) {
            return;
        }
        //this.Wcontext = context;


        httpInited = true;

        /**
         * 缓存的目录
         */
        baseDir = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ?
                Environment.getExternalStorageDirectory() : context.getCacheDir();
        baseDir = new File(baseDir, "woqu");
        DiskCache.setBaseDir(baseDir);


        //String token = "a9363cea86a08ef5d68f8fda8b8072469e374bc6";

        String token = null;
        DiskCache<String, byte[]> apiCache = new DiskCache<String, byte[]>("api", new DiskCache.ByteArraySerialization());
        Http.instance().option(HttpOption.BASE_URL, ApiUrl.HOST_URL).
                option(HttpOption.MIME, "application/json").
                param("t", token).param("v", ApiUrl.VERSION).
                param("os", ApiUrl.OS).
                option(HttpOption.CONNECT_TIMEOUT, 10000).
                option(HttpOption.READ_TIMEOUT, 10000).
                option(HttpOption.X_Token, token).
                option(HttpOption.X_Version, ApiUrl.VERSION).
                option(HttpOption.X_OS, ApiUrl.OS).
                setContentDecoder(new IContentDecoder.JSONDecoder()).
                cache(apiCache).fallbackToCache(true).
                always(new ICallback() {
                    @Override
                    public void call(Arguments arguments) {
                        Object object = arguments.get(0);

                        if (object instanceof Throwable) {
                            Throwable t = (Throwable) object;
                            StringWriter writer = new StringWriter();
                            writer.write(t.getMessage() + "\n");
                            t.printStackTrace(new PrintWriter(writer));
                            String s = writer.toString();
                            System.out.println(s);
                            return;
                        }

                        if (!(object instanceof JSONObject)) {
                            return;
                        }
                        JSONObject json = arguments.get(0);
                        if (!json.optBoolean("success")) {
                            String errorMessage = json.optString("message");
                            int code = json.optInt("code");
                            if (code == 13005) {
                            } else {
                            }
                        }
                    }
                }).start(new ICallback() {
            @Override
            public void call(Arguments arguments) {
//                if (flag == false) {
//                    flag = true;
//                    IntentFilter homeFilter = new IntentFilter(
//                            Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//                    mView.getContext().registerReceiver(dialogReceiver, homeFilter);
//                    if (mView.getParent() == null)
//                        wm.addView(mView, para);
//                }
            }
        }).complete(new ICallback() {
            @Override
            public void call(Arguments arguments) {
//                if (mView.getParent() != null) {
//                    flag = false;
//                    if (dialogReceiver != null) {
//                        mView.getContext().unregisterReceiver(dialogReceiver);
//                    }
//                    wm.removeView(mView);
//                }
            }
        }).
                callbackExecutor(new LooperCallbackExecutor()).
                fail(new ICallback() {
                    @Override
                    public void call(Arguments arguments) {
                    }
                });
        ChainedCache<String, byte[]> imageCache = ChainedCache.create(
                800 * 1024 * 1024, new MemoryCache.ByteArraySizer<String>(),
                "images", new DiskCache.ByteArraySerialization<String>()
        );
        Http.imageInstance().cache(imageCache).baseUrl(ApiUrl.HOST_URL).
                aheadReadInCache(true);
    }


}
