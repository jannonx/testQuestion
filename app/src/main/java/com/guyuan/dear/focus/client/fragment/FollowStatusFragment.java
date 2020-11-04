package com.guyuan.dear.focus.client.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFollowStatusBinding;
import com.guyuan.dear.focus.client.adapter.ClientPhoneAdapter;
import com.guyuan.dear.focus.client.adapter.FollowStatusExAdapter;
import com.guyuan.dear.focus.client.adapter.FollowStatusParentAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

import static com.guyuan.dear.utils.ConstantValue.FIRST_PAGE;
import static com.guyuan.dear.utils.ConstantValue.PAGE_SIZE;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 14:31
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowStatusFragment extends BaseDataBindingFragment<FragmentFollowStatusBinding, FocusClientViewModel> {

    public static final String TAG = "FollowStatusFragment";
    private List<CommentsBean> dataList = new ArrayList<>();
    private boolean isFocus = false;
    private ClientCompanyBean clientData;
    private View footerView;
    //    private FollowStatusExAdapter adapter;
    private BaseRecyclerViewAdapter adapter;

    public static FollowStatusFragment newInstance() {
        return newInstance(false);
    }

    public static FollowStatusFragment newInstance(boolean isFocus) {
        Bundle bundle = new Bundle();
        FollowStatusFragment fragment = new FollowStatusFragment();
        bundle.putBoolean(ConstantValue.KEY_BOOLEAN, isFocus);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static FollowStatusFragment newInstance(boolean isFocus, ClientCompanyBean data) {
        Bundle bundle = new Bundle();
        FollowStatusFragment fragment = new FollowStatusFragment();
        bundle.putBoolean(ConstantValue.KEY_BOOLEAN, isFocus);
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_follow_status;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        isFocus = arguments.getBoolean(ConstantValue.KEY_BOOLEAN);
        if (arguments.containsKey(ConstantValue.KEY_CONTENT)) {
            clientData = (ClientCompanyBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
            viewModel.getFollowCommentList(getListRequestBody(FIRST_PAGE));

        }
        initView();
        viewModel.getFollowListEvent().observe(getActivity(), new Observer<RefreshBean<CommentsBean>>() {
            @Override
            public void onChanged(RefreshBean<CommentsBean> dataRefreshBean) {
                LogUtils.showLog("CommentsBean=" + dataRefreshBean.getContent().size());
                List<CommentsBean> content = dataRefreshBean.getContent();
                dataList.clear();
                dataList.addAll(content);
                adapter.refreshData();

                TextView activateTime = footerView.findViewById(R.id.tv_activate_time);

                activateTime.setText(content.get(content.size() - 1).getCreateTime());
            }
        });


    }

    private RequestBody getListRequestBody(int pageNum) {
        ListClientRequestBody body = new ListClientRequestBody();
        ListClientRequestBody.FiltersBean filtersBean = new ListClientRequestBody.FiltersBean();
        filtersBean.setId(clientData.getId());
        body.setFilters(filtersBean);
        body.setPageNum(pageNum);
        body.setPageSize(PAGE_SIZE);

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    private void initView() {
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_client_all, null);
        FollowStatusParentAdapter listAdapter = new FollowStatusParentAdapter(getContext(), dataList,
                R.layout.item_follow_status_parent);
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setLoadMoreEnabled(false);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        adapter.addFooterView(footerView);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });


//        adapter = new FollowStatusExAdapter(getContext(), dataList);
//        LogUtils.showLog("isFocus=" + isFocus);
//        adapter.setCommentBtnVisible(isFocus);
//
//        binding.elvComments.setAdapter(adapter);
//        binding.elvComments.setGroupIndicator(null);
//        binding.elvComments.setChildIndicator(null);
//        binding.elvComments.addFooterView(footerView);
//        binding.elvComments.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                return true;
//            }
//        });
//
//        for (int i = 0; i < dataList.size(); i++) {
//            binding.elvComments.expandGroup(i);
//        }
//        adapter.notifyDataSetChanged();
//
//
//        adapter.setChildItemClickListener(new FollowStatusExAdapter.ChildItemClickListener() {
//
//            @Override
//            public void onCommentClicked(CommentsBean bean) {
//                LogUtils.showLog("评论哈哈哈哈");
//            }
//
//
//        });
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
