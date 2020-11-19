package com.guyuan.dear.focus.contract.view.contractRestart;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.contract.adapter.RestartedContractListAdapter;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 15:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartContractListViewModel extends BaseViewModel {
    private MutableLiveData<List<RestartedContractBean>> contractList = new MutableLiveData<>();
    private MutableLiveData<RestartedContractListAdapter.OnItemClickListener> onItemClickListener = new MutableLiveData<>();

    public MutableLiveData<List<RestartedContractBean>> getContractList() {
        return contractList;
    }


    public MutableLiveData<RestartedContractListAdapter.OnItemClickListener> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(RestartedContractListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener.postValue(onItemClickListener);
    }

    public void getRestartedContractListFromNet() {
        List<RestartedContractBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RestartedContractBean bean = new RestartedContractBean();
            bean.setContractNum(System.currentTimeMillis()+"");
            bean.setBuyer("深圳科大旭飞导航有限公司");
            if(i%2==0){
                bean.setRestartCause("客户重新设计了需求");
                bean.setRestartStateTag("激活");
            }else {
                bean.setRestartCause("客户需要和工程部协商方案");
                bean.setRestartStateTag("待办");
            }
            list.add(bean);
        }

        contractList.postValue(list);
    }
}
