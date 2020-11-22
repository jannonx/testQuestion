package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentFocusProjectSiteBinding;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.databinding.FragmentWorkCheckGoodImgBinding;
import com.guyuan.dear.focus.projectsite.adapter.CheckContentAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.ProjectModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.projectsite.adapter.CheckGoodsAdapter;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场--清点货物
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsFragment extends BaseDataBindingFragment<FragmentWorkCheckGoodImgBinding, WorkProjectSiteViewModel> {

    public static final String TAG = CheckGoodsFragment.class.getSimpleName();
    private  SiteExploreBean detailData;
    private List<CheckGoodsBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;
    public static CheckGoodsFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        CheckGoodsFragment fragment = new CheckGoodsFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_check_good_img;
    }

    @Override
    protected void initialization() {
        detailData = (SiteExploreBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);


        binding.clGoodsList.setVisibility(detailData.getCheckGoodsSatisfyType()== CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
        ?View.GONE:View.VISIBLE);



        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        CheckGoodsAdapter checkContentAdapter = new CheckGoodsAdapter(getContext(),
                listData, R.layout.item_goods_list, ProjectModuleType.TYPE_WORK);
        adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ProjectCheckConfirmDialog.show(getActivity(),listData.get(position),null);
            }
        });

        viewModel.getCheckGoodDetailData(detailData.getId());

        viewModel.getCheckGoodDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                    setDetailData(data);
            }
        });
    }

    private void setDetailData(SiteExploreBean detailProjectData) {
        detailProjectData.setProjectReportType(detailData.getProjectReportType());

        binding.tvProjectName.setText(detailProjectData.getSendGoodsNumber());
        binding.labelProjectCode.setText("项目名称：");
        binding.tvProjectCode.setText(detailProjectData.getProjectName());
        binding.labelCheckName.setText("项目编号：");
        binding.tvCheckName.setText(detailProjectData.getProjectNumber());


        //状态属性设置
        binding.tvProjectStatus.setText(detailProjectData.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(detailProjectData.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(detailProjectData.getStatusTextBg());
        int statusTextColor = detailProjectData.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));


        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());

        List<CheckGoodsBean> checkGoodsListData = detailProjectData.getCheckTransportProjectListVO();
        binding.tvTotalGoods.setText("共计货物："+(checkGoodsListData!=null?checkGoodsListData.size():0)+"件");
        listData.addAll(checkGoodsListData);
        adapter.refreshData();
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

}
