package com.guyuan.dear.focus.hr.view.hrStaffAttendDetail;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.hr.adapter.StaffAttendListAbsentAdapter;
import com.guyuan.dear.focus.hr.adapter.StaffAttendListLateAdapter;
import com.guyuan.dear.focus.hr.adapter.StaffAttendListLeaveEarlyAdapter;
import com.guyuan.dear.focus.hr.adapter.StaffAttendListNormalAdapter;
import com.guyuan.dear.focus.hr.bean.AbsentAttendRecords;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.bean.LateAttendRecords;
import com.guyuan.dear.focus.hr.bean.LeaveEarlyAttendRecords;
import com.guyuan.dear.focus.hr.bean.NormalAttendRecords;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/25 16:31
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AttendListFragment extends BaseFragment {

    private StaffAttendListAbsentAdapter absentAdapter;
    private StaffAttendListLateAdapter lateAdapter;
    private int attendType;
    private StaffAttendListLeaveEarlyAdapter leaveEarlyAdapter;
    private StaffAttendListNormalAdapter normalAdapter;
    private BaseRecyclerView recyclerView;
    private StaffAttendDetailViewModel viewModel;
    private BaseRecyclerViewAdapter adapter;

    public static AttendListFragment getNormalListInstance() {
        AttendListFragment fragment = new AttendListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, HrStatusGroup.GRP_TYPE_NORMAL);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AttendListFragment getLateListInstance() {
        AttendListFragment fragment = new AttendListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, HrStatusGroup.GRP_TYPE_LATE);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AttendListFragment getLeaveEarlyInstance() {
        AttendListFragment fragment = new AttendListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, HrStatusGroup.GRP_TYPE_LEAVE_EARLY);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AttendListFragment getAbsentInstance() {
        AttendListFragment fragment = new AttendListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_GRP_TYPE, HrStatusGroup.GRP_TYPE_ABSENT);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_staff_attend_list;
    }

    @Override
    protected void initialization() {
        initData();
        initView();
        initListeners();

    }


    private void initData() {
        viewModel = new ViewModelProvider(getActivity()).get(StaffAttendDetailViewModel.class);
        Bundle bundle = getArguments();
        attendType = bundle.getInt(ConstantValue.KEY_GRP_TYPE);
        switch (attendType) {
            case HrStatusGroup.GRP_TYPE_ABSENT:
                absentAdapter = new StaffAttendListAbsentAdapter(getContext(), viewModel.getAbsentRecords().getValue());
                viewModel.getAbsentRecords().observe(this, new Observer<List<AbsentAttendRecords.AbsentIncident>>() {
                    @Override
                    public void onChanged(List<AbsentAttendRecords.AbsentIncident> list) {
                        absentAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case HrStatusGroup.GRP_TYPE_LATE:
                lateAdapter = new StaffAttendListLateAdapter(this, viewModel.getLateRecords().getValue());
                viewModel.getLateRecords().observe(this, new Observer<List<LateAttendRecords.LateIncident>>() {
                    @Override
                    public void onChanged(List<LateAttendRecords.LateIncident> lateIncidents) {
                        lateAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case HrStatusGroup.GRP_TYPE_LEAVE_EARLY:
                leaveEarlyAdapter = new StaffAttendListLeaveEarlyAdapter(getContext(), viewModel.getLeaveEarlyRecords().getValue());
                viewModel.getLeaveEarlyRecords().observe(this, new Observer<List<LeaveEarlyAttendRecords.LeaveEarlyIncident>>() {
                    @Override
                    public void onChanged(List<LeaveEarlyAttendRecords.LeaveEarlyIncident> leaveEarlyIncidents) {
                        leaveEarlyAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case HrStatusGroup.GRP_TYPE_NORMAL:
                normalAdapter = new StaffAttendListNormalAdapter(getContext(), viewModel.getNormalRecords().getValue());
                viewModel.getNormalRecords().observe(this, new Observer<List<NormalAttendRecords.NormalAttendance>>() {
                    @Override
                    public void onChanged(List<NormalAttendRecords.NormalAttendance> list) {
                        normalAdapter.notifyDataSetChanged();
                    }
                });
                break;
            default:
                break;
        }

    }


    private void initView() {
        recyclerView = rootView.findViewById(R.id.fragment_staff_attend_list_recycler_view);

        switch (attendType) {
            case HrStatusGroup.GRP_TYPE_ABSENT:
                adapter = new BaseRecyclerViewAdapter(absentAdapter);
                break;
            case HrStatusGroup.GRP_TYPE_LATE:
                adapter = new BaseRecyclerViewAdapter(lateAdapter);
                break;
            case HrStatusGroup.GRP_TYPE_LEAVE_EARLY:
                adapter = new BaseRecyclerViewAdapter(leaveEarlyAdapter);
                break;
            case HrStatusGroup.GRP_TYPE_NORMAL:
                adapter = new BaseRecyclerViewAdapter(normalAdapter);
                break;
            default:
                break;
        }
        if (adapter != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setLoadMoreEnabled(false);
            recyclerView.setPullRefreshEnabled(false);
        }


    }

    private void initListeners() {

    }
}
