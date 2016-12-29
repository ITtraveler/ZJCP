package com.hgs.zjcp.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgs.zjcp.R;
import com.hgs.zjcp.data.MobCookDetail;
import com.hgs.zjcp.data.MobMenuCategory;
import com.hgs.zjcp.listener.NetLoadingListener;
import com.hgs.zjcp.utils.net.NetAction;
import com.hgs.zjcp.view.MySwipe;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hgs on 2016/10/30.
 * 菜谱类表
 */

public class MenuListActivity extends BaseActivity implements NetLoadingListener {

    private NetAction netAction;
    private int page = 1;
    private List<MobCookDetail> mobCookDetails = new ArrayList<MobCookDetail>();
    @BindView(R.id.menu_list_fresh)
    MySwipe mSwipeFlash;
    @BindView(R.id.menu_list_recycle)
    RecyclerView mRecycleView;
    @BindView(R.id.loading_layout)
    View loadingLayout;
    @BindView(R.id.loading_icon)
    ImageView loadingIcon;
    private MyRecycleAdapter mRecycleAdapter;
    private String ctgId;
    private ObjectAnimator animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        ButterKnife.bind(this);
        NetAction.addNetLoadingListener(NetAction.KUID_MENU_LIST, this);//设置监听器
        MobMenuCategory.Child child = (MobMenuCategory.Child) getIntent().getSerializableExtra("category");
        // initRecycleView();
        ctgId = child.getCtgId();
        netAction = NetAction.newAction();

        netAction.getMenuList(child.getCtgId(), page, mobCookDetails);//加载第一页数据，

        initRecycleView();

        mSwipeFlash.setRefreshing(true);
        mSwipeFlash.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mobCookDetails.clear();
                page = 1;
                netAction.getMenuList(ctgId, page, mobCookDetails);//加载第一页数据，
            }
        });
        //  mSwipeFresh.setRefreshing(true);
        mSwipeFlash.setOnUpLoadingListener(new MySwipe.OnUpLoadingListener() {
            @Override
            public void upLoading() {
                System.out.println("flash");
                //showUpLoadingAnim(loadingIcon);
                netAction.getMenuList(ctgId, ++page, mobCookDetails);//加载第下一页数据，
            }

            @Override
            public void scrollMoving(int scrollY, int distance) {
                System.out.println("dis:" + distance);
                if (loadingLayout != null) {
                    loadingLayout.setVisibility(View.VISIBLE);
                    showUpLoadingAnim(loadingIcon);
//                    if (distance < 150) {
//                        cancelUpLoadingAnim();
//                        loadingLayout.setVisibility(View.GONE);
//                    }
                }
            }
        });
    }

    //初始化recycleView
    private void initRecycleView() {
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecycleAdapter = new MyRecycleAdapter(mobCookDetails);
        mRecycleView.setAdapter(mRecycleAdapter);
    }

    //数据加载好后，监听器便调用
    @Override
    public void netLoadingComp(int kuid) {
        if (kuid == NetAction.KUID_MENU_LIST) {
            //mSwipeFresh.setRefreshing(false);
            mRecycleAdapter.notifyDataSetChanged();
            //取消下拉的动画
            mSwipeFlash.setRefreshing(false);
            //取消上拉的动画
            cancelUpLoadingAnim();
            if (loadingLayout != null)
                loadingLayout.setVisibility(View.GONE);
        }
    }


    //开启上拉加载Anim
    private void showUpLoadingAnim(View view) {
        animation = ObjectAnimator//
                .ofFloat(view, "rotation", 0.0F, 360.0F)//
                .setDuration(500);//
        animation.setRepeatMode(ValueAnimator.RESTART);
        animation.setRepeatCount(100);
        animation.start();
    }

    //取消上拉加载
    private void cancelUpLoadingAnim() {
        if (animation != null)
            animation.cancel();
    }

    class MyRecycleAdapter extends RecyclerView.Adapter<MyAdapterHolder> {
        List<MobCookDetail> mobCookDetails;

        MyRecycleAdapter(List<MobCookDetail> mobCookDetails) {
            this.mobCookDetails = mobCookDetails;
        }

        @Override
        public MyAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.menu_list, null);
            return new MyAdapterHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapterHolder holder, int position) {
            MobCookDetail cookDetail = mobCookDetails.get(position);
            netAction.loadImage(MenuListActivity.this, cookDetail.getThumbnail(), holder.ivCookImg);
            holder.tvCookName.setText(cookDetail.getName());
            holder.setOnClick(cookDetail);
        }

        @Override
        public int getItemCount() {
            return mobCookDetails.size();
        }

    }

    class MyAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.menu_list_cook_img)
        ImageView ivCookImg;
        @BindView(R.id.menu_list_cook_name)
        TextView tvCookName;
        @BindView(R.id.menu_list_cook)
        LinearLayout mCooksLayout;

        public MyAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //点击传入对应的内容
        public void setOnClick(final MobCookDetail cookDetail) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //判空
                    if (cookDetail != null && cookDetail.getRecipe() != null ) {
                        Intent intent = new Intent(MenuListActivity.this, CookDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("MobCookDetail", cookDetail);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            });

        }

    }
}

