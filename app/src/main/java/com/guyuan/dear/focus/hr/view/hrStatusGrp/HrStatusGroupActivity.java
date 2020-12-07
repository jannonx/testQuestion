package com.guyuan.dear.focus.hr.view.hrStatusGrp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityHrGroupContentBinding;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 我的关注-人员-人员概况-人员分类（按出勤状况）
 *
 * @author leoliao
 */
public class HrStatusGroupActivity extends BaseToolbarActivity<ActivityHrGroupContentBinding, HrStatusGrpViewModel> {

    /**
     * 显示当月异常人员
     * @param grpType {@link HrStatusGroup#GRP_TYPE_NORMAL},{@link HrStatusGroup#GRP_TYPE_LATE},{@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY},
     *                {@link HrStatusGroup#GRP_TYPE_ABSENT},{@link HrStatusGroup#GRP_TYPE_ON_LEAVE}
     */
    public static void start(Context context, String title, int grpType) {
        Intent starter = new Intent(context, HrStatusGroupActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_GRP_TYPE, grpType);
        context.startActivity(starter);
    }

    /**
     * 显示特定月份的异常人员，因为用的网络接口不一样，这里需要分开。
     * @param grpType {@link HrStatusGroup#GRP_TYPE_NORMAL},{@link HrStatusGroup#GRP_TYPE_LATE},{@link HrStatusGroup#GRP_TYPE_LEAVE_EARLY},
     *                {@link HrStatusGroup#GRP_TYPE_ABSENT},{@link HrStatusGroup#GRP_TYPE_ON_LEAVE}
     */
    public static void start(Context context, String title, int grpType, long date) {
        Intent starter = new Intent(context, HrStatusGroupActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_DATE, date);
        starter.putExtra(ConstantValue.KEY_GRP_TYPE, grpType);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_hr_group_content;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        int grpType = getIntent().getIntExtra(ConstantValue.KEY_GRP_TYPE, 0);
        long date = getIntent().getLongExtra(ConstantValue.KEY_DATE, -1);
        HrStatusGroupFragment fragment = null;
        if (date >= 0) {
            fragment = HrStatusGroupFragment.getInstance(grpType, date);
        } else {
            fragment = HrStatusGroupFragment.getInstance(grpType);
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_hr_grp_content_frame_layout, fragment)
                .commit();
    }


}