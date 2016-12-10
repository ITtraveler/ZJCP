package com.hgs.zjcp.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hgs on 2016/10/30.
 */
public class MySwipe extends SwipeRefreshLayout implements View.OnTouchListener {
    private RecyclerView mRecycler;
    private boolean loadDone = false;//是否加载完成
    private float oldY;
    private float curY;
    private OnUpLoadingListener loadingListener;
    private LinearLayoutManager layoutManager;
    private boolean loadStatus = false;//加载状态
    private int maxDis = 150;//滑动最长距离

    public MySwipe(Context context) {
        super(context);
    }

    public MySwipe(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
    }

    /**
     * l,t,r,b是父控件的可用空间
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (getChildCount() != 0) {
            View child = getChildAt(0);
            //判断其子视图是否为RecycleView
            if (child instanceof RecyclerView && !loadDone) {
                mRecycler = (RecyclerView) child;
                // mRecycler.get
                mRecycler.setOnTouchListener(this);
                loadDone = true;
                System.out.println("ok");
                layoutManager = (LinearLayoutManager) mRecycler.getLayoutManager();

            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //System.out.println(event.getY());
        if (loadDone) {
            int lvPosition;
            if (layoutManager == null) {
                layoutManager = (LinearLayoutManager) mRecycler.getLayoutManager();
            }
            lvPosition = layoutManager.findLastVisibleItemPosition();

            if (lvPosition == (layoutManager.getItemCount() - 1)) {
                curY = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldY = curY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        loadingListener.scrollMoving((int) curY, (int) oldY - (int) curY);//监听滑动Y坐标的变化
                        break;

                    case MotionEvent.ACTION_UP:
                        if (Math.abs(oldY - curY) >= maxDis) {
                            System.out.println("flash");
                            // setUpLoading(true);
                            loadingListener.upLoading();//通知上拉加载
                        }
                        break;

                }
            }
        }

        return false;
    }

    /**
     * 设置上拉距离，此界限作为刷新依据，默认150.
     *
     * @param maxDis
     */
    public void setMaxDis(int maxDis) {
        this.maxDis = maxDis;
    }
//    /**
//     * 是在是否上拉加载，从而决定是否显示上拉的视图
//     *
//     * @param upLoading  是否要上拉加载
//     */
//    public void setUpLoading(boolean upLoading) {
//        if (upLoading && !loadStatus) {
//           // addView(upLoadingView);
//            loadStatus = true;
//        } else if (!upLoading && loadStatus) {
//            //removeView(upLoadingView);
//            loadStatus = false;
//        }
//
//    }

    /**
     * 添加上拉刷新的监听器
     */

    public void setOnUpLoadingListener(OnUpLoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    /**
     * 上拉刷新的接口
     */
    public interface  OnUpLoadingListener {
        void upLoading();

        /**
         * @param scrollY  滚动的Y值变化
         * @param distance 滚动的距离
         */
        void scrollMoving(int scrollY, int distance);
    }
}
