package com.guyuan.dear.busbean;

import com.guyuan.dear.work.contractPause.beans.MyApplyBean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/13 11:38
 * @company: 固远（深圳）信息技术有限公司
 **/
public class UpdateRestartApplyListEvent {
    private MyApplyBean bean;

    public MyApplyBean getBean() {
        return bean;
    }

    public void setBean(MyApplyBean bean) {
        this.bean = bean;
    }
}
