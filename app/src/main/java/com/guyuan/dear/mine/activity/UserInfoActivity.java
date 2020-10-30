package com.guyuan.dear.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.focus.purchase.fragment.ContractProgressDetailFragment;
import com.guyuan.dear.mine.data.MineViewModel;
import com.guyuan.dear.mine.fragment.UserInfoFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.RequestBody;


/**
 * @description: 我的--个人中心
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class UserInfoActivity extends BaseFileUploadActivity<ActivityWithToolbarBinding, MineViewModel> {


    public static void start(Context context, String title) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(intent);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        binding.toolbarContainer.titleTv.setText(title);
        UserInfoFragment mFragment = UserInfoFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, mFragment, R.id.fragment_container,
                UserInfoFragment.TAG);
        setFirstPhotoListener(mFragment);
    }

    @Override
    public MineViewModel getViewModel() {
        return viewModel;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }

    @Override
    protected void upLoadPicAndVideo(int currentType, Map<String, RequestBody> fileMap) {
        super.upLoadPicAndVideo(currentType, fileMap);
        LogUtils.showLog("upLoadPicAndVideo..11111");
        viewModel.uploadPic(fileMap);
        viewModel.getUploadImageEvent().observe(this, new Observer<ResultBean<List<UploadBean>>>() {
            @Override
            public void onChanged(ResultBean<List<UploadBean>> dataRefreshBean) {
                //获取图片url
                List<UploadBean> data = dataRefreshBean.getData();
                LogUtils.showLog("data=" + data.get(0).getUrl());
            }
        });
    }

}
