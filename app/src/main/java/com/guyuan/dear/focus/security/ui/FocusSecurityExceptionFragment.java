package com.guyuan.dear.focus.security.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.security.adapter.SecurityReportAdapter;
import com.guyuan.dear.focus.security.data.FocusSecurityViewModel;
import com.guyuan.dear.focus.security.data.beans.SecurityContentBean;
import com.guyuan.dear.utils.GsonUtil;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/27 10:40
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusSecurityExceptionFragment extends BaseListFragment<SecurityContentBean, FragmentListBinding,FocusSecurityViewModel> {

    public static final String TAG = "FocusSecurityExceptionFragment";
    private FocusSecurityViewModel viewModel;

    public static FocusSecurityExceptionFragment newInstance() {

        Bundle args = new Bundle();

        FocusSecurityExceptionFragment fragment = new FocusSecurityExceptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FocusSecurityActivity activity = (FocusSecurityActivity) context;
        viewModel = activity.getViewModel();
    }

    @Override
    protected void initView() {
        SecurityReportAdapter reportAdapter = new SecurityReportAdapter(getContext(), listData,
                R.layout.item_security);
        adapter = new BaseRecyclerViewAdapter(reportAdapter);
        reportAdapter.hideFinishBtn(true);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                SecurityDetailActivity.start(getContext(), listData.get(position),
//                        SecurityDetailActivity.SECURITY_REPORT);
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        if(viewModel!=null){
            viewModel.getDangerPointReport(getDangerBody(FIRST_PAGE));
        }
    }

    private RequestBody getDangerBody(int pageNum) {
        ListRequestBody dangerBody = new ListRequestBody();
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setType("1");//只显示异常
        dangerBody.setFilters(filtersBean);
        dangerBody.setPageNum(pageNum);
        dangerBody.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(dangerBody);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
        viewModel.getDangerPointReport(getDangerBody(currentPage));
    }

    @Override
    protected void loadMore() {
        viewModel.getDangerPointReport(getDangerBody(++currentPage));
    }

    @Override
    protected boolean isPullEnable() {
        return true;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
