package com.guyuan.dear.focus.aftersale.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FooterExploreContentBinding;
import com.guyuan.dear.databinding.FragmentAfterSaleQuestionDescribeBinding;
import com.guyuan.dear.focus.aftersale.adapter.FocusAfterSaleQuestionAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleQuestionBean;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;


/**
 * @description: 我的关注--售后服务--详情--问题描述
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class QuestionDescribeFragment extends BaseDataBindingFragment<FragmentAfterSaleQuestionDescribeBinding, FocusAfterSaleViewModel> {
    /**
     * 清点是否异常，0正常，1异常
     */
    private static final int CHECK_RIGHT = 0;
    private static final int CHECK_WRONG = 1;
    public static final String TAG = QuestionDescribeFragment.class.getSimpleName();

    private BaseRecyclerViewAdapter adapter;
    private BaseRecyclerViewAdapter imageAdapter;
    private FooterExploreContentBinding footerBinding;
    private List<AfterSaleQuestionBean> listData = new ArrayList<>();
    List<String> imageDataList = new ArrayList<>();
    private AfterSaleBean simpleData;
    private View footerView;

    public static QuestionDescribeFragment newInstance(AfterSaleBean afterSaleBean) {
        Bundle bundle = new Bundle();
        QuestionDescribeFragment fragment = new QuestionDescribeFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, afterSaleBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_after_sale_question_describe;
    }

    @Override
    protected void initialization() {

    }

    /**
     * 设置数据
     *
     * @param afterSaleBean 报告数据
     */
    public void setQuestionDescribe(AfterSaleBean afterSaleBean) {
        simpleData = afterSaleBean;
        if (afterSaleBean.getSaleIssueSubList() != null) {
            listData.addAll(afterSaleBean.getSaleIssueSubList());
        }
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        FocusAfterSaleQuestionAdapter checkContentAdapter = new FocusAfterSaleQuestionAdapter(getContext(),
                listData, R.layout.item_aftet_sale_question_answer);
        adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        addContentFooterView();

    }


    /**
     * 添加列表FooterView,增加拖动范围
     */
    private void addContentFooterView() {
        footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_after_sale_question_describe, binding.baseRecycleView, false);
        adapter.addFooterView(footerView);


        TextView tvRemark = footerView.findViewById(R.id.tv_remark);
        TextView tvRecorder = footerView.findViewById(R.id.tv_recorder);
        BaseRecyclerView imageRecyclerView = footerView.findViewById(R.id.image_recycleView);
        tvRemark.setText(simpleData.getTitle());
        tvRecorder.setText(simpleData.getExamineManName());

        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                imageDataList, R.layout.item_explorate_image);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        imageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        imageRecyclerView.setAdapter(imageAdapter);
        imageRecyclerView.setPullRefreshEnabled(false);
        imageRecyclerView.setLoadMoreEnabled(false);


//        if (simpleData.getImgUrlList() != null) {
//            imageDataList.clear();
//            imageDataList.addAll(simpleData.getImgUrlList());
//        }


    }




    @Override
    protected int getVariableId() {
        return 0;
    }


}
