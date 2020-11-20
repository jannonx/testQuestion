package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusInstallationDebugAllBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.projectsite.activity.FocusSiteExplorationDetailActivity;
import com.guyuan.dear.focus.projectsite.adapter.ProjectInstallAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ProjectReportAdapter;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteStatusBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 我的关注--工程现场--货物清点报告--详情页面--到货清单
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallationDebugAllFragment extends BaseDataBindingFragment<FragmentFocusInstallationDebugAllBinding, FocusProjectSiteViewModel> {

    public static final String TAG = InstallationDebugAllFragment.class.getSimpleName();
    private List<InstallDebugBean> listData = new ArrayList<>();

    private SiteExploreBean detailData;
    private BaseRecyclerViewAdapter adapter;

    public static InstallationDebugAllFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        InstallationDebugAllFragment fragment = new InstallationDebugAllFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_installation_debug_all;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        detailData = (SiteExploreBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);

        ProjectInstallAdapter checkGoodsAdapter = new ProjectInstallAdapter(getContext(),
                listData, R.layout.item_install_project);
        adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                InstallDebugBean installDebugBean = listData.get(position);
                detailData.setId(installDebugBean.getId());
                FocusSiteExplorationDetailActivity.start(getContext(), detailData);
            }
        });

        viewModel.getInstallDebugDetailData(detailData.getId());

        viewModel.getInstallDebugDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                data.setProjectReportType(detailData.getProjectReportType());

                setDetailData(detailData = data);
            }
        });
    }


    private void setDetailData(SiteExploreBean detailProjectData) {
        binding.tvProjectName.setText(detailProjectData.getProjectName());
        //状态属性设置
        binding.tvProjectStatus.setText(detailProjectData.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(detailProjectData.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(detailProjectData.getStatusTextBg());
        int statusTextColor = detailProjectData.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));
        binding.tvProjectCode.setText(detailProjectData.getProjectNumber());
        binding.tvPerson.setText(detailProjectData.getName());

        binding.tvCompanyName.setText(detailProjectData.getCustomerName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getAddress());

        if (detailProjectData.getAppInstallDebugItemVOList() != null) {
            listData.addAll(detailProjectData.getAppInstallDebugItemVOList());
        }

        adapter.refreshData();
    }

    @Override
    protected int getVariableId() {
        return 0;
    }


}
