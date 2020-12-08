package com.guyuan.dear.focus.contract.view.contractSearchList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;

import static com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment.STATUS_TYPE_TOTAL;

/**
 * 根据客户名称或合同编号查找合同清单
 *
 * @author 廖华凯
 */
public class ContractSearchListActivity extends BaseActionBarActivity {

    private String clientNameOrContractNo;
    private String title;
    private int searchType;

    /**
     *
     * @param context
     * @param title
     * @param clientNameOrContractNumber
     * @param type
     * {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_EXCEPTION}
     * {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_TOTAL}
     */
    public static void start(Context context, @Nullable String title, String clientNameOrContractNumber,int type) {
        Intent starter = new Intent(context, ContractSearchListActivity.class);
        starter.putExtra(ConstantValue.KEY_KEY_WORD, clientNameOrContractNumber);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_SEARCH_TYPE,type);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract_search_list;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        if (!TextUtils.isEmpty(title)) {
            setTitleCenter(title);
        }
        searchType = intent.getIntExtra(ConstantValue.KEY_SEARCH_TYPE,STATUS_TYPE_TOTAL);
        clientNameOrContractNo = intent.getStringExtra(ConstantValue.KEY_KEY_WORD);
        if (!TextUtils.isEmpty(clientNameOrContractNo)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_contract_search_list_container_view, ContractSearchListFragment.getInstance(clientNameOrContractNo,searchType))
                    .commit();
        }

    }
}