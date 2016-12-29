package com.hgs.zjcp.utils.net;

/**
 * Created by Administrator on 2016/10/26.
 * 存放Uri地址
 */
public class NetUri {
    public static final String MOB_COOK_URL = "http://apicloud.mob.com/v1/cook";

    class MOBCook {
        //get  json key=appkey  分类接口
        public static final String COOK_CATEGORY = MOB_COOK_URL + "/category/query";
        //get  json   key=appkey&cid=0010001007&page=1&size=20   按分类标签菜谱查询接口
        public static final String COOK_CID_SEARCH = MOB_COOK_URL + "/menu/search";
        //get  json   key=appkey&id=00100010070000000001    根据菜谱的id查看详细内容接口
        public static final String COOK_ID_QUERY = MOB_COOK_URL + "/menu/query";
    }

    /**
     * 由于内容不够丰富决定废弃，改由聚合数据提供的微信精选
     */
    //get  json  key=123456&cid=1 &&page=1&&size=100  page从1开始；cid类别id，这里固定为{"cid":"27","name":"美食"}   微信精选接口
    public static final String WEIXIN_CHOICE = "http://apicloud.mob.com/wx/article/search";

    class JUHE{
        //http://v.juhe.cn/weixin/query?key=您申请的KEY&
        public static final String WEIXIN_CHOICE = "http://v.juhe.cn/weixin/query";
    }
}
