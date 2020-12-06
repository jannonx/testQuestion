package com.guyuan.dear.focus.aftersale.fragemnt;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.databinding.FragmentSaleCustomerAcceptanceDetailBinding;
import com.guyuan.dear.focus.aftersale.activity.CustomerAcceptanceDetailActivity;
import com.guyuan.dear.focus.aftersale.adapter.FocusAfterSaleQuestionAdapter;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleStatusBean;
import com.guyuan.dear.focus.aftersale.bean.PostInfoBean;
import com.guyuan.dear.focus.aftersale.bean.SaleAcceptedType;
import com.guyuan.dear.focus.aftersale.bean.SaleCheckType;
import com.guyuan.dear.focus.aftersale.bean.SaleSectionType;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.focus.projectsite.bean.FunctionModuleType;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.StringUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.fragment.ProjectCheckConfirmDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;


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
    private SaleAcceptedType saleAcceptedType = SaleAcceptedType.TYPE_ACCEPTED_QUALIFIED;
    private CustomerAcceptanceDetailActivity activity;
    private PostInfoBean postInfoBean;

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
        if (afterSaleBean.getModuleType() != null) {
            binding.llApplyPanel.setVisibility(FunctionModuleType.TYPE_WORK
                    == afterSaleBean.getModuleType() ? View.VISIBLE : View.GONE);
        }
        viewModel.getAfterSaleDetail(afterSaleBean.getId());
        viewModel.getAfterSaleDetailEvent().observe(getActivity(), new Observer<AfterSaleBean>() {
            @Override
            public void onChanged(AfterSaleBean data) {
                setAfterSaleBean(data);
            }
        });

        viewModel.getAfterSaleCustomerAcceptanceDetail(afterSaleBean.getId(),SaleSectionType.TYPE_SECTION_ACCEPT.getCode());
        viewModel.getAfterSaleCustomerAcceptanceDetailEvent().observe(getActivity(), new Observer<List<AfterSaleStatusBean>>() {
            @Override
            public void onChanged(List<AfterSaleStatusBean> data) {
                if (data!=null&&data.size()!=0){
                    setImageList(data.get(0));
                }

            }
        });

        viewModel.getUploadImageEvent().observe(this, new Observer<List<UploadBean>>() {
            @Override
            public void onChanged(List<UploadBean> dataList) {
                if (dataList.isEmpty()) return;
                List<String> imageUrlList = new ArrayList<>();
                for (UploadBean bean : dataList) {
                    LogUtils.showLog("upLoadPicAndVideo=" + bean.getUrl());
                    imageUrlList.add(bean.getUrl());
                }
                postAfterSaleInfo(imageUrlList);
            }
        });
        viewModel.getpostAfterSaleInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
//                setAfterSaleBean(data);
//                ToastUtils.showLong(getContext(), "提交成功");
                viewModel.getAfterSaleCustomerAcceptanceDetail(afterSaleBean.getId(),SaleSectionType.TYPE_SECTION_ACCEPT.getCode());
            }
        });
    }

    private void setImageList(AfterSaleStatusBean afterSaleStatusBean) {
        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                afterSaleStatusBean.getImgUrlList(), R.layout.item_explorate_image);
        BaseRecyclerViewAdapter  imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        binding.imageRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.imageRecycleView.setAdapter(imageAdapter);
        binding.imageRecycleView.setPullRefreshEnabled(false);
        binding.imageRecycleView.setLoadMoreEnabled(false);

    }

    private void postAfterSaleInfo(List<String> imageUrlList) {
        postInfoBean.setImgUrl(StringUtils.splicePhotoUrl(imageUrlList));
        String installStr = GsonUtil.objectToString(postInfoBean);
        RequestBody installRequestBody = RequestBody.create(MediaType.parse("application/json; " +
                "charset=utf-8"), installStr);
        viewModel.postAfterSaleInfo(installRequestBody);
    }


    /**
     * 设置数据
     *
     * @param data 详情数据
     */
    private void setAfterSaleBean(AfterSaleBean data) {
//        questionFragment.setQuestionDescribe(data);
        data.setSectionType(SaleSectionType.TYPE_SECTION_ACCEPT);
        binding.tvTitle.setText(data.getTitle());
        //我的工作(待验收)--显示
        binding.llApplyPanel.setVisibility(FunctionModuleType.TYPE_WORK == afterSaleBean.getModuleType()
                ? SaleAcceptedType.TYPE_ACCEPTED_QUALIFIED == data.getAcceptedType()
                || SaleAcceptedType.TYPE_ACCEPTED_UNQUALIFIED == data.getAcceptedType() ? View.GONE : View.VISIBLE
                : View.GONE);

        //状态属性设置
        binding.tvProjectStatus.setText(data.getStatusText());
        binding.tvProjectStatus.setBackgroundResource(data.getStatusTextBg());
        int statusTextColor = data.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));

        binding.tvProjectName.setText(data.getProjectName());
        binding.tvCustomerName.setText(data.getConsumerName());
        binding.tvAddress.setText(data.getConstructionLocaltion());
        binding.tvCheckName.setText(data.getExamineManName());
        binding.tvTime.setText(data.getCreateTime());


        binding.questionRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        FocusAfterSaleQuestionAdapter checkContentAdapter = new FocusAfterSaleQuestionAdapter(getContext(),
                data.getSaleIssueSubList(), R.layout.item_aftet_sale_question_answer);
        BaseRecyclerViewAdapter   adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
        binding.questionRecycleView.setAdapter(adapter);
        binding.questionRecycleView.setPullRefreshEnabled(false);
        binding.questionRecycleView.setLoadMoreEnabled(false);


        binding.tvRemark.setText(data.getTitle());
        binding.tvRecorder.setText(data.getExamineManName());


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pause_btn:
                saleAcceptedType = SaleAcceptedType.TYPE_ACCEPTED_UNQUALIFIED;
                showDialog();
                break;
            case R.id.tv_complete_btn:
                saleAcceptedType = SaleAcceptedType.TYPE_ACCEPTED_QUALIFIED;
                showDialog();
                break;
            default:
        }
    }


    private void showDialog() {
        rightDialog = new AfterSalePostInfoDialog(getActivity(), afterSaleBean, new AfterSalePostInfoDialog.OnDialogClickListener() {
            @Override
            public void onPickImageClick() {
                activity.openAlbum(BaseTabActivity.FIRST);
            }

            @Override
            public void onCommitCheckGoodsInfo(PostInfoBean data) {
                postInfoBean = data;
                postInfoBean.setStatus(saleAcceptedType.getCode());
                postInfoBean.setType(SaleSectionType.TYPE_SECTION_ACCEPT.getCode());
                activity.checkPhotoAndFileUpLoad(data.getImgUrlList());
                postInfoBean.clearImgUrlList();
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


    @Override
    protected int getVariableId() {
        return 0;
    }


    @Override
    public ArrayList<String> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onPhotoSelected(ArrayList<String> dataList) {
        photoList.addAll(dataList);
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
