package com.guyuan.dear.focus.contract.view.contractDetail;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.adapter.CommentListAdapter;
import com.guyuan.dear.focus.contract.bean.ContractDetailBean;
import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-销售概况-销售详情-跟进动态记录
 * @since: 2020/10/10 15:23
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractCommentsFragment extends BaseFragment {

    private List<ContractComment> mCommentList;
    private BaseRecyclerView mRecyclerView;
    private BaseRecyclerViewAdapter mAdapterWrapper;
    private CommentListAdapter adapter;

    public static ContractCommentsFragment getInstance(long buyerFirstCreateDate){
        ContractCommentsFragment fragment = new ContractCommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ConstantValue.KEY_BUYER_FIRST_CREATE_DATE,buyerFirstCreateDate);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_contract_comments;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        long date = arguments.getLong(ConstantValue.KEY_BUYER_FIRST_CREATE_DATE);
        mRecyclerView = rootView.findViewById(R.id.fragment_contract_comments_recycler_view);
        mCommentList = new ArrayList<>();
        adapter = new CommentListAdapter(getContext(), date, mCommentList);
        mAdapterWrapper = new BaseRecyclerViewAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapterWrapper);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(mCommentList.size()<=20){
                    mRecyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadMoreComments();
                            mAdapterWrapper.notifyDataSetChanged();
                            mRecyclerView.refreshComplete(50);
                        }
                    },3000);
                }else {
                    if(adapter.isHasMoreComments()){
                        adapter.setHasMoreComments(false);
                        mAdapterWrapper.notifyDataSetChanged();
                        showToastTip("已经到底了。");
                    }
                    mRecyclerView.refreshComplete(50);

                }

            }
        });
    }

    private void loadMoreComments() {
        for (int i = 0; i < 5; i++) {
            ContractComment comment = new ContractComment();
            comment.setCommenter("小明" + (mCommentList.size() + i));
            comment.setCommenterDept("销售部门");
            comment.setContent("客户对产品合作意愿很高，希望在价格方面给出些优惠政策。");
            comment.setDate(System.currentTimeMillis());
            List<ContractComment> subComments = new ArrayList<>();
            int random = new Random(System.currentTimeMillis()).nextInt(5);
            for (int i2 = 0; i2 < random; i2++) {
                ContractComment subComment = new ContractComment();
                subComment.setCommenter("小强" + (mCommentList.size() + i));
                subComment.setCommenterDept("售后部门");
                subComment.setContent("客户对产品质量很满意，且希望在功能上实现一定程度定制化。");
                subComment.setDate(System.currentTimeMillis());
                subComments.add(subComment);
            }
            comment.setSubComments(subComments);
            mCommentList.add(comment);
        }
    }

    public void updateViewByDate(ContractDetailBean data){
        adapter.setFirstCreateData(data.getBuyerFirstCreateDate());
        mCommentList.clear();
        mCommentList.addAll(data.getCommentList());
        mAdapterWrapper.notifyDataSetChanged();
    }
}
