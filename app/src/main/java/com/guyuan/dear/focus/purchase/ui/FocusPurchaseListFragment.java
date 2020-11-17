package com.guyuan.dear.focus.purchase.ui;

import android.os.Bundle;
import android.text.TextUtils;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListSearchBinding;
import com.guyuan.dear.focus.purchase.adapter.FocusPurchaseAdapter;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseListBean;

import java.util.List;

import retrofit2.http.PUT;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 15:59
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseListFragment extends BaseListSearchFragment<PurchaseListBean.ContentBean,
        FragmentListSearchBinding, FocusPurchaseViewModel> {

    public static final String TAG = "FocusPurchaseListFragment";
    public static final String STATUS_TYPE = "statusType";      //1.异常列表；2.详情列表
    public static final String NAME = "name";                   //产品代号或名称
    public static final String MOUTH_DATE = "mouthDate";        //查询时间
    public static final String RETURN_TYPE = "returnType";      //退换货类型（1.退货，2.换货）
    public static final String PRODUCT_TYPE = "productType";    //商品类型（1.原材料，2.配套设备）

    private int statusType;
    private String name = "";
    private String mouthDate = "";
    private int returnType;
    private int productType;

    public static FocusPurchaseListFragment newInstance(int statusType, String name) {

        Bundle args = new Bundle();
        args.putInt(STATUS_TYPE, statusType);
        args.putString(NAME, name);
        FocusPurchaseListFragment fragment = new FocusPurchaseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static FocusPurchaseListFragment newInstance(String mouthDate, String name, int returnType, int productType) {

        Bundle args = new Bundle();
        args.putString(MOUTH_DATE, mouthDate);
        args.putString(NAME, name);
        args.putInt(RETURN_TYPE, returnType);
        args.putInt(PRODUCT_TYPE, productType);
        FocusPurchaseListFragment fragment = new FocusPurchaseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        if (getArguments() != null) {
            statusType = getArguments().getInt(STATUS_TYPE);
            name = getArguments().getString(NAME);
            mouthDate = getArguments().getString(MOUTH_DATE);
            returnType = getArguments().getInt(RETURN_TYPE);
            productType = getArguments().getInt(PRODUCT_TYPE);

            FocusPurchaseAdapter adapter = new FocusPurchaseAdapter(listData, R.layout.item_focus_purchase);
            setDefaultAdapter(adapter);
            getListData(currentPage);
        }
    }

    @Override
    protected void refresh() {
        currentPage = FIRST_PAGE;
        getListData(currentPage);
    }

    @Override
    protected void loadMore() {
        getListData(++currentPage);
    }

    private void getListData(int pageIndex) {
        if (TextUtils.isEmpty(mouthDate)) {//正常或异常列表
            viewModel.getList(pageIndex, statusType, name);
        } else {//退换货列表
            viewModel.getReturnOrReplaceList(pageIndex, returnType, productType, mouthDate, name);
        }
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