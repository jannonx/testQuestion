package com.guyuan.dear.db.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Author: 廖华凯
 * Date: 2020/2/13
 * Project: Smart Management
 * Description:
 */
@Entity(indexes = {@Index(value = "name,type DESC", unique = true)})
public class SearchEntity {
    @Id(autoincrement = true)
    private Long id;
    @Index
    private String name;
    /**
     * 搜索类型
     * 1：设备
     * 2：员工
     */
    private short type;
    private long createTime;
    /**
     * 对外ID，可以是服务器上对应的设备/人员的id
     */
    private Long publicId;

    @Generated(hash = 873876006)
    public SearchEntity(Long id, String name, short type, long createTime,
            Long publicId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createTime = createTime;
        this.publicId = publicId;
    }

    @Generated(hash = 1021466028)
    public SearchEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Long getPublicId() {
        return publicId;
    }

    public void setPublicId(Long publicId) {
        this.publicId = publicId;
    }
}
