package com.guyuan.dear.focus.assess.data;

import androidx.hilt.lifecycle.ViewModelInject;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/16 17:21
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusAssessViewModel extends BaseViewModel {
    private FocusAssessRepository focusAssessRepository;

    @ViewModelInject
    public FocusAssessViewModel(FocusAssessRepository focusAssessRepository) {
        this.focusAssessRepository = focusAssessRepository;
    }


}
