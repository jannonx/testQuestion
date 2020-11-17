package com.guyuan.dear.work.qc.beans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 12:28
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcSubmitBean extends BaseQcSubmitBean {
    private BaseProductBatchInfo batchInfo;

    public BaseProductBatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BaseProductBatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }
}
