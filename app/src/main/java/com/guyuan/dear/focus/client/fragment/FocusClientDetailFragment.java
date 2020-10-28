package com.guyuan.dear.focus.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;

import com.guyuan.dear.databinding.FragmentFoucsClientDetailBinding;
import com.guyuan.dear.focus.client.activity.FocusClientDetailActivity;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.RequestBody;

import static com.guyuan.dear.utils.ConstantValue.FIRST_PAGE;
import static com.guyuan.dear.utils.ConstantValue.PAGE_SIZE;

/**
 * @description: 我的关注--客户详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientDetailFragment extends BaseDataBindingFragment<FragmentFoucsClientDetailBinding, FocusClientViewModel> {

    public static final String TAG = "FocusClientDetailFragment";
    private FocusClientViewModel viewModel;
    private FollowStatusFragment followStatusFragment;
    private BasicInfoFragment basicInfoFragment;


    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private ClientCompanyBean clientData;

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
        initView();
        initData();
    }

    private void initData() {
        Bundle arguments = getArguments();
        clientData = (ClientCompanyBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        viewModel.getClientBasicInfo(clientData.getId());
        viewModel.getFollowCommentList(getListRequestBody(FIRST_PAGE));
        viewModel.getClientBasicEvent().observe(getActivity(), new Observer<ResultBean<ClientCompanyBean>>() {
            @Override
            public void onChanged(ResultBean<ClientCompanyBean> dataRefreshBean) {

            }
        });
        viewModel.getFollowListEvent().observe(getActivity(), new Observer<ResultBean<RefreshBean<CommentsBean>>>() {
            @Override
            public void onChanged(ResultBean<RefreshBean<CommentsBean>> dataRefreshBean) {

            }
        });
    }

    private RequestBody getListRequestBody(int pageNum) {
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
        filtersBean.setId(clientData.getId());
        body.setFilters(filtersBean);
        body.setPageNum(pageNum);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    private void initView() {
        List<Fragment> tabFragmentList = new ArrayList<>();
        followStatusFragment = FollowStatusFragment.newInstance();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            FocusClientDetailActivity activity = (FocusClientDetailActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
