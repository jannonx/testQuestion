package com.guyuan.dear.approve;


import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.activity.ApplyForLeaveActivity;
import com.guyuan.dear.approve.activity.ApprovalsEntranceActivity;
import com.guyuan.dear.approve.activity.ApproveBusinessTripActivity;
import com.guyuan.dear.approve.activity.ApproveCommonActivity;
import com.guyuan.dear.approve.activity.ApproveEmployActivity;
import com.guyuan.dear.approve.activity.ApproveExpenseReimbursementActivity;
import com.guyuan.dear.approve.activity.ApproveLeaveOfficeActivity;
import com.guyuan.dear.approve.activity.ApproveOutActivity;
import com.guyuan.dear.approve.activity.ApproveOverTimeActivity;
import com.guyuan.dear.approve.activity.ApprovePayActivity;
import com.guyuan.dear.approve.activity.ApprovePrettyCashActivity;
import com.guyuan.dear.approve.activity.ApprovePurchaseActivity;
import com.guyuan.dear.approve.activity.ApproveSealActivity;
import com.guyuan.dear.approve.activity.MineApplyListActivity;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.databinding.FragmentApproveBinding;
import com.guyuan.dear.login.data.bean.ChildrenBean;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 掌上办公--审批首页
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApproveFragment extends BaseDataBindingFragment<FragmentApproveBinding, ApproveViewModel> implements View.OnClickListener {

    public static final String TAG = ApproveFragment.class.getSimpleName();


    public static ApproveFragment newInstance(ArrayList<ChildrenBean> data) {
        ApproveFragment fragment = new ApproveFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ConstantValue.KEY_APPROVE_MENU, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approve;
    }

    @Override
    public void initialization() {
        Bundle arguments = getArguments();
        ArrayList<ChildrenBean> menuList = arguments.getParcelableArrayList(ConstantValue.KEY_APPROVE_MENU);
        BaseMenuAdapter baseMenuAdapter = new BaseMenuAdapter(getActivity(), menuList,
                R.layout.item_fragment_menu);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(baseMenuAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = menuList.get(position).getUrl();
                String title = menuList.get(position).getTitle();
                switch (url) {
                    //请假
                    case ConstantValue.MOBILE_OFFICE_APPROVE_APPLY_FOR_LEAVE:
                        ApplyForLeaveActivity.start(getActivity(), title);
                        break;
                    //出差
                    case ConstantValue.MOBILE_OFFICE_APPROVE_BUSINESS_TRIP:
                        ApproveBusinessTripActivity.start(getActivity(), title);
                        break;
                    //采购
                    case ConstantValue.MOBILE_OFFICE_APPROVE_PURCHASE:
                        ApprovePurchaseActivity.start(getActivity(), title);
                        break;
                    //外出
                    case ConstantValue.MOBILE_OFFICE_APPROVE_OUT:
                        ApproveOutActivity.start(getActivity(), title);
                        break;
                    //加班
                    case ConstantValue.MOBILE_OFFICE_APPROVE_OVERTIME:
                        ApproveOverTimeActivity.start(getActivity(), title);
                        break;
                    //招聘
                    case ConstantValue.MOBILE_OFFICE_APPROVE_EMPLOY:
                        ApproveEmployActivity.start(getActivity(), title);
                        break;
                    //离职
                    case ConstantValue.MOBILE_OFFICE_APPROVE_LEAVE_OFFICE:
                        ApproveLeaveOfficeActivity.start(getActivity(), title);
                        break;
                    //报销
                    case ConstantValue.MOBILE_OFFICE_APPROVE_EXPENSE_REIMBURSEMENT:
                        ApproveExpenseReimbursementActivity.start(getActivity(), title);
                        break;
                    //付款
                    case ConstantValue.MOBILE_OFFICE_APPROVE_PAY:
                        ApprovePayActivity.start(getActivity(), title);
                        break;
                    //备用金
                    case ConstantValue.MOBILE_OFFICE_APPROVE_IMPREST:
                        ApprovePrettyCashActivity.start(getActivity(), title);
                        break;

                    //用印
                    case ConstantValue.MOBILE_OFFICE_APPROVE_SEAL:
                        ApproveSealActivity.start(getActivity(), title);
                        break;
                    //通用
                    case ConstantValue.MOBILE_OFFICE_APPROVE_COMMON:
                        ApproveCommonActivity.start(getActivity(), title);
                        break;
                    default:

                }
            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLoadMoreEnabled(false);
        binding.recyclerView.setPullRefreshEnabled(false);


        binding.llApproveMine.setOnClickListener(this);
        binding.llSponsorMine.setOnClickListener(this);
        binding.llCopyMine.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //我的审批
            case R.id.ll_approve_mine:
                ApprovalsEntranceActivity.start(getActivity());
                break;
            //我发起的
            case R.id.ll_sponsor_mine:
                MineApplyListActivity.start(getActivity());
                break;
            //抄给我的
            case R.id.ll_copy_mine:
//                ApplyCopyActivity.start(getActivity());
                break;
            default:
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
