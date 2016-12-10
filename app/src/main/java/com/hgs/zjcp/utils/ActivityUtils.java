package com.hgs.zjcp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hgs on 2016/10/26.
 */
public class ActivityUtils {
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
