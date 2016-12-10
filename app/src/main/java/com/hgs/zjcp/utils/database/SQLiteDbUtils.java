package com.hgs.zjcp.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/10/4.
 * sql数据库工具
 */
public class SQLiteDbUtils {
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public SQLiteDbUtils(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ZJCPBaseHelper(mContext).getWritableDatabase();
    }
}
