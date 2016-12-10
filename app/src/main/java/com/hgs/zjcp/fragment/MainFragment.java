package com.hgs.zjcp.fragment;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hgs.zjcp.listener.NetLoadingListener;
import com.hgs.zjcp.MyApplication;
import com.hgs.zjcp.R;
import com.hgs.zjcp.data.MobCook;
import com.hgs.zjcp.data.MobCookRecipe;
import com.hgs.zjcp.data.MobMethod;
import com.hgs.zjcp.utils.net.NetAction;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hgs on 2016/10/26.
 */
public class MainFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, ViewPager.OnPageChangeListener, NetLoadingListener {
    private static MainFragment mainFragment;
    private List<Bitmap> mADBitmapList;
    private NetAction netAction;
    private MobCook mobCook = new MobCook();

    public static MainFragment newInstance() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }

    @BindView(R.id.main_refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.main_AD_viewpager)
    ViewPager mADPager;
    @BindView(R.id.main_rg_adPoint)
    RadioGroup pointGroup;
    @BindView(R.id.guanmo_title)
    TextView tvGuanmoTitle;
    @BindView(R.id.guanmo_showImg)
    ImageView ivGuanmoShow;
    @BindView(R.id.guanmo_name)
    TextView tvGuanmoName;
    @BindView(R.id.guanmo_tag)
    LinearLayout guanmoTagLayout;
    //    @BindView(R.id.guanmo_recommend)
//    TextView tvGuanmoRecommend;
    @BindView(R.id.guanmo_ingredients)
    TextView tvGuanmoIngredients;
    @BindView(R.id.guanmo_step)
    LinearLayout guanmoStepLayout;

    private List<RadioButton> mADRBList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetAction.addNetLoadingListener(NetAction.KUID_GUANMO, this);
        netAction = NetAction.newAction();
        mADBitmapList = netAction.loadingAD(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        refreshLayout.setColorSchemeResources(R.color.colorBackground,R.color.colorBoard,R.color.colorRed);
        refreshLayout.setOnRefreshListener(this);
        initView();
        initGuanmo();
        // tvGuanmoTitle.setText(mobCook.toString());
        handler.sendEmptyMessageDelayed(0x1, 3000);
        netAction.getRandomCook(mobCook);
        return view;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1) {
                mADPager.setCurrentItem(mADPager.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(0x1, 3000);
            }
        }
    };


    /**
     * 初始化观摩
     */
    private void initGuanmo() {
        if (mobCook.getResult() != null && mobCook.getResult().getRecipe() != null && mobCook.getResult().getRecipe().getImg() != null && !mobCook.getResult().getRecipe().getImg().isEmpty()) {
            MobCookRecipe cookRecipe = mobCook.getResult().getRecipe();
            tvGuanmoTitle.setText("" + cookRecipe.getTitle());
            tvGuanmoName.setText("" + mobCook.getResult().getName());

            netAction.loadImage(getContext(), cookRecipe.getImg(), ivGuanmoShow);
            String[] tags = mobCook.getResult().getCtgTitles().split(",");
            guanmoTagLayout.removeAllViews();
            for (String tag : tags) {
                TextView textView = new TextView(getContext());
                LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mParam.setMargins(0, 15, 15, 20);
                textView.setLayoutParams(mParam);
                textView.setText(tag);
                textView.setBackground(getResources().getDrawable(R.drawable.tag_bg));
                guanmoTagLayout.addView(textView);
            }
//            if (!cookRecipe.getSumary().isEmpty())
//                tvGuanmoRecommend.setText("推荐理由:" + cookRecipe.getSumary());
            if (!cookRecipe.getIngredients().isEmpty()) {
                String ingredient = cookRecipe.getIngredients().replace("[\"", "");
                tvGuanmoIngredients.setText("制作准备\n" + ingredient.replace("\"]", ""));
            }
            if (cookRecipe.getMethod() != null && !cookRecipe.getMethod().isEmpty()) {//制作具体流程
                guanmoStepLayout.removeAllViews();
                Type type = new TypeToken<List<MobMethod>>() {
                }.getType();
                Gson gson = new Gson();
                List<MobMethod> methods = gson.fromJson(cookRecipe.getMethod(), type);
                for (MobMethod method : methods) {
                    System.out.println(method.toString());
                    if (method != null) {
                        if (method.getImg() != null && !method.getImg().isEmpty()) {
                            ImageView img = new ImageView(getContext());
                            float height = getResources().getDimension(R.dimen.step_image_height);
                            LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) height);
                            mParam.setMargins(10, 10, 10, 10);
                            img.setLayoutParams(mParam);
                            netAction.loadImage(getContext(), method.getImg(), img);
                            guanmoStepLayout.addView(img);
                        }
                        if (method.getStep() != null && !method.getStep().isEmpty()) {
                            TextView step = new TextView(getContext());
                            step.setText(method.getStep());
                            guanmoStepLayout.addView(step);
                        }
                    }
                }
                //guanmoStepLayout.refreshDrawableState();
                guanmoStepLayout.invalidate();
            }
        } else {
            netAction.getRandomCook(mobCook);
        }
    }


    private void initView() {
        MyADPagerAdapter mADPagerAdapter = new MyADPagerAdapter();
        mADPager.setAdapter(mADPagerAdapter);
        mADPager.setCurrentItem(Integer.MAX_VALUE / 2);
        mADPager.addOnPageChangeListener(this);
        mADPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.removeMessages(0x1);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(0x1, 3000);
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        handler.sendEmptyMessageDelayed(0x1, 3000);
                        break;
                }
                return false;
            }
        });
        initAD();

    }

    //初始化广告point
    private void initAD() {
        for (int i = 0; i < MyApplication.ADURIS.length; i++) {
            RadioButton radioButton = (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.ad_point, null, false);
            mADRBList.add(radioButton);
            pointGroup.addView(radioButton);
        }
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        netAction.getRandomCook(mobCook);

    }

    //AD ViewPager
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    //AD广告的处理
    @Override
    public void onPageSelected(int position) {
        for (RadioButton rb : mADRBList) {
            rb.setChecked(false);
        }
        mADRBList.get(position % MyApplication.ADURIS.length).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    //网络加载
    @Override
    public void netLoadingComp(int kuid) {
        if (kuid == NetAction.KUID_GUANMO) {
            initGuanmo();
            refreshLayout.setRefreshing(false);
        }
    }


    class MyADPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = new ImageView(getContext());
            iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (mADBitmapList.size() > 0) {
                Bitmap mBitmap = mADBitmapList.get(position % mADBitmapList.size());
                //Bitmap showBitmap = Bitmap.createBitmap(mBitmap,0,0,MyApplication.screenWidth,MyApplication.screenHeight);
                //iv.setImageBitmap(showBitmap);
                //iv.setBackground(getResources().getDrawable(R.drawable.page2,null));
                BitmapDrawable drawable = new BitmapDrawable(getResources(), mBitmap);
                iv.setBackground(drawable);
            } else {
                //iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.page1));
                iv.setBackground(getResources().getDrawable(R.drawable.page3, null));
            }
            container.addView(iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("ok  "+position);
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            // container.removeViewAt(position);
            container.removeView((View) object);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
