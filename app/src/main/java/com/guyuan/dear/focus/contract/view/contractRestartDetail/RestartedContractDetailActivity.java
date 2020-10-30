package com.guyuan.dear.focus.contract.view.contractRestartDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityRestartedContractDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author leo
 */
public class RestartedContractDetailActivity extends BaseToolbarActivity<
        ActivityRestartedContractDetailBinding,
        RestartedContractDetailViewModel> {

    public static void start(Context context,String title,String contractId) {
        Intent starter = new Intent(context, RestartedContractDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        starter.putExtra(ConstantValue.KEY_CONTRACT_ID,contractId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_restarted_contract_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        String contractId = intent.getStringExtra(ConstantValue.KEY_CONTRACT_ID);
        RestartedContractDetailFragment fragment = RestartedContractDetailFragment.getInstance(contractId);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_restarted_contract_detail_frame_layout,fragment)
                .commit();
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}