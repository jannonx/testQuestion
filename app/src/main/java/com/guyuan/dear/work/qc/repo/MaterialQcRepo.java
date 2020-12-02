package com.guyuan.dear.work.qc.repo;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.MaterialInfo;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 12:02
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcRepo extends BaseQcRepo {

    public Disposable getMaterialProjectListFromNet(DearNetHelper.NetCallback<List<BaseProjectBean>> callback){
        return DearNetHelper.getInstance().getMaterialProjectList(callback);

    }

    public Disposable loadMaterialInfoFromNet(int projectId, DearNetHelper.NetCallback<List<MaterialInfo>> callback) {
        return DearNetHelper.getInstance().getMaterialListByProject(projectId,callback);
    }




}
