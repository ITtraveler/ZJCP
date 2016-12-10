package com.hgs.zjcp.data;

/**
 * Created by hgs on 2016/10/28.
 */
public class MobMethod {
    private String img;
    private String step;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "MobMethod{" +
                "img='" + img + '\'' +
                ", step='" + step + '\'' +
                '}';
    }
}
