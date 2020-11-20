package com.guyuan.dear.focus.contract.view.contractDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityContractDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 我的关注-合同-合同概况-合同列表-合同详情
 *
 * @author leoliao
 */
public class ContractDetailActivity extends BaseToolbarActivity<ActivityContractDetailBinding, ContractDetailViewModel> {

    public static void start(Context context, String title, int contractId) {
        Intent starter = new Intent(context, ContractDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_CONTRACT_ID, contractId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        ContractDetailFragment fragment = ContractDetailFragment.getInstance(
                intent.getIntExtra(ConstantValue.KEY_CONTRACT_ID,0)
        );
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_contract_detail_frame_layout, fragment)
                .commit();

    }


}