package com.guyuan.dear.focus.contract.repos;

import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.net.DearNetHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/3 11:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractSearchListRepo {
    public Disposable getContractListFromNet(String comNameOrContractNo, int pageIndex, int pageSize,
                                             DearNetHelper.NetCallback<List<BaseContractBean>> callback) {
        return DearNetHelper.getInstance()
                .getContractListByCompanyNameOrContractNo(comNameOrContractNo, pageIndex, pageSize, callback);
    }
}
