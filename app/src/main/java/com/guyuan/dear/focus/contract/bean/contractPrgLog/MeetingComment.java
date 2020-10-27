package com.guyuan.dear.focus.contract.bean.contractPrgLog;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 18:03
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MeetingComment {
    private String name;
    private String imgUrl;
    private String content;
    private String dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
