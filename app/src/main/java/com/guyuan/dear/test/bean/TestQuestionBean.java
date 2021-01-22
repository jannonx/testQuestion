package com.guyuan.dear.test.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: Jannonx
 * @since: 2021/1/21 01:00
 */
public class TestQuestionBean implements Serializable {
    /**
     * 标识id
     */
    private long id;
    /**
     * 题目
     */
    private String title;
    /**
     * 选择项
     */
    private ItemQuestionType selectItem;
    /**
     * 正确项
     */
    private ItemQuestionType rightItem;
    /**
     * 选择结果
     */
    private ItemQuestionResultType resultType;
    /**
     * 选择项集合
     */
    private List<ItemQuestionBean> itemList;

    /**
     * 解析
     */
    private String parse;

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

    public ItemQuestionType getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(ItemQuestionType selectItem) {
        this.selectItem = selectItem;
    }

    public ItemQuestionType getRightItem() {
        return rightItem;
    }

    public void setRightItem(ItemQuestionType rightItem) {
        this.rightItem = rightItem;
    }

    public ItemQuestionResultType getResultType() {
        if (getSelectItem() == null) {
            return ItemQuestionResultType.TYPE_NO_SELECT;
        }
        return selectItem == rightItem ? ItemQuestionResultType.TYPE_RIGHT
                : ItemQuestionResultType.TYPE_WRONG;
    }

    public void setResultType(ItemQuestionResultType resultType) {
        this.resultType = resultType;
    }

    public List<ItemQuestionBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemQuestionBean> itemList) {
        this.itemList = itemList;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }
}
