package com.hgs.zjcp.utils.database;

/**
 * Created by Administrator on 2016/10/4.
 * sqlite数据库的Schema
 */
public class ZJDBSchema {
    public static final String DBNAME = "chengdi.db";
    public static final class ChengdiTable {
        public static final String TABLE_USER = "user";
        public static final String TABLE_DEMAND = "demand";
        /**
         * 用户信息表
         */
        public static final class User {
            public static final String UUID = "uuid";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String PORTRAITURI = "portraitUri";
            public static final String PHONE = "phone";
            public static final String NAME = "name";
            public static final String SEX = "sex";
            public static final String EDUCATION = "education";
            public static final String LOCATION = "location";
            public static final String HOMETOWN = "hometown";
            public static final String SCORE = "score";
            public static final String SUBSCRIPT = "subScript";
            public static final String AUTHENTICATION = "haveAuthentication";
            public static final String AGE = "age";
            public static final String JOB = "job";
        }

        /**
         * 需求表，包括用户发布的需求以及接单成功后别人的需求
         */
        public static final class Demand {
            public static final String UUID = "uuid";
            public static final String CATEGORY = "category";
            public static final String LIMITTIME = "limitTime";
            public static final String MONEY = "money";
            public static final String CONTENT = "content";
            public static final String REMARK = "remark";
            public static final String DESTINATION = "destination";
            public static final String DESTINATIONREMARK = "destinationRemark";
            public static final String PUBLISHTIME = "publishTime";
            public static final String PHONE = "phone";
            public static final String USERNAME = "username";
            public static final String STATUS = "status";

        }
    }
}
