package com.guyuan.dear.focus.contract.view.contractList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityContractListBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 我的关注-销售-合同概况-合同列表
 *
 * @author leoliao
 */
public class ContractListActivity extends BaseToolbarActivity<ActivityContractListBinding, ContractListViewModel> {

    /**
     *
     * @param context
     * @param title
     * @param contractType {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_EXCEPTION_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_EXECUTING_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_FINISHED_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_NEW_CONTRACTS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_PRE_ANNUAL_DELIVERS}
     * {@link com.guyuan.dear.focus.contract.bean.BaseContractBean#CONTRACT_TYPE_UNFINISHED_CONTRACTS}
     * @param date
     */
    public static void start(Context context, String title,int contractType,long date) {
        Intent starter = new Intent(context, ContractListActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_CONTRACT_TYPE,contractType);
        starter.putExtra(ConstantValue.KEY_DATE,date);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract_list;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        int contractType = intent.getIntExtra(ConstantValue.KEY_CONTRACT_TYPE,0);
        long date= intent.getLongExtra(ConstantValue.KEY_DATE,System.currentTimeMillis());
        setTitleCenter(title);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_contract_list_frame_layout,
                        ContractListFragment.getInstance(contractType,date))
                .commit();

    }


}