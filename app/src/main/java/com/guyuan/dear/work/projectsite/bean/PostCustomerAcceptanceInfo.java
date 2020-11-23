package com.guyuan.dear.work.projectsite.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/23 20:32
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostCustomerAcceptanceInfo implements Serializable {
    /**
     * 明细主表ID
     */
    private long id;
    /**
     * 状态（10待开始，20安装中，30完成,40暂停）
     */
    private int checkStatus;
    /**
     * 备注原因
     */
    private String checkRemark;
    /**
     * 附件图片
     */
    private List<String> checkUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public List<String> getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(List<String> checkUrl) {
        this.checkUrl = checkUrl;
    }
}
