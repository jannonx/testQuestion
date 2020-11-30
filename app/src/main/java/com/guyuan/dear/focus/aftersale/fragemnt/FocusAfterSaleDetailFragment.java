package com.guyuan.dear.focus.aftersale.fragemnt;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.FragmentFocusAfterSaleDetailBinding;
import com.guyuan.dear.focus.aftersale.activity.FocusAfterSaleDetailActivity;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @description: 我的关注--售后服务--详情页面
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusAfterSaleDetailFragment extends BaseDataBindingFragment<FragmentFocusAfterSaleDetailBinding, FocusAfterSaleViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener, View.OnClickListener {

    public static final String TAG = FocusAfterSaleDetailFragment.class.getSimpleName();
    private QuestionDescribeFragment questionFragment;
    private AfterSaleStatusFragment statusFragment;
    private AfterSalePostInfoDialog rightDialog;

    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private AfterSaleBean afterSaleBean;
    protected ArrayList<String> photoList = new ArrayList<>();
    private ProduceApplyDialog dialog;

    private FocusAfterSaleDetailActivity activity;

    public static FocusAfterSaleDetailFragment newInstance(AfterSaleBean data) {
        Bundle bundle = new Bundle();
        FocusAfterSaleDetailFragment fragment = new FocusAfterSaleDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_after_sale_detail;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        afterSaleBean = (AfterSaleBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        LogUtils.showLog("afterSaleBean=" + (afterSaleBean == null));
        if (afterSaleBean == null) {
            return;
        }
        binding.tvPauseBtn.setOnClickListener(this);
        binding.tvCompleteBtn.setOnClickListener(this);
        binding.tvActivateBtn.setOnClickListener(this);

        initViewPager();

        viewModel.getAfterSaleDetail(afterSaleBean.getId());
        viewModel.getAfterSaleDetailEvent().observe(getActivity(), new Observer<AfterSaleBean>() {
            @Override
            public void onChanged(AfterSaleBean data) {
                setAfterSaleBean(data);
            }
        });

    }


    /**
     * 设置数据
     *
     * @param data 详情数据
     */
    private void setAfterSaleBean(AfterSaleBean data) {
        questionFragment.setQuestionDescribe(data);


        binding.tvTitle.setText(data.getTitle());

        //状态属性设置
        binding.tvProjectStatus.setText(data.getStatusText());
        binding.tvProjectStatus.setBackgroundResource(data.getStatusTextBg());
        int statusTextColor = data.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));

        binding.tvProjectName.setText(data.getProjectName());
        binding.tvCustomerName.setText(data.getConsumerName());
        binding.tvAddress.setText(data.getConstructionLocaltion());
        binding.tvCheckName.setText(data.getExamineManName());
        binding.tvTime.setText(data.getUpdateTime());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pause_btn:

                break;
            case R.id.tv_complete_btn:
                break;
            case R.id.tv_activate_btn:
                break;

            default:
        }
    }


    private void clickRightBtn() {
        rightDialog = new AfterSalePostInfoDialog(getActivity(), afterSaleBean, new AfterSalePostInfoDialog.OnDialogClickListener() {
            @Override
            public void onPickImageClick() {
                activity.openAlbum(BaseTabActivity.FIRST);
            }

            @Override
            public void onCommitCheckGoodsInfo(PostCheckInfo data) {

            }

        });
        rightDialog.show();
        rightDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                LogUtils.showLog("rightDialog....dismiss");
                rightDialog = null;
            }
        });
    }



    private void initViewPager() {
        List<Fragment> tabFragmentList = new ArrayList<>();
        statusFragment = AfterSaleStatusFragment.newInstance(afterSaleBean);
        tabFragmentList.add(statusFragment);
        questionFragment = QuestionDescribeFragment.newInstance(afterSaleBean);
        tabFragmentList.add(questionFragment);

        titleList = getResources().getStringArray(R.array.focus_after_sale);
        TabAdapter tabAdapter = new
                TabAdapter(getChildFragmentManager(), Arrays.asList(titleList), tabFragmentList, getContext(),
                getCustomViewId());

        binding.viewPager.setAdapter(tabAdapter);

        //设置TabLayout和ViewPager联动
        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        binding.tabLayout.setTabIndicatorFullWidth(false);
        binding.tabLayout.setupWithViewPager(binding.viewPager, false);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabUnselected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            View view = tabAdapter.getView();
            if (tab != null) {
                setContent(i, view);
                tab.setCustomView(view);
                if (i == startPosition) {
                    setTabSelect(tab);
                    tab.select();
                } else {
                    setTabUnselected(tab);
                }
            }
        }
    }


    private int getCustomViewId() {
        return R.layout.layout_tab_text;
    }

    protected void setTabSelect(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (selectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        }
        tv.setTextSize(15f);
    }

    protected void setTabUnselected(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (unSelectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
        }
        tv.setTextSize(15f);
    }


    private void setContent(int position, View customView) {
        TextView tv = customView.findViewById(R.id.tv_tab_text);
        tv.setText(Arrays.asList(titleList).get(position));
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


    @Override
    public ArrayList<String> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onPhotoSelected(ArrayList<String> photoList) {
        photoList.addAll(photoList);

        if (rightDialog != null) {
            rightDialog.setPhotoList(photoList);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (FocusAfterSaleDetailActivity) getActivity();

        }
    }
}