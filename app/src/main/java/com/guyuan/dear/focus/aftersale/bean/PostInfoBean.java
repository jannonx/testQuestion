package com.guyuan.dear.focus.aftersale.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/26 17:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostInfoBean implements Serializable {
    /**
     * 更新时间
     */
    private String idea;
    /**
     * 图片（部分业务有图片上传,没有则不用传）
     */
    private String imgUrl;
    /**
     * 标题对应的标识key（对应不同业务状态的枚举标识值）
     */
    private int key;
    /**
     * 父节点ID(针对某条建议进行回复,为空就是所有)
     */
    private long parentId;
    /**
     * 主键ID（业务主键）
     */
    private long psAuditId;
    /**
     * 标题（可以为某个业务的显示标识）
     */
    private String title;
    /**
     * 业务类型
     */
    private int type;

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getPsAuditId() {
        return psAuditId;
    }

    public void setPsAuditId(long psAuditId) {
        this.psAuditId = psAuditId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
