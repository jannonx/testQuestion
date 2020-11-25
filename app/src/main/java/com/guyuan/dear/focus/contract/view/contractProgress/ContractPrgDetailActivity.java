package com.guyuan.dear.focus.contract.view.contractProgress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityContractPrgDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 我的关注-销售-合同进度列表-合同进度详情
 * @author leoliao
 */
public class ContractPrgDetailActivity extends BaseToolbarActivity<ActivityContractPrgDetailBinding,ContractPrgDetailViewModel> {

    private String mTitle;
    private int mContractId;

    public static void start(Context context,String title,int contractId) {
        Intent starter = new Intent(context, ContractPrgDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        starter.putExtra(ConstantValue.KEY_CONTRACT_ID,contractId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract_prg_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(mTitle);
        mContractId = intent.getIntExtra(ConstantValue.KEY_CONTRACT_ID,0);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_contract_prg_detail_frame_layout,ContractPrgDetailFragment.getInstance(mContractId))
                .commit();


    }


}