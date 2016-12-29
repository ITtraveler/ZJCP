package com.hgs.zjcp.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgs.zjcp.R;
import com.hgs.zjcp.data.JuheWXChoice;
import com.hgs.zjcp.data.MobCookDetail;
import com.hgs.zjcp.listener.NetLoadingListener;
import com.hgs.zjcp.ui.ChoiceWebActivity;
import com.hgs.zjcp.ui.CookDetailActivity;
import com.hgs.zjcp.ui.MenuListActivity;
import com.hgs.zjcp.utils.net.NetAction;
import com.hgs.zjcp.utils.net.NetUtils;
import com.hgs.zjcp.view.MySwipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hgs on 2016/10/26.
 */
public class ChoiceFragment extends BaseFragment implements NetLoadingListener, MySwipe.OnUpLoadingListener, SwipeRefreshLayout.OnRefreshListener {
    private static ChoiceFragment choiceFragment;
    //数据
    private List<JuheWXChoice.Content> choiceList = new ArrayList<>();
    private NetAction netAction;
    private int curPager = 1;//记录当前所显示的页
    private ObjectAnimator animation;
    private ChoiceRecycleAdapter mAdapter;


    @BindView(R.id.main_bar_title)
    TextView tvTitle;
    @BindView(R.id.choice_recycle)
    RecyclerView cRecycle;
    @BindView(R.id.choice_mySwipe)
    MySwipe mySwipe;
    @BindView(R.id.loading_layout)
    View loadingLayout;
    @BindView(R.id.loading_icon)
    ImageView loadingIcon;

    public static ChoiceFragment newInstance() {
        if (choiceFragment == null) {
            choiceFragment = new ChoiceFragment();
        }
        return choiceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加监听器
        NetAction.addNetLoadingListener(NetAction.KUID_CHOICE_LIST, this);
        netAction = NetAction.newAction();
        netAction.getJuheWXChoice("" + curPager, choiceList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        ButterKnife.bind(this, view);
        initView();
        initSwipe();
        return view;
    }

    /**
     * up/down refresh
     */
    private void initSwipe() {
        mySwipe.setOnUpLoadingListener(this);
        mySwipe.setOnRefreshListener(this);
    }

    private void initView() {
        tvTitle.setText("指尖精选");
        //recycleView
        cRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ChoiceRecycleAdapter(choiceList);
        cRecycle.setAdapter(mAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 监听器触发处，网络数据获取完成，触发此
     *
     * @param kuid
     */
    @Override
    public void netLoadingComp(int kuid) {
        if (kuid == NetAction.KUID_CHOICE_LIST) {
            //取消下拉动画
            mySwipe.setRefreshing(false);
            //取消上拉动画
            cancelUpLoadingAnim();
            if (loadingLayout != null)
                loadingLayout.setVisibility(View.GONE);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 上拉刷新部分
     */
    @Override
    public void upLoading() {
        curPager++;//当前页增加
        netAction.getJuheWXChoice("" + curPager, choiceList);
    }

    @Override
    public void scrollMoving(int scrollY, int distance) {
        if (loadingLayout != null) {
            loadingLayout.setVisibility(View.VISIBLE);
            showUpLoadingAnim(loadingIcon);
        }
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        curPager = 1;
        netAction.getJuheWXChoice("" + curPager, choiceList);
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

    class ChoiceRecycleAdapter extends RecyclerView.Adapter<MyAdapterHolder> {
        List<JuheWXChoice.Content> choiceList;

        ChoiceRecycleAdapter(List<JuheWXChoice.Content> choiceList) {
            this.choiceList = choiceList;
        }

        @Override
        public MyAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.choice_list_layout, null);
            return new MyAdapterHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapterHolder holder, int position) {
            String imgUri = choiceList.get(position).getFirstImg();
            if (imgUri == null || imgUri.isEmpty()) {
                holder.listImg.setVisibility(View.GONE);
            } else
                netAction.loadImage(getContext(), choiceList.get(position).getFirstImg(), holder.listImg);
            holder.listSource.setText("" + choiceList.get(position).getSource());
            holder.listTitle.setText("" + choiceList.get(position).getTitle());
            holder.setItemClickListener(choiceList.get(position).getUrl());
        }

        @Override
        public int getItemCount() {
            return choiceList.size();
        }

    }

    class MyAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.choice_list_img)
        ImageView listImg;
        @BindView(R.id.choice_list_title)
        TextView listTitle;
        @BindView(R.id.choice_list_source)
        TextView listSource;
        @BindView(R.id.choice_list)
        LinearLayout listLayout;

        public MyAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        /**
         * item点击事件
         *
         * @param uri
         */
        public void setItemClickListener(final String uri) {
            listLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (uri != null && !uri.isEmpty()) {
                        Intent intent = new Intent(getContext(), ChoiceWebActivity.class);
                        intent.putExtra("uri", uri);
                        startActivity(intent);
                    }
                }
            });

        }
    }
}
