package com.hgs.zjcp.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Administrator on 2016/10/4.
 * sqlite 数据库助手
 */
public class ZJCPBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;//版本号

    public ZJCPBaseHelper(Context context) {
        super(context, ZJDBSchema.DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表
        db.execSQL("create table " + ZJDBSchema.ChengdiTable.TABLE_USER
                + "(_id integer primary key autoincrement,"
                + ZJDBSchema.ChengdiTable.User.UUID + ","
                + ZJDBSchema.ChengdiTable.User.USERNAME + ","
                + ZJDBSchema.ChengdiTable.User.PASSWORD + ","
                + ZJDBSchema.ChengdiTable.User.PORTRAITURI + ","
                + ZJDBSchema.ChengdiTable.User.PHONE + ","
                + ZJDBSchema.ChengdiTable.User.NAME + ","
                + ZJDBSchema.ChengdiTable.User.SEX + ","
                + ZJDBSchema.ChengdiTable.User.AGE + ","
                + ZJDBSchema.ChengdiTable.User.JOB + ","
                + ZJDBSchema.ChengdiTable.User.EDUCATION + ","
                + ZJDBSchema.ChengdiTable.User.LOCATION + ","
                + ZJDBSchema.ChengdiTable.User.HOMETOWN + ","
                + ZJDBSchema.ChengdiTable.User.SUBSCRIPT + ","
                + ZJDBSchema.ChengdiTable.User.SCORE + ","
                + ZJDBSchema.ChengdiTable.User.AUTHENTICATION +
                ")");
        //创建需求表
        db.execSQL("create table " + ZJDBSchema.ChengdiTable.TABLE_DEMAND
                + "(_id integer primary key autoincrement,"
                + ZJDBSchema.ChengdiTable.Demand.UUID + ","
                + ZJDBSchema.ChengdiTable.Demand.CATEGORY + ","
                + ZJDBSchema.ChengdiTable.Demand.LIMITTIME + ","
                + ZJDBSchema.ChengdiTable.Demand.MONEY + ","
                + ZJDBSchema.ChengdiTable.Demand.CONTENT + ","
                + ZJDBSchema.ChengdiTable.Demand.REMARK + ","
                + ZJDBSchema.ChengdiTable.Demand.DESTINATION + ","
                + ZJDBSchema.ChengdiTable.Demand.DESTINATIONREMARK + ","
                + ZJDBSchema.ChengdiTable.Demand.PUBLISHTIME + ","
                + ZJDBSchema.ChengdiTable.Demand.PHONE + ","
                + ZJDBSchema.ChengdiTable.Demand.USERNAME + ","
                + ZJDBSchema.ChengdiTable.Demand.STATUS +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
