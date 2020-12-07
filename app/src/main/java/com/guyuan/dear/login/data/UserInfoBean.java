package com.guyuan.dear.login.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description: 用户实体类
 * @author: 许建宁
 * @since: 2020/11/24 23:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class UserInfoBean implements Parcelable {

    /**
     * 用户ID
     */
    private long id;
    /**
     * dsadsadasd
     */
    private long userId;
    /**
     * 摄像头ID
     */
    private long cameraId;
    /**
     * 公司ID
     */
    private long companyId;
    /**
     * 创建人
     */
    private long createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用工状态
     */
    private String employmentForm;
    /**
     * 入职时间
     */
    private String enterTime;
    /**
     * 用户头像
     */
    private String imgUrl;
    /**
     * 结婚状态：1.已婚 2.未婚
     */
    private int marryStatus;
    /**
     * 用户名
     */
    private String name;
    /**
     * dsadsadasd
     */
    private String code;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 离职时间
     */
    private String quitTime;
    /**
     * 盐
     */
    private String salt;
    /**
     * dsadsadasd
     */
    private String realName;
    /**
     * dsadsadasd
     */
    private String identity;
    /**
     * dsadsadasd
     */
    private String mobile;
    /**
     * 用户状态
     */
    private int status;
    /**
     * dsadsadasd
     */
    private String qrcode;
    /**
     * dsadsadasd
     */
    private long deptId;
    /**
     * dsadsadasd
     */
    private String deptIdName;

    /**
     * dsadsadasd
     */
    private long factoryId;
    /**
     * dsadsadasd
     */
    private String factoryIdName;
    /**
     * dsadsadasd
     */
    private long workshopId;
    /**
     * dsadsadasd
     */
    private long workshopIdName;
    /**
     * dsadsadasd
     */
    private long lineId;
    /**
     * dsadsadasd
     */
    private String lineIdName;
    /**
     * dsadsadasd
     */
    private long pointId;
    /**
     * dsadsadasd
     */
    private String pointIdName;
    /**
     * dsadsadasd
     */
    private long attachmentId;
    /**
     * dsadsadasd
     */
    private String createByName;
    /**
     * 修改人
     */
    private long lastUpdateBy;
    /**
     * dsadsadasd
     */
    private String lastUpdateByName;
    /**
     * dsadsadasd
     */
    private String lastUpdateTime;
    /**
     * 用户住址
     */
    private String userAddress;
    /**
     * 用户生日
     */
    private String userBirthday;
    /**
     * 用户学历
     */
    private String userEducation;
    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 用户身份证
     */
    private String userIdentity;
    /**
     * 民族
     */
    private String userNation;
    /**
     * 用户手机
     */
    private String userPhone;
    /**
     * 视频URL
     */
    private String videoUrl;
    /**
     * 用户工号
     */
    private String workId;
    /**
     * 用户性别
     */
    private int userSex;
    /**
     * dsadsadasd
     */
    private int delFlag;
    /**
     * dsadsadasd
     */
    private String nameRole;
    /**
     * dsadsadasd
     */
    private String remarkRole;
    /**
     * dsadsadasd
     */
    private String workStarttime;
    /**
     * dsadsadasd
     */
    private String workStoptime;
    /**
     * dsadsadasd
     */
    private String achieves;

    private String deptName; //部门

    private String roleName; //岗位

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.userId);
        dest.writeLong(this.cameraId);
        dest.writeLong(this.companyId);
        dest.writeLong(this.createBy);
        dest.writeString(this.createTime);
        dest.writeString(this.employmentForm);
        dest.writeString(this.enterTime);
        dest.writeString(this.imgUrl);
        dest.writeInt(this.marryStatus);
        dest.writeString(this.name);
        dest.writeString(this.code);
        dest.writeString(this.password);
        dest.writeString(this.quitTime);
        dest.writeString(this.salt);
        dest.writeString(this.realName);
        dest.writeString(this.identity);
        dest.writeString(this.mobile);
        dest.writeInt(this.status);
        dest.writeString(this.qrcode);
        dest.writeLong(this.deptId);
        dest.writeString(this.deptIdName);
        dest.writeLong(this.factoryId);
        dest.writeString(this.factoryIdName);
        dest.writeLong(this.workshopId);
        dest.writeLong(this.workshopIdName);
        dest.writeLong(this.lineId);
        dest.writeString(this.lineIdName);
        dest.writeLong(this.pointId);
        dest.writeString(this.pointIdName);
        dest.writeLong(this.attachmentId);
        dest.writeString(this.createByName);
        dest.writeLong(this.lastUpdateBy);
        dest.writeString(this.lastUpdateByName);
        dest.writeString(this.lastUpdateTime);
        dest.writeString(this.userAddress);
        dest.writeString(this.userBirthday);
        dest.writeString(this.userEducation);
        dest.writeString(this.userEmail);
        dest.writeString(this.updateTime);
        dest.writeString(this.userIdentity);
        dest.writeString(this.userNation);
        dest.writeString(this.userPhone);
        dest.writeString(this.videoUrl);
        dest.writeString(this.workId);
        dest.writeInt(this.userSex);
        dest.writeInt(this.delFlag);
        dest.writeString(this.nameRole);
        dest.writeString(this.remarkRole);
        dest.writeString(this.workStarttime);
        dest.writeString(this.workStarttime);
        dest.writeString(this.workStoptime);
        dest.writeString(this.achieves);
        dest.writeString(this.deptName);
        dest.writeString(this.roleName);
    }

    public UserInfoBean() {
    }

    protected UserInfoBean(Parcel in) {
        this.id = in.readLong();
        this.userId = in.readLong();
        this.cameraId = in.readLong();
        this.companyId = in.readLong();
        this.createBy = in.readLong();
        this.createTime = in.readString();
        this.employmentForm = in.readString();
        this.enterTime = in.readString();
        this.imgUrl = in.readString();
        this.marryStatus = in.readInt();
        this.name = in.readString();
        this.code = in.readString();
        this.password = in.readString();
        this.quitTime = in.readString();
        this.salt = in.readString();
        this.realName = in.readString();
        this.identity = in.readString();
        this.mobile = in.readString();
        this.status = in.readInt();
        this.qrcode = in.readString();
        this.deptId = in.readLong();
        this.deptIdName = in.readString();
        this.factoryId = in.readLong();
        this.factoryIdName = in.readString();
        this.workshopId = in.readLong();
        this.workshopIdName = in.readLong();
        this.lineId = in.readLong();
        this.lineIdName = in.readString();
        this.pointId = in.readLong();
        this.pointIdName = in.readString();
        this.attachmentId = in.readLong();
        this.createByName = in.readString();
        this.lastUpdateBy = in.readLong();
        this.lastUpdateByName = in.readString();
        this.lastUpdateTime = in.readString();
        this.userAddress = in.readString();
        this.userBirthday = in.readString();
        this.userEducation = in.readString();
        this.userEmail = in.readString();
        this.updateTime = in.readString();
        this.userIdentity = in.readString();
        this.userNation = in.readString();
        this.userPhone = in.readString();
        this.videoUrl = in.readString();
        this.workId = in.readString();
        this.userSex = in.readInt();
        this.delFlag = in.readInt();
        this.nameRole = in.readString();
        this.remarkRole = in.readString();
        this.workStarttime = in.readString();
        this.workStarttime = in.readString();
        this.workStoptime = in.readString();
        this.achieves = in.readString();
        this.deptName = in.readString();
        this.roleName = in.readString();
    }

    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel source) {
            return new UserInfoBean(source);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
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

    public long getCameraId() {
        return cameraId;
    }

    public void setCameraId(long cameraId) {
        this.cameraId = cameraId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEmploymentForm() {
        return employmentForm;
    }

    public void setEmploymentForm(String employmentForm) {
        this.employmentForm = employmentForm;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(int marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(String quitTime) {
        this.quitTime = quitTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
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

    public long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(long workshopId) {
        this.workshopId = workshopId;
    }

    public long getWorkshopIdName() {
        return workshopIdName;
    }

    public void setWorkshopIdName(long workshopIdName) {
        this.workshopIdName = workshopIdName;
    }

    public long getLineId() {
        return lineId;
    }

    public void setLineId(long lineId) {
        this.lineId = lineId;
    }

    public String getLineIdName() {
        return lineIdName;
    }

    public void setLineIdName(String lineIdName) {
        this.lineIdName = lineIdName;
    }

    public long getPointId() {
        return pointId;
    }

    public void setPointId(long pointId) {
        this.pointId = pointId;
    }

    public String getPointIdName() {
        return pointIdName;
    }

    public void setPointIdName(String pointIdName) {
        this.pointIdName = pointIdName;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getLastUpdateByName() {
        return lastUpdateByName;
    }

    public void setLastUpdateByName(String lastUpdateByName) {
        this.lastUpdateByName = lastUpdateByName;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserNation() {
        return userNation;
    }

    public void setUserNation(String userNation) {
        this.userNation = userNation;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getRemarkRole() {
        return remarkRole;
    }

    public void setRemarkRole(String remarkRole) {
        this.remarkRole = remarkRole;
    }

    public String getWorkStarttime() {
        return workStarttime;
    }

    public void setWorkStarttime(String workStarttime) {
        this.workStarttime = workStarttime;
    }

    public String getWorkStoptime() {
        return workStoptime;
    }

    public void setWorkStoptime(String workStoptime) {
        this.workStoptime = workStoptime;
    }

    public String getAchieves() {
        return achieves;
    }

    public void setAchieves(String achieves) {
        this.achieves = achieves;
    }
}
