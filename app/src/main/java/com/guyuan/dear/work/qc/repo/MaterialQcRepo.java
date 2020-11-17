package com.guyuan.dear.work.qc.repo;

import com.guyuan.dear.work.qc.beans.MaterialInfo;
import com.guyuan.dear.work.qc.beans.MaterialSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 12:02
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcRepo extends BaseQcRepo {

    public List<MaterialInfo> loadMaterialInfoFromNet() {
        List<MaterialInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MaterialInfo info = new MaterialInfo();
            info.setComment("暂无评述");
            info.setMaterialId("DEAR-MT-2020250"+i);
            info.setMaterialName("材料"+(i+1));
            info.setQuantity((i+1)*100);
            info.setMaterialType("碳钢");
            info.setUnit("块");
            list.add(info);
        }
        return list;
    }

    public List<MaterialSpec> loadMaterialSpecsFromNet() {
        List<MaterialSpec> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MaterialSpec spec = new MaterialSpec();
            spec.setMaterialSpecId(i+"");
            spec.setMaterialSpecName("规格"+(i+1));
            list.add(spec);

        }
        return list;
    }
}
