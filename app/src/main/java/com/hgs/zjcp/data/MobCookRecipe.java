package com.hgs.zjcp.data;

import java.io.Serializable;

/**
 * Created by hgs on 2016/10/27.
 */
public class MobCookRecipe implements Serializable{
    private String img;
    private String ingredients = "";//材料
    private String method;//制作的具体步骤
    private String sumary;//最终评价
    private String title;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    @Override
    public String toString() {
        return "MobCookRecipe{" +
                "img='" + img + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", method=" + method +
                ", sumary='" + sumary + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
