package com.guyuan.dear.mine.bean;

/**
 * @description:
 * @author: jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class MineRequestBody {
    /**
     * 创建人
     */
    private long createBy;
    /**
     * 反馈内容
     */
    private String remark;
    /**
     * 头像信息
     */
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
