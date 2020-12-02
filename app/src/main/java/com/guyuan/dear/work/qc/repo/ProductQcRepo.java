package com.guyuan.dear.work.qc.repo;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.work.qc.beans.BaseProductBatchInfo;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 17:42
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcRepo extends BaseQcRepo {

    public Disposable getProductProjectListFromNet(DearNetHelper.NetCallback<List<BaseProjectBean>> callback){
        return DearNetHelper.getInstance().getProductProjectList(callback);
    }

    public Disposable getProductBatchListByProjectId(int projectId, DearNetHelper.NetCallback<List<BaseProductBatchInfo>> callback){
        return DearNetHelper.getInstance().getProductListByProjectId(projectId,callback);
    }
}
