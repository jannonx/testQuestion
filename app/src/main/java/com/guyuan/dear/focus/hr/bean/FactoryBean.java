package com.guyuan.dear.focus.hr.bean;

import java.util.List;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class FactoryBean {
    private String name;
    private int id;
    private List<ParentDept> LvOneDeptList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ParentDept> getLvOneDeptList() {
        return LvOneDeptList;
    }

    public void setLvOneDeptList(List<ParentDept> lvOneDeptList) {
        LvOneDeptList = lvOneDeptList;
    }
}
