package com.guyuan.dear.base.fragment;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.utils.ToastUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/24 18:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseDearViewModel extends BaseViewModel {


    protected void showToast(String msg){
        ToastUtils.showShort(DearApplication.getInstance(),msg);
    }
}
