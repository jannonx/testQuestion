package com.guyuan.dear.focus.contract.view.contractSearchList;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.repos.ContractSearchListRepo;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/3 11:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractSearchListViewModel extends BaseDearViewModel {
    private ContractSearchListRepo repo = new ContractSearchListRepo();
    public MutableLiveData<List<BaseContractBean>> contractList = new MutableLiveData<>(new ArrayList<>());
    private int currentIndex = 1;
    private static final int PAGE_SIZE = 50;
    public MutableLiveData<Boolean> isLoadAll = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> shouldNotifyDateSetChange = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> shouldShowNoData = new MutableLiveData<>(true);

    public Disposable getContractListByComNameOrContractNo(String companyNameOrContractNo,int searchType) {
        return repo.getContractListFromNet(companyNameOrContractNo,searchType, currentIndex++, PAGE_SIZE, callback);
    }

    private BaseNetCallback<List<BaseContractBean>> callback = new BaseNetCallback<List<BaseContractBean>>() {
        @Override
        protected void handleResult(List<BaseContractBean> result) {
            if (result == null || result.isEmpty()) {
                isLoadAll.postValue(true);
                if (contractList.getValue().isEmpty()) {
                    shouldShowNoData.postValue(true);
                }
            } else {
                contractList.getValue().addAll(result);
                shouldNotifyDateSetChange.postValue(true);
                shouldShowNoData.postValue(false);
            }
        }
    };

}
