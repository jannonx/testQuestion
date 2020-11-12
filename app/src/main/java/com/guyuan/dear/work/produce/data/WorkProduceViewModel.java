package com.guyuan.dear.work.produce.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;

import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/3 11:53
 * @company: 固远（深圳）信息技术有限公司
 */
public class WorkProduceViewModel extends BaseViewModel {
    private WorkProduceRepository repository;
    private MutableLiveData<RefreshBean<FocusProduceBean>> listEvent = new MutableLiveData<>();


    @ViewModelInject
    public WorkProduceViewModel(WorkProduceRepository workProduceRepository) {
        this.repository = workProduceRepository;
    }

    public MutableLiveData<RefreshBean<FocusProduceBean>> getListEvent() {
        return listEvent;
    }


    /**
     * 查询生产报告列表
     *
     * @param body 请求参数
     */
    public void getProduceList(RequestBody body) {
        Disposable disposable = RxJavaHelper.build(this, repository.getProduceList(body))
                .getHelper().flow(listEvent);
        addSubscription(disposable);
    }
}
