package com.guyuan.dear.focus.client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.databinding.FragmentBasicInfoBinding;
import com.guyuan.dear.focus.client.adapter.ClientListAdapter;
import com.guyuan.dear.focus.client.adapter.ClientPhoneAdapter;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 14:31
 * @company: 固远（深圳）信息技术有限公司
 */
public class BasicInfoFragment extends BaseDataBindingFragment<FragmentBasicInfoBinding> {

    public static final String TAG = "BasicInfoFragment";
    private FocusClientViewModel viewModel;

    public static BasicInfoFragment newInstance() {
        Bundle args = new Bundle();
        BasicInfoFragment fragment = new BasicInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_basic_info;
    }

    @Override
    protected void initialization() {
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_client_basic_info, null);
        List<SimpleTabBean> listData = new ArrayList<>();
        ClientPhoneAdapter listAdapter = new ClientPhoneAdapter(getContext(), listData,
                R.layout.item_client_phone);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(listAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        adapter.addFooterView(footerView);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
}
