package com.guyuan.dear.focus.contract.repos;

import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.net.DearNetHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/19 14:11
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractListRepo {

    public Disposable loadContractListFromNet(int contractType, long date, int pageIndex, int pageSize,
                                              DearNetHelper.NetCallback<List<BaseContractBean>> callback) {

        return DearNetHelper.getInstance().getContractListByTypeAndDate(
                contractType, date, pageIndex, pageSize, callback);
    }
}
