package com.guyuan.dear.focus.contract.view.contractList;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.repos.ContractListRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 18:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractListViewModel extends BaseDearViewModel {
    private ContractListRepo repo = new ContractListRepo();
    private MutableLiveData<List<BaseContractBean>> contractList = new MutableLiveData<List<BaseContractBean>>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllLoaded = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> shouldShowNoData = new MutableLiveData<>(true);
    private int pageIndex = 1;
    private static final int PAGE_SIZE = 50;

    public MutableLiveData<List<BaseContractBean>> getContractList() {
        return contractList;
    }

    public Disposable loadContractListFromNet(int contractType, long date) {
        return repo.loadContractListFromNet(contractType, date,
                pageIndex++, PAGE_SIZE, new BaseNetCallback<List<BaseContractBean>>() {
                    @Override
                    protected void handleResult(List<BaseContractBean> result) {
                        if (result.isEmpty()) {
                            isAllLoaded.setValue(true);
                            if(contractList.getValue().isEmpty()){
                                shouldShowNoData.postValue(true);
                            }
                        } else {
                            contractList.getValue().addAll(result);
                            contractList.postValue(contractList.getValue());
                            shouldShowNoData.postValue(false);
                        }
                    }
                });
    }

    public MutableLiveData<Boolean> getIsAllLoaded() {
        return isAllLoaded;
    }
}
