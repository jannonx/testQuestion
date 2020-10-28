package com.guyuan.dear.approve.fragment;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.google.gson.Gson;
import com.guyuan.dear.approve.adapter.ApprovedListAdapter;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.bean.ApprovalData;
import com.guyuan.dear.approve.bean.ApproveListBody;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 掌上办公--审批--我的审批--我已审批列表
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApplyYetApprovedFragment extends BaseListFragment<ApplyBean, FragmentListBinding, ApproveViewModel> {
    public static final String TAG = ApplyYetApprovedFragment.class.getSimpleName();

    public static ApplyYetApprovedFragment getInstance() {
        return new ApplyYetApprovedFragment();
    }




    @Override
    protected void initView() {
        ApprovedListAdapter approveAdapter = new ApprovedListAdapter(getActivity(), listData, R.layout.item_examine_and_approve);
        adapter = new BaseRecyclerViewAdapter(approveAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);
        recycleView.setLoadMoreEnabled(false);
        recycleView.setPullRefreshEnabled(false);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ApplyBean bean = listData.get(position);
//                ApplyDetailPageActivity.start(getActivity(), bean);
            }
        });
        getApplyYetApproveList();
    }


    /**
     * 请求数据
     */
    private void getApplyYetApproveList() {
        ApproveListBody requestBody = new ApproveListBody();
        requestBody.setPageNum(ConstantValue.FIRST_PAGE);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        String str = new Gson().toJson(requestBody);
//        mPresenter.getApplyYetApproveList(RequestBody.create(MediaType.parse("application/json; " +
//                "charset=utf-8"), str));
    }

    /**
     * 请求回调
     *
     * @param result 数据实体
     */
    public void showApplyYetApproveList(RefreshBean<ApprovalData> result) {
        List<ApprovalData> approvals = result.getContent();
        ArrayList<ApplyBean> applies = new ArrayList<>();
        for (ApprovalData approvalData : approvals) {
            ApplyBean apply = approvalData.gettApproveAll();
            apply.setStatus(approvalData.getStatus());
            applies.add(apply);
        }
        setListData(applies);

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(BusEventSubmitOK event) {
//        getApplyYetApproveList();
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

//    @Override
//    protected boolean isEventBusNeedRegister() {
//        return true;
//    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
