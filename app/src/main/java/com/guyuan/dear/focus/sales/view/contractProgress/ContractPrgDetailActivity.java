package com.guyuan.dear.focus.sales.view.contractProgress;

import androidx.appcompat.app.AppCompatActivity;

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
    private String mContractId;

    public static void start(Context context,String title,String contractId) {
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
        mContractId = intent.getStringExtra(ConstantValue.KEY_CONTRACT_ID);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_contract_prg_detail_frame_layout,ContractPrgDetailFragment.getInstance(mContractId))
                .commit();


    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}