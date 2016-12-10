package com.hgs.zjcp;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/10/26.
 */
public class MyApplication extends Application {
    public static final String MOBKEY = "17a5cdcafeca8";
    public static String[] ADURIS = {"http://file.weixinkd.com/article_201610_20_20w_58082edd0bdea.jpg",
            "http://site.meishij.net/p2/20161020/20161020140319_605.png",
            "http://site.meishij.net/p2/20161020/20161020140320_417.png",
            "http://site.meishij.net/r/115/13/2253365/a2253365_59964.jpg"
    };
    public static int screenWidth, screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        getScreen();

    }

    //获得屏幕的宽和高
    private void getScreen() {

        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }



}
