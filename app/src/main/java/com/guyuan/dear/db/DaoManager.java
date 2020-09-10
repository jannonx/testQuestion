package com.guyuan.dear.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Author: 廖华凯
 * Date: 2020/2/13
 * Project: Smart Management
 * Description:
 */
public class DaoManager {
    private DaoMaster mDaoMaster;
    private static DaoManager INSTANCE;
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private SQLiteDatabase mDatabase;

    private DaoManager() {
    }

    public static DaoManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DaoManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DaoManager();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, "smart_factory.db");
        mDatabase = mDevOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDatabase);
    }

    public void close(){
        if(mDevOpenHelper!=null){
            mDevOpenHelper.close();
            mDevOpenHelper=null;
        }
    }

    public DaoSession getSession() {
        return mDaoMaster.newSession();
    }

    public SQLiteDatabase getDatabase(){
        return mDatabase;
    }


}
