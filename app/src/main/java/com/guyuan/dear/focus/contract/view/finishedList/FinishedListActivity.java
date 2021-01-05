package com.guyuan.dear.focus.contract.view.finishedList;

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
public class FinishedListActivity extends BaseActionBarActivity {

    public static void start(Context context,long date) {
        Intent starter = new Intent(context, FinishedListActivity.class);
        starter.putExtra(ConstantValue.KEY_DATE,date);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_finished_list;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("已经完成");
        Intent intent = getIntent();
        long date = intent.getLongExtra(ConstantValue.KEY_DATE, System.currentTimeMillis());
        FinishedListFragment fragment = FinishedListFragment.getInstance(date);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.finished_list_activity_fragment_container, fragment)
                .commit();
    }
}