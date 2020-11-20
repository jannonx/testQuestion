package com.guyuan.dear.work.goodssign.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.goodssign.api.GoodsSignApiService;
import com.guyuan.dear.work.goodssign.data.bean.GoodsDetailBean;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignDetailBean;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignListBean;

import io.reactivex.functions.Consumer;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:33
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignViewModel extends BaseViewModel {
    private GoodsSignApiService apiService;
    private MutableLiveData<GoodsSignListBean> goodsSignListMLD = new MutableLiveData<>();
    private MutableLiveData<GoodsSignDetailBean> goodsSignDetailMLD = new MutableLiveData<>();
    private MutableLiveData<GoodsDetailBean> goodsDetailMLD = new MutableLiveData<>();
    private MutableLiveData<Integer> allSignMLD = new MutableLiveData<>();
    private MutableLiveData<Integer> signMLD = new MutableLiveData<>();

    @ViewModelInject
    public GoodsSignViewModel(GoodsSignRepository repository) {
        apiService = repository.getApiService();
    }

    public GoodsSignApiService getApiService() {
        return apiService;
    }

    public MutableLiveData<GoodsSignListBean> getGoodsSignListMLD() {
        return goodsSignListMLD;
    }

    public MutableLiveData<GoodsSignDetailBean> getGoodsSignDetailMLD() {
        return goodsSignDetailMLD;
    }

    public MutableLiveData<GoodsDetailBean> getGoodsDetailMLD() {
        return goodsDetailMLD;
    }

    public MutableLiveData<Integer> getAllSignMLD() {
        return allSignMLD;
    }

    public MutableLiveData<Integer> getSignMLD() {
        return signMLD;
    }

    //获取货物签收列表
    public void getGoodsSignList(int pageIndex, String name) {
        ListRequestBody body = new ListRequestBody();
        body.setPageNum(pageIndex);
        body.setPageSize(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setName(name);
        body.setFilters(filtersBean);

        RxJavaHelper.build(this, apiService.getGoodsSignList(
                CommonUtils.getCommonRequestBody(body))).getHelper().flow(goodsSignListMLD);
    }

    //获取货物签收详情
    public void getGoodsSignDetail(int contractID) {
        RxJavaHelper.build(this, apiService.getGoodsSignDetail(contractID))
                .getHelper()
                .flow(goodsSignDetailMLD);
    }

    //获取货物详情
    public void getGoodsDetail(int productID) {
        RxJavaHelper.build(this, apiService.getGoodsSignItemDetail(productID))
                .getHelper()
                .flow(goodsDetailMLD);
    }

    //全部签收
    public void signAll(int contractID) {
        RxJavaHelper.build(this, apiService.signAll(contractID))
                .setPreTip("正在签收...")
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        int result = (int) o;
                        if (result == 0) {//成功关闭当前界面
                            getGoodsSignDetail(contractID);
                        }
                    }
                })
                .getHelper().flow();
    }

    //详情页签收
    public void signDetail(int itemID) {
        RxJavaHelper.build(this, apiService.sign(itemID))
                .setPreTip("正在签收...")
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        int result = (int) o;
                        if (result == 0) {//成功刷新界面
                            getGoodsDetail(itemID);
                        }
                    }
                })
                .getHelper().flow();
    }

    public void sign(int itemID, int contractID) {
        RxJavaHelper.build(this, apiService.sign(itemID))
                .setPreTip("正在签收...")
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        int result = (int) o;
                        if (result == 0) {//成功刷新界面
                            getGoodsSignDetail(contractID);
                        }
                    }
                })
                .getHelper().flow();
    }
}