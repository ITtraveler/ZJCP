package com.hgs.zjcp.data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by hgs on 2016/10/27.
 * 一条完整详细的Cook数据
 */
public class MobCookDetail implements Serializable{
    public static final long SUID = 20162319;
    private String[] ctgIds;//array 	分类ID
    private String ctgTitles;// 	分类标签
    private String menuId;// 	菜谱id
    private MobCookRecipe recipe;// 	制作步骤
    private String thumbnail;// 	预览图地址
    private String name;// 	菜谱名称

    public String[] getCtgIds() {
        return ctgIds;
    }

    public void setCtgIds(String[] ctgIds) {
        this.ctgIds = ctgIds;
    }

    public String getCtgTitles() {
        return ctgTitles;
    }

    public void setCtgTitles(String ctgTitles) {
        this.ctgTitles = ctgTitles;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public MobCookRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(MobCookRecipe recipe) {
        this.recipe = recipe;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MobCookDetail{" +
                "ctgIds=" + Arrays.toString(ctgIds) +
                ", ctgTitles='" + ctgTitles + '\'' +
                ", menuId='" + menuId + '\'' +
                ", recipe=" + recipe +
                ", thumbnail='" + thumbnail + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
