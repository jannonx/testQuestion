package com.guyuan.dear.work.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentWorkClientDetailBinding;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.client.activity.WorkClientDetailActivity;
import com.guyuan.dear.work.client.data.WorkClientViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @description: 我的关注--客户详情
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkClientDetailFragment extends BaseDataBindingFragment<FragmentWorkClientDetailBinding, WorkClientViewModel> {

    public static final String TAG = WorkClientDetailFragment.class.getSimpleName();
    private FollowStatusFragment followStatusFragment;
    private BasicInfoFragment basicInfoFragment;
    private ClientCompanyBean clientData;

    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;


    public static WorkClientDetailFragment newInstance(ClientCompanyBean data) {
        Bundle bundle = new Bundle();
        WorkClientDetailFragment fragment = new WorkClientDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_client_detail;
    }

    @Override
    protected void initialization() {
        initView();
        initData();

    }

    private void initData() {

        viewModel.getClientBasicInfo(clientData.getId());

        viewModel.getClientBasicEvent().observe(getActivity(), new Observer<ClientCompanyBean>() {
            @Override
            public void onChanged(ClientCompanyBean dataRefreshBean) {
                setCompanyData(dataRefreshBean);
            }
        });
        binding.tvEditFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editClientFollowComment();
            }
        });
    }


    /**
     * 设置数据
     */
    private void setCompanyData(ClientCompanyBean dataRefreshBean) {
        basicInfoFragment.setData(dataRefreshBean);
        binding.tvClientName.setText(dataRefreshBean.getCusName());
        binding.tvSalesman.setText(dataRefreshBean.getSalesman());
        binding.tvLatestTime.setText(dataRefreshBean.getFollowUpTime());
    }

    /**
     * 添加客户评论
     */
    private void editClientFollowComment() {

        FollowCommentDialog.show(getActivity(), new FollowCommentDialog.OnFollowClickListener() {
            @Override
            public void onClick(String content) {
                viewModel.postClientFollowUp(clientData.getId(), content);
            }
        });
        viewModel.getFollowClientEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer dataRefreshBean) {
                ToastUtils.showShort(getContext(), "评论成功!");
                followStatusFragment.refresh();
                //刷新列表
            }
        });
    }


    private void initView() {
        Bundle arguments = getArguments();
        clientData = (ClientCompanyBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);

        List<Fragment> tabFragmentList = new ArrayList<>();
        followStatusFragment = FollowStatusFragment.newInstance(clientData);
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
                if (i == startPosition) {
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
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        }
        tv.setTextSize(15f);
    }

    protected void setTabUnselected(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (unSelectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
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
