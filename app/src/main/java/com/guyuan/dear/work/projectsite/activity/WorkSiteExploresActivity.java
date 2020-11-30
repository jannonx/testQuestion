package com.guyuan.dear.work.projectsite.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;
import com.guyuan.dear.work.projectsite.fragment.CheckGoodsFragment;
import com.guyuan.dear.work.projectsite.fragment.SiteExplorationFragment;
import com.guyuan.dear.work.projectsite.fragment.WorkProjectReportListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.RequestBody;

/**
 * @description: 我的工作--勘查现场/
 * @author: 许建宁
 * @since: 2020/11/18 10:27
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class WorkSiteExploresActivity extends BaseFileUploadActivity<ActivityWithToolbarBinding, WorkProjectSiteViewModel> {

    public static void start(Context context, SiteExploreBean data) {
        Intent intent = new Intent(context, WorkSiteExploresActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        SiteExploreBean data = (SiteExploreBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText(data.getProjectReportType().getDes());
        SiteExplorationFragment mFragment = SiteExplorationFragment.newInstance(data);
        setFirstPhotoListener(mFragment);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                SiteExplorationFragment.TAG);
    }

    @Override
    public WorkProjectSiteViewModel getViewModel() {
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