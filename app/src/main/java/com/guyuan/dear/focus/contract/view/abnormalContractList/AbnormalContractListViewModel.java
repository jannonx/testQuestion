package com.guyuan.dear.focus.contract.view.abnormalContractList;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.repos.AbnormalContractListRepo;
import com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/9 17:42
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AbnormalContractListViewModel extends BaseDearViewModel {
    private AbnormalContractListRepo repo = new AbnormalContractListRepo();
    public static final int SEARCH_TYPE = ContractStatusListFragment.STATUS_TYPE_EXCEPTION;
    public MutableLiveData<Boolean> shouldNotifyDataSetChange = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isLoadAll = new MutableLiveData<>(false);
    public MutableLiveData<List<BaseContractBean>> contractList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<Boolean> shouldShowNoData = new MutableLiveData<>(false);
    private int currentIndex = 1;
    private static final int PAGE_SIZE = 50;

    public void loadNextPageFromNet() {
        Disposable disposable = repo.getAbnormalContractListFromNet(currentIndex++, PAGE_SIZE, new BaseNetCallback<List<BaseContractBean>>() {
            @Override
            protected void handleResult(List<BaseContractBean> result) {
                if (result.isEmpty()) {
                    isLoadAll.postValue(true);
                    if (contractList.getValue().isEmpty()) {
                        shouldShowNoData.postValue(true);
                    }
                }else {
                    contractList.getValue().addAll(result);
                    shouldNotifyDataSetChange.postValue(true);
                }
            }
        });
        addSubscription(disposable);
    }


}
