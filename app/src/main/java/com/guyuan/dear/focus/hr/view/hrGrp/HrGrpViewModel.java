package com.guyuan.dear.focus.hr.view.hrGrp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.bean.StaffBean;
import com.guyuan.dear.focus.hr.view.home.HrHomeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/21 17:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrGrpViewModel extends BaseViewModel {
    /**
     * ！有坑注意！
     * 子类必须要带有一个和父类一样的构造函数，如：LoginViewModel(Application app)。否则根据源码，在ViewModelProvider中生成时会报错。
     * by Leo
     *
     * @param application
     */
    public HrGrpViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<HrStaffsByDept>> staffs = new MutableLiveData<>();
    public int grpType;

    public void setGrpType(int grpType) {
        this.grpType = grpType;
    }

    public void update() {
        int count=10;
        List<HrStaffsByDept> list = new ArrayList<>();
        for(int i=0;i<count;i++){
            HrStaffsByDept dept = new HrStaffsByDept();
            dept.setGrpType(grpType);
            dept.setGrpLabel("部门"+i);
            dept.setStaffs(new ArrayList<StaffBean>(){
                {
                    StaffBean staff = new StaffBean();
                    staff.setDept(dept.getGrpLabel());
                    staff.setName("Leo");
                    staff.setId(1);
                    add(staff);
                }
            });
            list.add(dept);
        }
        staffs.postValue(list);
    }
}
