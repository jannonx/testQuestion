package com.guyuan.dear.customizeview.editListView;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/12 12:00
 * @company : 固远（深圳）信息技术有限公司
 **/

public class EditListViewBean {
    private boolean editable;  //是否可编辑
    private String content;    //内容

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}