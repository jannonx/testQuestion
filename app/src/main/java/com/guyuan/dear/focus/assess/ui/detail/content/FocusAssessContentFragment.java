package com.guyuan.dear.focus.assess.ui.detail.content;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.ScanPicAdapter;
import com.guyuan.dear.base.bean.PicBean;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.databinding.FragmentFocusAssessContentBinding;
import com.guyuan.dear.focus.assess.data.FocusAssessViewModel;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;
import com.guyuan.dear.focus.assess.data.bean.AuditContentBean;
import com.guyuan.dear.focus.assess.data.bean.AuditFormResultBean;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/21 10:54
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAssessContentFragment extends BaseDataBindingFragment<FragmentFocusAssessContentBinding,
        FocusAssessViewModel> implements TabLayoutHelper.TabLayoutListener {

    public static final String TAG = "FocusAssessContentFragment";
    public static final String DETAIL = "detail";

    public static FocusAssessContentFragment newInstance(AssessDetailBean detailBean) {

        Bundle args = new Bundle();
        args.putParcelable(DETAIL, detailBean);
        FocusAssessContentFragment fragment = new FocusAssessContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_assess_content;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            AssessDetailBean detailBean = getArguments().getParcelable(DETAIL);
            if (detailBean != null) {
                binding.setVariable(BR.focusAssessDetailBean, detailBean);
                initTab(detailBean);
            }
        }
    }

    private void initTab(AssessDetailBean detailBean) {
        List<Fragment> fragmentList = new ArrayList<>();
        List<AuditFormResultBean> resultList = detailBean.getAuditFormResultVOList();
        List<AuditContentBean> pointList = detailBean.getAuditContentList();
        if (resultList != null && resultList.size() > 0) {
            setImg(resultList);
            FocusAssessResultFragment resultFragment = FocusAssessResultFragment.newInstance(resultList);
            fragmentList.add(resultFragment);
        }

        if (pointList != null && pointList.size() > 0) {
            FocusAssessPointFragment pointFragment = FocusAssessPointFragment.newInstance(pointList);
            fragmentList.add(pointFragment);
        }

        new TabLayoutHelper(getActivity(), binding.assessContentTl, binding.assessResultVp,
                fragmentList, TabLayoutHelper.UNDERLINE).setTab().setListener(this).setCustomView();
    }

    private void setImg(List<AuditFormResultBean> resultList) {
        List<String> urlList = getImgUrlList(resultList);
        if (urlList.size() > 0) {
            binding.picLl.setVisibility(View.VISIBLE);
            ScanPicAdapter scanPicAdapter = new ScanPicAdapter(getImgList(resultList), R.layout.item_pic_scan);
            BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(scanPicAdapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    FullScreenShowActivity.start(getContext(), urlList, i);
                }
            });
            binding.imgRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
            binding.imgRv.setAdapter(adapter);
            binding.imgRv.setLoadMoreEnabled(false);
            binding.imgRv.setPullRefreshEnabled(false);
        }
    }

    private List<PicBean> getImgList(List<AuditFormResultBean> resultList) {
        List<PicBean> picBeanList = new ArrayList<>();
        for (AuditFormResultBean resultBean : resultList) {
            PicBean picBean = new PicBean();
            picBean.setName(resultBean.getAuditUserName());
            picBean.setUrl(resultBean.getAuditImgUrl());
            picBeanList.add(picBean);
        }
        return picBeanList;
    }

    private List<String> getImgUrlList(List<AuditFormResultBean> resultList) {
        List<String> picBeanList = new ArrayList<>();
        for (AuditFormResultBean resultBean : resultList) {
            String url = resultBean.getAuditImgUrl();
            if (!TextUtils.isEmpty(url)) {
                picBeanList.add(url);
            }
        }
        return picBeanList;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    public void setCustomContent(View customView, int currentPosition) {
        TextView tv = customView.findViewById(R.id.tab_tv);
        switch (currentPosition) {
            case 0:
                tv.setText("判定结果");
                break;

            case 1:
                tv.setText("评审内容");
                break;
        }
    }
}