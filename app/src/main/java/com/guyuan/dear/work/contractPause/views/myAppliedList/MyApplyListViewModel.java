package com.guyuan.dear.work.contractPause.views.myAppliedList;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractPause.repos.MyApplyListRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyListViewModel extends BaseViewModel {
    private MyApplyListRepo repo = new MyApplyListRepo();
    private MutableLiveData<List<MyApplyBean>> pauseApplyList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<MyApplyBean>> restartApplyList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isLoadAllPauseList = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isLoadAllRestartList = new MutableLiveData<>(false);
    private static final int PAGE_SIZE = 50;
    private int pauseApplyPageIndex = 1;
    private int restartApplyPageIndex = 1;

    public MutableLiveData<Boolean> getIsLoadAllPauseList() {
        return isLoadAllPauseList;
    }

    public MutableLiveData<Boolean> getIsLoadAllRestartList() {
        return isLoadAllRestartList;
    }

    public MutableLiveData<List<MyApplyBean>> getPauseApplyList() {
        return pauseApplyList;
    }

    public MutableLiveData<List<MyApplyBean>> getRestartApplyList() {
        return restartApplyList;
    }

    public Disposable updatePauseApplyListFromNet() {
        return repo.getMyPauseApplyList(pauseApplyPageIndex++, PAGE_SIZE, callBackGetPauseList);
    }

    private DearNetHelper.NetCallback<List<MyApplyBean>> callBackGetPauseList = new DearNetHelper.NetCallback<List<MyApplyBean>>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);
        }

        @Override
        public void onGetResult(List<MyApplyBean> result) {
            if (result != null) {
                if (result.isEmpty()) {
                    isLoadAllPauseList.postValue(true);
                    ToastUtils.showShort(DearApplication.getInstance(), "已经全部加载完毕。");
                } else {
                    pauseApplyList.getValue().addAll(result);
                    Collections.sort(pauseApplyList.getValue(), comparator);
                    pauseApplyList.postValue(pauseApplyList.getValue());
                }
            }
            isShowLoading.postValue(false);

        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
        }
    };

    private Comparator<MyApplyBean> comparator = new Comparator<MyApplyBean>() {
        @Override
        public int compare(MyApplyBean o1, MyApplyBean o2) {
            return (int) (o2.getApplyDate()-o1.getApplyDate());
        }
    };


    public Disposable updateRestartApplyListFromNet() {
        return repo.getMyRestartApplyList(restartApplyPageIndex++, PAGE_SIZE, callBackGetRestartList);
    }

    private DearNetHelper.NetCallback<List<MyApplyBean>> callBackGetRestartList = new DearNetHelper.NetCallback<List<MyApplyBean>>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);
        }

        @Override
        public void onGetResult(List<MyApplyBean> result) {
            if (result != null) {
                if (result.isEmpty()) {
                    isLoadAllRestartList.postValue(true);
                    ToastUtils.showShort(DearApplication.getInstance(), "已经全部加载完毕。");
                } else {
                    restartApplyList.getValue().addAll(result);
                    Collections.sort(restartApplyList.getValue(), comparator);
                    restartApplyList.postValue(restartApplyList.getValue());
                }
            }
            isShowLoading.postValue(false);

        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());
        }
    };

    public void clearPauseApplyList() {
        pauseApplyList.postValue(new ArrayList<>());
        isLoadAllPauseList.postValue(false);
        pauseApplyPageIndex = 1;
    }

    public void clearRestartApplyList() {
        restartApplyList.postValue(new ArrayList<>());
        isLoadAllRestartList.postValue(false);
        restartApplyPageIndex = 1;
    }


    /**
     * 更新列表中的特定item
     * @param applyBean
     */
    public void updatePauseApplyState(MyApplyBean applyBean) {
        if(pauseApplyList.getValue()!=null){
            List<MyApplyBean> value = pauseApplyList.getValue();
            for (int i = 0; i < value.size(); i++) {
                MyApplyBean bean = value.get(i);
                if(bean.getExamineId()==applyBean.getExamineId()){
                    bean.setApplyState(applyBean.getApplyState());
                    pauseApplyList.postValue(value);
                    break;
                }
            }
        }
    }

    public void updateRestartApplyState(MyApplyBean applyBean) {
        if(restartApplyList.getValue()!=null){
            List<MyApplyBean> value = restartApplyList.getValue();
            for (int i = 0; i < value.size(); i++) {
                MyApplyBean bean = value.get(i);
                if(bean.getExamineId()==applyBean.getExamineId()){
                    bean.setApplyState(applyBean.getApplyState());
                    restartApplyList.postValue(value);
                    break;
                }
            }
        }
    }
}
