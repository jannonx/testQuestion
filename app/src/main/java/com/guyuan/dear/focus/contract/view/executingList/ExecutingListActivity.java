package com.guyuan.dear.focus.contract.view.executingList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.view.finishedList.FinishedListFragment;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author leoliao
 */
public class ExecutingListActivity extends BaseActionBarActivity {

    public static void start(Context context,long date) {
        Intent starter = new Intent(context, ExecutingListActivity.class);
        starter.putExtra(ConstantValue.KEY_DATE,date);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_executing_list;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("正在执行");
        Intent intent = getIntent();
        long date = intent.getLongExtra(ConstantValue.KEY_DATE, System.currentTimeMillis());
        ExecutingListFragment fragment = ExecutingListFragment.getInstance(date);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_executing_contract_list_fragment_container, fragment)
                .commit();

    }
}