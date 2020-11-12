package com.guyuan.dear.focus.hr.view.hrStatusGrp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.hr.adapter.StaffsDeptGrpExpListAdapter;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.focus.hr.bean.StaffBasicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 这个视图模型按照当前出勤状况给所有人员分组
 * @since: 2020/9/21 17:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStatusGrpViewModel extends BaseViewModel {


    public MutableLiveData<List<HrStaffsByDept>> staffs = new MutableLiveData<>();
    public int grpType;
    public StaffsDeptGrpExpListAdapter.DeptGrpExpAdapterCallback callback;

    public void setGrpType(int grpType) {
        this.grpType = grpType;
    }

    /**
     * 生成模拟数据，显示UI
     */
    public void loadDataFromNet() {
        int count=10;
        List<HrStaffsByDept> list = new ArrayList<>();
        for(int i=0;i<count;i++){
            HrStaffsByDept dept = new HrStaffsByDept();
            dept.setGrpType(grpType);
            dept.setGrpLabel("部门"+i);
            dept.setDeptId(i);
            dept.setStaffs(new ArrayList<StaffBasicInfo>(){
                {
                    StaffBasicInfo staff = new StaffBasicInfo();
                    staff.setDept(dept.getGrpLabel());
                    staff.setName("Leo");
                    staff.setId(1);
                    staff.setDeptId(dept.getDeptId());
                    add(staff);
                }
            });
            list.add(dept);
        }
        staffs.postValue(list);
    }

    public void setCallback(StaffsDeptGrpExpListAdapter.DeptGrpExpAdapterCallback callback) {
        this.callback = callback;
    }

    /**
     * 下来加载更多时，生成更多模拟数据。
     * @param grpType
     * @param deptId
     * @param startPos
     * @param size
     */
    public void loadMoreStaffs(int grpType, long deptId, int startPos, int size) {
        if(staffs.getValue()==null){
            return;
        }
        for (HrStaffsByDept dept : staffs.getValue()) {
            long deptDeptId = dept.getDeptId();
            if(deptDeptId == deptId){
                List<StaffBasicInfo> staffs = dept.getStaffs();
                for(int i=0;i<size;i++){
                    StaffBasicInfo staff = new StaffBasicInfo();
                    staff.setName("NewGuy"+(startPos+i));
                    staff.setId(0);
                    staff.setDept(dept.getGrpLabel());
                    staff.setDeptId(deptId);
                    staffs.add(staff);
                }
                break;
            }
        }
        this.staffs.setValue(this.staffs.getValue());
    }
}
