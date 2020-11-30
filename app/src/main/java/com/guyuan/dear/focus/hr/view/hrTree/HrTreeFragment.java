package com.guyuan.dear.focus.hr.view.hrTree;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentHrTreeBinding;
import com.guyuan.dear.focus.hr.adapter.HrTreeChildDeptAdapter;
import com.guyuan.dear.focus.hr.adapter.HrTreeNavigatorAdapter;
import com.guyuan.dear.focus.hr.adapter.HrTreeParentDeptAdapter;
import com.guyuan.dear.focus.hr.adapter.HrTreeStaffAdapter;
import com.guyuan.dear.focus.hr.bean.ChildDept;
import com.guyuan.dear.focus.hr.bean.ParentDept;
import com.guyuan.dear.focus.hr.bean.StaffWorkStatusInfo;
import com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail.StaffMonthlyDetailActivity;
import com.guyuan.dear.work.contractPause.beans.DeptBean;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

/**
 * 公司人员架构
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeFragment extends BaseMvvmFragment<FragmentHrTreeBinding, HrTreeViewModel> {

    private HrTreeParentDeptAdapter mParentDeptAdapter;
    private HrTreeChildDeptAdapter mChildDeptAdapter;
    private HrTreeStaffAdapter mStaffAdapter;
    private HrTreeNavigatorAdapter mTopAdapter;
    private List<ParentDept> mParentDeptList = new ArrayList<>();
    private List<ChildDept> mChildDeptList = new ArrayList<>();
    private List<StaffWorkStatusInfo> mStaffList = new ArrayList<>();
    private List<DeptBean> mTopDeptList = new ArrayList<>();
    private ConcatAdapter mConcatAdapter;

    public static HrTreeFragment getInstance() {
        return new HrTreeFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_tree_vm;
    }

    @Override
    protected void initData() {
        Disposable disposable = getViewModel().loadDataFromNet();
        addDisposable(disposable);
    }

    @Override
    protected void initViews() {
        initTopNavigator();
        initCentralRecyclerView();
    }

    private void initTopNavigator() {
        RecyclerView recyclerView = getViewDataBinding().fragmentHrTreeTopRecyclerView;
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        mTopDeptList.addAll(getViewModel().getTopNavigationList().getValue());
        recyclerView.setLayoutManager(manager);
        mTopAdapter = new HrTreeNavigatorAdapter(mTopDeptList);
        recyclerView.setAdapter(mTopAdapter);

    }

    private void initCentralRecyclerView() {
        HrTreeViewModel viewModel = getViewModel();
        RecyclerView mainRecyclerView = getViewDataBinding().fragmentHrTreeRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mainRecyclerView.setLayoutManager(layoutManager);

        mParentDeptList.addAll(viewModel.parentDeptList.getValue());
        mParentDeptAdapter = new HrTreeParentDeptAdapter(mParentDeptList);

        mChildDeptList.addAll(viewModel.childDeptList.getValue());
        mChildDeptAdapter = new HrTreeChildDeptAdapter(mChildDeptList);

        mStaffList.addAll(viewModel.staffs.getValue());
        mStaffAdapter = new HrTreeStaffAdapter(mStaffList);

        mConcatAdapter = new ConcatAdapter();
        mConcatAdapter.addAdapter(mParentDeptAdapter);

        mainRecyclerView.setAdapter(mConcatAdapter);
    }

    @Override
    protected void initListeners() {
        initTopNavigatorListener();
        initParentDeptListener();
        initChildDeptListener();
        initStaffListAdapter();
    }

    private void initTopNavigatorListener() {
        mTopAdapter.setListener(new HrTreeNavigatorAdapter.TopItemClickListener() {
            @Override
            public void onItemClick(DeptBean item, int pos) {
                //点击顶部导航，返回上一层
                //最多两级部门，这里只有点击第一级部门才能返回第一级部门，点击第二级部门不会做任何改变。
                if (pos != 1 && mTopDeptList.size() > 1) {
                    List<DeptBean> value = getViewModel().getTopNavigationList().getValue();
                    value.clear();
                    value.add(item);
                    getViewModel().getTopNavigationList().postValue(value);
                    getViewModel().updateChildDeptListByParentDept(item);
                }
            }
        });

        getViewModel().getTopNavigationList().observe(getViewLifecycleOwner(), new Observer<List<DeptBean>>() {
            @Override
            public void onChanged(List<DeptBean> deptBeans) {
                mTopDeptList.clear();
                mTopDeptList.addAll(deptBeans);
                mTopAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initStaffListAdapter() {
        getViewModel().staffs.observe(getViewLifecycleOwner(), new Observer<List<StaffWorkStatusInfo>>() {
            @Override
            public void onChanged(List<StaffWorkStatusInfo> staffWorkStatusInfos) {
                mStaffList.clear();
                mStaffList.addAll(staffWorkStatusInfos);
                if (mConcatAdapter.getAdapters().contains(mParentDeptAdapter)) {
                    mConcatAdapter.removeAdapter(mParentDeptAdapter);
                }
                if (mConcatAdapter.getAdapters().contains(mChildDeptAdapter)) {
                    mConcatAdapter.removeAdapter(mChildDeptAdapter);
                }
                if (!mConcatAdapter.getAdapters().contains(mStaffAdapter)) {
                    mConcatAdapter.addAdapter(mStaffAdapter);
                } else {
                    mStaffAdapter.notifyDataSetChanged();
                }
            }
        });

        mStaffAdapter.setListener(new HrTreeStaffAdapter.HrTreeStaffAdapterListener() {
            @Override
            public void onItemClick(StaffWorkStatusInfo item, int position) {
                //点击人员，进入人员详情界面
                StaffMonthlyDetailActivity.start(getActivity(), item.getName(), (int) item.getId());
            }
        });
    }

    private void initChildDeptListener() {
        getViewModel().childDeptList.observe(getViewLifecycleOwner(), new Observer<List<ChildDept>>() {
            @Override
            public void onChanged(List<ChildDept> childDepts) {
                mChildDeptList.clear();
                mChildDeptList.addAll(childDepts);
                if (mConcatAdapter.getAdapters().contains(mParentDeptAdapter)) {
                    mConcatAdapter.removeAdapter(mParentDeptAdapter);
                }
                if (mConcatAdapter.getAdapters().contains(mStaffAdapter)) {
                    mConcatAdapter.removeAdapter(mStaffAdapter);
                }
                if (!mConcatAdapter.getAdapters().contains(mChildDeptAdapter)) {
                    mConcatAdapter.addAdapter(mChildDeptAdapter);
                } else {
                    mChildDeptAdapter.notifyDataSetChanged();
                }
            }
        });

        mChildDeptAdapter.setListener(new HrTreeChildDeptAdapter.HrTreeChildDeptListener() {
            @Override
            public void onItemClick(ChildDept item, int pos) {
                //点击二级部门，显示部门下所有人员
                List<StaffWorkStatusInfo> value = getViewModel().staffs.getValue();
                value.clear();
                value.addAll(item.getStaffs());
                getViewModel().staffs.postValue(value);
                //更新顶部导航栏
                List<DeptBean> navigationList = getViewModel().getTopNavigationList().getValue();
                navigationList.clear();
                List<ParentDept> parentDepts = getViewModel().parentDeptList.getValue();
                for (ParentDept dept : parentDepts) {
                    boolean isFound = false;
                    List<ChildDept> childDeptList = dept.getChildDeptList();
                    for (ChildDept childDept : childDeptList) {
                        if (childDept.getId() == item.getId()) {
                            navigationList.add(dept);
                            isFound = true;
                            break;
                        }
                    }
                    if (isFound) {
                        break;
                    }
                }
                navigationList.add(item);
                getViewModel().getTopNavigationList().postValue(navigationList);

            }
        });
    }

    private void initParentDeptListener() {
        HrTreeViewModel viewModel = getViewModel();
        viewModel.parentDeptList.observe(getViewLifecycleOwner(), new Observer<List<ParentDept>>() {
            @Override
            public void onChanged(List<ParentDept> parentDepts) {
                mParentDeptList.clear();
                mParentDeptList.addAll(parentDepts);
                if (mConcatAdapter.getAdapters().contains(mChildDeptAdapter)) {
                    mConcatAdapter.removeAdapter(mChildDeptAdapter);
                }
                if (mConcatAdapter.getAdapters().contains(mStaffAdapter)) {
                    mConcatAdapter.removeAdapter(mStaffAdapter);
                }
                if (!mConcatAdapter.getAdapters().contains(mParentDeptAdapter)) {
                    mConcatAdapter.addAdapter(mParentDeptAdapter);
                } else {
                    mParentDeptAdapter.notifyDataSetChanged();
                }
            }
        });

        mParentDeptAdapter.setItemClickListener(new HrTreeParentDeptAdapter.ItemClickListener() {
            @Override
            public void onClickItem(ParentDept item, int pos) {
                //点击一级部门，显示其二级部门
                List<ChildDept> value = viewModel.childDeptList.getValue();
                value.clear();
                value.addAll(item.getChildDeptList());
                viewModel.childDeptList.postValue(value);
                //更新顶部导航栏
                List<DeptBean> naviValue = viewModel.getTopNavigationList().getValue();
                naviValue.add(item);
                viewModel.getTopNavigationList().postValue(naviValue);
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_tree;
    }
}
