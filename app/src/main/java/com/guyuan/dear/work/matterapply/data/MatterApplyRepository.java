package com.guyuan.dear.work.matterapply.data;

import com.guyuan.dear.work.matterapply.api.MatterApplyApiService;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 18:53
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyRepository {
    private MatterApplyApiService applyApiService;

    public MatterApplyRepository(MatterApplyApiService applyApiService) {
        this.applyApiService = applyApiService;
    }

    public MatterApplyApiService getApplyApiService() {
        return applyApiService;
    }
}