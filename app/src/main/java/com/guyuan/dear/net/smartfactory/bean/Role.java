package com.guyuan.dear.net.smartfactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 廖华凯
 * @since 2020/1/9 13:36
 **/
public class Role implements Parcelable {

    /**
     * id : 472
     * createBy : null
     * createTime : null
     * lastUpdateBy : null
     * lastUpdateTime : null
     * userId : 166
     * roleId : 38
     * roleName : 操作员
     */

    private long id;
    private long userId;
    private long roleId;
    private String roleName;

    public Role() {
    }

    protected Role(Parcel in) {
        id = in.readLong();
        userId = in.readLong();
        roleId = in.readLong();
        roleName = in.readString();
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(userId);
        dest.writeLong(roleId);
        dest.writeString(roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
