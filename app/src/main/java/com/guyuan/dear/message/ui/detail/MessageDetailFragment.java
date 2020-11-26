package com.guyuan.dear.message.ui.detail;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMessageDetailBinding;
import com.guyuan.dear.message.data.MessageViewModel;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/26 12:15
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MessageDetailFragment extends BaseDataBindingFragment<FragmentMessageDetailBinding, MessageViewModel> {

    public static final String TAG = "MessageDetailFragment";

    public static MessageDetailFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(ConstantValue.KEY_ID, id);
        MessageDetailFragment fragment = new MessageDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return BR.messageVM;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_message_detail;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            int id = getArguments().getInt(ConstantValue.KEY_ID);
            if (viewModel != null) {
                viewModel.getMessageDetail(id);
            }
        }
    }
}