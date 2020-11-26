package com.guyuan.dear.focus.aftersale.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.ActivityFocusAfterSaleDetailBinding;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.aftersale.fragemnt.FocusAfterSaleDetailFragment;
import com.guyuan.dear.focus.projectsite.activity.FocusSiteExplorationDetailActivity;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.focus.projectsite.fragment.FocusSiteExplorationDetailFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 14:43
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleDetailActivity extends BaseFileUploadActivity<ActivityWithToolbarBinding, FocusProjectSiteViewModel> {

    public static void start(Context context, AfterSaleBean data) {
        Intent intent = new Intent(context, FocusSiteExplorationDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        AfterSaleBean bean = (AfterSaleBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText("详情");
//        LogUtils.showLog("initFragment="+bean.getModuleType().getDes());
        FocusAfterSaleDetailFragment mFragment = FocusAfterSaleDetailFragment.newInstance(null);
        setFirstPhotoListener(mFragment);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                FocusAfterSaleDetailFragment.TAG);
    }

    @Override
    public FocusProjectSiteViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void upLoadPicAndVideo(int currentType, Map<String, RequestBody> fileMap) {
        super.upLoadPicAndVideo(currentType, fileMap);
        viewModel.uploadPic(fileMap);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }


}