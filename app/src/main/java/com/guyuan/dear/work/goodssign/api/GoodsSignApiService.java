package com.guyuan.dear.work.goodssign.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.work.goodssign.data.bean.GoodsDetailBean;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignDetailBean;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignListBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 19:30
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface GoodsSignApiService extends BaseApiService {
    String GOODS_SIGN_LIST = BASE + "purchasecontracitem/findReceiveList";
    String GOODS_SIGN_DETAIL = BASE + "purchaseContract/findById";
    String GOODS_SIGN_ITEM_DETAIL = BASE + "purchasecontracitem/findById";
    String GOODS_SIGN_ALL_SIGN = BASE + "purchasecontracitem/oneClickConfirmation";
    String GOODS_SIGN = BASE + "purchasecontracitem/confirmById";

    final String CONTRACT_ID = "contractId";
    final String PRODUCT_ID = "productId";
    final String ITEM_ID = "itemId";

    //获取货物签收列表
    @POST(GOODS_SIGN_LIST)
    Observable<ResultBean<GoodsSignListBean>> getGoodsSignList(@Body RequestBody body);

    //获取货物签收详情
    @GET(GOODS_SIGN_DETAIL)
    Observable<ResultBean<GoodsSignDetailBean>> getGoodsSignDetail(@Query(CONTRACT_ID) int contractID);

    //获取货物详情
    @GET(GOODS_SIGN_ITEM_DETAIL)
    Observable<ResultBean<GoodsDetailBean>> getGoodsSignItemDetail(@Query(PRODUCT_ID) int productID);

    //全部签收
    @GET(GOODS_SIGN_ALL_SIGN)
    Observable<ResultBean<Integer>> signAll(@Query(CONTRACT_ID) int contractID);

    //签收
    @GET(GOODS_SIGN)
    Observable<ResultBean<Integer>> sign(@Query(ITEM_ID) int itemID);
}