
package com.android_api.util;

import android.app.Activity;
import android.view.Gravity;

import android.widget.Toast;


/**
 * Created by dengt on 15-4-21.
 */
public class DialogUtil {
    private static Toast toast;
    public static int gravity = Gravity.CENTER;

    public static void showMessage(Activity activity,String message) {
        if (toast == null) {
            toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.setGravity(gravity, 0, 0);
        toast.show();

    }


}
