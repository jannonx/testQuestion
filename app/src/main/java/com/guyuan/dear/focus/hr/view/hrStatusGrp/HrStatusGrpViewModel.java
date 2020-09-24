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
 * @description:
 * @since: 2020/9/21 17:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStatusGrpViewModel extends BaseViewModel {
    /**
     * ！有坑注意！
     * 子类必须要带有一个和父类一样的构造函数，如：LoginViewModel(Application app)。否则根据源码，在ViewModelProvider中生成时会报错。
     * by Leo
     *
     * @param application
     */
    public HrStatusGrpViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<HrStaffsByDept>> staffs = new MutableLiveData<>();
    public int grpType;
    public StaffsDeptGrpExpListAdapter.DeptGrpExpAdapterCallback callback;

    public void setGrpType(int grpType) {
        this.grpType = grpType;
    }

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
