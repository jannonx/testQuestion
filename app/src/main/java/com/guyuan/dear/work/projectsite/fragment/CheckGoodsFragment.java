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
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.adapter.CheckGoodsAdapter;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
    private SiteExploreBean detailData;
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


        binding.clGoodsList.setVisibility(detailData.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                ? View.GONE : View.VISIBLE);


        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        CheckGoodsAdapter checkContentAdapter = new CheckGoodsAdapter(getContext(),
                listData, R.layout.item_goods_list, ProjectModuleType.TYPE_WORK);
        adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        viewModel.getCheckGoodDetailData(detailData.getId());

        initListener();

    }

    private void initListener() {
        viewModel.getCheckGoodDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setDetailData(data);
            }
        });
        viewModel.getCommitCheckGoodInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                ToastUtils.showLong(getContext(), "提交成功");
                if (detailData.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING) {
                    viewModel.getCheckGoodDetailData(detailData.getId());
                } else {
                    getActivity().finish();
                }

            }
        });
        binding.tvActivateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCheckGoods();
            }
        });
    }

    /**
     * 确认到货及完成清点
     */
    private void confirmCheckGoods() {
        ProjectCheckConfirmDialog.show(getActivity(), detailData, new ProjectCheckConfirmDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(PostCheckInfo data) {
                String str = GsonUtil.objectToString(data);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), str);

                viewModel.postCheckGoodInfo(requestBody);
            }
        });
    }

    private void setDetailData(SiteExploreBean data) {
        data.setProjectReportType(detailData.getProjectReportType());
        detailData = data;

        binding.tvProjectName.setText(data.getSendGoodsNumber());
        binding.labelProjectCode.setText("项目名称：");
        binding.tvProjectCode.setText(data.getProjectName());
        binding.labelCheckName.setText("项目编号：");
        binding.tvCheckName.setText(data.getProjectNumber());


        //状态属性设置
        binding.tvProjectStatus.setText(data.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(data.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(data.getStatusTextBg());
        int statusTextColor = data.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));


        binding.tvCompanyName.setText(data.getCusName());
        binding.tvTime.setText(data.getCreateTime());
        binding.tvCompanyLocation.setText(data.getDestination());

        List<CheckGoodsBean> checkGoodsListData = data.getCheckTransportProjectListVO();
        binding.tvTotalGoods.setText("共计货物：" + (checkGoodsListData != null ? checkGoodsListData.size() : 0) + "件");
        listData.addAll(checkGoodsListData);
        adapter.refreshData();

        binding.tvActivateBtn.setText(data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                ? "确认到货" : data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_CHECK_ING ? "完成清点" : "");
        binding.tvActivateBtn.setVisibility(data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                || data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_CHECK_ING ? View.VISIBLE : View.GONE);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

}
