package com.jannonx.electric.base.fragment;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.jannonx.electric.base.app.DearApplication;
import com.jannonx.electric.utils.ToastUtils;

/**
 * @author: Jannonx
 * @description:
 * @since: 2020/11/24 18:59
 **/
public class BaseDearViewModel extends BaseViewModel {


    protected void showToast(String msg){
        ToastUtils.showShort(DearApplication.getInstance(),msg);
    }
}
