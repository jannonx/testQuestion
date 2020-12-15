package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentExploreContentBinding;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ProjectInstallAdapter;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;


/**
 * @description: 我的关注--工程现场--客户验收报告--详情页面--验收记录
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class AcceptanceRecordFragment extends BaseDataBindingFragment<FragmentExploreContentBinding, FocusProjectSiteViewModel> {

    public static final String TAG = AcceptanceRecordFragment.class.getSimpleName();
    private List<InstallDebugBean> installDebugContentList = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;
    private View footerView;
    private TextView tvRecorder, tvRemark;
    private SiteExploreBean acceptanceData;

    public static AcceptanceRecordFragment newInstance() {
        Bundle bundle = new Bundle();
        AcceptanceRecordFragment fragment = new AcceptanceRecordFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_explore_content;
    }

    @Override
    protected void initialization() {

    }


    /**
     * 填充数据
     *
     * @param data
     */
    public void setRecordData(SiteExploreBean data) {
        acceptanceData = data;

        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProjectInstallAdapter installDebugAdapter = new ProjectInstallAdapter(getContext(),
                installDebugContentList, R.layout.item_install_project);
        adapter = new BaseRecyclerViewAdapter(installDebugAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        addContentFooterView();

    }

    private void addContentFooterView() {
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_acceptance_record, binding.baseRecycleView, false);
        tvRecorder = footerView.findViewById(R.id.tv_recorder);
        tvRemark = footerView.findViewById(R.id.tv_remark);
        LinearLayoutCompat llDocument = footerView.findViewById(R.id.ll_document);
        BaseRecyclerView imageRecyclerView = footerView.findViewById(R.id.image_recycleView);

        tvRecorder.setText(acceptanceData.getPersonLiableName());
        tvRemark.setText(acceptanceData.getCheckRemark());
        llDocument.setVisibility((acceptanceData.getImgUrlList() == null
                || acceptanceData.getImgUrlList().size() == 0) ? View.GONE : View.VISIBLE);

        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                acceptanceData.getImgUrlList(), R.layout.item_explorate_image);
        BaseRecyclerViewAdapter imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        imageRecyclerView.setAdapter(imageAdapter);
        imageRecyclerView.setPullRefreshEnabled(false);
        imageRecyclerView.setLoadMoreEnabled(false);

        adapter.addFooterView(footerView);
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
