package com.hgs.zjcp.listener;

/**
 * Created by hgs on 2016/10/28.
 * 策略模式网络加载专用接口，kuid作为某个事件的一个id。
 */
public interface NetLoadingListener {
    void netLoadingComp(int kuid);
}
