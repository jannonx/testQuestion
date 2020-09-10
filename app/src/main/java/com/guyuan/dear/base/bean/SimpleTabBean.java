package com.guyuan.dear.base.bean;


import java.io.Serializable;
import java.util.List;


/**
 * @description:
 * @author: tl
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class SimpleTabBean implements Serializable {
    private long id;
    private String title;
    private boolean isSelected;
    private List<SimpleTabBean> childList;

    public SimpleTabBean() {
    }

    public SimpleTabBean(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<SimpleTabBean> getChildList() {
        return childList;
    }

    public void setChildList(List<SimpleTabBean> childList) {
        this.childList = childList;
    }
}
