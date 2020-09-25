package com.guyuan.dear.focus.security.ui;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.security.data.FocusSecurityViewModel;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/24 16:50
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityActivity extends BaseTabActivity<ActivityBaseTabBinding, FocusSecurityViewModel> implements TabLayoutHelper.TabLayoutListener {
    @Override
    protected List<String> getTitles() {
        return null;
    }

    @Override
    protected List<Fragment> getFragments() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected List<Integer> setTabIconList() {
        return null;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }


}
