package com.guyuan.dear.work.qc.repo;

import com.guyuan.dear.base.fragment.DearBaseViewModel;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.work.qc.beans.BaseProductBatchInfo;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.ProductInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 17:42
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcRepo extends BaseQcRepo {

//    public List<BaseProductBatchInfo> loadBatchInfoListFromNet() {
//        List<BaseProductBatchInfo> batchInfos = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            BaseProductBatchInfo info = new BaseProductBatchInfo();
//            info.setBatchId("DEAR-20201222-B"+i);
//            List<ProductInfo> list = new ArrayList<>();
//            for (int i1 = 0; i1 < 3; i1++) {
//                ProductInfo info1 = new ProductInfo();
//                info1.setProductId("DEAR-PROD00"+(i1+i));
//                info1.setProductName("空气分离机");
//                info1.setQuantity(new Random().nextInt(500));
//                info1.setUnit("台");
//                list.add(info1);
//            }
//            info.setProducts(list);
//            batchInfos.add(info);
//        }
//        return batchInfos;
//    }

    public Disposable getProductBatchListByProjectId(int projectId, DearNetHelper.NetCallback<List<BaseProductBatchInfo>> callback){
        return DearNetHelper.getInstance().getProductListByProjectId(projectId,callback);
    }
}
