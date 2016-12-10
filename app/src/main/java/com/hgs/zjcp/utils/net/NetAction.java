package com.hgs.zjcp.utils.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hgs.zjcp.R;
import com.hgs.zjcp.data.MobCookDetail;
import com.hgs.zjcp.data.MobCookChoice;
import com.hgs.zjcp.data.MobMenuList;
import com.hgs.zjcp.listener.NetLoadingListener;
import com.hgs.zjcp.MyApplication;
import com.hgs.zjcp.data.MobCook;
import com.hgs.zjcp.data.MobMenuCategory;
import com.hgs.zjcp.utils.ActivityUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;

/**
 * Created by hgs on 2016/10/26.
 */
public class NetAction {
    private static NetAction netAction;

    public static NetAction newAction() {
        if (netAction == null) {
            netAction = new NetAction();
        }
        return netAction;
    }

    public static final int KUID_GUANMO = 100;//观摩卡片的netListener 的kuid
    public static final int KUID_MENU_LIST = 101;//菜单列表
    public static final int KUID_CHOICE_LIST = 102;//精选列表的kuid
    private static Map<Integer, NetLoadingListener> netLoadings = new HashMap<>();

    //添加网络加载事件
    public static void addNetLoadingListener(int kuid, NetLoadingListener listener) {
        netLoadings.put(kuid, listener);
        System.out.println("add ok");
    }

    /**
     * 广告加载，从网络中获取图片
     * 返回bitmap的list
     *
     * @return
     */
    public List<Bitmap> loadingAD(final Context context) {
        final List<Bitmap> adBitmapList = new ArrayList<>();
        for (String uri : MyApplication.ADURIS)
            NetUtils.getBitmap(uri, new BitmapCallback() {
                @Override
                public void onError(Call call, Exception e, int i) {
                    ActivityUtils.showToast(context, "网络异常！\n" + e);
                }

                @Override
                public void onResponse(Bitmap bitmap, int i) {
                    adBitmapList.add(bitmap);
                }
            });
        return adBitmapList;
    }

    /**
     * 加载图片，并设置ImageView的背景
     *
     * @param context
     * @param uri
     * @param imageView
     */
    public void loadImage(final Context context, String uri, final ImageView imageView) {
        imageView.setBackground(context.getResources().getDrawable(R.mipmap.img_cook));
        if (uri != null && !uri.isEmpty()) {
            NetUtils.getBitmap(uri, new BitmapCallback() {
                @Override
                public void onError(Call call, Exception e, int i) {

                }

                @Override
                public void onResponse(Bitmap bitmap, int i) {
                    //context
                    BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
                    imageView.setBackground(drawable);//设置为背景颜色，这样可以布满怎个控件
                }
            });
        }
    }

    /**
     * 随机产生一个MobCook
     *
     * @param mobCook
     */
    public void getRandomCook(final MobCook mobCook) {
        Map<String, String> param = new HashMap<>();
        param.put("key", MyApplication.MOBKEY);
        param.put("id", randomMenuId());//随机产生一个菜单MOB的ID
        NetUtils.get(NetUri.MOBCook.COOK_ID_QUERY, param, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                System.out.println(e);
            }

            @Override
            public void onResponse(String s, int i) {
                Gson gson = new Gson();
                MobCook cook = gson.fromJson(s, MobCook.class);
                mobCook.setMsg(cook.getMsg());
                mobCook.setResult(cook.getResult());
                mobCook.setRetCode(cook.getRetCode());
                netLoadings.get(KUID_GUANMO).netLoadingComp(NetAction.KUID_GUANMO);
            }
        });

    }

    /**
     * 随机创作一个MenuId，即可用Mob提供的API进行查找
     *
     * @return
     */
    private String randomMenuId() {
        String category = randomCategory();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(category);
        switch (category) {
            case "0010001007":
                int r0 = random.nextInt(17730) + 1;
                createMenuID(sb, r0);
                break;
            case "0010001008":
                int r1 = random.nextInt(7000) + 17900;
                createMenuID(sb, r1);
                break;
            case "0010001009":
                int r2 = random.nextInt(3900) + 25100;
                createMenuID(sb, r2);
                break;
            case "0010001010":

                int r3 = random.nextInt(2500) + 29100;
                createMenuID(sb, r3);
                break;
            case "0010001011":
                int r4 = random.nextInt(2000) + 32000;
                createMenuID(sb, r4);
                break;
        }
        return sb.toString();
    }

    private void createMenuID(StringBuilder sb, int r0) {
        int length = String.valueOf(r0).length();
        for (int i = 0; i < (10 - length); i++)
            sb.append("0");
        sb.append(r0);
    }

    //0010001007~0010001011 分别是荤菜、素菜、汤类、西点、主食
    private String randomCategory() {
        Random random = new Random();
        String r = String.valueOf(random.nextInt(5) + 7);
        return r.length() == 2 ? "00100010" + r : "001000100" + r;
    }

    /**
     * 加载菜谱类别类表
     * 由于这些数据事先从网络获取，因此不需要网络了。
     */
    public List<MobMenuCategory> getMenuCategoryList(Context context) {
        //读入cookCategory.txt中的内容
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = context.getResources().getAssets().open("cookCategory.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<List<MobMenuCategory>>() {
        }.getType();
        //解析内容
        Gson gson = new Gson();
        //System.out.println("  sb: " + sb.toString());
        List<MobMenuCategory> mmc = gson.fromJson(sb.toString(), type);
        return mmc;
    }

    public void getMenuList(String cid, int page, final List<MobCookDetail> mobCookDetails) {
        Map<String, String> param = new HashMap<>();
        param.put("key", MyApplication.MOBKEY);
        param.put("cid", cid);
        param.put("page", "" + page);//加载第几页
        param.put("size", "" + 20);//设置每次只加载20条

        NetUtils.get(NetUri.MOBCook.COOK_CID_SEARCH, param, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                System.out.println(s);
                Gson gson = new Gson();
                MobMenuList menuList = gson.fromJson(s, MobMenuList.class);
                List<MobCookDetail> mobCooks = Arrays.asList(menuList.getResult().getList());
                mobCookDetails.addAll(mobCooks);
                netLoadings.get(KUID_MENU_LIST).netLoadingComp(NetAction.KUID_MENU_LIST);
            }
        });
    }

    /**
     * page 从1开始
     *
     * @param page
     * @param choice
     */
    public void getCookChoice(String page, final List<MobCookChoice.Choice> choice) {
        Map<String, String> param = new HashMap<>();
        param.put("key", MyApplication.MOBKEY);
        param.put("cid", "27");
        param.put("page", page);
        param.put("size", "20");
        NetUtils.get(NetUri.WEIXIN_CHOICE, param, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                Gson gson = new Gson();
                MobCookChoice cookChoice = gson.fromJson(s, MobCookChoice.class);
                choice.addAll(Arrays.asList(cookChoice.getResult().getChoiceList()));
                //       EventBus.getDefault().post(200);
                netLoadings.get(KUID_CHOICE_LIST).netLoadingComp(KUID_CHOICE_LIST);
            }
        });
    }

}
