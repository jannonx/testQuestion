package com.guyuan.dear.work.qc.repo;

import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
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

    public Disposable loadMaterialInfoFromNet(int projectId, DearNetHelper.NetCallback<List<MaterialInfo>> callback) {
//        List<MaterialInfo> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            MaterialInfo info = new MaterialInfo();
//            info.setComment("暂无评述");
//            info.setMaterialId("DEAR-MT-2020250"+i);
//            info.setMaterialName("材料"+(i+1));
//            info.setQuantity((i+1)*100);
//            info.setMaterialType("碳钢");
//            info.setUnit("块");
//            list.add(info);
//        }
//        return list;
        return DearNetHelper.getInstance().getMaterialListByProject(projectId,callback);
    }




}
