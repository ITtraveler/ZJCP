package com.hgs.zjcp.data;

import java.util.Arrays;

/**
 * Created by hgs on 2016/10/30.
 */
public class MobMenuList {
    private String msg;
    private Result result;
    private String retcode;

    public String getMsg() {
        return msg;
    }

    public Result getResult() {
        return result;
    }

    public String getRetcode() {
        return retcode;
    }

    public class Result {
        private String curPage;
        private MobCookDetail[] list;
        private String total;

        public String getCurPage() {
            return curPage;
        }

        public MobCookDetail[] getList() {
            return list;
        }

        public String getTotal() {
            return total;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "curPage='" + curPage + '\'' +
                    ", list=" + Arrays.toString(list) +
                    ", total='" + total + '\'' +
                    '}';
        }
    }
}
