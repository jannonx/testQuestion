package com.guyuan.dear.focus.aftersale.fragemnt;

import android.os.Bundle;
import android.view.View;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.aftersale.activity.CustomerAcceptanceDetailActivity;
import com.guyuan.dear.focus.aftersale.activity.FocusAfterSaleDetailActivity;
import com.guyuan.dear.focus.aftersale.adapter.FocusAfterSaleAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.ListSaleRequestBody;
import com.guyuan.dear.focus.aftersale.bean.SaleQualifiedType;
import com.guyuan.dear.focus.aftersale.bean.SaleSectionType;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.projectsite.bean.FunctionModuleType;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 13:57
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleFragment extends BaseListSearchFragment<AfterSaleBean, FragmentListSearchBinding, FocusAfterSaleViewModel> {

    public static final String TAG = FocusAfterSaleFragment.class.getSimpleName();
    //合格状态
    private SaleQualifiedType qualifiedType;

    public static FocusAfterSaleFragment newInstance(SaleQualifiedType saleQualifiedType) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantValue.KEY_CONTENT, saleQualifiedType);
        FocusAfterSaleFragment fragment = new FocusAfterSaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        etSearch.setHint("输入项目名称、编号、人员");
        qualifiedType = (SaleQualifiedType) getArguments().getSerializable(ConstantValue.KEY_CONTENT);

        FocusAfterSaleAdapter saleAdapter = new FocusAfterSaleAdapter(getContext(), listData, R.layout.item_focus_after_sale);
        adapter = new BaseRecyclerViewAdapter(saleAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setPullRefreshEnabled(isPullEnable());
        recycleView.setLoadMoreEnabled(isLoadMoreEnable());

        viewModel.getAfterSaleList(getListRequestBody(true));




        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AfterSaleBean bean = listData.get(position);
                bean.setModuleType(FunctionModuleType.TYPE_FOCUS);
                CustomerAcceptanceDetailActivity.start(getContext(), bean);
            }
        });
    }

    /**
     * 设置处理数据
     *
     * @param content 回调数据
     */
    public void dealDataByAddType(List<AfterSaleBean> content) {
        List<AfterSaleBean> tempList = new ArrayList<>();
        for (AfterSaleBean bean : content) {
            bean.setSectionType(SaleSectionType.TYPE_SECTION_ACCEPT);
            tempList.add(bean);
        }
        setListData(tempList);
    }


    /**
     * 请求参数配置
     *
     * @param isRefresh 是否刷新
     * @return
     */
    private RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListSaleRequestBody body = new ListSaleRequestBody();
        ListSaleRequestBody.FiltersBean filtersBean = new ListSaleRequestBody.FiltersBean();
        filtersBean.setStatus(qualifiedType.getCode());
        filtersBean.setName(searchContent);
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);
        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected void refresh() {
        viewModel.getAfterSaleList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getAfterSaleList(getListRequestBody(false));
    }

    @Override
    protected boolean isPullEnable() {
        return false;
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