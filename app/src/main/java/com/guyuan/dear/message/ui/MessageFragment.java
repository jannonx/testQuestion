package com.guyuan.dear.message.ui;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.message.adapter.MessageAdapter;
import com.guyuan.dear.message.data.MessageViewModel;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 13:47
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MessageFragment extends BaseListSearchFragment<MessageBean, ActivityBaseTabBinding, MessageViewModel> {

    public static final String TAG = "MessageFragment";

    public static MessageFragment newInstance() {

        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        MessageAdapter messageAdapter = new MessageAdapter(listData, R.layout.item_message);
        setDefaultAdapter(messageAdapter);
        if (viewModel != null) {
            viewModel.getMessageList(currentPage, searchContent);
        }
    }

    @Override
    protected void refresh() {
        currentPage = ConstantValue.FIRST_PAGE;
        viewModel.getMessageList(currentPage, searchContent);
    }

    @Override
    protected void loadMore() {
        viewModel.getMessageList(++currentPage, searchContent);
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}