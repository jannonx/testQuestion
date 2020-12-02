package com.guyuan.dear.focus.hr.view.hrTree;

import android.view.View;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.customizeview.searchview.HrSearchView;
import com.guyuan.dear.focus.hr.bean.ChildDept;
import com.guyuan.dear.focus.hr.bean.FactoryBean;
import com.guyuan.dear.focus.hr.bean.ParentDept;
import com.guyuan.dear.focus.hr.bean.StaffWorkStatusInfo;
import com.guyuan.dear.focus.hr.repos.HrTreeRepo;
import com.guyuan.dear.net.resultBeans.NetIdAndStatusMapping;
import com.guyuan.dear.work.contractPause.beans.DeptBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeViewModel extends BaseDearViewModel {
    public MutableLiveData<String> companyName = new MutableLiveData<>("开封迪尔空分实业有限公司");
    public MutableLiveData<List<ParentDept>> parentDeptList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<ChildDept>> childDeptList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<StaffWorkStatusInfo>> staffs = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<DeptBean>> topNavigationList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<HrSearchView.SelectStaffCallback> onSelectSearchStaff = new MutableLiveData<>();

    public MutableLiveData<View.OnClickListener> onClickNavigateToFrontPage = new MutableLiveData<>(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            topNavigationList.getValue().clear();
            topNavigationList.postValue(topNavigationList.getValue());
            parentDeptList.postValue(parentDeptList.getValue());
        }
    });

    private HrTreeRepo mRepo = new HrTreeRepo();

    public MutableLiveData<List<DeptBean>> getTopNavigationList() {
        return topNavigationList;
    }

    public Disposable loadDataFromNet() {
        return mRepo.getStaffIdAndAttendStatusMappings(new BaseNetCallback<List<NetIdAndStatusMapping>>() {
            @Override
            protected void handleResult(List<NetIdAndStatusMapping> result) {
                sortStaffsByDept(result);
            }
        });
    }

    private void sortStaffsByDept(List<NetIdAndStatusMapping> result) {
        addSubscription(mRepo.sortStaffsByDept(result, new BaseNetCallback<List<ParentDept>>() {
            @Override
            protected void handleResult(List<ParentDept> result) {
                parentDeptList.postValue(result);
            }
        }));
    }


    public void updateChildDeptListByParentDept(DeptBean item) {
        for (ParentDept parentDept : parentDeptList.getValue()) {
            if(parentDept.getId()==item.getId()){
                List<ChildDept> childDeptList = parentDept.getChildDeptList();
                List<ChildDept> value = this.childDeptList.getValue();
                value.clear();
                value.addAll(childDeptList);
                this.childDeptList.postValue(value);
                break;
            }
        }
    }

}
