package com.guyuan.dear.focus.hr.view.pickStaffs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.searchview.HrSearchView;
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
 * @description: 选人界面
 * @since: 2020/11/3 12:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsFragment extends BaseMvvmFragment<FragmentPickStaffsBinding, PickStaffsViewModel> {

    /**
     *
     * @param preSelected 已选的人。这部分默认显示选中，用户可以取消或重新选中。
     * @param hidden 隐藏的人。这部分不显示出来，不能操作。
     * @param disabled 不能操作的打人。这部分用户能看到，但不能操作。可以和已选的人重叠。
     * @param maxSelectCount 最大选择人数
     * @return
     */
    public static PickStaffsFragment getInstance(@Nullable ArrayList<StaffBean> preSelected,
                                                 @Nullable ArrayList<StaffBean> hidden,
                                                 @Nullable ArrayList<StaffBean> disabled,
                                                 int maxSelectCount) {
        Bundle bundle = new Bundle();
        if(preSelected!=null){
            bundle.putParcelableArrayList(ConstantValue.KEY_PRE_SELECTED_STAFFS,preSelected);
        }
        if(hidden!=null){
            bundle.putParcelableArrayList(ConstantValue.KEY_HIDDEN_STAFFS,hidden);
        }
        if(disabled!=null){
            bundle.putParcelableArrayList(ConstantValue.KEY_DISABLE_STAFFS,disabled);
        }
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
        ArrayList<StaffBean> disabled = bundle.getParcelableArrayList(ConstantValue.KEY_DISABLE_STAFFS);
        int maxSelect = bundle.getInt(ConstantValue.KEY_MAX_SELECT_COUNT);
        getViewModel().init(preSelected,hiddenStaffs,disabled,maxSelect);
        //如果有需要隐藏起来的人员，设置成在搜索栏里也搜索不到。
        if(hiddenStaffs!=null){
            getViewDataBinding().fragmentPickStaffsHrSearchView.searchBarForStaffSearchView.addHiddenStaffs(hiddenStaffs);
        }

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        //点击历史选人列表，将联动二级选人列表中人员选择状态的UI
        getViewModel().setOnClickHistoryStaff(new PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener() {
            @Override
            public void onItemClick(PickStaffBean bean, int pos) {
                if(bean.isPick()){
                    if(getViewModel().checkIsExceedMaxSelectCount()){
                        bean.setPick(false);
                        bean.setPickTime(0);
                        showToastTip("已经超出最大选择人数。");
                        return;
                    }
                }

                List<PickStaffsExpParentBean> grpBeans = getViewModel().getGrpBeans().getValue();
                //遍历二级选人列表，找到被点选/反选的人，更新UI
                for (int i = 0; i < grpBeans.size(); i++) {
                    //从一级菜单开始
                    PickStaffsExpParentBean temp = grpBeans.get(i);
                    List<PickStaffBean> staffs = temp.getStaffs();
                    //遍历二级菜单
                    for (int i1 = 0; i1 < staffs.size(); i1++) {
                        PickStaffBean staffBean = staffs.get(i1);
                        if(staffBean.getId().equals(bean.getId())){
                            //如果找到了被点选/反选的人，更新adapter，刷新UI
                            ExpandableListView expListView = getViewDataBinding().fragmentPickStaffsExpListView;
                            PickStaffsExpListAdapter adapter = (PickStaffsExpListAdapter) expListView.getExpandableListAdapter();
                            adapter.notifyDataSetChanged();
                            //expListView 更新UI时需要重新收起/展开，否则子菜单不会刷新
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

        //点击二级选人列表，将联动历史选人列表中的人员选择状态的UI
        getViewModel().setOnToggleStaff(new PickStaffsExpListAdapter.ItemCallback() {
            @Override
            public void onTogglePickStaff(int grpPos, int childPos, PickStaffBean item) {

                if(item.isPick()){
                    if(getViewModel().checkIsExceedMaxSelectCount()){
                        item.setPick(false);
                        item.setPickTime(0);
                        showToastTip("已经超出最大选择人数。");
                        return;
                    }
                }

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
                //保存选择记录
                getViewModel().saveStaffSelectHistoryToLocal(staffs);
                FragmentActivity activity = getActivity();
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS,staffs);
                activity.setResult(Activity.RESULT_OK,intent);
                activity.finish();
            }
        });

        //点击上方搜索栏下拉的人员，并选中
        getViewModel().onSelectSearchStaff.postValue(new HrSearchView.SelectStaffCallback() {
            @Override
            public void onStaffSelected(StaffBean staff) {
                MutableLiveData<List<PickStaffBean>> allStaffs = getViewModel().getAllStaffs();
                if(allStaffs.getValue()==null){
                    return;
                }
                //改变公共数据源中员工的点选状态
                if(!getViewModel().checkStaffSelectable(staff.getId())){
                    showToastTip("无法选择该员工");
                    return;
                }
                for (PickStaffBean bean : allStaffs.getValue()) {
                    if(bean.getId().equals(staff.getId())){
                        bean.setPickTime(System.currentTimeMillis());
                        bean.setPick(true);
                        break;
                    }
                }
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

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pick_staffs;
    }
}
