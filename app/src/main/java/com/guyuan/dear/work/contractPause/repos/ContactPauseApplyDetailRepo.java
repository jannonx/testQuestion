package com.guyuan.dear.work.contractPause.repos;

import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.work.contractPause.beans.myPauseApplyDetail.MyApplyDetailBean;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/23 16:16
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContactPauseApplyDetailRepo {
//    public Disposable getMyApplyDetail(int id,  DearNetHelper.NetCallback<MyApplyDetailBean> callback){
//        return DearNetHelper.getInstance().getMyApplyDetailFromNet(id,callback);
//    }

    public Disposable getContractDetailFromNet(long examineId,DearNetHelper.NetCallback<DetailContractApplyBean> callback) {
        return DearNetHelper.getInstance().getContractApplyDetail((int) examineId, callback);
    }
}
