package com.guyuan.dear.focus.hr.view.pickStaffs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityPickStaffsBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;

/**
 * 选人界面
 * @author Leo
 */
public class PickStaffsActivity extends BaseToolbarActivity<ActivityPickStaffsBinding, PickStaffsViewModel> {

    /**
     *
     * @param context
     * @param reqCode
     * @param title
     * @param preSelect
     * @param hiddenStaffs
     * @param maxSelectCount
     */
    public static void startForResult(Activity context, int reqCode, String title, ArrayList<StaffBean> preSelect, ArrayList<StaffBean> hiddenStaffs, int maxSelectCount) {
        Intent starter = new Intent(context, PickStaffsActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putParcelableArrayListExtra(ConstantValue.KEY_PRE_SELECTED_STAFFS, preSelect);
        starter.putParcelableArrayListExtra(ConstantValue.KEY_HIDDEN_STAFFS, hiddenStaffs);
        starter.putExtra(ConstantValue.KEY_MAX_SELECT_COUNT, maxSelectCount);
        context.startActivityForResult(starter, reqCode);
    }

    /**
     *
     * @param context
     * @param reqCode
     * @param title
     * @param preSelect
     * @param hiddenStaffs
     * @param maxSelectCount
     */
    public static void startForResult(Fragment context, int reqCode, String title, ArrayList<StaffBean> preSelect, ArrayList<StaffBean> hiddenStaffs, int maxSelectCount) {
        Intent starter = new Intent(context.getContext(), PickStaffsActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putParcelableArrayListExtra(ConstantValue.KEY_PRE_SELECTED_STAFFS, preSelect);
        starter.putParcelableArrayListExtra(ConstantValue.KEY_HIDDEN_STAFFS, hiddenStaffs);
        starter.putExtra(ConstantValue.KEY_MAX_SELECT_COUNT, maxSelectCount);
        context.startActivityForResult(starter, reqCode);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_pick_staffs;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        ArrayList<StaffBean> preSelect = intent.getParcelableArrayListExtra(ConstantValue.KEY_PRE_SELECTED_STAFFS);
        ArrayList<StaffBean> hiddenStaffs = intent.getParcelableArrayListExtra(ConstantValue.KEY_HIDDEN_STAFFS);
        int maxSelect = intent.getIntExtra(ConstantValue.KEY_MAX_SELECT_COUNT, 10);
        getSupportFragmentManager().beginTransaction().add(R.id.activity_pick_staffs_frame_layout,
                PickStaffsFragment.getInstance(preSelect, hiddenStaffs, maxSelect)).commit();

    }

}