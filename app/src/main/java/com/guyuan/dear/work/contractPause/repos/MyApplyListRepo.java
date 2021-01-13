package com.guyuan.dear.work.contractPause.repos;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/23 14:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyListRepo {
    public Disposable getMyPauseApplyList(int pageIndex, int pageSize, DearNetHelper.NetCallback<List<MyApplyBean>> callback){
        return DearNetHelper.getInstance().getMyPauseApplyList(pageIndex,pageSize,callback);
    }

    public Disposable getMyRestartApplyList(int pageIndex, int pageSize, DearNetHelper.NetCallback<List<MyApplyBean>> callback){
        return DearNetHelper.getInstance().getMyRestartApplyList(pageIndex,pageSize,callback);
    }

    public Disposable getMyPauseApplyByExamineId(int examineId,DearNetHelper.NetCallback<MyApplyBean> callback){
        return DearNetHelper.getInstance().getMyPauseContractApplyByExamineId(examineId,callback);
    }

    public Disposable getMyRestartApplyByExamineId(int examineId,DearNetHelper.NetCallback<MyApplyBean> callback){
        return DearNetHelper.getInstance().getMyRestartContractApplyByExamineId(examineId,callback);
    }

}
