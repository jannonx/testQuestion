package com.guyuan.dear.work.qc.views.productQc;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.qc.beans.BaseProductBatchInfo;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.beans.ProductInfo;
import com.guyuan.dear.work.qc.beans.ProductQcSubmitBean;
import com.guyuan.dear.work.qc.repo.ProductQcRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 10:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcViewModel extends BaseViewModel {
    /**
     * 数据源
     */
    public MutableLiveData<ArrayList<StaffBean>> qcCheckers = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> verifiers =new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<BaseProductBatchInfo> batchInfo = new MutableLiveData<>();
    public MutableLiveData<BaseProjectBean> projectInfo = new MutableLiveData<>();
    public MutableLiveData<BaseQcApproachBean> qcApproach = new MutableLiveData<>();
    public MutableLiveData<Integer> sampleSize = new MutableLiveData<>();
    public MutableLiveData<Integer> reportResult = new MutableLiveData<>();
    public MutableLiveData<String> comments = new MutableLiveData<>();
    public MutableLiveData<Boolean> isNeedVerify = new MutableLiveData<>();
    public MutableLiveData<Integer> judgeCondition = new MutableLiveData<>();




    /**
     * 点击事件：选择项目
     */
    public MutableLiveData<View.OnClickListener> onClickSelectProject = new MutableLiveData<>();
    /**
     * 点击事件：选择出厂编号
     */
    public MutableLiveData<View.OnClickListener> onClickSelectBatch = new MutableLiveData<>();
    /**
     * 点击事件：选择质检方式
     */
    public MutableLiveData<View.OnClickListener> onClickSelectQcApproach = new MutableLiveData<>();
    /**
     * 点击事件：选择质检人员
     */
    public MutableLiveData<View.OnClickListener> onClickAddQcCheckers = new MutableLiveData<>();
    /**
     * 点击事件：选择判定结果
     */
    public MutableLiveData<View.OnClickListener> onClickSelectResult = new MutableLiveData<>();
    /**
     * 点击事件：选择是否需要审批
     */
    public MutableLiveData<View.OnClickListener> onClickSelectIfNeedVerify = new MutableLiveData<>();
    /**
     * 点击事件：选择审批人
     */
    public MutableLiveData<View.OnClickListener> onClickAddVerifiers = new MutableLiveData<>();
    /**
     * 点击事件：提交
     */
    public MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();

    private ProductQcRepo repo = new ProductQcRepo();


    public List<BaseProductBatchInfo> loadBatchInfoListFromNet() {
        return repo.loadBatchInfoListFromNet();
    }

    public List<BaseProjectBean> getProjectListFromNet() {
        return repo.getProjectListFromNet();
    }


    public List<BaseQcApproachBean> getQcApproaches() {
        return repo.getQcApproaches();
    }

    public void updateProjectInfo(BaseProjectBean projectBean) {
        projectInfo.postValue(projectBean);
    }

    public void updateBatchInfo(BaseProductBatchInfo info) {
        batchInfo.postValue(info);
    }

    public void updateQcApproach(BaseQcApproachBean approachBean) {
        qcApproach.postValue(approachBean);
    }

    public void updateJudgeCondition(int selection) {
        judgeCondition.postValue(selection);
    }

    public void updateQcCheckers(ArrayList<StaffBean> staffs) {
        qcCheckers.postValue(staffs);
    }

    public void updateQcResult(int result) {
       reportResult.postValue(result);
    }

    public void updateIsVerify(boolean verify) {
        isNeedVerify.postValue(verify);
    }

    public void updateVerifiers(ArrayList<StaffBean> staffs) {
       verifiers.postValue(staffs);
    }


}
