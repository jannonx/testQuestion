package com.guyuan.dear.focus.qc.views.qcReportList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.google.gson.Gson;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.LinearVerticalPaddingDecorator;
import com.guyuan.dear.databinding.FragmentProductQcPassListBinding;
import com.guyuan.dear.focus.qc.adapters.AllQcListAdapter;
import com.guyuan.dear.focus.qc.beans.BaseMaterialQcReport;
import com.guyuan.dear.focus.qc.beans.BaseProductQcReport;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.views.materialQcDetail.MaterialQcReportDetailActivity;
import com.guyuan.dear.focus.qc.views.productQcDetail.ProductQcReportDetailActivity;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

import static com.guyuan.dear.focus.qc.beans.GenericQcReport.REPORT_TYPE_MATERIAL;
import static com.guyuan.dear.focus.qc.beans.GenericQcReport.REPORT_TYPE_PRODUCT;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 10:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSumTypeListFragment extends BaseMvvmFragment<FragmentProductQcPassListBinding, QcReportListViewModel> {

    private long start;
    private long end;
    private int type;
    public static final int TYPE_PRODUCT_PASS = 1;
    public static final int TYPE_PRODUCT_REJECT = 2;
    public static final int TYPE_MATERIAL_PASS = 3;
    public static final int TYPE_MATERIAL_REJECT = 4;
    private List<GenericQcReport> list;
    private BaseRecyclerViewAdapter wrapper;
    private MutableLiveData<Boolean> shouldShowNoData = new MutableLiveData<>(true);


    /**
     * @param dateFrom
     * @param dateTo
     * @param type     {@link QcSumTypeListFragment#TYPE_MATERIAL_PASS}
     *                 {@link QcSumTypeListFragment#TYPE_MATERIAL_REJECT}
     *                 {@link QcSumTypeListFragment#TYPE_PRODUCT_PASS}
     *                 {@link QcSumTypeListFragment#TYPE_PRODUCT_REJECT}
     * @return
     */
    public static QcSumTypeListFragment getInstance(long dateFrom, long dateTo, int type) {
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantValue.KEY_DATE_START, dateFrom);
        bundle.putLong(ConstantValue.KEY_DATE_TO, dateTo);
        bundle.putInt(ConstantValue.KEY_REPORT_TYPE, type);
        QcSumTypeListFragment fragment = new QcSumTypeListFragment();
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
        type = bundle.getInt(ConstantValue.KEY_REPORT_TYPE);
        switch (type) {
            case TYPE_PRODUCT_PASS:
                getViewModel().upDateProductPassList(start, end);
                break;
            case TYPE_MATERIAL_REJECT:
                getViewModel().upDateMaterialRejectList(start, end);
                break;
            case TYPE_PRODUCT_REJECT:
                getViewModel().upDateProductRejectList(start, end);
                break;
            case TYPE_MATERIAL_PASS:
                getViewModel().upDateMaterialPassList(start, end);
                break;
            default:
                break;
        }

    }

    @Override
    protected void initViews() {
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentProductQcListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        switch (type) {
            case TYPE_PRODUCT_PASS:
            case TYPE_PRODUCT_REJECT:
                list = getViewModel().getProductReports().getValue();
                getViewModel().getIsAllProductReportLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            recyclerView.refreshComplete(0);
                            recyclerView.setLoadMoreEnabled(false);
                        }
                    }
                });
                getViewModel().getProductReports().observe(getViewLifecycleOwner(), new Observer<List<GenericQcReport>>() {
                    @Override
                    public void onChanged(List<GenericQcReport> genericQcReports) {
                        recyclerView.refreshComplete(0);
                        if (genericQcReports.isEmpty()) {
                            shouldShowNoData.postValue(true);
                        } else {
                            if (wrapper != null) {
                                shouldShowNoData.postValue(false);
                                wrapper.notifyDataSetChanged();
                            }
                        }

                    }
                });
                break;
            case TYPE_MATERIAL_REJECT:
            case TYPE_MATERIAL_PASS:
                list = getViewModel().getMaterialReports().getValue();
                getViewModel().getIsAllMaterialReportLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            recyclerView.refreshComplete(0);
                            recyclerView.setLoadMoreEnabled(false);
                        }
                    }
                });
                getViewModel().getMaterialReports().observe(getViewLifecycleOwner(), new Observer<List<GenericQcReport>>() {
                    @Override
                    public void onChanged(List<GenericQcReport> genericQcReports) {
                        recyclerView.refreshComplete(0);
                        if (genericQcReports.isEmpty()) {
                            shouldShowNoData.postValue(true);
                        } else {
                            if (wrapper != null) {
                                shouldShowNoData.postValue(false);
                                wrapper.notifyDataSetChanged();
                            }
                        }

                    }
                });
                break;
            default:
                break;
        }

        if (list == null) {
            return;
        }

        AllQcListAdapter adapter = new AllQcListAdapter(list, getContext());
        wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.addItemDecoration(new LinearVerticalPaddingDecorator(12, 16));
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                switch (type) {
                    case TYPE_PRODUCT_PASS:
                        getViewModel().upDateProductPassList(start, end);
                        break;
                    case TYPE_MATERIAL_REJECT:
                        getViewModel().upDateMaterialRejectList(start, end);
                        break;
                    case TYPE_PRODUCT_REJECT:
                        getViewModel().upDateProductRejectList(start, end);
                        break;
                    case TYPE_MATERIAL_PASS:
                        getViewModel().upDateMaterialPassList(start, end);
                        break;
                    default:
                        break;
                }
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                GenericQcReport report = null;
                switch (type) {
                    case TYPE_PRODUCT_PASS:
                    case TYPE_PRODUCT_REJECT: {
                        report = getViewModel().getProductReports().getValue().get(i);
                    }
                    break;
                    case TYPE_MATERIAL_REJECT:
                    case TYPE_MATERIAL_PASS: {
                        report = getViewModel().getMaterialReports().getValue().get(i);
                    }
                    break;
                    default:
                        break;
                }
                if (report == null) {
                    return;
                }
                String json = report.getJson();
                int type = report.getType();
                if (type == REPORT_TYPE_MATERIAL) {
                    BaseMaterialQcReport qcReport = new Gson().fromJson(json, BaseMaterialQcReport.class);
                    MaterialQcReportDetailActivity.start(getContext(), "质检详情", qcReport);
                } else if (type == REPORT_TYPE_PRODUCT) {
                    BaseProductQcReport qcReport = new Gson().fromJson(json, BaseProductQcReport.class);
                    ProductQcReportDetailActivity.start(getContext(), "质检详情", qcReport);
                }
            }
        });

    }

    @Override
    protected void initListeners() {
        shouldShowNoData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                getViewDataBinding().fragmentProductQcListNoDarta.setIsShow(aBoolean);
            }
        });


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_product_qc_pass_list;
    }
}
