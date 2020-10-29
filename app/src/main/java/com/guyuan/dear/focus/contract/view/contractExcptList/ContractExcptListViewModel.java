package com.guyuan.dear.focus.contract.view.contractExcptList;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.contract.adapter.ContractExcptListAdapter;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:02
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractExcptListViewModel extends BaseViewModel {
    private MutableLiveData<List<BaseContractExcptBean>> contractList = new MutableLiveData<>();
    private MutableLiveData<ContractExcptListAdapter.ItemClickListener> itemClickListener = new MutableLiveData<>();

    public MutableLiveData<List<BaseContractExcptBean>> getContractList() {
        return contractList;
    }

    public MutableLiveData<ContractExcptListAdapter.ItemClickListener> getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ContractExcptListAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener.postValue(itemClickListener);
    }

    public void getExcptContractsFromNet() {
        List<BaseContractExcptBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BaseContractExcptBean bean = new BaseContractExcptBean();
            bean.setContractId(System.currentTimeMillis()+"");
            bean.setBuyer("深圳固远智能机器人有限公司");
            if(i%2==0){
                bean.setJudgement("国家政策暂停");
                bean.setExceptionTag("暂停");
                bean.setCause("客户要求此暂缓项目。");
            }else {
                bean.setJudgement("成本预算亏损导致终止");
                bean.setExceptionTag("终止");
                bean.setCause("需要和客户重新商定工程造价。");
            }
            list.add(bean);
        }
        contractList.postValue(list);
    }
}
