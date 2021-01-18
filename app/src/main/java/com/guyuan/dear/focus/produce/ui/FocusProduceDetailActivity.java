package com.guyuan.dear.focus.produce.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.fragment.FocusProduceDetailFragment;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import dagger.hilt.android.AndroidEntryPoint;

import static com.guyuan.dear.focus.produce.fragment.FocusProduceDetailFragment.BUSINESS_ID;
import static com.guyuan.dear.focus.produce.fragment.FocusProduceDetailFragment.BUSINESS_TYPE;
import static com.guyuan.dear.office.approval.ui.ApprovalActivity.APPROVAL_TYPE;
import static com.guyuan.dear.office.approval.ui.ApprovalActivity.IS_APPROVED;


/**
 * @description: 我的关注--生产--生产详情
 * @author: 许建宁
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class FocusProduceDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusProduceViewModel> {
    private FocusProduceDetailFragment mFragment;
    public static void start(Context context, FocusProduceBean data, boolean isFooterBtnShow) {
        Intent intent = new Intent(context, FocusProduceDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        intent.putExtra(ConstantValue.KEY_BOOLEAN, isFooterBtnShow);
        context.startActivity(intent);
    }

    public static void start(Context context, boolean isApproval, FocusProduceBean data, int businessId,
                             int businessType, int apprType) {
        Intent intent = new Intent(context, FocusProduceDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        intent.putExtra(ConstantValue.KEY_BOOLEAN, false);
        intent.putExtra(IS_APPROVED, isApproval);
        intent.putExtra(APPROVAL_TYPE, apprType);
        intent.putExtra(BUSINESS_ID, businessId);
        intent.putExtra(BUSINESS_TYPE, businessType);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        FocusProduceBean bean = (FocusProduceBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        boolean isFooterBtnShow = getIntent().getBooleanExtra(ConstantValue.KEY_BOOLEAN, false);
        boolean isApproval = getIntent().getBooleanExtra(IS_APPROVED, false);
        int businessId = getIntent().getIntExtra(BUSINESS_ID, -1);
        int businessType = getIntent().getIntExtra(BUSINESS_TYPE, -1);
        int type = getIntent().getIntExtra(APPROVAL_TYPE, -1);
        binding.toolbarContainer.titleTv.setText("生产详情");


        LogUtils.showLog("listData=" + (bean == null));
        if (isApproval) {
            mFragment = FocusProduceDetailFragment.newInstance(bean, businessId,
                    businessType, type, isApproval);
        } else {
            mFragment = FocusProduceDetailFragment.newInstance(bean, isFooterBtnShow);
        }
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                FocusProduceDetailFragment.TAG);
    }

    @Override
    public FocusProduceViewModel getViewModel() {
        return viewModel;
    }


    @Override
    public void onContractPaused() {
        super.onContractPaused();
        mFragment.setContractPaused();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
