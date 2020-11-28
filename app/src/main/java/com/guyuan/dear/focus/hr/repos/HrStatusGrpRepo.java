package com.guyuan.dear.focus.hr.repos;

import androidx.lifecycle.LiveData;

import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.work.contractPause.beans.DeptBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 15:03
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStatusGrpRepo {
    public Disposable getStaffListByAttendType(int type, DearNetHelper.NetCallback<List<StaffBean>> callback) {
        return DearNetHelper.getInstance().getStaffByAttendanceType(type, callback);
    }

    public Disposable getStaffListByDateAndAttendType(long date,int type, DearNetHelper.NetCallback<List<StaffBean>> callback){
        return DearNetHelper.getInstance().getStaffsByDateAndAttendanceType(date, type, callback);
    }

    /**
     * 把人员按部门分类
     * @param staffBeans
     * @return
     */
    public List<HrStaffsByDept> groupStaffsByDept(List<StaffBean> staffBeans) {
        List<HrStaffsByDept> depts = new ArrayList<>();
        for (StaffBean staffBean : staffBeans) {
            insertStaffIntoDept(staffBean, depts);
        }
        return depts;
    }

    /**
     * 把人员放到对应的部门，如果不存在对应的部门，新建该部门。
     * @param staffBean
     * @param depts
     */
    private void insertStaffIntoDept(StaffBean staffBean, List<HrStaffsByDept> depts) {
        //先找出人员的部门id
        long deptId = getStaffDeptId(staffBean);
        //遍历列表中是否存在该部门，如果存在，直接放进该部门，如果不存在，新建部门，并把人员放进去。
        for (HrStaffsByDept dept : depts) {
            if (dept.getDeptId() == deptId) {
                dept.getStaffs().add(staffBean);
                return;
            }
        }
        HrStaffsByDept dept = createDeptByStaff(staffBean,deptId);
        dept.getStaffs().add(staffBean);
        depts.add(dept);
    }

    /**
     * 根据人员信息，生成一个新部门
     * @param staffBean
     * @param deptId
     * @return
     */
    private HrStaffsByDept createDeptByStaff(StaffBean staffBean, long deptId) {
        HrStaffsByDept dept = new HrStaffsByDept();
        dept.setDeptId(deptId);
        dept.setDept(getStaffDeptName(staffBean,deptId));
        dept.setStaffs(new ArrayList<>());
        return dept;
    }

    /**
     * 获取人员部门的名字
     * @param staffBean
     * @param deptId
     * @return
     */
    private String getStaffDeptName(StaffBean staffBean,long deptId) {
        List<DeptBean> depts = staffBean.getDepts();
        if(depts!=null){
            for (DeptBean dept : depts) {
                if(dept.getId()==deptId){
                    return dept.getDeptName();
                }
            }
        }
        return "未知部门";
    }

    /**
     * 查找用户的部门ID，一个用户可能有几级部门，这里取最顶层的。
     * @param staffBean
     * @return
     */
    private long getStaffDeptId(StaffBean staffBean) {
        List<DeptBean> depts = staffBean.getDepts();
        if (depts != null) {
            long id = 0;
            short lowestLevel = Short.MAX_VALUE;
            for (DeptBean dept : depts) {
                short level = dept.getLevel();
                if (lowestLevel >= level) {
                    lowestLevel = level;
                    id = dept.getId();
                }

            }
            return id;
        }
        return 0;
    }
}
