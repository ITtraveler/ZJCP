package com.hgs.zjcp.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hgs.zjcp.R;

/**
 * Created by hgs on 2016/10/26.
 */
public class ActivityUtils {
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();

    }

    /**
     * snackbar
     * @param context
     * @param view
     * @param content
     */
    public static void showSnackbar(Context context,View view,String content){
        Snackbar snackbar = Snackbar.make(view,""+content,Snackbar.LENGTH_SHORT);
        View mView = snackbar.getView();
        mView.setBackgroundColor(context.getResources().getColor(R.color.colorSnackBarBg));
        TextView tvSnackbarText = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
        tvSnackbarText.setText("hello world");
        tvSnackbarText.setTextColor(context.getResources().getColor(R.color.colorSnackBarText));
        snackbar.show();
    }
}
