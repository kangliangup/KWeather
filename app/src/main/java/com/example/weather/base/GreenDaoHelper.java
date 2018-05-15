package com.example.weather.base;


import android.database.sqlite.SQLiteDatabase;

import com.example.weather.DaoMaster;
import com.example.weather.DaoSession;


public class GreenDaoHelper {
    private static SQLiteDatabase mDb;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    /**
     * 设置greenDao
     */
    public static void initDatabase() {
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance(), "task_db", null);
        mDb = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();


    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static SQLiteDatabase getDb() {
        return mDb;
    }


}
