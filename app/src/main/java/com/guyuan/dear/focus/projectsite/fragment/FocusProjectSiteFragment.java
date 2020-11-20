package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProjectSiteBinding;
import com.guyuan.dear.focus.projectsite.activity.ProjectReportClassifyActivity;
import com.guyuan.dear.focus.projectsite.bean.ProjectOverViewBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.LogUtils;

/**
 * @description: 我的关注--工程现场
 * @author: Jannonx
 * @since: 2020/11/17 11:54
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProjectSiteFragment extends BaseDataBindingFragment<FragmentFocusProjectSiteBinding, FocusProjectSiteViewModel>
        implements View.OnClickListener {

    public static final String TAG = FocusProjectSiteFragment.class.getSimpleName();

    public static FocusProjectSiteFragment newInstance() {
        Bundle args = new Bundle();
        FocusProjectSiteFragment fragment = new FocusProjectSiteFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_project_site;
    }

    @Override
    protected void initialization() {

        LogUtils.showLog("initialization");
        binding.clSiteExploration.setOnClickListener(this);
        binding.clGoodsCheck.setOnClickListener(this);
        binding.clSafeCheck.setOnClickListener(this);
        binding.clInstallationDebug.setOnClickListener(this);
        binding.clCustomerAcceptance.setOnClickListener(this);

        viewModel.getProjectSiteOverViewData();
        viewModel.getProjectSiteOverViewEvent().observe(getActivity(), new Observer<ProjectOverViewBean>() {
            @Override

            public void onChanged(ProjectOverViewBean data) {
                setData(data);
            }
        });
    }


    /**
     * 设置数据
     *
     * @param data
     */
    private void setData(ProjectOverViewBean data) {
        binding.tvSiteExploration.setText(data.getProspectNumber());
        binding.tvSafeCheck.setText(data.getSafetyNumber());
        binding.tvGoodsCheck.setText(data.getGoodsNumber());
        binding.tvInstallationDebug.setText(data.getInstallNumber());
        binding.tvCustomerAcceptance.setText(data.getCusNumber());
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        LogUtils.showLog("onClick");
        switch (v.getId()) {
            //现场勘查报告
            case R.id.cl_site_exploration:
                ProjectReportClassifyActivity.start(getContext(), ProjectReportType.TYPE_SITE_EXPLORATION);
                break;
            //货物清点报告
            case R.id.cl_goods_check:
                ProjectReportClassifyActivity.start(getContext(), ProjectReportType.TYPE_CHECK_GOODS);
                break;
            //安全排查报告
            case R.id.cl_safe_check:
                ProjectReportClassifyActivity.start(getContext(), ProjectReportType.TYPE_CHECK_SAFE);

                break;
            //安装调试报告
            case R.id.cl_installation_debug:
                ProjectReportClassifyActivity.start(getContext(), ProjectReportType.TYPE_INSTALLATION_DEBUG);
                break;
            //客户验收报告
            case R.id.cl_customer_acceptance:
                ProjectReportClassifyActivity.start(getContext(), ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE);
                break;
            default:
        }
    }
}
