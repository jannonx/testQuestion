package com.guyuan.dear.focus.qc.views.qcSearchList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.gson.Gson;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator;
import com.guyuan.dear.databinding.FragmentQcSearchListBinding;
import com.guyuan.dear.focus.qc.adapters.AllQcListAdapter;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.views.materialQcDetail.MaterialQcReportDetailActivity;
import com.guyuan.dear.focus.qc.views.productQcDetail.ProductQcReportDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/4 10:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSearchListFragment extends BaseMvvmFragment<FragmentQcSearchListBinding, QcSearchListViewModel> {

    private String keyWord;
    private int searchType;
    private BaseRecyclerView recyclerView;
    private BaseRecyclerViewAdapter adapter;

    /**
     * @param keyWords   产品名称、代号、出厂编号，字符长度255以内
     * @param searchType {@link QcSearchListActivity#SEARCH_TYPE_ALL}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_PASS_REPORTS}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_REJECTED_REPORTS}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_MY_REPORTS}
     */
    public static QcSearchListFragment getInstance(String keyWords, int searchType) {
        QcSearchListFragment fragment = new QcSearchListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.KEY_KEY_WORD, keyWords);
        bundle.putInt(ConstantValue.KEY_SEARCH_TYPE, searchType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_qc_search_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        keyWord = bundle.getString(ConstantValue.KEY_KEY_WORD);
        searchType = bundle.getInt(ConstantValue.KEY_SEARCH_TYPE);

        getViewModel().updateSearchResult(keyWord, searchType);
    }

    @Override
    protected void initViews() {
        recyclerView = getViewDataBinding().fragmentQcSearchListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        AllQcListAdapter adapter = new AllQcListAdapter(getViewModel().reportList.getValue(), getContext());
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator(12,16));
        this.adapter = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
    }

    @Override
    protected void initListeners() {
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().updateSearchResult(keyWord, searchType);
            }
        });

        getViewModel().isAllLoaded.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    recyclerView.setLoadMoreEnabled(false);
                    //一开始就没数据，就不用提示全部加载完毕了。
                    if(!getViewModel().reportList.getValue().isEmpty()){
                        showToastTip("已经全部加载完毕。");
                    }
                }
            }
        });

        getViewModel().isShouldNotifyDataSetChange.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    recyclerView.refreshComplete(0);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GenericQcReport qcReport = getViewModel().reportList.getValue().get(position);
                int type = qcReport.getType();
                if (type == GenericQcReport.REPORT_TYPE_PRODUCT) {
                    ProductQcReportDetailActivity.start(getContext(), "产品质量报告",
                            new Gson().fromJson(qcReport.getJson(), BaseProductQcReport.class));
                } else if (type == GenericQcReport.REPORT_TYPE_MATERIAL) {
                    MaterialQcReportDetailActivity.start(getContext(), "原材料质量报告",
                            new Gson().fromJson(qcReport.getJson(), BaseMaterialQcReport.class));
                }
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_qc_search_list;
    }
}
