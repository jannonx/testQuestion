package com.guyuan.dear.work.contractPause.views.myAppliedList;

import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractPause.repos.MyApplyListRepo;

import java.util.ArrayList;
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


//    /**
//     * 生成模拟数据，显示在主界面UI
//     *
//     * @param uid
//     */
//    public void getListFromNet(String uid) {
//        List<MyApplyBean> list = new ArrayList<>();
//        int states[] = new int[]{MyApplyBean.APPLY_PENDING_FOR_START,
//                MyApplyBean.APPLY_PROCESSING, MyApplyBean.APPLY_APPROVED, MyApplyBean.APPLY_REJECTED};
//        String judements[] = new String[]{
//                "国际政策维度", "项目成本维度", "维护成本维度"
//        };
//        for (int i = 0; i < 25; i++) {
//            MyApplyBean bean = new MyApplyBean();
//            bean.setState(states[i % states.length]);
//            bean.setBuyer("北京天行健科技有限公司");
//            bean.setJudgement(judements[i % judements.length]);
//            bean.setDate(System.currentTimeMillis());
//            bean.setContractNum(System.currentTimeMillis() + "");
//            list.add(bean);
//        }
//        pauseApplyList.postValue(list);
//    }
}
