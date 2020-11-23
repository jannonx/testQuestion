package com.guyuan.dear.focus.transport.ui.detail;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.library.baseAdapters.BR;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentTransportMessageBinding;
import com.guyuan.dear.dialog.TipDialogFragment;
import com.guyuan.dear.focus.transport.adapter.TransportMessageAdapter;
import com.guyuan.dear.focus.transport.data.TransportViewModel;
import com.guyuan.dear.utils.CommonUtils;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:21
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportMessageFragment extends BaseDataBindingFragment<FragmentTransportMessageBinding, TransportViewModel> {

    public static final String TAG = "TransportMessageFragment";

    public static TransportMessageFragment newInstance() {

        Bundle args = new Bundle();

        TransportMessageFragment fragment = new TransportMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getVariableId() {
        return BR.transportViewModel;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_transport_message;
    }

    @Override
    protected void initialization() {
        binding.transportReceivePhoneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.transportReceivePhoneTv.getText().toString();
                CommonUtils.makePhoneCall(getActivity(), phone);
            }
        });

        binding.transportSendPhoneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.transportSendPhoneTv.getText().toString();
                CommonUtils.makePhoneCall(getActivity(), phone);
            }
        });
    }


}