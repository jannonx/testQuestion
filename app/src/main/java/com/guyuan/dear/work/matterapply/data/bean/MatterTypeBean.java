package com.guyuan.dear.work.matterapply.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 20:32
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterTypeBean {

    /**
     * createBy :
     * createTime :
     * delFlag : 0
     * description :
     * id : 0
     * key :
     * lastUpdateBy :
     * lastUpdateTime :
     * remarks :
     * sort : 0
     * type :
     * value :
     */

    private String createBy;            //创建人
    private String createTime;          //创建时间
    private int delFlag;                //是否删除
    private String description;         //说明
    private int id;                     //编号
    private String key;                 //系统配置键
    private String lastUpdateBy;        //最后修改人
    private String lastUpdateTime;      //最后修改时间
    private String remarks;             //备注
    private int sort;                   //顺序
    private String type;                //系统配置类型
    private String value;               //系统配置值

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}