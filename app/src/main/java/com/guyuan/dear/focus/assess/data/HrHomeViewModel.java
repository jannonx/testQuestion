package com.guyuan.dear.focus.assess.data;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;

/**
 * @author: 廖华凯
 * @description: 首次使用在 {@link com.guyuan.dear.focus.assess.ui.HrHomeActivity}
 * @since: 2020/9/17 16:05
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrHomeViewModel extends BaseViewModel {
    public HrHomeViewModel() {
        super(DearApplication.getInstance());
    }
}
