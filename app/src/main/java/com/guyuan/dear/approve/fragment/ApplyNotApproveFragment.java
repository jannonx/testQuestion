package com.guyuan.dear.approve.fragment;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.util.LogUtils;
import com.google.gson.Gson;
import com.guyuan.dear.approve.activity.ApprovalsEntranceActivity;
import com.guyuan.dear.approve.adapter.ApprovedListAdapter;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.bean.ApprovalData;
import com.guyuan.dear.approve.bean.ApproveListBody;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.login.ui.LoginActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 掌上办公--审批--我的审批--待我审批列表
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApplyNotApproveFragment extends BaseListFragment<ApplyBean, FragmentListBinding,ApproveViewModel> {
    public static final String TAG = ApplyNotApproveFragment.class.getSimpleName();


    public static ApplyNotApproveFragment getInstance() {
        return new ApplyNotApproveFragment();
    }

    @Override
    protected void initView() {
        ApprovedListAdapter approveAdapter = new ApprovedListAdapter(getActivity(), listData, R.layout.item_examine_and_approve);
        adapter = new BaseRecyclerViewAdapter(approveAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ApplyDetailPageActivity.start(getActivity(), listData.get(position), true);
            }
        });

        getApplyNotApproveList();
    }


    private void getApplyNotApproveList() {
        ApproveListBody requestBody = new ApproveListBody();
        requestBody.setPageNum(ConstantValue.FIRST_PAGE);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        String str = new Gson().toJson(requestBody);
        viewModel.getApplyNotApproveList(RequestBody.create(MediaType.parse("application/json; " +
                "charset=utf-8"), str));

//        viewModel.getApplyNotApproveEvent()
        viewModel.getApplyNotApproveEvent().observe(getActivity(), new Observer<RefreshBean<ApprovalData>>() {
            @Override
            public void onChanged(RefreshBean<ApprovalData> dataRefreshBean) {
                showApplyNotApproveList(dataRefreshBean);
            }
        });
    }

    public void showApplyNotApproveList(RefreshBean<ApprovalData> result) {
        List<ApprovalData> approvals = result.getContent();
        ArrayList<ApplyBean> applies = new ArrayList<>();
        for (ApprovalData approvalData : approvals) {
            ApplyBean apply = approvalData.gettApproveAll();
            apply.setStatus(approvalData.getStatus());
            apply.setSessionId(approvalData.getId());
            applies.add(apply);
        }
        LogUtils.showLog("showApplyNotApproveList=" + result.getContent().size() + "//adapter=" + (adapter == null) + "///currentType=" + currentType);
        setListData(applies);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(BusEventSubmitOK event) {
//        getApplyNotApproveList();
//    }
//
//    @Override
//    protected boolean isEventBusNeedRegister() {
//        return true;
//    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
