package com.guyuan.dear.focus.contract.view.contractExptDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityContractExcptDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author 廖华凯
 * 我的关注-合同-合同异常列表-合同异常详情
 */
public class ContractExcptDetailActivity extends BaseToolbarActivity<ActivityContractExcptDetailBinding,ContractExcptDetailViewModel> {

    public static void start(Context context,String title,String contractId) {
        Intent starter = new Intent(context, ContractExcptDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        starter.putExtra(ConstantValue.KEY_CONTRACT_ID,contractId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract_excpt_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        String contractId = intent.getStringExtra(ConstantValue.KEY_CONTRACT_ID);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_contract_excpt_detail_frame_layout,ContractExcptDetailFragment.getInstance(contractId))
                .commit();

    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}