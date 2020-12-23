package com.guyuan.dear.work.client.fragment;


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
import com.guyuan.dear.focus.client.bean.ClientType;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.client.bean.PostClientInfo;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.client.data.WorkClientViewModel;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;


/**
 * @description: 我的工作-客户详情--跟进动态
 * @author: 许建宁
 * @since: 2020/11/4 14:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusFragment extends BaseListFragment<CommentsBean, FragmentListBinding, WorkClientViewModel> {

    public static final String TAG = FollowStatusFragment.class.getSimpleName();
    private ClientCompanyBean clientData;
    private View footerView;

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
        LogUtils.showLog("initialization=" + clientData.getCusName());

        footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_client_all, null);
        FollowStatusParentAdapter listAdapter = new FollowStatusParentAdapter(getContext(), listData,
                R.layout.item_follow_status_parent);
        listAdapter.setCommentBtnVisible(true);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.addFooterView(footerView);

        listAdapter.setClickListener(new FollowStatusParentAdapter.OnFollowClickListener() {
            @Override
            public void onClick(long followId) {
                editUserFollowComment(followId);
            }
        });

        viewModel.getFollowListEvent().observe(getActivity(), new Observer<RefreshBean<CommentsBean>>() {
            @Override
            public void onChanged(RefreshBean<CommentsBean> dataRefreshBean) {
                LogUtils.showLog("CommentsBean=" + dataRefreshBean.getContent().size());
                List<CommentsBean> content = dataRefreshBean.getContent();
                setListData(content);
                TextView activateTime = footerView.findViewById(R.id.tv_activate_time);
                if (content.size() > 0) {
                    activateTime.setText(content.get(content.size() - 1).getCreateTime());
                }
            }
        });

    }

    @Override
    public void refresh() {
        viewModel.getFollowCommentList(getListRequestBody(true));
    }

    @Override
    protected void loadMore() {
        viewModel.getFollowCommentList(getListRequestBody(false));
    }

    @Override
    protected boolean isPullEnable() {
        return true;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    /**
     * 添加客户评论
     */
    private void editUserFollowComment(long followId) {
        FollowCommentDialog.show(getActivity(), new FollowCommentDialog.OnFollowClickListener() {
            @Override
            public void onClick(String content) {
                PostClientInfo postInfoBean=new PostClientInfo();
                postInfoBean.setId(followId);
                postInfoBean.setContent(content);
                String installStr = GsonUtil.objectToString(postInfoBean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), installStr);
                viewModel.postUserFollowUp(requestBody);
            }
        });
        viewModel.getFollowUserEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer dataRefreshBean) {
                ToastUtils.showShort(getContext(), "评论成功!");
                viewModel.getFollowCommentList(getListRequestBody(true));
                //刷新列表
            }
        });
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    private RequestBody getListRequestBody(boolean isRefresh) {
        currentType = isRefresh ? REFRESH : LOAD_MORE;
        currentPage = isRefresh ? FIRST_PAGE : currentPage + 1;
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
        filtersBean.setId(ClientType.TYPE_CLIENT_ALL==clientData.getClientType()?
                clientData.getId():clientData.getCusId());
        body.setFilters(filtersBean);
        body.setPageNum(currentPage);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

}
