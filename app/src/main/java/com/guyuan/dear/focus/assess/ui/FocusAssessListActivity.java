package com.guyuan.dear.focus.assess.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithoutToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessListBean;
import com.guyuan.dear.scan.util.ConstanceValues;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/28 17:04
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusAssessListActivity extends BaseToolbarActivity<ActivityWithoutToolbarBinding, FocusAssessViewModel> {

    private FocusAssessListFragment listFragment;

    public static void start(Context context, String content) {
        Intent starter = new Intent(context, FocusAssessListActivity.class);
        starter.putExtra(ConstantValue.KEY_CONTENT, content);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
    //    setObserver();
        String searchContent = getIntent().getStringExtra(ConstantValue.KEY_CONTENT);
        listFragment = FocusAssessListFragment.newInstance(FocusAssessListFragment.OVERVIEW_SEARCH, searchContent);
        ActivityUtils.addFragmentToActivity(fragmentManager, listFragment, R.id.fragment_container,
                FocusAssessListFragment.TAG);
    }


    private void setObserver() {
        if (viewModel != null) {
            viewModel.assessOverviewSearchListBean.observe(this, new Observer<AssessListBean>() {
                @Override
                public void onChanged(AssessListBean assessListBean) {
                    listFragment.setListData(assessListBean.getContent());
                }
            });
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}