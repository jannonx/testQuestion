package com.guyuan.dear.focus.purchase.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.focus.purchase.api.FocusPurchaseApiService;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseDetailBean;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseListBean;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseOverviewBean;
import com.guyuan.dear.focus.purchase.ui.FocusPurchaseOverviewFragment;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @description:
 * @author: 唐力
 * @since: 2020/9/17 11:07
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusPurchaseViewModel extends BaseViewModel {
    private FocusPurchaseApiService apiService;
    private MutableLiveData<PurchaseOverviewBean> purchaseOverviewMLD = new MutableLiveData<>();
    private MutableLiveData<PurchaseListBean> purchaseListMLD = new MutableLiveData<>();
    private MutableLiveData<PurchaseListBean> purchaseExceptionListMLD = new MutableLiveData<>();
    private MutableLiveData<PurchaseDetailBean> purchaseDetailMLD = new MutableLiveData<>();
    private MutableLiveData<PurchaseListBean> purchaseReturnOrReplaceListMLD = new MutableLiveData<>();


    @ViewModelInject
    public FocusPurchaseViewModel(FocusPurchaseRepository focusPurchaseRepository) {
        apiService = focusPurchaseRepository.getFocusPurchaseApiService();
    }

    public MutableLiveData<PurchaseOverviewBean> getPurchaseOverviewMLD() {
        return purchaseOverviewMLD;
    }

    public MutableLiveData<PurchaseListBean> getPurchaseListMLD() {
        return purchaseListMLD;
    }

    public MutableLiveData<PurchaseDetailBean> getPurchaseDetailMLD() {
        return purchaseDetailMLD;
    }

    public MutableLiveData<PurchaseListBean> getPurchaseReturnOrReplaceListMLD() {
        return purchaseReturnOrReplaceListMLD;
    }

    public MutableLiveData<PurchaseListBean> getPurchaseExceptionListMLD() {
        return purchaseExceptionListMLD;
    }

    //获取概览
    public void getOverView(String mouthDate, String name) {
        RxJavaHelper.build(this, apiService.getPurchaseOverview(mouthDate, name))
                .getHelper().flow(purchaseOverviewMLD);
    }

    //获取正常或异常列表
    public void getList(int pageIndex,int type, String name) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageSize(pageIndex);
        requestBody.setPageNum(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setName(name);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this,
                apiService.getPurchaseList(CommonUtils.getCommonRequestBody(requestBody)))
                .getHelper().flow(getType(type));
    }

    public MutableLiveData<PurchaseListBean> getType(int type) {
        switch (type) {
            case FocusPurchaseOverviewFragment.TOTAL:

                return purchaseListMLD;

            case FocusPurchaseOverviewFragment.EXCEPTION:

                return purchaseExceptionListMLD;

            default:

                return purchaseListMLD;
        }
    }

    //获取列表详情
    public void getDetail(int itemID, int returnID) {
        RxJavaHelper.build(this, apiService.getPurchaseDetail(itemID, returnID))
                .getHelper().flow(purchaseDetailMLD);
    }

    //获取退换货列表
    public void getReturnOrReplaceList(int pageIndex,int returnType, int productType, String mouthDate, String name) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageSize(pageIndex);
        requestBody.setPageNum(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setReturnType(returnType);
        filtersBean.setProductType(productType);
        filtersBean.setMouthDate(mouthDate);
        filtersBean.setName(name);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this, apiService.
                getPurchaseReturnOrReplaceList(CommonUtils.getCommonRequestBody(requestBody)))
                .getHelper().flow(purchaseReturnOrReplaceListMLD);
    }


}
