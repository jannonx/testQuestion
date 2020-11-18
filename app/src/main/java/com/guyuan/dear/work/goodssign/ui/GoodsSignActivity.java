package com.guyuan.dear.work.goodssign.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.goodssign.data.GoodsSignViewModel;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignListBean;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:26
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class GoodsSignActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, GoodsSignViewModel> {

    private GoodsSignFragment goodsSignFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, GoodsSignActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        goodsSignFragment = GoodsSignFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, goodsSignFragment,
                R.id.fragment_container, GoodsSignFragment.TAG);

        viewModel.getGoodsSignListMLD().observe(this, new Observer<GoodsSignListBean>() {
            @Override
            public void onChanged(GoodsSignListBean goodsSignListBean) {
                List<GoodsSignListBean.ContentBean> dataList = goodsSignListBean.getContent();
                if (dataList != null && dataList.size() > 0) {
                    goodsSignFragment.setListData(dataList);
                }
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}