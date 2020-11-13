package com.guyuan.dear.work.assess.data.bean;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/13 11:28
 * @company : 固远（深圳）信息技术有限公司
 **/

public class RoomBean {

    /**
     * id : 20
     * meetingRoomName : 测试1111
     * parent : 0
     * cameraId : 123123
     * delFlag : null
     * factoryId : null
     * factoryName : null
     * createTime : 2020-10-27 18:24:03
     */

    private int id;
    private String meetingRoomName;
    private int parent;
    private int cameraId;
    private Object delFlag;
    private Object factoryId;
    private Object factoryName;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public Object getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Object factoryId) {
        this.factoryId = factoryId;
    }

    public Object getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(Object factoryName) {
        this.factoryName = factoryName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}