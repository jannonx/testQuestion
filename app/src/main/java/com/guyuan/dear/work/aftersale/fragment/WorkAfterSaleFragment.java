package com.guyuan.dear.work.aftersale.fragment;

import android.os.Bundle;
import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.aftersale.activity.CustomerAcceptanceDetailActivity;
import com.guyuan.dear.focus.aftersale.activity.FocusAfterSaleDetailActivity;
import com.guyuan.dear.focus.aftersale.adapter.FocusAfterSaleAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.EventSaleListRefresh;
import com.guyuan.dear.focus.aftersale.bean.ListSaleRequestBody;
import com.guyuan.dear.focus.aftersale.bean.SaleSectionType;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.projectsite.type.FunctionModuleType;
import com.guyuan.dear.login.data.bean.LoginBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 13:57
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAfterSaleFragment extends BaseListSearchFragment<AfterSaleBean, FragmentListSearchBinding, FocusAfterSaleViewModel> {

    public static final String TAG = WorkAfterSaleFragment.class.getSimpleName();
    //合格状态
    private SaleSectionType saleSectionType;
    private boolean isFirstLoad=true;

    public static WorkAfterSaleFragment newInstance(SaleSectionType saleSectionType) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantValue.KEY_CONTENT, saleSectionType);
        WorkAfterSaleFragment fragment = new WorkAfterSaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        searchBar.setHint("输入项目名称、编号、人员");
        saleSectionType = (SaleSectionType) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        EventBus.getDefault().register(this);
        FocusAfterSaleAdapter saleAdapter = new FocusAfterSaleAdapter(getContext(),
                listData, R.layout.item_focus_after_sale);
        adapter = new BaseRecyclerViewAdapter(saleAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setPullRefreshEnabled(isPullEnable());
        recycleView.setLoadMoreEnabled(isLoadMoreEnable());


        getListDataByClassify(true);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AfterSaleBean bean = listData.get(position);
                bean.setModuleType(FunctionModuleType.TYPE_WORK);
                if (SaleSectionType.TYPE_SECTION_CHECK == saleSectionType) {
                    bean.setSectionType(SaleSectionType.TYPE_SECTION_CHECK );
                    FocusAfterSaleDetailActivity.start(getContext(), bean);
                } else {
                    CustomerAcceptanceDetailActivity.start(getContext(), bean);
                }

            }
        });
    }

    /**
     * 设置处理数据
     *
     * @param content 回调数据
     */
    public void dealDataByAddType(List<AfterSaleBean> content, SaleSectionType sectionType) {
        List<AfterSaleBean> tempList = new ArrayList<>();
        for (AfterSaleBean bean : content) {
            bean.setSectionType(sectionType);
            LogUtils.showLog("saleSectionType=" + (sectionType == null ? "null" : sectionType.getDes()));
            tempList.add(bean);
        }
        setListData(tempList);
        adapter.refreshData();
    }

    @Override
    public void onResume() {
        super.onResume();
        //第一次加载不刷新，再次进入页面刷新数据
        if (!isFirstLoad ) {
            getListDataByClassify(true);
        }
        isFirstLoad = false;
    }
    /**
     * 请求参数配置
     *
     * @param isRefresh 是否刷新
     * @return
     */
    private RequestBody getListRequestBody(boolean isRefresh) {
        LoginBean loginInfo = CommonUtils.getLoginInfo();
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListSaleRequestBody body = new ListSaleRequestBody();
        ListSaleRequestBody.FiltersBean filtersBean = new ListSaleRequestBody.FiltersBean();
        filtersBean.setUserId(SaleSectionType.TYPE_SECTION_ACCEPT == saleSectionType ? loginInfo.getUserInfo().getId() : null);
        filtersBean.setName(searchContent);
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);
        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshMessage(EventSaleListRefresh event) {
        getListDataByClassify(true, true);
    }

    public void getListDataByClassify(boolean isRefresh) {
        getListDataByClassify(isRefresh, false);
    }

    public void getListDataByClassify(boolean isRefresh, boolean isAll) {
        if (isAll || SaleSectionType.TYPE_SECTION_ACCEPT == saleSectionType) {
            viewModel.getAfterSaleCustomerAcceptanceList(getListRequestBody(isRefresh));
        } else if (isAll || SaleSectionType.TYPE_SECTION_CHECK == saleSectionType) {
            viewModel.getAfterSaleList(getListRequestBody(isRefresh));
        }
    }

    @Override
    protected void refresh() {
        getListDataByClassify(true);
    }

    @Override
    protected void loadMore() {
        getListDataByClassify(false);
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

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }
}