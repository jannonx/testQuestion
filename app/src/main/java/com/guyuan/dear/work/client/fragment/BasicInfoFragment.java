package com.guyuan.dear.work.client.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentBasicInfoBinding;
import com.guyuan.dear.focus.client.adapter.ClientPhoneAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ClientContactBean;
import com.guyuan.dear.work.client.data.WorkClientViewModel;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作-客户详情--基础信息
 * @author: Jannonx
 * @since: 2020/11/4 14:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class BasicInfoFragment extends BaseDataBindingFragment<FragmentBasicInfoBinding, WorkClientViewModel> {

    public static final String TAG = "BasicInfoFragment";
    private List<ClientContactBean> listData = new ArrayList<>();
    private View footerView;
    private BaseRecyclerViewAdapter adapter;

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
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_client_basic_info, null);
        ClientPhoneAdapter listAdapter = new ClientPhoneAdapter(getContext(), listData,
                R.layout.item_client_phone);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setLoadMoreEnabled(false);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        adapter.addFooterView(footerView);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listData.get(position).getPhone()));
                startActivity(dialIntent);
            }
        });
    }

    public void setData(ClientCompanyBean dataRefreshBean) {
        listData.clear();
        listData.addAll(dataRefreshBean.getList());
        adapter.refreshData();

        TextView address = footerView.findViewById(R.id.tv_client_address);
        TextView salesman = footerView.findViewById(R.id.tv_salesman);
        TextView remark = footerView.findViewById(R.id.tv_remark);

        address.setText(dataRefreshBean.getCusAddress());
        salesman.setText(dataRefreshBean.getSalesman());
        remark.setText(dataRefreshBean.getRemarks());
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
