package com.guyuan.dear.focus.client.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;

import com.guyuan.dear.databinding.FragmentFoucsClientDetailBinding;
import com.guyuan.dear.focus.client.adapter.ClientPhoneHeaderAdapter;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ClientContactBean;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 我的关注--客户详情
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientDetailFragment extends BaseDataBindingFragment<FragmentFoucsClientDetailBinding, FocusClientViewModel> {

    public static final String TAG = FocusClientDetailFragment.class.getSimpleName();
    private BasicInfoFragment basicInfoFragment;

    private final static int START_INDEX = 0;
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private ClientCompanyBean clientData;

    private BaseRecyclerViewAdapter headerAdapter;
    private List<ClientContactBean> headerData = new ArrayList<>();

    public static FocusClientDetailFragment newInstance(ClientCompanyBean data) {
        Bundle bundle = new Bundle();
        FocusClientDetailFragment fragment = new FocusClientDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_foucs_client_detail;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        clientData = (ClientCompanyBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        initHeaderView();
        initViewPager();
        viewModel.getClientBasicInfo(clientData.getId());

        viewModel.getClientBasicEvent().observe(getActivity(), new Observer<ClientCompanyBean>() {
            @Override
            public void onChanged(ClientCompanyBean dataRefreshBean) {
                setCompanyData(dataRefreshBean);
            }
        });
    }

    private void setCompanyData(ClientCompanyBean dataRefreshBean) {
        basicInfoFragment.setData(dataRefreshBean);
        binding.tvClientName.setText(dataRefreshBean.getCusName());
        headerData.clear();
        headerData.addAll(dataRefreshBean.getList());
        headerAdapter.refreshData();

    }


    private void initHeaderView() {
        ClientPhoneHeaderAdapter adapter = new ClientPhoneHeaderAdapter(getContext(), headerData,
                R.layout.item_focus_client_phone);
        headerAdapter = new BaseRecyclerViewAdapter(adapter);
        binding.baseRecycleView.setAdapter(headerAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setLoadMoreEnabled(false);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        headerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    private void initViewPager() {
        List<Fragment> tabFragmentList = new ArrayList<>();
        FollowStatusFragment followStatusFragment = FollowStatusFragment.newInstance(clientData);
        basicInfoFragment = BasicInfoFragment.newInstance();

        tabFragmentList.add(followStatusFragment);
        tabFragmentList.add(basicInfoFragment);

        titleList = getResources().getStringArray(R.array.focus_client_tab);
        TabAdapter tabAdapter = new
                TabAdapter(getChildFragmentManager(), Arrays.asList(titleList), tabFragmentList, getContext(),
                getCustomViewId());

        binding.viewPager.setAdapter(tabAdapter);

        //设置TabLayout和ViewPager联动
        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        binding.tabLayout.setTabIndicatorFullWidth(false);
        binding.tabLayout.setupWithViewPager(binding.viewPager, false);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabUnselected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            View view = tabAdapter.getView();
            if (tab != null) {
                setContent(i, view);
                tab.setCustomView(view);
                if (i == START_INDEX) {
                    setTabSelect(tab);
                    tab.select();
                } else {
                    setTabUnselected(tab);
                }
            }
        }
    }


    private int getCustomViewId() {
        return R.layout.layout_tab_text;
    }

    protected void setTabSelect(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (selectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff, null));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333, null));
        }
        tv.setTextSize(15f);
    }

    protected void setTabUnselected(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (unSelectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333, null));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff, null));
        }
        tv.setTextSize(15f);
    }


    private void setContent(int position, View customView) {
        TextView tv = customView.findViewById(R.id.tv_tab_text);
        tv.setText(Arrays.asList(titleList).get(position));
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
