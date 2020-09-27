package com.guyuan.dear.focus.security.ui.type;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.security.data.FocusSecurityViewModel;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/27 14:18
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityTypeActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, FocusSecurityViewModel> {


    public static void start(Context context,int id, String title) {
        Intent starter = new Intent(context, FocusSecurityTypeActivity.class);
    //    starter.putExtra(RealTimeSecurityTypeListFragment.ID, id);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public void viewModuleCallBack(Object o) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}
