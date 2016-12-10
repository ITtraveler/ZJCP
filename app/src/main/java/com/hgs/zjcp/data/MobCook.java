package com.hgs.zjcp.data;

/**
 * Created by hgs on 2016/10/27.
 */
public class MobCook {
    private String retCode;    // 	返回码
    private String msg;// 	返回说明
    private MobCookDetail result;// 	返回结果集

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MobCookDetail getResult() {
        return result;
    }

    public void setResult(MobCookDetail result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MobCook{" +
                "retCode='" + retCode + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
