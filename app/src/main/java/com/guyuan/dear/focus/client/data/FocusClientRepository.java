package com.guyuan.dear.focus.client.data;


import com.guyuan.dear.focus.client.api.FocusClientApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientRepository {
    private FocusClientApiService clientApiService;

    public FocusClientRepository(FocusClientApiService clientApiService) {
        this.clientApiService = clientApiService;
    }


}
