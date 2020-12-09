package com.guyuan.dear.focus.produce.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/12/8 14:51
 * @company: 固远（深圳）信息技术有限公司
 */
public class IntentBean implements Serializable {
    /**
     * 创建生产计划的开始时间
     */
    private String startTime;
    /**
     * 创建生产计划的结束时间
     */
    private String endTime;

    /**
     * 类型
     */
    private ProductStatusType type;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ProductStatusType getType() {
        return type;
    }

    public void setType(ProductStatusType type) {
        this.type = type;
    }

    public IntentBean setStatusType(ProductStatusType type) {
        this.setType(type);
        return this;
    }
}
