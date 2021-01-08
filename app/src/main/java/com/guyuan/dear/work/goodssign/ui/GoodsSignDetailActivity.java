package com.guyuan.dear.work.goodssign.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.databinding.ActivityGoodsSignDetailBinding;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.goodssign.adapter.GoodsSignDetailAdapter;
import com.guyuan.dear.work.goodssign.data.GoodsSignViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 20:23
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class GoodsSignDetailActivity extends BaseToolbarActivity<ActivityGoodsSignDetailBinding,
        GoodsSignViewModel> implements GoodsSignDetailAdapter.GoodsDetailListener {

    private int id;
    private int projectID;

    public static void start(Context context, String title, int id, int projectID) {
        Intent starter = new Intent(context, GoodsSignDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_ID, id);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.PROJECT_ID, projectID);
        context.startActivity(starter);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        projectID = getIntent().getIntExtra(ConstantValue.PROJECT_ID, 0);
        setTitleCenter(title);
        binding.goodsAllCommitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signAll(id);
            }
        });
        CommonUtils.getContractStatus(BaseApiService.PROJECT_ID, String.valueOf(projectID));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getGoodsSignDetail(id);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_goods_sign_detail;
    }

    @Override
    protected int getVariableId() {
        return BR.goodsSignViewModel;
    }

    @Override
    public void sign(int id, int receiveNum) {
        if (viewModel != null) {
            viewModel.sign(id, receiveNum, id);
        }
    }
}