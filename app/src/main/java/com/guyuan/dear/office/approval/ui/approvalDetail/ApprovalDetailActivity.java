package com.guyuan.dear.office.approval.ui.approvalDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityApprovalDetailBinding;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.contract.adapter.ContractExceptionOrTotalAdapter;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractDetailFragment;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.fragment.FocusProduceDetailComplexFragment;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/1 16:27
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalDetailActivity extends BaseToolbarActivity<ActivityApprovalDetailBinding, ApprovalViewModel> {

    public static final int CONTRACT = 1;
    public static final int PRODUCE = 2;

    private Fragment fragment;
    private int type;
    private int id;

    public static void start(Context context, int type, int id) {
        Intent starter = new Intent(context, ApprovalDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TYPE, type);
        starter.putExtra(ConstantValue.KEY_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        type = getIntent().getIntExtra(ConstantValue.KEY_TYPE, 0);
        id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        setContent();
    }


    private void setContent() {
        switch (type) {
            case CONTRACT:
                fragment = ContractDetailFragment.getInstance(id);
                break;

            case PRODUCE:
                FocusProduceBean data = new FocusProduceBean();
                data.setEquipmentId(id);
                fragment = FocusProduceDetailComplexFragment.newInstance(data, false);
                break;
        }
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}