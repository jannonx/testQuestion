package com.guyuan.dear.db;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.guyuan.dear.base.app.DearApplication;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 12:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DearDbManager {
    private static DearDbManager instance;
    private DearDb db;

    private DearDbManager(){
        db = Room.databaseBuilder(DearApplication.getInstance(),DearDb.class,"dear_app.db").build();
    }

    public static DearDbManager getInstance() {
        if (instance == null) {
            synchronized (DearDbManager.class) {
                if (instance == null) {
                    instance = new DearDbManager();
                }
            }
        }
        return instance;
    }

    public DearDb getDataBase() {
        return db;
    }
}
