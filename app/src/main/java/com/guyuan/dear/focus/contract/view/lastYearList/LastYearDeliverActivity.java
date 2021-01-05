package com.guyuan.dear.focus.contract.view.lastYearList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author leoliao
 */
public class LastYearDeliverActivity extends BaseActionBarActivity {

    public static void start(Context context, long date) {
        Intent starter = new Intent(context, LastYearDeliverActivity.class);
        starter.putExtra(ConstantValue.KEY_DATE, date);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_last_year_deliver;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("上年交付");
        Intent intent = getIntent();
        long date = intent.getLongExtra(ConstantValue.KEY_DATE, System.currentTimeMillis());
        LastYearDeliverFragment fragment = LastYearDeliverFragment.getInstance(date);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_last_year_deliver_fragment_container, fragment)
                .commit();
    }
}