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
    //显示消息种类,1:显示警告消息、预警消息；2：正常消息、办公消息
    public static final String TAG = "MessageFragment";
    public static final int MESSAGE_WARN = 1;        //警告消息
    public static final int MESSAGE_PRE_WARN = 1;    //预警消息
    public static final int MESSAGE_COMMON = 2;      //普通消息
    public static final int MESSAGE_OFFICE = 2;      //办公消息
    private int msgType;

    public static MessageFragment newInstance(int msgType) {

        Bundle args = new Bundle();
        args.getInt(ConstantValue.KEY_TYPE, msgType);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        msgType = getArguments().getInt(ConstantValue.KEY_TYPE);
        MessageAdapter messageAdapter = new MessageAdapter(listData, R.layout.item_message);
        setDefaultAdapter(messageAdapter);
        if (viewModel != null) {
            viewModel.getMessageList(currentPage,msgType, searchContent);
        }
    }

    @Override
    protected void refresh() {
        currentPage = ConstantValue.FIRST_PAGE;
        viewModel.getMessageList(currentPage, msgType,searchContent);
    }

    @Override
    protected void loadMore() {
        viewModel.getMessageList(++currentPage, msgType,searchContent);
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