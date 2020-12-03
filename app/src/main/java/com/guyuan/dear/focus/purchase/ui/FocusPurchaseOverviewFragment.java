package com.guyuan.dear.focus.purchase.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.library.baseAdapters.BR;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.editListView.EditListView;
import com.guyuan.dear.databinding.FragmentFocusPurchaseOverviewBinding;
import com.guyuan.dear.focus.assess.data.bean.OverviewBody;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.CommonUtils;

import retrofit2.http.PUT;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 15:55
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseOverviewFragment extends BaseDataBindingFragment<
        FragmentFocusPurchaseOverviewBinding, FocusPurchaseViewModel> implements View.OnClickListener {

    public static final String TAG = "FocusPurchaseOverviewFragment";
    public static final int EXCEPTION = 1;     //异常
    public static final int TOTAL = 2;         //总共
    public static final int RETURN = 1;        //退货
    public static final int REPLACE = 2;       //换货
    public static final int MATERIAL = 1;      //原材料
    public static final int PRODUCT = 2;       //产品
    private String currentDate = "";

    public static FocusPurchaseOverviewFragment newInstance() {

        Bundle args = new Bundle();

        FocusPurchaseOverviewFragment fragment = new FocusPurchaseOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return BR.focusPurchaseVM;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_purchase_overview;
    }

    @Override
    protected void initialization() {
        setDate();
        setListener();
        viewModel.getOverView(currentDate, "");
    }

    //设置时间
    private void setDate() {
        binding.purchaseSearchRl.etSearch.setHint("输入产品名称、产品代号");
        currentDate = CalenderUtils.getInstance().toLongYearAndMonth(System.currentTimeMillis());
        binding.focusPurchaseDateTv.setText(currentDate);
        binding.focusPurchaseDateTv.setOnClickListener(this);
    }

    private void setListener() {
        binding.purchaseProductReturnRl.setOnClickListener(this);
        binding.purchaseProductReplaceRl.setOnClickListener(this);
        binding.purchaseMaterialReturnRl.setOnClickListener(this);
        binding.purchaseMaterialReplaceRl.setOnClickListener(this);
        binding.purchaseSearchRl.tvSearchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.purchase_product_return_rl) {
            if (Integer.valueOf(binding.purchaseReturnTv.getText().toString()) > 0) {
                FocusPurchaseSearchActivity.start(getContext(), "产品-退货", currentDate, "", RETURN, PRODUCT);
            }
        } else if (id == R.id.purchase_product_replace_rl) {
            if (Integer.valueOf(binding.purchaseReplaceTv.getText().toString()) > 0) {
                FocusPurchaseSearchActivity.start(getContext(), "产品-换货", currentDate, "", REPLACE, PRODUCT);
            }
        } else if (id == R.id.purchase_material_return_rl) {
            if (Integer.valueOf(binding.purchaseMaterialReturnTv.getText().toString()) > 0) {
                FocusPurchaseSearchActivity.start(getContext(), "原材料-退货", currentDate, "", RETURN, MATERIAL);
            }
        } else if (id == R.id.purchase_material_replace_rl) {
            if (Integer.valueOf(binding.purchaseMaterialReplaceTv.getText().toString()) > 0) {
                FocusPurchaseSearchActivity.start(getContext(), "原材料-换货", currentDate, "", REPLACE, MATERIAL);
            }
        } else if (id == R.id.tv_search_btn) {
            Editable searchContent = binding.purchaseSearchRl.etSearch.getText();
            if (TextUtils.isEmpty(searchContent) || searchContent == null) {
                showToastTip("请输入搜索内容");
            } else {
                FocusPurchaseSearchActivity.start(getContext(), searchContent.toString(), TOTAL, searchContent.toString());
            }
        } else if (id == R.id.focus_purchase_date_tv) {
            AlertDialogUtils.pickYearAndMouth(getContext(), "请选择查询年份", new AlertDialogUtils.YearStringListener() {
                @Override
                public void onSelected(String year) {
                    currentDate = year;
                    binding.focusPurchaseDateTv.setText(currentDate);
                    OverviewBody body = new OverviewBody();
                    body.setAuditTime(year);
                    viewModel.getOverView(currentDate, "");
                }
            });
        }
    }

}