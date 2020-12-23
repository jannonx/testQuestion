package com.guyuan.dear.focus.client.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.client.adapter.FollowStatusParentAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;


/**
 * @description: 我的关注-客户详情--跟进动态页面
 * @author: 许建宁
 * @since: 2020/10/27 14:31
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusFragment extends BaseListFragment<CommentsBean, FragmentListBinding, FocusClientViewModel> {

    public static final String TAG = FollowStatusFragment.class.getSimpleName();
    private View footerView;
    private ClientCompanyBean clientData;

    public static FollowStatusFragment newInstance(ClientCompanyBean data) {
        Bundle bundle = new Bundle();
        FollowStatusFragment fragment = new FollowStatusFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        clientData = (ClientCompanyBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        viewModel.getFollowCommentList(getListRequestBody(true));


        FollowStatusParentAdapter listAdapter = new FollowStatusParentAdapter(getContext(), listData,
                R.layout.item_follow_status_parent);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加footer_view，显示首页创建时间
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_client_all, recycleView, false);
        adapter.addFooterView(footerView);

        viewModel.getFollowListEvent().observe(getActivity(), new Observer<RefreshBean<CommentsBean>>() {
            @Override
            public void onChanged(RefreshBean<CommentsBean> dataRefreshBean) {
                LogUtils.showLog("CommentsBean=" + dataRefreshBean.getContent().size());
                List<CommentsBean> content = dataRefreshBean.getContent();
                setListData(content);
                if (content.size() > 0) {
                    TextView activateTime = footerView.findViewById(R.id.tv_activate_time);
                    activateTime.setText(content.get(content.size() - 1).getCreateTime());
                }
            }
        });
    }

    @Override
    protected void refresh() {
        viewModel.getFollowCommentList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getFollowCommentList(getListRequestBody(false));
    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    private RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
        filtersBean.setId(clientData.getId());
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
