package com.guyuan.dear.focus.contract.repos;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.focus.contract.bean.BaseContractBean;
import com.guyuan.dear.net.DearNetHelper;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/9 20:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AllContractListRepo extends BaseDearViewModel {
    public Disposable getAllContractListFromNet(int pageIndex, int pageSize, DearNetHelper.NetCallback<List<BaseContractBean>> callback){
        return DearNetHelper.getInstance().getAllContractList(pageIndex,pageSize,callback);
    }
}
