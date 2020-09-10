package com.guyuan.dear.net.smartfactory.bean;

import android.os.Parcel;
import android.os.Parcelable;


import com.guyuan.dear.db.entities.UserEntity;

import java.util.List;
import java.util.Objects;

/**
 * 历史遗留产物，能不用尽量别用。用 {@link StaffBean2P0} 代替。
 * @deprecated 先别删这个类
 * @author 廖华凯
 * @since 2019/12/24 9:46
 **/
public class StaffBean implements Parcelable {

    /**
     * id : 47
     * createBy : null
     * createTime : null
     * lastUpdateBy : null
     * lastUpdateTime : null
     * name : 杨奕雄
     * password : 5a59c50ac679013c8f6e970097e465f26233d34554d947e0e30839d91be00897
     * salt : 4d05b9b6d30f47aeb901
     * email : system@qq.com
     * mobile : 13889700023
     * status : 1
     * deptId : null
     * deptName : null
     * delFlag : null
     * roleNames : null
     * staffId : null
     * code : null
     * realName : null
     * identity : null
     * factoryId : null
     * workshopId : null
     * lineId : null
     * pointId : null
     * attachmentId : null
     * userRoles : []
     */

    private long id;
    private String createBy;
    private long createTime;
    private String lastUpdateBy;
    private long lastUpdateTime;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private int status;//1 在岗 2 请假 3 未到岗 4 迟到 other 未知
    private long deptId;
    private String deptName;
    private int delFlag;
    private long staffId;
    private String code;
    private String realName;
    private String identity;
    private long factoryId;
    private long workshopId;
    private long lineId;
    private long pointId;
    private long attachmentId;//工资
    private boolean isSelected;
    private String imgUrl;
    private boolean isEnable = true;
    private List<Role> userRoles;
    private String empNo;
    private String factoryIdName;
    private String workshopIdName;
    private String lineIdName;
    private String pointIdName;
    private long salary;
    private String workStarttime;
    private String workStoptime;
    private String dvrSeriesNum;
    private String cameraChannel;
    private String nameRole;
    private String nameDept;
    private int staffTotal;

    private String address;
    private String birthDay;
    private String enterTime;
    private String gender;
    private String marryStatus;
    private String rNational;
    private int abnormalCount;
    private int totalLateCount;

    private int singleLateCount;
    private List<String> singleLateTime;//迟到
    private List<String> singleLeaveTime;//请假
    private List<String> singleAbsentTime;//缺席
    private int sortingFactor1;
    private int sortingFactor2;
    private long locId;


    public StaffBean() {
    }

    protected StaffBean(Parcel in) {
        id = in.readLong();
        createBy = in.readString();
        createTime = in.readLong();
        lastUpdateBy = in.readString();
        lastUpdateTime = in.readLong();
        name = in.readString();
        password = in.readString();
        salt = in.readString();
        email = in.readString();
        mobile = in.readString();
        status = in.readInt();
        deptId = in.readLong();
        deptName = in.readString();
        delFlag = in.readInt();
        staffId = in.readLong();
        code = in.readString();
        realName = in.readString();
        identity = in.readString();
        factoryId = in.readLong();
        workshopId = in.readLong();
        lineId = in.readLong();
        pointId = in.readLong();
        attachmentId = in.readLong();
        isSelected = in.readByte() != 0;
        imgUrl = in.readString();
        isEnable = in.readByte() != 0;
        userRoles = in.createTypedArrayList(Role.CREATOR);
        empNo = in.readString();
        factoryIdName = in.readString();
        workshopIdName = in.readString();
        lineIdName = in.readString();
        pointIdName = in.readString();
        salary = in.readLong();
        workStarttime = in.readString();
        workStoptime = in.readString();
        dvrSeriesNum = in.readString();
        cameraChannel = in.readString();
        nameRole = in.readString();
        nameDept = in.readString();
        staffTotal = in.readInt();
        address = in.readString();
        birthDay = in.readString();
        enterTime = in.readString();
        gender = in.readString();
        marryStatus = in.readString();
        rNational = in.readString();
        abnormalCount = in.readInt();
        totalLateCount = in.readInt();
        singleLateCount = in.readInt();
        singleLateTime = in.createStringArrayList();
        singleLeaveTime = in.createStringArrayList();
        singleAbsentTime = in.createStringArrayList();
        sortingFactor1 = in.readInt();
        sortingFactor2 = in.readInt();
        locId = in.readLong();
    }

