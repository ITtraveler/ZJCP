package com.hgs.zjcp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.hgs.zjcp.R;
import com.hgs.zjcp.fragment.ChoiceFragment;
import com.hgs.zjcp.fragment.MainFragment;
import com.hgs.zjcp.fragment.MenuFragment;
import com.hgs.zjcp.fragment.MineFragment;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    // RadioButton rbMain,rbMenu,rbChoice,rbMine;
    Fragment[] fragments = {MainFragment.newInstance(), MenuFragment.newInstance(),
            ChoiceFragment.newInstance(), MineFragment.newInstance()};
    private FragmentManager fm;

    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    @BindView(R.id.bottom_rb_main)
    RadioButton radioButton;
    @BindViews({R.id.bottom_rb_main, R.id.bottom_rb_menu, R.id.bottom_rb_choice, R.id.bottom_rb_mine})
    List<RadioButton> bottomRBList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        mPagerAdapter = new MyPagerAdapter(fm);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);//增加缓存pager，以免被销毁
        mViewPager.addOnPageChangeListener(this);
    }

    @OnClick(R.id.bottom_rb_main)
    public void rbMainClick() {
        mViewPager.setCurrentItem(0);
        changeRadioButton(0);
    }

    @OnClick(R.id.bottom_rb_menu)
    public void rbMenuClick() {
        mViewPager.setCurrentItem(1);
        changeRadioButton(1);
    }

    @OnClick(R.id.bottom_rb_choice)
    public void rbChoiceClick() {
        mViewPager.setCurrentItem(2);
        changeRadioButton(2);
    }

    @OnClick(R.id.bottom_rb_mine)
    public void rbMineClick() {
        mViewPager.setCurrentItem(3);
        changeRadioButton(3);
    }

    /**
     * 改变底部选择栏的check。
     *
     * @param i
     */
    private void changeRadioButton(int i) {
        bottomRBList.get(i).setChecked(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeRadioButton(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
