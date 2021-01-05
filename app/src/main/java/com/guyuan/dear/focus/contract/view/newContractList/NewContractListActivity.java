package com.guyuan.dear.focus.contract.view.newContractList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.view.lastYearList.LastYearDeliverFragment;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author leoliao
 */
public class NewContractListActivity extends BaseActionBarActivity {

    public static void start(Context context,long date) {
        Intent starter = new Intent(context, NewContractListActivity.class);
        starter.putExtra(ConstantValue.KEY_DATE,date);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_new_contract_list;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("今年新签");
        Intent intent = getIntent();
        long date = intent.getLongExtra(ConstantValue.KEY_DATE, System.currentTimeMillis());
        NewContractListFragment fragment = NewContractListFragment.getInstance(date);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_new_contract_list_recycler_view, fragment)
                .commit();

    }
}