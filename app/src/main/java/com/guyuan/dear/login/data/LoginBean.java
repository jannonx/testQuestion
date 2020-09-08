package com.guyuan.dear.login.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LoginBean implements Parcelable  {

    private int id;
    private Object createBy;
    private Object createTime;
    private Object lastUpdateBy;
    private long lastUpdateTime;
    private int userId;
    private String token;
    private long expireTime;
    private UserInfoBean userInfo;
    private List<AppMenusBean> appMenus;

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        id = in.readInt();
        lastUpdateTime = in.readLong();
        userId = in.readInt();
        token = in.readString();
        expireTime = in.readLong();
        appMenus = in.createTypedArrayList(AppMenusBean.CREATOR);
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Object lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getUserId() {
        return userInfo.getId();
    }

    public void setUserId(int userId) {
//        this.userId = userId;
        userInfo.setId(userId);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<AppMenusBean> getAppMenus() {
        return appMenus;
    }

    public void setAppMenus(List<AppMenusBean> appMenus) {
        this.appMenus = appMenus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(lastUpdateTime);
        dest.writeInt(userId);
        dest.writeString(token);
        dest.writeLong(expireTime);
        dest.writeTypedList(appMenus);
    }

    public static class UserInfoBean {

        private int id;
        private int userId;
        private String name;
        private String code;
        private String realName;
        private String identity;
        private String mobile;
        private int status;
        private Object qrcode;
        private int deptId;
        private String deptIdName;
        private long factoryId;
        private String factoryIdName;
        private int workshopId;
        private String workshopIdName;
        private int lineId;
        private String lineIdName;
        private int pointId;
        private String pointIdName;
        private String imgUrl;
        private Object attachmentId;
        private String createBy;
        private Object createByName;
        private Object createTime;
        private String lastUpdateBy;
        private Object lastUpdateByName;
        private String lastUpdateTime;
        private int delFlag;
        private Object nameRole;
        private Object remarkRole;
        private Object workStarttime;
        private Object workStoptime;
        private Object achieves;

        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", realName='" + realName + '\'' +
                    ", identity='" + identity + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", status=" + status +
                    ", qrcode=" + qrcode +
                    ", deptId=" + deptId +
                    ", deptIdName='" + deptIdName + '\'' +
                    ", factoryId=" + factoryId +
                    ", factoryIdName='" + factoryIdName + '\'' +
                    ", workshopId=" + workshopId +
                    ", workshopIdName='" + workshopIdName + '\'' +
                    ", lineId=" + lineId +
                    ", lineIdName='" + lineIdName + '\'' +
                    ", pointId=" + pointId +
                    ", pointIdName='" + pointIdName + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", attachmentId=" + attachmentId +
                    ", createBy='" + createBy + '\'' +
                    ", createByName=" + createByName +
                    ", createTime=" + createTime +
                    ", lastUpdateBy='" + lastUpdateBy + '\'' +
                    ", lastUpdateByName=" + lastUpdateByName +
                    ", lastUpdateTime='" + lastUpdateTime + '\'' +
                    ", delFlag=" + delFlag +
                    ", nameRole=" + nameRole +
                    ", remarkRole=" + remarkRole +
                    ", workStarttime=" + workStarttime +
                    ", workStoptime=" + workStoptime +
                    ", achieves=" + achieves +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return id;
        }

        public void setUserId(int userId) {
            this.id = userId;
        }

        public String getName() {
            return realName;
        }

        public void setName(String name) {
            this.realName = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getQrcode() {
            return qrcode;
        }

        public void setQrcode(Object qrcode) {
            this.qrcode = qrcode;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getDeptIdName() {
            return deptIdName;
        }

        public void setDeptIdName(String deptIdName) {
            this.deptIdName = deptIdName;
        }

        public long getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(long factoryId) {
            this.factoryId = factoryId;
        }

        public String getFactoryIdName() {
            return factoryIdName;
        }

        public void setFactoryIdName(String factoryIdName) {
            this.factoryIdName = factoryIdName;
        }

        public int getWorkshopId() {
            return workshopId;
        }

        public void setWorkshopId(int workshopId) {
            this.workshopId = workshopId;
        }

        public String getWorkshopIdName() {
            return workshopIdName;
        }

        public void setWorkshopIdName(String workshopIdName) {
            this.workshopIdName = workshopIdName;
        }

        public int getLineId() {
            return lineId;
        }

        public void setLineId(int lineId) {
            this.lineId = lineId;
        }

        public String getLineIdName() {
            return lineIdName;
        }

        public void setLineIdName(String lineIdName) {
            this.lineIdName = lineIdName;
        }

        public int getPointId() {
            return pointId;
        }

        public void setPointId(int pointId) {
            this.pointId = pointId;
        }

        public String getPointIdName() {
            return pointIdName;
        }

        public void setPointIdName(String pointIdName) {
            this.pointIdName = pointIdName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Object getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(Object attachmentId) {
            this.attachmentId = attachmentId;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Object getCreateByName() {
            return createByName;
        }

        public void setCreateByName(Object createByName) {
            this.createByName = createByName;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public String getLastUpdateBy() {
            return lastUpdateBy;
        }

        public void setLastUpdateBy(String lastUpdateBy) {
            this.lastUpdateBy = lastUpdateBy;
        }

        public Object getLastUpdateByName() {
            return lastUpdateByName;
        }

        public void setLastUpdateByName(Object lastUpdateByName) {
            this.lastUpdateByName = lastUpdateByName;
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

        public Object getNameRole() {
            return nameRole;
        }

        public void setNameRole(Object nameRole) {
            this.nameRole = nameRole;
        }

        public Object getRemarkRole() {
            return remarkRole;
        }

        public void setRemarkRole(Object remarkRole) {
            this.remarkRole = remarkRole;
        }

        public Object getWorkStarttime() {
            return workStarttime;
        }

        public void setWorkStarttime(Object workStarttime) {
            this.workStarttime = workStarttime;
        }

        public Object getWorkStoptime() {
            return workStoptime;
        }

        public void setWorkStoptime(Object workStoptime) {
            this.workStoptime = workStoptime;
        }

        public Object getAchieves() {
            return achieves;
        }

        public void setAchieves(Object achieves) {
            this.achieves = achieves;
        }


    }

    public static class AppMenusBean implements Parcelable {


        private int id;
        private String name;
        private int parentId;
        private String url;
        private String perms;
        private int type;
        private String icon;
        private int orderNum;
        private Object createBy;
        private Object createTime;
        private Object lastUpdateBy;
        private Object lastUpdateTime;
        private int delFlag;
        private int hidden;
        private String title;
        private Object parentName;
        private int level;

        private List<ChildrenBean> children;

        public AppMenusBean() {
        }

        protected AppMenusBean(Parcel in) {
            id = in.readInt();
            name = in.readString();
            parentId = in.readInt();
            url = in.readString();
            perms = in.readString();
            type = in.readInt();
            icon = in.readString();
            orderNum = in.readInt();
            delFlag = in.readInt();
            hidden = in.readInt();
            title = in.readString();
            level = in.readInt();
            children = in.createTypedArrayList(ChildrenBean.CREATOR);
        }

        public static final Creator<AppMenusBean> CREATOR = new Creator<AppMenusBean>() {
            @Override
            public AppMenusBean createFromParcel(Parcel in) {
                return new AppMenusBean(in);
            }

            @Override
            public AppMenusBean[] newArray(int size) {
                return new AppMenusBean[size];
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

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getLastUpdateBy() {
            return lastUpdateBy;
        }

        public void setLastUpdateBy(Object lastUpdateBy) {
            this.lastUpdateBy = lastUpdateBy;
        }

        public Object getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(Object lastUpdateTime) {
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

        public Object getParentName() {
            return parentName;
        }

        public void setParentName(Object parentName) {
            this.parentName = parentName;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeInt(parentId);
            dest.writeString(url);
            dest.writeString(perms);
            dest.writeInt(type);
            dest.writeString(icon);
            dest.writeInt(orderNum);
            dest.writeInt(delFlag);
            dest.writeInt(hidden);
            dest.writeString(title);
            dest.writeInt(level);
            dest.writeTypedList(children);
        }

        public static class ChildrenBean implements Parcelable {
            private int id;
            private String name;
            private int parentId;
            private String url;
            private String perms;
            private int type;
            private String icon;
            private int orderNum;
            private Object createBy;
            private Object createTime;
            private Object lastUpdateBy;
            private Object lastUpdateTime;
            private int delFlag;
            private int hidden;
            private String title;
            private String parentName;
            private int level;
            private String warnContent;  //模块异常提示语
            private List<ChildrenBean> children;

            public ChildrenBean() {
            }

            protected ChildrenBean(Parcel in) {
                id = in.readInt();
                name = in.readString();
                parentId = in.readInt();
                url = in.readString();
                perms = in.readString();
                type = in.readInt();
                icon = in.readString();
                orderNum = in.readInt();
                delFlag = in.readInt();
                hidden = in.readInt();
                title = in.readString();
                parentName = in.readString();
                level = in.readInt();
                warnContent = in.readString();
                children = in.createTypedArrayList(ChildrenBean.CREATOR);
            }

            public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
                @Override
                public ChildrenBean createFromParcel(Parcel in) {
                    return new ChildrenBean(in);
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

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getLastUpdateBy() {
                return lastUpdateBy;
            }

            public void setLastUpdateBy(Object lastUpdateBy) {
                this.lastUpdateBy = lastUpdateBy;
            }

            public Object getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(Object lastUpdateTime) {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(name);
                dest.writeInt(parentId);
                dest.writeString(url);
                dest.writeString(perms);
                dest.writeInt(type);
                dest.writeString(icon);
                dest.writeInt(orderNum);
                dest.writeInt(delFlag);
                dest.writeInt(hidden);
                dest.writeString(title);
                dest.writeString(parentName);
                dest.writeInt(level);
                dest.writeString(warnContent);
                dest.writeTypedList(children);
            }
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "id=" + id +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", lastUpdateBy=" + lastUpdateBy +
                ", lastUpdateTime=" + lastUpdateTime +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                ", expireTime=" + expireTime +
                ", userInfo=" + userInfo +
                ", appMenus=" + appMenus +
                '}';
    }
}
