package com.guyuan.dear.focus.quality.data;


import com.guyuan.dear.focus.quality.api.FocusQualityApiService;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusQualityRepository {
    private FocusQualityApiService focusQualityApiService;

    public FocusQualityRepository(FocusQualityApiService focusQualityApiService) {
        this.focusQualityApiService = focusQualityApiService;
    }


}
