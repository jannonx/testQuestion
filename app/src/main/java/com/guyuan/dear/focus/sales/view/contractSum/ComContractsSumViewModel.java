package com.guyuan.dear.focus.sales.view.contractSum;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.sales.bean.ComContractsBean;

import java.util.Random;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/29 15:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComContractsSumViewModel extends BaseViewModel {
    private MutableLiveData<ComContractsBean> comContractSum = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickSelectDate = new MutableLiveData<>();
    private MutableLiveData<Long> selectDate = new MutableLiveData<Long>(0L);

    public MutableLiveData<ComContractsBean> getComContractSum() {
        return comContractSum;
    }


    public MutableLiveData<View.OnClickListener> getOnClickSelectDate() {
        return onClickSelectDate;
    }

    public void setOnClickSelectDate(View.OnClickListener listener) {
        this.onClickSelectDate.postValue(listener);
    }

    public MutableLiveData<Long> getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(long date) {
        this.selectDate.postValue(date);
        updateComContractSumByDate(date);
    }

    private void updateComContractSumByDate(long date) {
        ComContractsBean value = comContractSum.getValue();
        if(value==null){
            value = new ComContractsBean();
        }
        Random random = new Random(System.currentTimeMillis());
        value.setClientTotal(random.nextInt(25));
        value.setContractsTotal(random.nextInt(100));
        value.setPreAnnualDelivers(random.nextInt(50));
        value.setNewContracts(random.nextInt(50));
        value.setFinishedContracts(random.nextInt(10));
        value.setExecutingContracts(random.nextInt(10));
        value.setUnfinishedContracts(random.nextInt(50));
        value.setExceptionContracts(random.nextInt(10));
        comContractSum.postValue(value);
    }
}
