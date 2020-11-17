package com.guyuan.dear.focus.projectsite.data;


import com.guyuan.dear.focus.projectsite.api.FocusProjectSiteApiService;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/9/17 11:56
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProjectSiteRepository {
    private FocusProjectSiteApiService focusProjectSiteApiService;

    public FocusProjectSiteRepository(FocusProjectSiteApiService focusProjectSiteApiService) {
        this.focusProjectSiteApiService = focusProjectSiteApiService;
    }


}
