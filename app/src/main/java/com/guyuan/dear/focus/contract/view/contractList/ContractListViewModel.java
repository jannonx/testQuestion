package com.guyuan.dear.focus.contract.view.contractList;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.focus.contract.repos.ContractListRepo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 18:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractListViewModel extends BaseViewModel {
    private ContractListRepo repo = new ContractListRepo();
    private MutableLiveData<List<BaseContractBean>> contractList = new MutableLiveData<List<BaseContractBean>>(new ArrayList<>());
    private MutableLiveData<Boolean> isAllLoaded = new MutableLiveData<>(false);
    private int pageIndex;
    private static final int PAGE_SIZE = 50;

    public MutableLiveData<List<BaseContractBean>> getContractList() {
        return contractList;
    }

    public Disposable loadContractListFromNet(int contractType, long date) {
        if(isAllLoaded.getValue()){
            return new Disposable() {
                @Override
                public void dispose() {

                }

                @Override
                public boolean isDisposed() {
                    return false;
                }
            };
        }
        return repo.loadContractListFromNet(contractType, date,
                pageIndex++, PAGE_SIZE, getContractListCallback);
    }

    private DearNetHelper.NetCallback<List<BaseContractBean>> getContractListCallback
            = new DearNetHelper.NetCallback<List<BaseContractBean>>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);

        }

        @Override
        public void onGetResult(List<BaseContractBean> result) {
            isShowLoading.postValue(false);
            if(result.isEmpty()){
                ToastUtils.showShort(DearApplication.getInstance(),"已经全部加载完毕");
                isAllLoaded.setValue(true);
            }else {
                contractList.getValue().addAll(result);
                contractList.postValue(contractList.getValue());
            }

        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(),error.getMessage());
        }
    };

    public MutableLiveData<Boolean> getIsAllLoaded() {
        return isAllLoaded;
    }
}
