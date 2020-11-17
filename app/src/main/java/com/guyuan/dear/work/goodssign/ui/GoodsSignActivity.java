package com.guyuan.dear.work.goodssign.ui;

import android.os.Bundle;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.work.goodssign.data.GoodsSignViewModel;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:26
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class GoodsSignActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, GoodsSignViewModel> {
    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}