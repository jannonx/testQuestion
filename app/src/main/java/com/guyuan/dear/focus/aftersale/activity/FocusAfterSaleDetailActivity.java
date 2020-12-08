package com.guyuan.dear.focus.aftersale.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.aftersale.fragemnt.FocusAfterSaleDetailFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.RequestBody;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 14:43
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class FocusAfterSaleDetailActivity extends BaseFileUploadActivity<ActivityWithToolbarBinding, FocusAfterSaleViewModel> {

    public static void start(Context context, AfterSaleBean data) {
        Intent intent = new Intent(context, FocusAfterSaleDetailActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, data);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        AfterSaleBean bean = (AfterSaleBean) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);
        binding.toolbarContainer.titleTv.setText(bean.getSectionType().getDes()+"详情");
        FocusAfterSaleDetailFragment mFragment = FocusAfterSaleDetailFragment.newInstance(bean);
        setFirstPhotoListener(mFragment);
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                FocusAfterSaleDetailFragment.TAG);
    }

    @Override
    public FocusAfterSaleViewModel getViewModel() {
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