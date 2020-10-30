package com.guyuan.dear.focus.security.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityFocusDeviceDetailBinding;
import com.guyuan.dear.focus.security.data.beans.SecurityContentBean;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/27 15:26
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityDetailActivity extends BaseToolbarActivity<ActivityFocusDeviceDetailBinding, BaseViewModel> {

    public static final String CONTENT = "content";
    public static final String TYPE = "type";
    public static final int SECURITY_SEARCH = 0x001;
    public static final int SECURITY_REPORT = 0x002;

    public static void starter(Context context, SecurityContentBean securityContentBean, int type) {
        Intent starter = new Intent(context, FocusSecurityDetailActivity.class);
        starter.putExtra(CONTENT, securityContentBean);
        starter.putExtra(TYPE, type);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_focus_security_detail;
    }
}
