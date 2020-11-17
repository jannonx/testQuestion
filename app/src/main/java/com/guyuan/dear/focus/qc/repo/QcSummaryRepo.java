package com.guyuan.dear.focus.qc.repo;

import com.guyuan.dear.focus.qc.beans.MaterialQcSumBean;
import com.guyuan.dear.focus.qc.beans.ProductQcSumBean;
import com.guyuan.dear.focus.qc.beans.QcSummaryBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/11 17:07
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSummaryRepo {

    public QcSummaryBean getQcSummaryByDate(long from,long to){
        QcSummaryBean bean = new QcSummaryBean();
        bean.setStartPeriod(from);
        bean.setEndPeriod(to);
        ProductQcSumBean pSum = new ProductQcSumBean();
        pSum.setStartPeriod(from);
        pSum.setEndPeriod(to);
        pSum.setPassNum(90);
        pSum.setRejectNum(10);
        pSum.setSampleSize(100);
        bean.setProductSum(pSum);
        MaterialQcSumBean mSum = new MaterialQcSumBean();
        mSum.setStartPeriod(from);
        mSum.setEndPeriod(to);
        mSum.setPassNum(578);
        mSum.setRejectNum(2);
        mSum.setSampleSize(580);
        bean.setMaterialSum(mSum);
        return bean;
    }


}
