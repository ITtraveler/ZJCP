package com.hgs.zjcp.data;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by hgs on 2016/12/20.
 * 聚合数据提供的微信精选
 */

public class JuheWXChoice {
    private int error_code;//返回码
    private String reason;//返回说明
    private Result result;//结果集

    public Result getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public class Result {
        List<Content> list;

        public List<Content> getList() {
            return list;
        }

        public void setList(List<Content> list) {
            this.list = list;
        }
    }

    public class Content {
        private String id;
        private String title;
        private String source;//来源
        private String firstImg;//缩略图
        private String mark;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFirstImg() {
            return firstImg;
        }

        public void setFirstImg(String firstImg) {
            this.firstImg = firstImg;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Content{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", source='" + source + '\'' +
                    ", firstImg='" + firstImg + '\'' +
                    ", mark='" + mark + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
