package com.hgs.zjcp.data;

import java.util.Arrays;

/**
 * Created by hgs on 2016/11/16.
 * cook新闻封装类
 */
public class MobCookChoice {
    private String msg;
    private String retCode;
    private Result result;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public Result getResult() {
        return result;
    }

    public class Result {
        String curPage;
        Choice choiceList[];

        public String getCurPage() {
            return curPage;
        }

        public Choice[] getChoiceList() {
            return choiceList;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "curPage='" + curPage + '\'' +
                    ", choiceList=" + Arrays.toString(choiceList) +
                    '}';
        }
    }

    public class Choice {
        String cid;
        String id;
        String pubTime;
        String sourceUrl;
        String subTitle;
        String thumbnails;
        String title;

        public String getCid() {
            return cid;
        }

        public String getId() {
            return id;
        }

        public String getPubTime() {
            return pubTime;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getThumbnails() {
            return thumbnails;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Choice{" +
                    "cid='" + cid + '\'' +
                    ", id='" + id + '\'' +
                    ", pubTime='" + pubTime + '\'' +
                    ", sourceUrl='" + sourceUrl + '\'' +
                    ", subTitle='" + subTitle + '\'' +
                    ", thumbnails='" + thumbnails + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
