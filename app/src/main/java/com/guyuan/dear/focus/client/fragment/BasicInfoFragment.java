package com.guyuan.dear.focus.client.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentBasicInfoBinding;
import com.guyuan.dear.focus.client.adapter.ClientPhoneAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ClientContactBean;
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
public class BasicInfoFragment extends BaseDataBindingFragment<FragmentBasicInfoBinding, FocusClientViewModel> {

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
        ClientCompanyBean clientCompanyBean = new ClientCompanyBean();
        List<ClientContactBean> childList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClientContactBean contactBean = new ClientContactBean();
            contactBean.setMailbox("ClientContactBean" + i);
            contactBean.setPhone("18123985606");
            childList.add(contactBean);
        }
        clientCompanyBean.setList(childList);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_client_basic_info, null);
        ClientPhoneAdapter listAdapter = new ClientPhoneAdapter(getContext(), clientCompanyBean.getList(),
                R.layout.item_client_phone);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(listAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setLoadMoreEnabled(false);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        adapter.addFooterView(footerView);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + childList.get(position).getPhone()));
                startActivity(dialIntent);
            }
        });
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
