package com.guyuan.dear.message.ui;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageInfosBean;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.message.adapter.MessageAdapter;
import com.guyuan.dear.message.data.MessageViewModel;
import com.guyuan.dear.message.ui.detail.MessageDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 13:47
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MessageFragment extends BaseListSearchFragment<MessageBean, FragmentListSearchBinding, MessageViewModel> {
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
        searchBar.setHint("搜索内容");
        msgType = getArguments().getInt(ConstantValue.KEY_TYPE);
        MessageAdapter messageAdapter = new MessageAdapter(listData, R.layout.item_message);
        setDefaultAdapter(messageAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                if (listData.size() > 0) {
                    MessageBean bean = listData.get(i);
                    List<MessageInfosBean> infosBeans = bean.getMessageInfos();
                    if (infosBeans != null && infosBeans.size() > 0) {
                        recycleView.initCurrentItem();
                        MessageDetailActivity.start(getContext(), bean.getMsgTitle(),
                                infosBeans.get(0).getId());
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void refresh() {
        currentType = REFRESH;
        currentPage = ConstantValue.FIRST_PAGE;
        viewModel.getMessageList(currentPage, msgType, searchContent);
    }

    @Override
    protected void loadMore() {
        viewModel.getMessageList(++currentPage, msgType, searchContent);
    }

    @Override
    protected boolean isPullEnable() {
        return true;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}