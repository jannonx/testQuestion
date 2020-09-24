package com.guyuan.dear.focus.hr.view.hrStatusGrp;

import android.os.Bundle;
import android.os.Handler;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentHrGroupBinding;
import com.guyuan.dear.focus.hr.adapter.StaffsDeptGrpExpListAdapter;
import com.guyuan.dear.focus.hr.bean.StaffBasicInfo;
import com.guyuan.dear.focus.hr.view.hrStaffStatusInfo.StaffStatusInfoActivity;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/21 18:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStatusGroupFragment extends BaseMvvmFragment<FragmentHrGroupBinding, HrStatusGrpViewModel> {

    private int grpType;

    public static HrStatusGroupFragment getInstance(int grpType) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, grpType);
        HrStatusGroupFragment fragment = new HrStatusGroupFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_group_vm;
    }

    @Override
    protected void initData() {
        grpType = getArguments().getInt(ConstantValue.KEY_GRP_TYPE);


    }

    @Override
    protected void initViews() {
        getViewModel().setGrpType(grpType);
        getViewModel().loadDataFromNet();

    }

    @Override
    protected void initListeners() {
        getViewModel().setCallback(new StaffsDeptGrpExpListAdapter.DeptGrpExpAdapterCallback() {
            @Override
            public void onClickLoadMore(int grpType, long deptId, int pageStartIndex, int pageSize, int position) {
                showLoading(getParentFragmentManager());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getViewModel().loadMoreStaffs(grpType, deptId, pageStartIndex, pageSize);
                        hideLoading();
                    }
                },4000);

            }

            @Override
            public void onClickStaff(StaffBasicInfo item) {
                StaffStatusInfoActivity.start(getActivity(),item.getId());
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_group;
    }
}
