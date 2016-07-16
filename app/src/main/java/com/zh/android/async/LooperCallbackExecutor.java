package com.zh.android.async;

import android.os.Handler;
import android.os.Looper;

import com.zh.android.util.Logger;

public class LooperCallbackExecutor implements ICallbackExecutor {

    private final Handler handler;

    public LooperCallbackExecutor() {
        this(Looper.getMainLooper());
    }

    public LooperCallbackExecutor(Looper looper) {
        handler = new Handler(looper);
    }

    @Override
    public void run(final ICallback callback, final Arguments arguments) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    callback.call(arguments);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.e("async", "run callback failed");
                }
            }
        };
        if (Looper.myLooper() == handler.getLooper()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }

}
