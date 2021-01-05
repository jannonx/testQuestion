package com.guyuan.dear.focus.contract.view.exceptionList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.view.executingList.ExecutingListFragment;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author leoliao
 */
public class ExceptionContractListActivity extends BaseActionBarActivity {

    public static void start(Context context,long date) {
        Intent starter = new Intent(context, ExceptionContractListActivity.class);
        starter.putExtra(ConstantValue.KEY_DATE,date);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_exception_contract_list;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("执行异常");
        Intent intent = getIntent();
        long date = intent.getLongExtra(ConstantValue.KEY_DATE, System.currentTimeMillis());
        ExceptionContractListFragment fragment = ExceptionContractListFragment.getInstance(date);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_exception_contract_list_fragment_container, fragment)
                .commit();

    }
}