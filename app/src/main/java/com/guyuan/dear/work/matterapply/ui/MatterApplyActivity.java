package com.guyuan.dear.work.matterapply.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.device.data.beans.FactoryRealTimeBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.matterapply.data.MatterApplyViewModel;
import com.guyuan.dear.work.matterapply.data.bean.MatterApplyListBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 18:43
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class MatterApplyActivity extends BaseTabActivity<ActivityBaseTabBinding,
        MatterApplyViewModel> implements MatterApplyViewModel.MatterApplyViewModelListener {

    private MatterApplyFragment applyFragment;
    private MatterAppliedFragment appliedFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, MatterApplyActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.work_matter_apply_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        applyFragment = MatterApplyFragment.newInstance();
        appliedFragment = MatterAppliedFragment.newInstance();
        fragmentList.add(applyFragment);
        fragmentList.add(appliedFragment);
        return fragmentList;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        if (viewModel != null) {
            setObserver();
        }
    }


    private void setObserver() {
        viewModel.setListener(this);
        viewModel.getMatterApplyListMLD().observe(this, new Observer<MatterApplyListBean>() {
            @Override
            public void onChanged(MatterApplyListBean matterApplyListBean) {
                appliedFragment.setListData(matterApplyListBean.getContent());
            }
        });
    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.selector_material_apply);
        tabDrawableList.add(R.drawable.selector_material_applied);
        return tabDrawableList;
    }

    @Override
    public void applySuccess() {
        applyFragment.clearData(MatterApplyFragment.CLEAR_ALL);
        binding.baseVp.setCurrentItem(1);
    }
}