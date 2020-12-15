package com.guyuan.dear.login.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 自己菜单
 * @author: 许建宁
 * @since: 2020/11/25 00:00
 * @company: 固远（深圳）信息技术有限公司
 */
public class ChildrenBean implements Parcelable {
    private int id;
    private String name;
    private int parentId;
    private String url;
    private String perms;
    private int type;
    private String icon;
    private int orderNum;
    private String createBy;
    private String createTime;
    private String lastUpdateBy;
    private String lastUpdateTime;
    private int delFlag;
    private int hidden;
    private String title;
    private String parentName;
    private int level;
    private String warnContent;  //模块异常提示语
    private List<ChildrenBean> children;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.parentId);
        dest.writeString(this.url);
        dest.writeString(this.perms);
        dest.writeInt(this.type);
        dest.writeString(this.icon);
        dest.writeInt(this.orderNum);
        dest.writeString(this.createBy);
        dest.writeString(this.createTime);
        dest.writeString(this.lastUpdateBy);
        dest.writeString(this.lastUpdateTime);
        dest.writeInt(this.delFlag);
        dest.writeInt(this.hidden);
        dest.writeString(this.title);
        dest.writeString(this.parentName);
        dest.writeInt(this.level);
        dest.writeString(this.warnContent);
        dest.writeList(this.children);
    }

    public ChildrenBean() {
    }

    protected ChildrenBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.parentId = in.readInt();
        this.url = in.readString();
        this.perms = in.readString();
        this.type = in.readInt();
        this.icon = in.readString();
        this.orderNum = in.readInt();
        this.createBy = in.readString();
        this.createTime = in.readString();
        this.lastUpdateBy = in.readString();
        this.lastUpdateTime = in.readString();
        this.delFlag = in.readInt();
        this.hidden = in.readInt();
        this.title = in.readString();
        this.parentName = in.readString();
        this.level = in.readInt();
        this.warnContent = in.readString();
        this.children = new ArrayList<ChildrenBean>();
        in.readList(this.children, ChildrenBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChildrenBean> CREATOR = new Parcelable.Creator<ChildrenBean>() {
        @Override
        public ChildrenBean createFromParcel(Parcel source) {
            return new ChildrenBean(source);
        }

        @Override
        public ChildrenBean[] newArray(int size) {
            return new ChildrenBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

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

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static Creator<ChildrenBean> getCREATOR() {
        return CREATOR;
    }
}
