package com.guyuan.dear.focus.contract.view.contractSum;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.ComContractsBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/29 15:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComContractsSumViewModel extends BaseViewModel {
    private MutableLiveData<ComContractsBean> comContractSum = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickSelectDate = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowPreAnnualDelivers = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowNewContracts = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowFinished = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowExecuting = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowUnfinished = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickShowExceptions = new MutableLiveData<>();

    private MutableLiveData<Long> selectDate = new MutableLiveData<Long>(System.currentTimeMillis());

    public MutableLiveData<ComContractsBean> getComContractSum() {
        return comContractSum;
    }


    public MutableLiveData<View.OnClickListener> getOnClickSelectDate() {
        return onClickSelectDate;
    }

    public void setOnClickSelectDate(View.OnClickListener listener) {
        this.onClickSelectDate.postValue(listener);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowPreAnnualDelivers() {
        return onClickShowPreAnnualDelivers;
    }

    public void setOnClickShowPreAnnualDelivers(View.OnClickListener listener) {
        onClickShowPreAnnualDelivers.postValue(listener);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowNewContracts() {
        return onClickShowNewContracts;
    }

    public void setOnClickShowNewContracts(View.OnClickListener listener) {
        this.onClickShowNewContracts.postValue(listener);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowFinished() {
        return onClickShowFinished;
    }

    public void setOnClickShowFinished(View.OnClickListener listener) {
        this.onClickShowFinished.postValue(listener);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowExecuting() {
        return onClickShowExecuting;
    }

    public void setOnClickShowExecuting(View.OnClickListener listener) {
        this.onClickShowExecuting.postValue(listener);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowUnfinished() {
        return onClickShowUnfinished;
    }

    public void setOnClickShowUnfinished(View.OnClickListener listener) {
        this.onClickShowUnfinished.postValue(listener);
    }

    public MutableLiveData<View.OnClickListener> getOnClickShowExceptions() {
        return onClickShowExceptions;
    }

    public void setOnClickShowExceptions(View.OnClickListener listener) {
        this.onClickShowExceptions.postValue(listener);
    }

    public MutableLiveData<Long> getSelectDate() {
        return selectDate;
    }

    public Disposable updateSummary(long date) {
        this.selectDate.postValue(date);
        return updateComContractSumByDate(date);
    }

    private Disposable updateComContractSumByDate(long date) {

        return DearNetHelper.getInstance().getContractSumByDate(date,
                new DearNetHelper.NetCallback<ComContractsBean>() {
            @Override
            public void onStart(Disposable disposable) {
                isShowLoading.postValue(true);

            }

            @Override
            public void onGetResult(ComContractsBean result) {
                isShowLoading.postValue(false);
                comContractSum.postValue(result);
            }

            @Override
            public void onError(Throwable error) {
                isShowLoading.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(),error.getMessage());
            }
        });
    }
}
