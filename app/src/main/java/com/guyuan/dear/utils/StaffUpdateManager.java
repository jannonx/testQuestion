package com.guyuan.dear.utils;

import com.example.httplibrary.bean.BasePageResultBean;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.db.DearDbManager;
import com.guyuan.dear.db.entities.DeptEntity;
import com.guyuan.dear.db.entities.StaffDeptCrosRef;
import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetStaffBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 15:03
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffUpdateManager {
    private static StaffUpdateManager instance;

    private StaffUpdateManager() {

    }

    public static StaffUpdateManager getInstance() {
        if (instance == null) {
            synchronized (StaffUpdateManager.class) {
                if (instance == null) {
                    instance = new StaffUpdateManager();
                }
            }
        }
        return instance;
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
                        List<NetStaffBean> content = result.getContent();
                        if (content != null) {
                            List<StaffEntity> staffEntities = new ArrayList<>();
                            List<DeptEntity> deptEntities = new ArrayList<>();
                            List<StaffDeptCrosRef> crosRefs = new ArrayList<>();

                            //1,分解，重新包装
                            for (NetStaffBean bean : content) {
                                BeanMapper.staffBeanToEntities(bean, staffEntities, deptEntities, crosRefs);
                            }
                            //2，保存到本地
                            for (StaffEntity entity : staffEntities) {
                                //保存人员
                                DearDbManager.getInstance().getDataBase().getStaffDao().insertStaffs(entity);
                            }
                            for (DeptEntity entity : deptEntities) {
                                //保存部门
                                DearDbManager.getInstance().getDataBase().getDeptDao().insertDept(entity);
                            }
                            for (StaffDeptCrosRef crosRef : crosRefs) {
                                //多对多关联表
                                DearDbManager.getInstance().getDataBase().getStaffDeptCroRefDao().insert(crosRef);

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
}
