package com.guyuan.dear.work.contractPause.views.myAppliedList;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.work.contractPause.adapters.MyPauseApplyListAdapter;
import com.guyuan.dear.work.contractPause.beans.ContractApplyBean;
import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyListViewModel extends BaseViewModel {
    private MutableLiveData<List<MyPauseApplyBean>> applyList = new MutableLiveData<>();
    private MutableLiveData<MyPauseApplyListAdapter.MyPauseListItemClickListener> onClickListItem =
            new MutableLiveData<>();

    public MutableLiveData<List<MyPauseApplyBean>> getApplyList() {
        return applyList;
    }


    public MutableLiveData<MyPauseApplyListAdapter.MyPauseListItemClickListener> getOnClickListItem() {
        return onClickListItem;
    }

    public void setOnClickListItem(MyPauseApplyListAdapter.MyPauseListItemClickListener onClickListItem) {
        this.onClickListItem.postValue(onClickListItem);
    }

    /**
     * 生成模拟数据，显示在主界面UI
     * @param uid
     */
    public void getListFromNet(String uid){
        List<MyPauseApplyBean> list = new ArrayList<>();
        int states[] = new int[]{MyPauseApplyBean.APPLY_PENDING_FOR_START,
                MyPauseApplyBean.APPLY_PROCESSING,MyPauseApplyBean.APPLY_APPROVED,MyPauseApplyBean.APPLY_REJECTED};
        String judements[] = new String[]{
                "国际政策维度","项目成本维度","维护成本维度"
        };
        for (int i = 0; i < 25; i++) {
            MyPauseApplyBean bean = new MyPauseApplyBean();
            bean.setState(states[i%states.length]);
            bean.setBuyer("北京天行健科技有限公司");
            bean.setJudgement(judements[i%judements.length]);
            bean.setDate(System.currentTimeMillis());
            bean.setContractId(System.currentTimeMillis()+"");
            list.add(bean);
        }
        applyList.postValue(list);
    }
}
