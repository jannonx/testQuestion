package com.guyuan.dear.base.bean;

import java.io.Serializable;

/**
 * @description: 部门bean
 * @author:Jannonx
 * @date: 2020/6/29 14:32
 */
/**
 * @description: 公司bean
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class DepartmentDataBean implements Serializable {


    /**
     * 主键ID
     */
    private int id;
    /**
     * 公司ID
     */
    private int factoryId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 是否存在子节点
     */
    private int hasChild;

    /**
     * 菜单类型：1.按位置 2.按照部门
     */
    private int menuType;
    /**
     * 父节点ID
     */
    private int parentId;
    /**
     * 预留特殊排序
     */
    private int sort;
    /**
     * 状态：1.可用 2.停用
     */
    private int status;
    /**
     * 名字
     */
    private String name;
    /**
     * 类型级别：1.公司级别 2.厂区级别 3.栋级别 4.楼层级别 5.部门级别 6.几部
     */
    private int typeLevel;
    /**
     * 修改时间
     */
    private String updateTime;



    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getHasChild() {
        return hasChild;
    }

    public void setHasChild(int hasChild) {
        this.hasChild = hasChild;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuType() {
        return menuType;
    }

    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(int typeLevel) {
        this.typeLevel = typeLevel;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


}