    public static final Creator<StaffBean> CREATOR = new Creator<StaffBean>() {
        @Override
        public StaffBean createFromParcel(Parcel in) {
            return new StaffBean(in);
        }

        @Override
        public StaffBean[] newArray(int size) {
            return new StaffBean[size];
        }
    };

    public int getTotalLateCount() {
        return totalLateCount;
    }

    public void setTotalLateCount(int totalLateCount) {
        this.totalLateCount = totalLateCount;
    }

    public int getSingleLateCount() {
        return singleLateCount;
    }

    public void setSingleLateCount(int singleLateCount) {
        this.singleLateCount = singleLateCount;
    }

    public List<String> getSingleLateTime() {
        return singleLateTime;
    }

    public void setSingleLateTime(List<String> singleLateTime) {
        this.singleLateTime = singleLateTime;
    }

    public List<String> getSingleLeaveTime() {
        return singleLeaveTime;
    }



    public void setSingleLeaveTime(List<String> singleLeaveTime) {
        this.singleLeaveTime = singleLeaveTime;
    }

    public List<String> getSingleAbsentTime() {
        return singleAbsentTime;
    }

    public void setSingleAbsentTime(List<String> singleAbsentTime) {
        this.singleAbsentTime = singleAbsentTime;
    }

    public int getAbnormalCount() {
        return abnormalCount;
    }

