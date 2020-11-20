package com.guyuan.dear.focus.purchase.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseDetailBean;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseListBean;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseOverviewBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @description:
 * @author: 唐力
 * @since: 2020/9/17 11:03
 * @company: 固远（深圳）信息技术有限公司
 */
public interface FocusPurchaseApiService extends BaseApiService {
    String PURCHASE_OVERVIEW = BASE + "purchasecontracitem/findOverview";
    String PURCHASE_LIST = BASE + "purchasecontracitem/findList";
    String PURCHASE_DETAIL = BASE + "purchasecontracitem/findRetuExchList";
    String PURCHASE_RETURN_OR_REPLACE_LIST = BASE + "purchasecontracitem/findReturnList";
    String MOUTH_DATE = "mouthDate";
    String NAME = "name";
    String ITEM_ID = "itemId";
    String RETURN_TYPE = "returnType";

    //采购概览
    @GET(PURCHASE_OVERVIEW)
    Observable<ResultBean<PurchaseOverviewBean>> getPurchaseOverview(@Query(MOUTH_DATE) String mouthDate, @Query(NAME) String name);

    //采购列表
    @POST(PURCHASE_LIST)
    Observable<ResultBean<PurchaseListBean>> getPurchaseList(@Body RequestBody body);

    //采购退换货列表
    @POST(PURCHASE_RETURN_OR_REPLACE_LIST)
    Observable<ResultBean<PurchaseListBean>> getPurchaseReturnOrReplaceList(@Body RequestBody body);

    //采购详情
    @GET(PURCHASE_DETAIL)
    Observable<ResultBean<PurchaseDetailBean>> getPurchaseDetail(@Query(ITEM_ID) int itemID,
                                                                 @Query(RETURN_TYPE) int returnID);


} 