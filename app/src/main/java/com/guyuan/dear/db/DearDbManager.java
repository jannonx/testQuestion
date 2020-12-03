package com.guyuan.dear.db;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.httplibrary.bean.BasePageResultBean;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.db.dao.StaffSelectHistoryDao;
import com.guyuan.dear.db.entities.DeptEntity;
import com.guyuan.dear.db.entities.StaffDeptCrosRef;
import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.db.entities.StaffSelectHistoryEntity;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetStaffBean;
import com.guyuan.dear.utils.BeanMapper;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 12:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DearDbManager {
    private static DearDbManager instance;
    private DearDb db;

    private DearDbManager() {
        db = Room.databaseBuilder(DearApplication.getInstance(), DearDb.class, "dear_app.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addMigrations(
                        new Migration(1, 2) {
                            @Override
                            public void migrate(@NonNull SupportSQLiteDatabase database) {
                                LogUtils.showLog("升级数据库1->2");
                                DearApplication.getInstance().saveCacheData(ConstantValue.KEY_STAFF_TABLE_LAST_UPDATE_TIME, 0L);
                            }
                        },
                        new Migration(2, 3) {
                            @Override
                            public void migrate(@NonNull SupportSQLiteDatabase database) {
                                LogUtils.showLog("升级数据库2->3");

                                //SQLite supports a limited subset of ALTER TABLE. The ALTER TABLE command in SQLite allows the user to
                                // rename a table or to add a new column to an existing table. It is not possible to rename a column,
                                // remove a column, or add or remove constraints from a table.
                                database.execSQL("AlTER TABLE 'StaffEntity' RENAME TO 'StaffEntityOld';");
                                database.execSQL("CREATE TABLE IF NOT EXISTS 'StaffEntity' (" +
                                        "'name' TEXT, " +
                                        "'_id' INTEGER NOT NULL, " +
                                        "'imgUrl' TEXT, " +
                                        "'deleteFlag' INTEGER NOT NULL, " +
                                        "'workId' TEXT, " +
                                        "PRIMARY KEY('_id'));"
                                );
                                database.execSQL("INSERT INTO 'StaffEntity' SELECT name,userId,imgUrl,deleteFlag,workId FROM 'StaffEntityOld';");
                                database.execSQL("DROP TABLE StaffEntityOld;");

                                database.execSQL("ALTER TABLE StaffDeptCrosRef RENAME TO StaffDeptCrosRefOld;");
                                database.execSQL("CREATE TABLE IF NOT EXISTS StaffDeptCrosRef (_id INTEGER NOT NULL, deptId INTEGER NOT NULL, PRIMARY KEY(_id, deptId));");
                                database.execSQL("INSERT INTO StaffDeptCrosRef SELECT userId,deptId FROM StaffDeptCrosRefOld;");
                                database.execSQL("DROP TABLE StaffDeptCrosRefOld;");

                            }
                        })
                .build();
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

    /**
     * 向服务器请求所有更新人员的列表，重新保存到本地。
     */
    public void initiateStaffUpdate() {
        Long lastUpdateTime = (Long) DearApplication.getInstance()
                .getCacheData(ConstantValue.KEY_STAFF_TABLE_LAST_UPDATE_TIME, 0L);
        updateLocalStaffInfo(1, 50, lastUpdateTime);
    }

    /**
     * @param pageIndex      当前页码，从1开始
     * @param pageSize       每页容量
     * @param lastUpdateTime 上次更新时间，过滤掉更新之前的数据，因为已经保存在本地了。
     */
    private void updateLocalStaffInfo(int pageIndex, int pageSize, long lastUpdateTime) {
        DearNetHelper.getInstance().getAllStaffs(pageIndex, pageSize, lastUpdateTime,
                new DearNetHelper.NetCallback<BasePageResultBean<NetStaffBean>>() {
                    @Override
                    public void onStart(Disposable disposable) {
                        LogUtils.showLog("begin to update local staffs, last update time=" + lastUpdateTime);
                    }

                    @Override
                    public void onGetResult(BasePageResultBean<NetStaffBean> result) {
                        int totalPages = result.getTotalPages();
                        int pageIndex = result.getPageIndex();
                        List<NetStaffBean> netStaffBeans = result.getContent();
                        if (netStaffBeans != null) {
                            List<StaffEntity> staffEntities = new ArrayList<>();
                            List<DeptEntity> deptEntities = new ArrayList<>();
                            List<StaffDeptCrosRef> crosRefs = new ArrayList<>();

                            //1,分解，重新包装
                            for (NetStaffBean bean : netStaffBeans) {
                                BeanMapper.netStaffBeanToEntities(bean, staffEntities, deptEntities, crosRefs);
                            }
                            //2，保存到本地
                            for (StaffEntity entity : staffEntities) {
                                //保存人员
                                db.getStaffDao().insertStaffs(entity);
                            }
                            for (DeptEntity entity : deptEntities) {
                                //保存部门
                                db.getDeptDao().insertDept(entity);
                            }
                            for (StaffDeptCrosRef crosRef : crosRefs) {
                                //多对多关联表
                                db.getStaffDeptCroRefDao().insert(crosRef);

                            }
                        }

                        //3,继续请求下一页数据，直到最后一页。
                        if (totalPages > 0 && totalPages != pageIndex) {
                            updateLocalStaffInfo(pageIndex + 1, pageSize, lastUpdateTime);
                        } else {
                            //4，到了最后一页，记录本次更新时间
                            String updateTime = result.getUpdateTime();
                            Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(updateTime);
                            DearApplication.getInstance().saveCacheData(ConstantValue.KEY_STAFF_TABLE_LAST_UPDATE_TIME, date.getTime());
                        }

                    }

                    @Override
                    public void onError(Throwable error) {
                        LogUtils.showLog("update staff fail:" + error.getMessage());
                    }
                });
    }

    /**
     * 最大历史选人记录数，超出部分忽略掉
     */
    private static final int MAX_HISTORY_RECORD_COUNT = 10;

    /**
     * 更新选人历史
     *
     * @param newSelect
     */
    public void updateStaffSelectHistory(List<StaffBean> newSelect) {
        int size = newSelect.size();
        if (size > MAX_HISTORY_RECORD_COUNT) {
            size = MAX_HISTORY_RECORD_COUNT;
        }
        StaffSelectHistoryEntity[] newHistory = new StaffSelectHistoryEntity[size];
        long selectDate = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            StaffSelectHistoryEntity entity = new StaffSelectHistoryEntity();
            entity.selectDate = selectDate;
            entity.staffId = newSelect.get(newSelect.size() - 1 - i).getId();//从列表后面读起
            newHistory[i] = entity;
        }
        //找出数据库中最早的选人历史数据，用新的历史代替，保证不超过10条记录
        StaffSelectHistoryDao dao = db.getStaffSelectHistoryDao();
        dao.insert(newHistory);
        List<StaffSelectHistoryEntity> historyEntities = dao.loadAllAscByDate();
        if (historyEntities.size() > MAX_HISTORY_RECORD_COUNT) {
            for (int i = 0; i < historyEntities.size() - MAX_HISTORY_RECORD_COUNT; i++) {
                StaffSelectHistoryEntity entity = historyEntities.get(i);
                dao.delete(entity);
            }
        }
    }


}
