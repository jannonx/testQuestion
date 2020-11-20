package com.guyuan.dear.work.goodssign.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityGoodsSignDetailBinding;
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

    private int contractID;

    public static void start(Context context, String title, int id) {
        Intent starter = new Intent(context, GoodsSignDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_ID, id);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        contractID = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        setTitleCenter(title);
        viewModel.getGoodsSignDetail(contractID);

        binding.goodsAllCommitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signAll(contractID);
            }
        });
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
    public void sign(int id) {
        if (viewModel != null) {
            viewModel.sign(id, contractID);
        }
    }
}