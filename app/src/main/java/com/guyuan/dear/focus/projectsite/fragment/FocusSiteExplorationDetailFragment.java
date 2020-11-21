package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusSiteExplorationBinding;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @description: 我的关注--工程现场--现场勘查报告--详情页面
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusSiteExplorationDetailFragment extends BaseDataBindingFragment<FragmentFocusSiteExplorationBinding, FocusProjectSiteViewModel>
        implements View.OnClickListener {

    public static final String TAG = FocusSiteExplorationDetailFragment.class.getSimpleName();

    private ProjectSiteStatusFragment statusFragment;
    private ExploreContentFragment planFragment;

    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private SiteExploreBean detailProjectData;

    private ProduceApplyDialog dialog;

    public static FocusSiteExplorationDetailFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        FocusSiteExplorationDetailFragment fragment = new FocusSiteExplorationDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_site_exploration;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        detailProjectData = (SiteExploreBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        initViewPager();
        getDataListByClassify();
    }


    /**
     * 根据报告类型请求数据
     */
    private void getDataListByClassify() {
        switch (detailProjectData.getProjectReportType()) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                viewModel.getSiteExploreDetailData(detailProjectData.getId());
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                viewModel.getCheckGoodDetailData(detailProjectData.getId());
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                viewModel.getCheckSafeDetailData(detailProjectData.getId());
                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
                viewModel.getInstallDebugDetailData(detailProjectData.getId());
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                viewModel.getCustomerAcceptanceDetailData(detailProjectData.getId());
                break;

            default:
        }

        viewModel.getSiteExploreDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
        viewModel.getCheckGoodDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
        viewModel.getCheckSafeDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
        viewModel.getInstallDebugDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
        viewModel.getCustomerAcceptanceDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setProduceData(data);
            }
        });
    }

    /**
     * 设置数据
     *
     * @param data 详情数据
     */
    private void setProduceData(SiteExploreBean data) {
        if (getActivity() == null) return;

        data.setProjectReportType(detailProjectData.getProjectReportType());
        detailProjectData = data;
        //设置审核数据
        planFragment.setCheckContentData(detailProjectData);
        switch (detailProjectData.getProjectReportType()) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                setSiteExplorationData(detailProjectData);
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                setCheckGoodsData(detailProjectData);
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                setCheckSafeData(detailProjectData);
                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
                setInstallationDebugData(detailProjectData);
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                setCustomerAcceptanceData(detailProjectData);
                break;

            default:
        }


        //状态属性设置
        binding.tvProjectStatus.setText(detailProjectData.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(detailProjectData.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(detailProjectData.getStatusTextBg());
        int statusTextColor = detailProjectData.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));


    }

    /**
     * @param detailProjectData
     */
    private void setSiteExplorationData(SiteExploreBean detailProjectData) {
        binding.tvProjectName.setText(detailProjectData.getProjectName());

        binding.tvProjectCode.setText(detailProjectData.getProjectNum());
        binding.tvCheckName.setText(detailProjectData.getName());

        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());
    }

    private void setCheckGoodsData(SiteExploreBean detailProjectData) {
        binding.tvProjectName.setText(detailProjectData.getSendGoodsNumber());
        binding.labelProjectCode.setText("项目名称：");
        binding.tvProjectCode.setText(detailProjectData.getProjectName());
        binding.labelCheckName.setText("项目编号：");
        binding.tvCheckName.setText(detailProjectData.getProjectNumber());


        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());
    }

    private void setCheckSafeData(SiteExploreBean detailProjectData) {

        binding.tvProjectName.setText(detailProjectData.getProjectName());


        binding.tvProjectCode.setText(detailProjectData.getProjectNum());
        binding.labelCheckName.setText("安全人员：");
        binding.tvCheckName.setText(detailProjectData.getName());

        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());


    }

    private void setInstallationDebugData(SiteExploreBean detailProjectData) {

    }


    private void setCustomerAcceptanceData(SiteExploreBean detailProjectData) {


    }


    @Override
    public void onClick(View v) {

    }


    private void initViewPager() {
        List<Fragment> tabFragmentList = new ArrayList<>();
        statusFragment = ProjectSiteStatusFragment.newInstance(detailProjectData);
        planFragment = ExploreContentFragment.newInstance(detailProjectData);

        tabFragmentList.add(statusFragment);
        tabFragmentList.add(planFragment);

        titleList = getResources().getStringArray(detailProjectData.getProjectReportTabArray());
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
