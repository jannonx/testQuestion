package com.guyuan.dear.mine.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2021/1/4 17:18
 * @company: 固远（深圳）信息技术有限公司
 */
public class PrivacyPolicyDataBean implements Serializable {
    private long id;
    private String descHtml;

    public String getDescHtml() {
        return descHtml;
    }

    public void setDescHtml(String descHtml) {
        this.descHtml = descHtml;
    }
}
