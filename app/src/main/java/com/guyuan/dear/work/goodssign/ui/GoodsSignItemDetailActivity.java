package com.guyuan.dear.work.goodssign.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityGoodsSignItemDetailBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.goodssign.data.GoodsSignViewModel;
import com.guyuan.dear.work.goodssign.data.bean.GoodsDetailBean;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 20:47
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class GoodsSignItemDetailActivity extends BaseToolbarActivity<ActivityGoodsSignItemDetailBinding, GoodsSignViewModel> {

    public static void start(Context context, String title, int id) {
        Intent starter = new Intent(context, GoodsSignItemDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        setTitleCenter(title);
        viewModel.getGoodsDetail(id);

        binding.goodsDetailCommitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.sign(id);
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_goods_sign_item_detail;
    }

    @Override
    protected int getVariableId() {
        return BR.goodsSignViewModel;
    }
}