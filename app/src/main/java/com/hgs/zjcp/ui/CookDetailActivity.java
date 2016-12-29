package com.hgs.zjcp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hgs.zjcp.R;
import com.hgs.zjcp.data.MobCookDetail;
import com.hgs.zjcp.data.MobCookRecipe;
import com.hgs.zjcp.data.MobMethod;
import com.hgs.zjcp.utils.net.NetAction;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hgs on 2016/10/31.
 *
 */
public class CookDetailActivity extends BaseActivity {
    @BindView(R.id.cook_title)
    TextView tvCookTitle;
    @BindView(R.id.cook_showImg)
    ImageView ivCookShow;
    @BindView(R.id.cook_name)
    TextView tvCookName;
    @BindView(R.id.cook_tag)
    LinearLayout cookTagLayout;
    @BindView(R.id.cook_ingredients)
    TextView tvCookIngredients;
    @BindView(R.id.cook_step)
    LinearLayout cookStepLayout;
    private MobCookDetail cookDetail;
    private NetAction netAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cookDetail = (MobCookDetail) getIntent().getSerializableExtra("MobCookDetail");
        System.out.println(cookDetail);
        netAction = NetAction.newAction();
        setContentView(R.layout.activity_cook_detail);
        ButterKnife.bind(this);
        initCook();
    }

    private void initCook() {
        MobCookRecipe cookRecipe = cookDetail.getRecipe();
        tvCookTitle.setText("" + cookRecipe.getTitle());
        tvCookName.setText("" + cookDetail.getName());

        netAction.loadImage(this, cookRecipe.getImg(), ivCookShow);
        String[] tags = cookDetail.getCtgTitles().split(",");
        cookTagLayout.removeAllViews();
        for (String tag : tags) {
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mParam.setMargins(0, 10, 10, 5);
            textView.setLayoutParams(mParam);
            textView.setText(tag);
            textView.setBackground(getResources().getDrawable(R.drawable.tag_bg));
            cookTagLayout.addView(textView);
        }
//            if (!cookRecipe.getSumary().isEmpty())
//                tvcookRecommend.setText("推荐理由:" + cookRecipe.getSumary());
        if (!cookRecipe.getIngredients().isEmpty()) {
            String ingredient = cookRecipe.getIngredients().replace("[\"", "");
            tvCookIngredients.setText("制作准备\n" + ingredient.replace("\"]", ""));
        }
        if (cookRecipe.getMethod() != null && !cookRecipe.getMethod().isEmpty()) {//制作具体流程
            cookStepLayout.removeAllViews();
            Type type = new TypeToken<List<MobMethod>>() {
            }.getType();
            Gson gson = new Gson();
            List<MobMethod> methods = gson.fromJson(cookRecipe.getMethod(), type);
            for (MobMethod method : methods) {
                System.out.println(method.toString());
                if (method != null) {
                    if (method.getImg() != null && !method.getImg().isEmpty()) {
                        ImageView img = new ImageView(this);
                        float height = getResources().getDimension(R.dimen.step_image_height);
                        LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) height);
                        mParam.setMargins(10, 20, 10, 20);
                        img.setLayoutParams(mParam);
                        netAction.loadImage(this, method.getImg(), img);
                        cookStepLayout.addView(img);
                    }
                    if (method.getStep() != null && !method.getStep().isEmpty()) {
                        TextView step = new TextView(this);
                        step.setText(method.getStep());
                        cookStepLayout.addView(step);
                    }
                }
            }
            //cookStepLayout.refreshDrawableState();
            cookStepLayout.invalidate();
        }
    }
}
