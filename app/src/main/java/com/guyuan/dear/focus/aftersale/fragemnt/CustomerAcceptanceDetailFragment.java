package com.guyuan.dear.focus.aftersale.fragemnt;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.FragmentSaleCustomerAcceptanceDetailBinding;
import com.guyuan.dear.focus.aftersale.activity.CustomerAcceptanceDetailActivity;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.SaleSectionType;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.fragment.ProjectCheckConfirmDialog;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;


/**
 * @description: 我的关注--售后服务--客户验收--详情页面
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class CustomerAcceptanceDetailFragment extends BaseDataBindingFragment<FragmentSaleCustomerAcceptanceDetailBinding, FocusAfterSaleViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener, View.OnClickListener {

    public static final String TAG = CustomerAcceptanceDetailFragment.class.getSimpleName();
    private QuestionDescribeFragment questionFragment;
    private AfterSaleStatusFragment statusFragment;
    private ProjectCheckConfirmDialog leftDialog;
    private AfterSalePostInfoDialog rightDialog;

    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private AfterSaleBean afterSaleBean;
    protected ArrayList<String> photoList = new ArrayList<>();
    private ProduceApplyDialog dialog;

    private CustomerAcceptanceDetailActivity activity;

    public static CustomerAcceptanceDetailFragment newInstance(AfterSaleBean data) {
        Bundle bundle = new Bundle();
        CustomerAcceptanceDetailFragment fragment = new CustomerAcceptanceDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sale_customer_acceptance_detail;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        afterSaleBean = (AfterSaleBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);

        binding.tvPauseBtn.setOnClickListener(this);
        binding.tvCompleteBtn.setOnClickListener(this);

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
        data.setSectionType(SaleSectionType.TYPE_SECTION_ACCEPT);
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
                clickLeftBtn();
                break;
            case R.id.tv_complete_btn:
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

    private void clickLeftBtn() {

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
        if (leftDialog != null) {
            leftDialog.setPhotoList(photoList);
        }

        if (rightDialog != null) {
            rightDialog.setPhotoList(photoList);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (CustomerAcceptanceDetailActivity) getActivity();

        }
    }
}
