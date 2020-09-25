package com.guyuan.dear.focus.security.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by tl
 * created at 2020/6/12
 */
public class SecurityContentBean implements Parcelable{
    /**
     * id : 352
     * degree : 1
     * securityId : 25
     * reason : 安全点报警内容,安全点报警内容
     * imgUrl : null
     * note :
     * status : 1
     * createName : admin
     * createPhone : 13889700023
     * createTime : 1591846579000
     * tSecurityBaseVo : {"id":25,"name":"货梯","code":"AQ001","factoryName":"东莞厂区",
     * "workshopName":"5栋8楼","typeName":"电气危险区域","dutyName":"宣观秀","dutyPhone":"18124855679",
     * "urgentName":null,"urgentPhone":null,"qrCode":"159178407883512708","status":1,
     * "createTime":1591782705000,"imgUrl":"159178399565245136","cameraChannel":"15",
     * "dvrSeriesNum":"E22840701"}
     */

    private int id;                                //主键id
    private int degree;                            //紧急程度:1.预警、2.轻微、3.严重
    private int securityId;                        //安全点id
    private String reason;                         //汇报原因
    private String imgUrl;                         //图片/视频地址
    private String note;                           //备注
    private int status;                            //异常状态:0异常,1已解决
    private String createName;                     //报警人
    private String createPhone;                    //报警人电话
    private String createTime;                       //报警时间
    private SecurityBaseBean tSecurityBaseVo;

    public SecurityContentBean() {
    }

    public SecurityContentBean(Parcel in) {
        id = in.readInt();
        degree = in.readInt();
        securityId = in.readInt();
        reason = in.readString();
        imgUrl = in.readString();
        note = in.readString();
        status = in.readInt();
        createName = in.readString();
        createPhone = in.readString();
        createTime = in.readString();
        tSecurityBaseVo = in.readParcelable(SecurityBaseBean.class.getClassLoader());
    }

    public static final Creator<SecurityContentBean> CREATOR = new Creator<SecurityContentBean>() {
        @Override
        public SecurityContentBean createFromParcel(Parcel in) {
            return new SecurityContentBean(in);
        }

        @Override
        public SecurityContentBean[] newArray(int size) {
            return new SecurityContentBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getSecurityId() {
        return securityId;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public SecurityBaseBean getTSecurityBaseVo() {
        return tSecurityBaseVo;
    }

    public void setTSecurityBaseVo(SecurityBaseBean tSecurityBaseVo) {
        this.tSecurityBaseVo = tSecurityBaseVo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(degree);
        dest.writeInt(securityId);
        dest.writeString(reason);
        dest.writeString(imgUrl);
        dest.writeString(note);
        dest.writeInt(status);
        dest.writeString(createName);
        dest.writeString(createPhone);
        dest.writeString(createTime);
        dest.writeParcelable(tSecurityBaseVo, flags);
    }
}
