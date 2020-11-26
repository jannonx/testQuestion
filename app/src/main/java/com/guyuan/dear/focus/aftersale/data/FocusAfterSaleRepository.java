package com.guyuan.dear.focus.aftersale.data;


import com.example.httplibrary.bean.RefreshBean;
import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.focus.aftersale.api.FocusAfterSaleApiService;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;


import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusAfterSaleRepository {
    private FocusAfterSaleApiService apiService;

    public FocusAfterSaleRepository(FocusAfterSaleApiService focusAfterSaleApiService) {
        this.apiService = focusAfterSaleApiService;
    }


    Observable<ResultBean<RefreshBean<AfterSaleBean>>> getAfterSaleList(RequestBody body) {
        return apiService.getAfterSaleList(body);
    }

    Observable<ResultBean<AfterSaleBean>> getAfterSaleDetail(long id) {
        return apiService.getAfterSaleDetail(id);
    }
}
