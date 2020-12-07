package com.guyuan.dear.focus.aftersale.bean;

import java.io.Serializable;

/**
 * @description: 我的关注--售后服务--回复意见集
 * @author: 许建宁
 * @since: 2020/11/25 19:47
 * @company: 固远（深圳）信息技术有限公司
 */
public class AfterSaleQuestionBean implements Serializable {
    /**
     * 主键
     */
    private long id;
    /**
     * 售后主键id
     */
    private long mainId;
    /**
     * 类型
     */
    private int type;
    /**
     * 问题描述
     */
    private String describe;
    /**
     * 回答
     */
    private String answer;
    /**
     * 问题图片
     */
    private String img;
    /**
     * 创建人
     */
    private long createBy;
    /**
     * dsasdasdsa
     */
    private String createTime;
    /**
     * 创建时间
     */
    private long updateBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 删除标记
     */
    private int delFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMainId() {
        return mainId;
    }

    public void setMainId(long mainId) {
        this.mainId = mainId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAnswer() {
        return answer == null ? "-" : answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
