package com.guyuan.dear.work.matterapply.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.databinding.ToolbarBinding;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.databinding.ActivityMatterApplyDetailBinding;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.matterapply.data.MatterApplyViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/24 11:16
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class MatterApplyDetailActivity extends BaseToolbarActivity<ActivityMatterApplyDetailBinding, MatterApplyViewModel> {


    public static void start(Context context, String title, int requestID, int projectID) {
        Intent starter = new Intent(context, MatterApplyDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_ID, requestID);
        starter.putExtra(ConstantValue.PROJECT_ID, projectID);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        int projectID = getIntent().getIntExtra(ConstantValue.PROJECT_ID, 0);
        setTitleCenter(title);
        if (viewModel != null) {
            viewModel.getMatterDetail(id);
            CommonUtils.getContractStatus(BaseApiService.ID, String.valueOf(projectID));
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_matter_apply_detail;
    }

    @Override
    protected int getVariableId() {
        return BR.matterApplyViewModel;
    }
}