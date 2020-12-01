package com.guyuan.dear.focus.client.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/12/1 11:53
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostClientInfo implements Serializable {
    /**
     * 主键ID
     */
    private long id;
    /**
     * 留言
     */
    private String content;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
