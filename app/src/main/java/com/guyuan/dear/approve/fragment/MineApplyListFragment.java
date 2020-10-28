package com.guyuan.dear.approve.fragment;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.util.LogUtils;
import com.google.gson.Gson;
import com.guyuan.dear.approve.activity.ApprovalsEntranceActivity;
import com.guyuan.dear.approve.activity.MineApplyListActivity;
import com.guyuan.dear.approve.adapter.ApprovedListAdapter;
import com.guyuan.dear.approve.bean.ApplyBean;
import com.guyuan.dear.approve.bean.ApproveListBody;
import com.guyuan.dear.approve.data.ApproveViewModel;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.utils.ConstantValue;

import com.guyuan.dear.R;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 掌上办公--审批--我发起的
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class MineApplyListFragment extends BaseListFragment<ApplyBean, FragmentListBinding, ApproveViewModel> {
    public static final String TAG = MineApplyListFragment.class.getSimpleName();

    public static MineApplyListFragment getInstance() {
        return new MineApplyListFragment();
    }

    @Override
    protected void initView() {
        ApprovedListAdapter approveAdapter = new ApprovedListAdapter(getActivity(), listData, R.layout.item_examine_and_approve);
        adapter = new BaseRecyclerViewAdapter(approveAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ApplyBean bean = listData.get(position);
//                ApplyDetailPageActivity.start(activity, bean);
            }
        });

        getMyApplyList();
    }


    private void getMyApplyList() {
        ApproveListBody requestBody = new ApproveListBody();
        requestBody.setPageNum(ConstantValue.FIRST_PAGE);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        String str = new Gson().toJson(requestBody);
//        mPresenter.getMyApplyList(RequestBody.create(MediaType.parse("application/json; " +
//            "charset=utf-8"), str));

    }

    public void showMyApplyList(RefreshBean<ApplyBean> result) {
        LogUtils.showLog("showApplyNotApproveList=" + result.getContent().size() + "//adapter=" + (adapter == null) + "///currentType=" + currentType);
        setListData(result.getContent());
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
