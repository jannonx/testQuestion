package com.guyuan.dear.focus.contract.view.lastYearList;

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
 * @since: 2021/1/4 10:44
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LastYearDeliverViewModel extends BaseDearViewModel {
    public MutableLiveData<List<BaseContractBean>> contractList = new MutableLiveData<>(new ArrayList<>());
    public int searchType = BaseContractBean.CONTRACT_TYPE_PRE_ANNUAL_DELIVERS;
    private ContractListRepo repo = new ContractListRepo();
    private int pageIndex = 1;
    public MutableLiveData<Boolean> isAllLoaded = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> shouldShowNoData = new MutableLiveData<>(true);

    public void loadContractListFromNet(long date) {
        Disposable disposable = repo.loadContractListFromNet(searchType, date,
                pageIndex++, 50, new BaseNetCallback<List<BaseContractBean>>() {
                    @Override
                    protected void handleResult(List<BaseContractBean> result) {
                        if (result.isEmpty()) {
                            isAllLoaded.setValue(true);
                            if (contractList.getValue().isEmpty()) {
                                shouldShowNoData.postValue(true);
                            }
                        } else {
                            contractList.getValue().addAll(result);
                            contractList.postValue(contractList.getValue());
                            shouldShowNoData.postValue(false);
                        }
                    }
                });
        addSubscription(disposable);
    }
}
