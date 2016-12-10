package com.hgs.zjcp.utils.net;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;


/**
 * Created by Administrator on 2016/10/2.
 * 用于网络，对OKHttp框架的封装
 */
public class NetUtils {
    private static String TAG = "OKHTTP_NETUTILS";

    /**
     * @param url
     * @param params
     * @param stringCallback
     * @return
     */
    public static void get(String url, Map<String, String> params, StringCallback stringCallback) {
        GetBuilder okHttpUtils = OkHttpUtils.get().url(url);
        if (params != null) {
            params.keySet();
            //添加参数
            for (String k : params.keySet()) {
                okHttpUtils.addParams(k, params.get(k));
            }
        }
        okHttpUtils
                .build()
                .execute(stringCallback);
    }

    /**
     * @param url
     * @param params   post的参数
     * @param callBack
     * @return
     */
    public static void post(String url, Map<String, String> params, Callback callBack) {
        PostFormBuilder okHttpUtils = OkHttpUtils.post().url(url);
        if (params != null) {
            params.keySet();
            //添加参数
            for (String k : params.keySet()) {
                okHttpUtils.addParams(k, params.get(k));
            }
        }
        okHttpUtils
                .build()
                .execute(callBack);

    }

    /**
     * 提交json内容
     *
     * @param url
     * @param JSONContent    要提交的JSON内容
     * @param stringCallback
     */
    public static void postJson(String url, String JSONContent, StringCallback stringCallback) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(JSONContent)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(stringCallback);
    }

    /**
     * post提交文件
     *
     * @param url
     * @param file
     * @param stringCallback
     */
    public static void postFile(String url, File file, StringCallback stringCallback) {
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(stringCallback);
    }

    /**
     * 形如表单<input type="file" name="mFile"/>
     * 表单的形式提交文件，可多个文件一起提交
     *
     * @param url
     * @param formFiles      提交的文件
     * @param params
     * @param headers
     * @param stringCallback
     */
    public static void postFile(String url, List<PostFormFile> formFiles, Map<String, String> params, Map<String, String> headers, StringCallback stringCallback) {

        PostFormBuilder okHttpUtils = OkHttpUtils.post();
        for (PostFormFile formFile : formFiles) {
            okHttpUtils.addFile(formFile.getName(), formFile.getFileName(), formFile.getFile());
        }
        okHttpUtils
                .url(url)
                .params(params)//
                .headers(headers)//
                .build()//
                .execute(stringCallback);
    }

    /**
     * Bitmap的下载获取
     *
     * @param url
     * @param bitmapCallback
     */
    public static void getBitmap(String url, BitmapCallback bitmapCallback) {
        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(bitmapCallback);
    }

    /**
     * 文件
     *
     * @param url
     * @param fileCallBack 文件的回调
     */
    public static void downLoadFile(String url, FileCallBack fileCallBack) {
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(fileCallBack);
    }
}
