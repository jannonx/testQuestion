package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.assess.data.HrHomeViewModel;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 廖华凯
 * @description : 我的关注-人员总界面
 * @since: 2020/9/16 17:12
 * @company : 固远（深圳）信息技术有限公司
 **/
public class HrHomeActivity extends BaseTabActivity<ActivityBaseTabBinding, HrHomeViewModel> {

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, HrHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        List<String> list = new ArrayList<String>() {
            {
                add("人员概况");
                add("人员异常");
                add("人员详情");
            }
        };
        return list;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<Fragment>(){
            {
                add(HrSummaryFragment.getInstance());
                add(HrAbnormalityFragment.getInstance());
                add(HrStructFragment.getInstance());
            }
        };
        return fragments;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
    }

    @Override
    protected List<Integer> setTabSelectedIconList() {
        List<Integer> list = new ArrayList<Integer>() {
            {
                add(R.mipmap.device_maintain);
                add(R.mipmap.device_fix);
                add(R.mipmap.device_maintain);
                add(R.mipmap.device_fix);
            }

        };
        return list;
    }

    @Override
    protected List<Integer> setTabUnselectedIconList() {
        List<Integer> list = new ArrayList<Integer>() {
            {
                add(R.mipmap.device_maintain);
                add(R.mipmap.device_fix);
                add(R.mipmap.device_maintain);
                add(R.mipmap.device_fix);
            }

        };
        return list;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}