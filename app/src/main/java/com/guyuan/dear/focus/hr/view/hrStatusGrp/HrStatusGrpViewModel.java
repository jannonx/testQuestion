package com.guyuan.dear.focus.hr.view.hrStatusGrp;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.hr.adapter.HrStaffAdapter;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.focus.hr.repos.HrStatusGrpRepo;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description: 这个视图模型按照当前出勤状况给所有人员分组
 * @since: 2020/9/21 17:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStatusGrpViewModel extends BaseDearViewModel {

    private HrStatusGrpRepo repo = new HrStatusGrpRepo();
    public MutableLiveData<List<HrStaffsByDept>> staffs = new MutableLiveData<>();
    public MutableLiveData<HrStaffAdapter.HrStaffAdapterItemClickListener> onItemClickListener = new MutableLiveData<>();

    public Disposable loadStaffListByType(int type) {
        return repo.getStaffListByAttendType(type, new BaseNetCallback<List<StaffBean>>() {
            @Override
            protected void handleResult(List<StaffBean> result) {
                staffs.postValue(repo.groupStaffsByDept(result));
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
