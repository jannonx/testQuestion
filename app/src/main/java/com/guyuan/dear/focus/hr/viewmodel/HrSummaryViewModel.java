package com.guyuan.dear.focus.hr.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/18 11:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryViewModel extends BaseViewModel {
    /**
     * 0为显示公司总人员概况；1为显示公司外勤人员概况
     */
    private MutableLiveData<Integer> mode = new MutableLiveData<>();
    private MutableLiveData<Integer> staffTotal=new MutableLiveData<>();


    /**
     * ！有坑注意！
     * 子类必须要带有一个和父类一样的构造函数，如：LoginViewModel(Application app)。否则根据源码，在ViewModelProvider中生成时会报错。
     * by Leo
     *
     * @param application
     */
    public HrSummaryViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getMode() {
        return mode;
    }

    public void setMode(MutableLiveData<Integer> mode) {
        this.mode = mode;
    }

    public MutableLiveData<Integer> getStaffTotal() {
        return staffTotal;
    }

    public void setStaffTotal(MutableLiveData<Integer> staffTotal) {
        this.staffTotal = staffTotal;
    }
}
