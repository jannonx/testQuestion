package com.guyuan.dear.focus.sales.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;

import java.util.List;

/**
 * 我的关注-销售-进来主界面
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:41
 * @company: 固远（深圳）信息技术有限公司
 */
public class SalesHomeActivity  extends BaseTabActivity<ActivityBaseTabBinding, SalesHomeViewModel> {

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