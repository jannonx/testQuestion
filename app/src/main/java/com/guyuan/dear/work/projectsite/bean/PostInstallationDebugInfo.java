package com.guyuan.dear.work.projectsite.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 工程现场--检查提交信息
 * @author: 许建宁
 * @since: 2020/11/21 22:03
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostInstallationDebugInfo implements Serializable {

    /**
     * 明细主表ID
     */
    private long id;
    /**
     * 状态（10待开始，20安装中，30完成,40暂停）
     */
    private int status;
    /**
     * 备注原因
     */
    private String remark;
    /**
     * 附件图片
     */
    private List<String> imgUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }
}
