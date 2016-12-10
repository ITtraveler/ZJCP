package com.hgs.zjcp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgs.zjcp.R;
import com.hgs.zjcp.data.MobCookChoice;
import com.hgs.zjcp.listener.NetLoadingListener;
import com.hgs.zjcp.utils.net.NetAction;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hgs on 2016/10/26.
 */
public class ChoiceFragment extends BaseFragment implements NetLoadingListener {
    private static ChoiceFragment choiceFragment;
    private List<MobCookChoice.Choice> choiceList = new ArrayList<>();
    private NetAction netAction;

    @BindView(R.id.main_bar_title)
    TextView tvTitle;
    @BindView(R.id.choice_recycle)
    RecyclerView cRecycle;
    public static ChoiceFragment newInstance() {
        if (choiceFragment == null) {
            choiceFragment = new ChoiceFragment();
        }
        return choiceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetAction.addNetLoadingListener(NetAction.KUID_CHOICE_LIST, this);
        netAction = NetAction.newAction();
        netAction.getCookChoice("1", choiceList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        tvTitle.setText("指尖精选");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void netLoadingComp(int kuid) {
        if (kuid == NetAction.KUID_CHOICE_LIST) {

        }
    }
}
