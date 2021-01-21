package com.guyuan.dear.test.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2021/1/21 01:00
 * @company: 固远（深圳）信息技术有限公司
 */
public class ItemQuestionBean implements Serializable {
    private ItemQuestionType itemType;
    private String content;
    private int selectIndex=-1;

    public ItemQuestionBean() {
    }

    public ItemQuestionBean(ItemQuestionType itemType, String content) {
        this.itemType = itemType;
        this.content = content;
    }

    public ItemQuestionType getItemType() {
        return itemType;
    }

    public void setItemType(ItemQuestionType itemType) {
        this.itemType = itemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    @Override
    public String toString() {
        return "ItemQuestionBean{" +
                "itemType=" + itemType.getSign() +
                ", content='" + content + '\'' +
                ", selectIndex=" + selectIndex +
                '}';
    }
}
