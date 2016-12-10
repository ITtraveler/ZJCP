package com.hgs.zjcp.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.hgs.zjcp.R;

/**
 * Created by hgs on 2016/10/30.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout{
    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeResources(R.color.colorBackground,R.color.colorBoard,R.color.colorRed);
    }


}
