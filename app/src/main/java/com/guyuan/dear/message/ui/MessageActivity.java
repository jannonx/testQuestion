package com.guyuan.dear.message.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.message.data.MessageViewModel;
import com.guyuan.dear.message.data.bean.MessageListBean;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 14:44
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class MessageActivity extends BaseToolbarActivity<ActivityWithToolbarBinding, MessageViewModel> {

    private MessageFragment messageFragment;

    public static void start(Context context, String title, int msgType) {
        Intent starter = new Intent(context, MessageActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_TYPE, msgType);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        int msgType = getIntent().getIntExtra(ConstantValue.KEY_TYPE, 0);
        setTitleCenter(title);
        messageFragment = MessageFragment.newInstance(msgType);
        ActivityUtils.addFragmentToActivity(fragmentManager, messageFragment, R.id.fragment_container, MessageFragment.TAG);
        setObserver();
    }

    private void setObserver() {
        if (viewModel != null) {
            viewModel.getMessageListMLD().observe(this, new Observer<MessageListBean>() {
                @Override
                public void onChanged(MessageListBean messageBeans) {
                    messageFragment.setListData(messageBeans.getContent());
                }
            });
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_with_toolbar;
    }
}