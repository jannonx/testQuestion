package com.guyuan.dear.focus.qc.views.qcReportList;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator;
import com.guyuan.dear.databinding.FragmentProductQcPassListBinding;
import com.guyuan.dear.focus.qc.adapters.ProductQcListAdapter;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.views.productQcDetail.ProductQcReportDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 10:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcPassListFragment extends BaseMvvmFragment<FragmentProductQcPassListBinding, QcReportListViewModel> {

    private int pageIndex = 1;
    private int pageSize = 50;
    private long start;
    private long end;

    public static ProductQcPassListFragment getInstance(long dateFrom, long dateTo) {
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantValue.KEY_DATE_START, dateFrom);
        bundle.putLong(ConstantValue.KEY_DATE_TO, dateTo);
        ProductQcPassListFragment fragment = new ProductQcPassListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_product_qc_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        start = bundle.getLong(ConstantValue.KEY_DATE_START);
        end = bundle.getLong(ConstantValue.KEY_DATE_TO);
        getViewModel().upDateProductPassList(start, end, pageIndex, pageSize);

    }

    @Override
    protected void initViews() {
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentProductQcListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        ProductQcListAdapter adapter = new ProductQcListAdapter(getViewModel().getProductReports().getValue());
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator(12,16));
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //假数据最多三页
                if (pageIndex < 3) {
                    showLoading(getParentFragmentManager());
                    pageIndex++;
                    getViewModel().upDateProductPassList(start, end, pageIndex, pageSize);
                    hideLoading();
                } else {
                    showToastTip("已经全部加载完成。");
                    recyclerView.setLoadMoreEnabled(false);
                }
                recyclerView.refreshComplete(0);
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                BaseProductQcReport qcReport = getViewModel().getProductReports().getValue().get(i);
                ProductQcReportDetailActivity.start(getContext(), "质检详情", qcReport);
            }
        });
    }

    @Override
    protected void initListeners() {


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_product_qc_pass_list;
    }
}
