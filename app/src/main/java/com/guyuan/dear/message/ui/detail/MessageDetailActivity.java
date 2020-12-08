package com.guyuan.dear.message.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.example.mvvmlibrary.util.ActivityUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.message.data.MessageViewModel;
import com.guyuan.dear.utils.ConstantValue;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/26 12:14
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class MessageDetailActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, MessageViewModel> {

    public static void start(Context context, String title, int id) {
        Intent starter = new Intent(context, MessageDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int id = getIntent().getIntExtra(ConstantValue.KEY_ID, 0);
        setTitleCenter(title);
        MessageDetailFragment detailFragment = MessageDetailFragment.newInstance(id);
        ActivityUtils.addFragmentToActivity(fragmentManager, detailFragment, R.id.fragment_container,
                MessageDetailFragment.TAG);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}