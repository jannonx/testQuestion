package com.guyuan.dear.focus.contract.view.contractApplyDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityContractApplyDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author 廖华凯
 * 我的关注-合同-合同异常列表-合同异常详情
 */
public class ContractApplyDetailActivity extends BaseToolbarActivity<ActivityContractApplyDetailBinding, ContractApplyDetailViewModel> {

    public static void start(Context context, String title, long examineId) {
        Intent starter = new Intent(context, ContractApplyDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_EXAMINE_ID, examineId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract_apply_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        long id = intent.getLongExtra(ConstantValue.KEY_EXAMINE_ID, 0L);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_contract_apply_detail_frame_layout, ContractApplyDetailFragment.getInstance(id))
                .commit();

    }

}