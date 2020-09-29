package com.guyuan.dear.focus.hr.view.hrStaffAttendDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityStaffAttendDetailBinding;
import com.guyuan.dear.utils.ConstantValue;

/**
 * 我的关注-人员概况-人员分类-点击人员头像-人员详情
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:41
 * @company: 固远（深圳）信息技术有限公司
 */
public class StaffAttendDetailActivity extends BaseToolbarActivity<ActivityStaffAttendDetailBinding, StaffAttendDetailViewModel> {


    public static void start(Context context, long staffId) {
        Intent starter = new Intent(context, StaffAttendDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_STAFF_ID, staffId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_staff_attend_detail;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("员工信息");
        Intent intent = getIntent();
        long id = intent.getLongExtra(ConstantValue.KEY_STAFF_ID, 0);
        StaffAttendDetailFragment fragment = StaffAttendDetailFragment.getInstance(id);
        getSupportFragmentManager().beginTransaction().add(R.id.activity_staff_status_info_frame_layout, fragment).commit();
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}