    public void setAbnormalCount(int abnormalCount) {
        this.abnormalCount = abnormalCount;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(String marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getrNational() {
        return rNational;
    }

    public void setrNational(String rNational) {
        this.rNational = rNational;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
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

    public long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    public long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(long workshopId) {
        this.workshopId = workshopId;
    }

    public long getLineId() {
        return lineId;
    }

    public void setLineId(long lineId) {
        this.lineId = lineId;
    }

    public long getPointId() {
        return pointId;
    }

    public void setPointId(long pointId) {
        this.pointId = pointId;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFactoryIdName() {
        return factoryIdName;
    }

    public void setFactoryIdName(String factoryIdName) {
        this.factoryIdName = factoryIdName;
    }

    public String getWorkshopIdName() {
        return workshopIdName;
    }

    public void setWorkshopIdName(String workshopIdName) {
        this.workshopIdName = workshopIdName;
    }

    public String getLineIdName() {
        return lineIdName;
    }

    public void setLineIdName(String lineIdName) {
        this.lineIdName = lineIdName;
    }

    public String getPointIdName() {
        return pointIdName;
    }

    public void setPointIdName(String pointIdName) {
        this.pointIdName = pointIdName;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
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

    public String getDvrSeriesNum() {
        return dvrSeriesNum;
    }

    public void setDvrSeriesNum(String dvrSeriesNum) {
        this.dvrSeriesNum = dvrSeriesNum;
    }

    public String getCameraChannel() {
        return cameraChannel;
    }

    public void setCameraChannel(String cameraChannel) {
        this.cameraChannel = cameraChannel;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getNameDept() {
        return nameDept;
    }

    public void setNameDept(String nameDept) {
        this.nameDept = nameDept;
    }

    public int getStaffTotal() {
        return staffTotal;
    }

    public void setStaffTotal(int staffTotal) {
        this.staffTotal = staffTotal;
    }

    public int getSortingFactor1() {
        return sortingFactor1;
    }

    public void setSortingFactor1(int sortingFactor1) {
        this.sortingFactor1 = sortingFactor1;
    }

    public int getSortingFactor2() {
        return sortingFactor2;
    }

    public void setSortingFactor2(int sortingFactor2) {
        this.sortingFactor2 = sortingFactor2;
    }

    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public String getStatusDescription() {
        //1 在岗 2 请假 3 未到岗 other 未知
        switch (getStatus()) {
            case 1:
                return "工作中";
            case 2:
                return "请假中";
            case 3:
                return "未到岗";
            case 0:
            default:
                break;
        }
        return "未知";
    }

    @Override public String toString() {
        return "StaffBean{" +
            "id=" + id +
            ", createBy='" + createBy + '\'' +
            ", createTime=" + createTime +
            ", lastUpdateBy='" + lastUpdateBy + '\'' +
            ", lastUpdateTime=" + lastUpdateTime +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", status=" + status +
            ", deptId=" + deptId +
            ", deptName='" + deptName + '\'' +
            ", delFlag=" + delFlag +
            ", staffId=" + staffId +
            ", code='" + code + '\'' +
            ", realName='" + realName + '\'' +
            ", identity='" + identity + '\'' +
            ", factoryId=" + factoryId +
            ", workshopId=" + workshopId +
            ", lineId=" + lineId +
            ", pointId=" + pointId +
            ", attachmentId=" + attachmentId +
            ", isSelected=" + isSelected +
            ", imgUrl='" + imgUrl + '\'' +
            ", isEnable=" + isEnable +
            ", userRoles=" + userRoles +
            ", empNo='" + empNo + '\'' +
            ", factoryIdName='" + factoryIdName + '\'' +
            ", workshopIdName='" + workshopIdName + '\'' +
            ", lineIdName='" + lineIdName + '\'' +
            ", pointIdName='" + pointIdName + '\'' +
            ", salary=" + salary +
            ", workStarttime='" + workStarttime + '\'' +
            ", workStoptime='" + workStoptime + '\'' +
            ", dvrSeriesNum='" + dvrSeriesNum + '\'' +
            ", cameraChannel='" + cameraChannel + '\'' +
            ", nameRole='" + nameRole + '\'' +
            ", nameDept='" + nameDept + '\'' +
            ", staffTotal=" + staffTotal +
            ", address='" + address + '\'' +
            ", birthDay='" + birthDay + '\'' +
            ", enterTime='" + enterTime + '\'' +
            ", gender='" + gender + '\'' +
            ", marryStatus='" + marryStatus + '\'' +
            ", rNational='" + rNational + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StaffBean staffBean = (StaffBean) o;
        return id == staffBean.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UserEntity toUserEntity(){
        UserEntity entity = new UserEntity();
        entity.setName(name);
        entity.setImgUrl(imgUrl);
        entity.setId(id);
        return entity;
    }

    public static StaffBean from(UserEntity entity){
        StaffBean bean = new StaffBean();
        bean.setName(entity.getName());
        bean.setId(entity.getId());
        bean.setImgUrl(entity.getImgUrl());
        return bean;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(createBy);
        dest.writeLong(createTime);
        dest.writeString(lastUpdateBy);
        dest.writeLong(lastUpdateTime);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(salt);
        dest.writeString(email);
        dest.writeString(mobile);
        dest.writeInt(status);
        dest.writeLong(deptId);
        dest.writeString(deptName);
        dest.writeInt(delFlag);
        dest.writeLong(staffId);
        dest.writeString(code);
        dest.writeString(realName);
        dest.writeString(identity);
        dest.writeLong(factoryId);
        dest.writeLong(workshopId);
        dest.writeLong(lineId);
        dest.writeLong(pointId);
        dest.writeLong(attachmentId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeString(imgUrl);
        dest.writeByte((byte) (isEnable ? 1 : 0));
        dest.writeTypedList(userRoles);
        dest.writeString(empNo);
        dest.writeString(factoryIdName);
        dest.writeString(workshopIdName);
        dest.writeString(lineIdName);
        dest.writeString(pointIdName);
        dest.writeLong(salary);
        dest.writeString(workStarttime);
        dest.writeString(workStoptime);
        dest.writeString(dvrSeriesNum);
        dest.writeString(cameraChannel);
        dest.writeString(nameRole);
        dest.writeString(nameDept);
        dest.writeInt(staffTotal);
        dest.writeString(address);
        dest.writeString(birthDay);
        dest.writeString(enterTime);
        dest.writeString(gender);
        dest.writeString(marryStatus);
        dest.writeString(rNational);
        dest.writeInt(abnormalCount);
        dest.writeInt(totalLateCount);
        dest.writeInt(singleLateCount);
        dest.writeStringList(singleLateTime);
        dest.writeStringList(singleLeaveTime);
        dest.writeStringList(singleAbsentTime);
        dest.writeInt(sortingFactor1);
        dest.writeInt(sortingFactor2);
        dest.writeLong(locId);
    }
}
