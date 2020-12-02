package com.guyuan.dear.focus.produce.fragment;


import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ListProduceRequestBody;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.utils.GsonUtil;

import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/9 14:41
 * @company: 固远（深圳）信息技术有限公司
 */
public abstract class BaseProduceFragment extends BaseListSearchFragment<FocusProduceBean, FragmentListBinding, FocusProduceViewModel> {
    protected ProductStatusType statusType;

    /**
     * 生产列表请求参数配置
     *
     * @param isRefresh 是否刷新
     * @return
     */
    protected RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListProduceRequestBody body = new ListProduceRequestBody();
        ListProduceRequestBody.FiltersBean filtersBean = new ListProduceRequestBody.FiltersBean();
        filtersBean.setName(etSearch.getText().toString());
        if (statusType != null) {
            filtersBean.setStatus(statusType.getCode());
        }
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }


    protected RequestBody getListRequestBody(String name) {
        currentType = REFRESH ;
        currentPage = FIRST_PAGE ;
        ListProduceRequestBody body = new ListProduceRequestBody();
        ListProduceRequestBody.FiltersBean filtersBean = new ListProduceRequestBody.FiltersBean();
        filtersBean.setName(name);
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected void onSearch(String text) {
        bindRefresh();
    }

    @Override
    protected void editTextChanged(String text) {
        bindRefresh();
    }

    @Override
    protected void refresh() {
        bindRefresh();
    }

    @Override
    protected void loadMore() {

    }

    public abstract void bindRefresh();


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
