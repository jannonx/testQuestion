package com.guyuan.dear.utils;

import android.text.TextUtils;

import com.guyuan.dear.db.entities.DeptEntity;
import com.guyuan.dear.db.entities.StaffAndDepts;
import com.guyuan.dear.db.entities.StaffDeptCrosRef;
import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;
import com.guyuan.dear.net.resultBeans.NetStaffBean;
import com.guyuan.dear.work.contractPause.beans.DeptBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个公共类专门用来处理本地bean，数据库entity以及网络返回的bean的相互转换
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/9 15:12
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BeanMapper {
    /**
     * 把网络返回的staffbean分解成本地数据库的staffEntity和deptEntity
     *
     * @param bean
     * @param staffEntities
     * @param deptEntities
     * @param crosRefs
     */
    public static void staffBeanToEntities(NetStaffBean bean, List<StaffEntity> staffEntities,
                                           List<DeptEntity> deptEntities, List<StaffDeptCrosRef> crosRefs) {
        StaffEntity staff = new StaffEntity();
        staff.userId = bean.getUserId();
        staff.deleteFlag = bean.getDeleteFlag();
        staff.imgUrl = bean.getImgUrl();
        staff.name = bean.getName();
        staff.workId = bean.getWorkId();
        //判断是否列表中包含了此人，如果包含了就跳过
        if (!staffEntities.contains(staff)) {
            staffEntities.add(staff);
        } else {
            return;
        }

        //检查部门名称或部门ID是否对得上，如果后台数据不规范，就不加入部门列表
        String departmentName = bean.getDeptNames();
        String department = bean.getDeptIds();
        if (TextUtils.isEmpty(departmentName) || TextUtils.isEmpty(department)) {
            return;
        }
        String[] nameSpits = departmentName.split(",");
        String[] idSplits = department.split(",");
        //逐个检查返回的部门并加入列表，避免重复
        if (nameSpits.length == idSplits.length) {
            if (nameSpits.length >= 1) {
                DeptEntity lv1Dept = new DeptEntity();
                lv1Dept.deptId = Long.parseLong(idSplits[0]);
                lv1Dept.deptName = nameSpits[0];
                lv1Dept.level = 0;

                if (!deptEntities.contains(lv1Dept)) {
                    deptEntities.add(lv1Dept);
                }
                StaffDeptCrosRef crosRef = new StaffDeptCrosRef();
                crosRef.userId = staff.userId;
                crosRef.deptId = lv1Dept.deptId;
                crosRef.level = lv1Dept.level;
                if (!crosRefs.contains(crosRef)) {
                    crosRefs.add(crosRef);
                }

            }
            if (nameSpits.length >= 2) {
                DeptEntity lv2Dept = new DeptEntity();
                lv2Dept.deptId = Long.parseLong(idSplits[1]);
                lv2Dept.deptName = nameSpits[1];
                lv2Dept.level = 1;
                if (!deptEntities.contains(lv2Dept)) {
                    deptEntities.add(lv2Dept);
                }
                StaffDeptCrosRef crosRef = new StaffDeptCrosRef();
                crosRef.userId = staff.userId;
                crosRef.deptId = lv2Dept.deptId;
                crosRef.level = lv2Dept.level;
                if (!crosRefs.contains(crosRef)) {
                    crosRefs.add(crosRef);
                }
            }
            if (nameSpits.length >= 3) {
                DeptEntity lv3Dept = new DeptEntity();
                lv3Dept.deptId = Long.parseLong(idSplits[2]);
                lv3Dept.deptName = nameSpits[2];
                lv3Dept.level = 2;
                if (!deptEntities.contains(lv3Dept)) {
                    deptEntities.add(lv3Dept);
                }
                StaffDeptCrosRef crosRef = new StaffDeptCrosRef();
                crosRef.userId = staff.userId;
                crosRef.deptId = lv3Dept.deptId;
                crosRef.level = lv3Dept.level;
                if (!crosRefs.contains(crosRef)) {
                    crosRefs.add(crosRef);
                }
            }
        }
    }


    /**
     * 把本地数据库的用户信息转成本地bean
     * @param entity
     * @return
     */
    public static StaffBean entityToStaffBean(StaffAndDepts entity) {
        StaffBean bean = new StaffBean();
        bean.setImgUrl(entity.staffEntity.imgUrl);
        bean.setId(entity.staffEntity.userId);
        bean.setName(entity.staffEntity.name);
        bean.setWorkId(entity.staffEntity.workId);
        List<DeptBean> deptBeans = new ArrayList<>();
        for (DeptEntity dept : entity.deptEntities) {
            DeptBean deptBean = new DeptBean();
            deptBean.setDeptName(dept.deptName);
            deptBean.setId(dept.deptId);
            deptBean.setLevel((short) dept.level);
            deptBeans.add(deptBean);
        }
        bean.setDepts(deptBeans);
        return bean;
    }

    /**
     * 把基础用户bean转成选人界面的用户bean
     * @param bean
     * @return
     */
    public static PickStaffBean StaffBeanToPickStaffBean(StaffBean bean){
        PickStaffBean pickStaffBean = new PickStaffBean();
        pickStaffBean.setWorkId(bean.getWorkId());
        pickStaffBean.setImgUrl(bean.getImgUrl());
        pickStaffBean.setId(bean.getId());
        pickStaffBean.setName(bean.getName());
        pickStaffBean.setDepts(bean.getDepts());
        return pickStaffBean;
    }
}
