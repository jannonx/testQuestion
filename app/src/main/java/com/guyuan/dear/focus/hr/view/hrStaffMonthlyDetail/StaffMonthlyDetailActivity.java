package com.guyuan.dear.focus.hr.view.hrStaffMonthlyDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 显示人员考勤情况，可以切换月份
 *
 * @author 廖华凯
 */
public class StaffMonthlyDetailActivity extends BaseActionBarActivity {

    public static void start(Context context, String title, int staffId) {
        Intent starter = new Intent(context, StaffMonthlyDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_STAFF_ID, staffId);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_staff_monthly_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        int id = intent.getIntExtra(ConstantValue.KEY_STAFF_ID, 0);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_staff_monthly_detail_fragment_container, StaffMonthlyDetailFragment.getInstance(id))
                .commit();

    }
}