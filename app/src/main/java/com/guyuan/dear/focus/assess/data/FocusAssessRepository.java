package com.guyuan.dear.focus.assess.data;

import com.guyuan.dear.focus.assess.api.FocusAssessApiService;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:22
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusAssessRepository {
    private FocusAssessApiService focusAssessApiService;

    public FocusAssessRepository(FocusAssessApiService focusAssessApiService) {
        this.focusAssessApiService = focusAssessApiService;
    }


}
