package com.hgs.zjcp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.hgs.zjcp.fragment.ChoiceFragment;
import com.hgs.zjcp.fragment.MainFragment;
import com.hgs.zjcp.fragment.MenuFragment;
import com.hgs.zjcp.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    // RadioButton rbMain,rbMenu,rbChoice,rbMine;
    Fragment[] fragments = {MainFragment.newInstance(), MenuFragment.newInstance(),
            ChoiceFragment.newInstance(), MineFragment.newInstance()};
    private FragmentManager fm;

    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        mPagerAdapter = new MyPagerAdapter(fm);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);//增加缓存pager，以免被销毁
    }

    private void initFragment() {

    }

    @OnClick(R.id.bottom_rb_main)
    public void rbMainClick() {
        mViewPager.setCurrentItem(0);
    }

    @OnClick(R.id.bottom_rb_menu)
    public void rbMenuClick() {
        mViewPager.setCurrentItem(1);
    }

    @OnClick(R.id.bottom_rb_choice)
    public void rbChoiceClick() {
        mViewPager.setCurrentItem(2);
    }

    @OnClick(R.id.bottom_rb_mine)
    public void rbMineClick() {
        mViewPager.setCurrentItem(3);
    }

    /**
     * FragmentPagerAdapter与FragmentStatePagerAdapter不同之处在于前者会remove不需要的fragment，后者只是销毁的view，但对象依然还在
     */
    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

    }
}
