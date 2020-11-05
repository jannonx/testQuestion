package com.guyuan.dear.focus.hr.view.pickStaffs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentPickStaffsBinding;
import com.guyuan.dear.focus.hr.adapter.PickStaffsExpListAdapter;
import com.guyuan.dear.focus.hr.adapter.PickStaffsHistoryStaffsAdapter;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;
import com.guyuan.dear.focus.hr.bean.PickStaffsExpParentBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 12:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsFragment extends BaseMvvmFragment<FragmentPickStaffsBinding, PickStaffsViewModel> {

    public static PickStaffsFragment getInstance(ArrayList<StaffBean> preSelected,ArrayList<StaffBean> hidden,int maxSelectCount) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ConstantValue.KEY_PRE_SELECTED_STAFFS,preSelected);
        bundle.putParcelableArrayList(ConstantValue.KEY_HIDDEN_STAFFS,hidden);
        bundle.putInt(ConstantValue.KEY_MAX_SELECT_COUNT,maxSelectCount);
        PickStaffsFragment fragment = new PickStaffsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_pick_staffs_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        ArrayList<StaffBean> preSelected = bundle.getParcelableArrayList(ConstantValue.KEY_PRE_SELECTED_STAFFS);
        ArrayList<StaffBean> hiddenStaffs = bundle.getParcelableArrayList(ConstantValue.KEY_HIDDEN_STAFFS);
        int maxSelect = bundle.getInt(ConstantValue.KEY_MAX_SELECT_COUNT);
        getViewModel().init(preSelected,hiddenStaffs,maxSelect);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        //点击历史选人列表，将联动二级选人列表UI人员选择状态
        getViewModel().setOnClickHistoryStaff(new PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener() {
            @Override
            public void onItemClick(PickStaffBean bean, int pos) {
                List<PickStaffsExpParentBean> grpBeans = getViewModel().getGrpBeans().getValue();
                for (int i = 0; i < grpBeans.size(); i++) {
                    PickStaffsExpParentBean temp = grpBeans.get(i);
                    List<PickStaffBean> staffs = temp.getStaffs();
                    for (int i1 = 0; i1 < staffs.size(); i1++) {
                        PickStaffBean staffBean = staffs.get(i1);
                        if(staffBean.getId().equals(bean.getId())){
                            ExpandableListView expListView = getViewDataBinding().fragmentPickStaffsExpListView;
                            PickStaffsExpListAdapter adapter = (PickStaffsExpListAdapter) expListView.getExpandableListAdapter();
                            adapter.notifyDataSetChanged();
                            if(expListView.isGroupExpanded(i)){
                                expListView.collapseGroup(i);
                                expListView.expandGroup(i);
                            }
                            getViewModel().updateSelectCount();
                            return;
                        }
                    }
                }
            }
        });

        //点击二级选人列表，将联动历史选人列表UI人员选择状态
        getViewModel().setOnToggleStaff(new PickStaffsExpListAdapter.ItemCallback() {
            @Override
            public void onTogglePickStaff(int grpPos, int childPos, PickStaffBean item) {
                RecyclerView view = getViewDataBinding().fragmentPickStaffsRecyclerViewHistoryStaffs;
                PickStaffsHistoryStaffsAdapter adapter = (PickStaffsHistoryStaffsAdapter) view.getAdapter();
                List<PickStaffBean> list = adapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    PickStaffBean bean = list.get(i);
                    if(item.getId().equals(bean.getId())){
                        adapter.notifyItemChanged(i);
                        break;
                    }
                }
                getViewModel().updateSelectCount();
            }
        });

        //点击历史选人列表中的"选择全部"，则同时更新历史选人列表UI和二级选人列表UI
        getViewDataBinding().fragmentPickStaffsCbxSelectAllHistoryStaffs.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //更改数据源点选状态
                getViewModel().selectAllHistoryStaffs(isChecked);
                //更新最新点选人数UI
                getViewModel().updateSelectCount();
                //更新历史选人列表UI
                ExpandableListView expListView = getViewDataBinding().fragmentPickStaffsExpListView;
                PickStaffsExpListAdapter adapter = (PickStaffsExpListAdapter) expListView.getExpandableListAdapter();
                adapter.notifyDataSetChanged();
                //更新二级选人列表UI
                RecyclerView view = getViewDataBinding().fragmentPickStaffsRecyclerViewHistoryStaffs;
                PickStaffsHistoryStaffsAdapter adapter2 = (PickStaffsHistoryStaffsAdapter) view.getAdapter();
                adapter2.notifyDataSetChanged();
            }
        });

        //点击确认，把所选清单返回给上一个activity
        getViewModel().setOnClickSubmit(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> staffs = getViewModel().getSelectedStaffs();
                FragmentActivity activity = getActivity();
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS,staffs);
                activity.setResult(Activity.RESULT_OK,intent);
                activity.finish();
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pick_staffs;
    }
}
