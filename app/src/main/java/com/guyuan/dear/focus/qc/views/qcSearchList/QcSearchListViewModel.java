package com.guyuan.dear.focus.qc.views.qcSearchList;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.qc.beans.GenericQcReport;
import com.guyuan.dear.focus.qc.repo.QcSearchListRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/4 10:46
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSearchListViewModel extends BaseDearViewModel {
    private QcSearchListRepo repo = new QcSearchListRepo();
    public MutableLiveData<List<GenericQcReport>> reportList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<Boolean> isShouldNotifyDataSetChange = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isAllLoaded = new MutableLiveData<>(false);
    private int currentPage = 1;
    private static final int PAGE_SIZE = 50;

    public void updateSearchResult(String keyWord, int searchType) {
        addSubscription(repo.getSearchList(keyWord, searchType, currentPage++, PAGE_SIZE, callback));
    }

    private BaseNetCallback<List<GenericQcReport>> callback = new BaseNetCallback<List<GenericQcReport>>() {
        @Override
        protected void handleResult(List<GenericQcReport> result) {
            if(result==null||result.isEmpty()){
                isAllLoaded.postValue(true);
            }else {
                reportList.getValue().addAll(result);
                isShouldNotifyDataSetChange.postValue(true);
            }
        }
    };


}
