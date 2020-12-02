package com.guyuan.dear.focus.contract.repos;

import com.guyuan.dear.focus.contract.bean.BaseContractExcptBean;
import com.guyuan.dear.focus.contract.bean.ContractBean;
import com.guyuan.dear.focus.contract.bean.RestartedContractBean;
import com.guyuan.dear.net.DearNetHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/19 16:35
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractStatusListRepo {
    public Disposable getPauseContractList(int pageIndex, int pageSize, DearNetHelper.NetCallback<List<BaseContractExcptBean>> callback) {
        return DearNetHelper.getInstance().getMyPauseContractList(pageIndex, pageSize, callback);
    }

    public Disposable getRestartedContractList(int pageIndex, int pageSize, DearNetHelper.NetCallback<List<RestartedContractBean>> callback) {
        return DearNetHelper.getInstance().getMyRestartedContractList(pageIndex, pageSize, callback);
    }

    public Disposable getExceptionOrTotalContractList(int type, int pageIndex, String searchContent, DearNetHelper.NetCallback<List<ContractBean.ContentBean>> callback) {
        return DearNetHelper.getInstance().getExceptionOrTotalContractList(type, pageIndex, searchContent, callback);
    }
}
