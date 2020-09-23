package com.guyuan.dear.focus.hr.view.hrStaffStatusInfo;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:38
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffStatusInfoViewModel extends BaseViewModel {
    /**
     * ！有坑注意！
     * 子类必须要带有一个和父类一样的构造函数，如：LoginViewModel(Application app)。否则根据源码，在ViewModelProvider中生成时会报错。
     * by Leo
     *
     * @param application
     */
    public StaffStatusInfoViewModel(@NonNull Application application) {
        super(application);
    }
}
