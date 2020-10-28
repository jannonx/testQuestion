package com.guyuan.dear.focus.contract.view.contractList;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 18:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractListViewModel extends BaseViewModel {
    private MutableLiveData<List<BaseContractBean>> contractList = new MutableLiveData<List<BaseContractBean>>();

    public MutableLiveData<List<BaseContractBean>> getContractList() {
        return contractList;
    }

    public void loadContractListFromNet(int contractType){
        List<BaseContractBean> value = contractList.getValue();
        if(value==null){
            value = new ArrayList<>();
        }
        for (int i=0;i<5;i++){
            BaseContractBean bean =new BaseContractBean();
            bean.setDate(System.currentTimeMillis());
            bean.setBuyer("深圳固远智能机器人有限公司");
            bean.setContractId("DEAR-2020/10/10");
            bean.setSalesPerson("莫大毛");
            value.add(bean);
        }

        contractList.postValue(value);
    }
}
