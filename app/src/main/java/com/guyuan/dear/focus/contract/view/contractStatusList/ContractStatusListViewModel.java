package com.guyuan.dear.focus.contract.view.contractStatusList;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.focus.contract.bean.ContractBean;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;
import com.guyuan.dear.focus.contract.repos.ContractStatusListRepo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;
import com.sun.jna.platform.win32.Winspool;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 12:02
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractStatusListViewModel extends BaseDearViewModel {
    private ContractStatusListRepo repo = new ContractStatusListRepo();
    private MutableLiveData<List<BaseContractExcptBean>> pauseContractList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<RestartedContractBean>> restartContractList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<ContractBean.ContentBean>> exceptionContractList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<ContractBean.ContentBean>> totalContractList = new MutableLiveData<>(new ArrayList<>());
    private int pauseListPageIndex = 1;
    private MutableLiveData<Boolean> isAllPauseListLoaded = new MutableLiveData<>(false);
    private int restartListPageIndex = 1;
    private int exceptionListPageIndex = 1;
    private int totalListPageIndex = 1;
    private MutableLiveData<Boolean> isAllRestartListLoaded = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isAllExceptionListLoaded = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isAllTotalListLoaded = new MutableLiveData<>(false);
    private static final int PAGE_SIZE = 50;


    public MutableLiveData<List<BaseContractExcptBean>> getPauseContractList() {
        return pauseContractList;
    }

    public MutableLiveData<List<RestartedContractBean>> getRestartContractList() {
        return restartContractList;
    }


    public MutableLiveData<List<ContractBean.ContentBean>> getExceptionContractList() {
        return exceptionContractList;
    }

    public MutableLiveData<List<ContractBean.ContentBean>> getTotalContractList() {
        return totalContractList;
    }

    public MutableLiveData<Boolean> getIsAllPauseListLoaded() {
        return isAllPauseListLoaded;
    }

    public MutableLiveData<Boolean> getIsAllRestartListLoaded() {
        return isAllRestartListLoaded;
    }

    public MutableLiveData<Boolean> getIsAllExceptionListLoaded() {
        return isAllExceptionListLoaded;
    }

    public MutableLiveData<Boolean> getIsAllTotalListLoaded() {
        return isAllTotalListLoaded;
    }

    public Disposable getPauseContractsFromNet() {
//        List<BaseContractExcptBean> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            BaseContractExcptBean bean = new BaseContractExcptBean();
//            bean.setContractNum(System.currentTimeMillis()+"");
//            bean.setBuyer("深圳固远智能机器人有限公司");
//            if(i%2==0){
//                bean.setJudgement("国家政策暂停");
//                bean.setExceptionTag("暂停");
//                bean.setCause("客户要求此暂缓项目。");
//            }else {
//                bean.setJudgement("成本预算亏损导致终止");
//                bean.setExceptionTag("终止");
//                bean.setCause("需要和客户重新商定工程造价。");
//            }
//            list.add(bean);
//        }
//        contractList.postValue(list);
        return repo.getPauseContractList(pauseListPageIndex++, PAGE_SIZE, getPauseListCallback);
    }

    private DearNetHelper.NetCallback<List<BaseContractExcptBean>> getPauseListCallback
            = new DearNetHelper.NetCallback<List<BaseContractExcptBean>>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.setValue(true);

        }

        @Override
        public void onGetResult(List<BaseContractExcptBean> result) {
            isShowLoading.setValue(false);
            if (result.isEmpty()) {
                isAllPauseListLoaded.setValue(true);
                ToastUtils.showShort(DearApplication.getInstance(), "已经全部加载完毕。");
            } else {
                pauseContractList.getValue().addAll(result);
                pauseContractList.postValue(pauseContractList.getValue());
            }
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.setValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
        }
    };

    public Disposable getRestartedContractFromNet() {
        return repo.getRestartedContractList(restartListPageIndex++, PAGE_SIZE, getRestartListCallback);
    }

    private DearNetHelper.NetCallback<List<RestartedContractBean>> getRestartListCallback
            = new DearNetHelper.NetCallback<List<RestartedContractBean>>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.setValue(true);

        }

        @Override
        public void onGetResult(List<RestartedContractBean> result) {
            isShowLoading.setValue(false);
            if (result.isEmpty()) {
                isAllRestartListLoaded.setValue(true);
                ToastUtils.showShort(DearApplication.getInstance(), "已经全部加载完毕。");
            } else {
                restartContractList.getValue().addAll(result);
                restartContractList.postValue(restartContractList.getValue());
            }
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.setValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
        }
    };


    public Disposable getExceptionContractList(int type, int pageIndex, String searchContent) {
        return repo.getExceptionOrTotalContractList(type, pageIndex, searchContent, new BaseNetCallback<List<ContractBean.ContentBean>>() {
            @Override
            protected void handleResult(List<ContractBean.ContentBean> result) {
                if (result.isEmpty()) {
                    isAllExceptionListLoaded.setValue(true);
                    ToastUtils.showShort(DearApplication.getInstance(), "已经全部加载完毕。");
                } else {
                    exceptionContractList.getValue().addAll(result);
                    exceptionContractList.postValue(exceptionContractList.getValue());
                }
            }
        });
    }



    public Disposable getTotalContractList(int type, int pageIndex, String searchContent) {
        return repo.getExceptionOrTotalContractList(type, pageIndex, searchContent, new BaseNetCallback<List<ContractBean.ContentBean>>() {
            @Override
            protected void handleResult(List<ContractBean.ContentBean> result) {
                if (result.isEmpty()) {
                    isAllTotalListLoaded.setValue(true);
                    ToastUtils.showShort(DearApplication.getInstance(), "已经全部加载完毕。");
                } else {
                    totalContractList.getValue().addAll(result);
                    totalContractList.postValue(totalContractList.getValue());
                }
            }
        });
    }


}
