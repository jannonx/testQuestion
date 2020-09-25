package com.guyuan.dear.focus.security.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.focus.security.data.beans.SecurityBaseBean;

import java.util.List;

/**
 * Created by TL
 * on 2019/11/30
 */
public class DangerProfileBean {

    /**
     * id : 19
     * name : 东莞厂区
     * workshopSecurityVo : [{"id":4,"name":"4栋1楼","tSecurityBaseVo":[]},{"id":5,"name":"4栋2楼",
     * "tSecurityBaseVo":[]},{"id":6,"name":"4栋3楼","tSecurityBaseVo":[]},{"id":7,"name":"4栋4楼",
     * "tSecurityBaseVo":[]},{"id":8,"name":"4栋5楼","tSecurityBaseVo":[]},{"id":9,"name":"4栋6楼",
     * "tSecurityBaseVo":[]},{"id":10,"name":"5栋1楼","tSecurityBaseVo":[]},{"id":11,"name":"5栋2楼",
     * "tSecurityBaseVo":[]},{"id":12,"name":"5栋3楼","tSecurityBaseVo":[]},{"id":13,"name":"5栋4楼",
     * "tSecurityBaseVo":[]},{"id":14,"name":"5栋5楼","tSecurityBaseVo":[]},{"id":15,"name":"5栋6楼",
     * "tSecurityBaseVo":[]},{"id":16,"name":"5栋7楼","tSecurityBaseVo":[]},{"id":17,"name":"4栋7楼",
     * "tSecurityBaseVo":[]},{"id":18,"name":"5栋8楼","tSecurityBaseVo":[]},{"id":19,"name":"外面",
     * "tSecurityBaseVo":[{"id":30,"name":"自动浸漆区","code":"AQ006","factoryName":"东莞厂区",
     * "workshopName":"4栋2楼","typeName":"危险器材区域","dutyName":"蒙跃林","dutyPhone":"13713185139",
     * "urgentName":"向吉伟","urgentPhone":"13168068862","qrCode":"159178431673469818","status":1,
     * "createTime":1591784317000,"imgUrl":"159178429359041826","cameraChannel":"19",
     * "dvrSeriesNum":"E09409340"}]}]
     */

    private int id;
    private String name;
    private List<WorkshopSecurityVoBean> workshopSecurityVo;

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

    public List<WorkshopSecurityVoBean> getWorkshopSecurityVo() {
        return workshopSecurityVo;
    }

    public void setWorkshopSecurityVo(List<WorkshopSecurityVoBean> workshopSecurityVo) {
        this.workshopSecurityVo = workshopSecurityVo;
    }

    public static class WorkshopSecurityVoBean implements Parcelable {
        /**
         * id : 4
         * name : 4栋1楼
         * tSecurityBaseVo : []
         */

        private int id;
        private String name;
        private List<SecurityBaseBean> tsecurityBaseVo;

        protected WorkshopSecurityVoBean(Parcel in) {
            id = in.readInt();
            name = in.readString();
            tsecurityBaseVo = in.createTypedArrayList(SecurityBaseBean.CREATOR);
        }

        public static final Creator<WorkshopSecurityVoBean> CREATOR = new Creator<WorkshopSecurityVoBean>() {
            @Override
            public WorkshopSecurityVoBean createFromParcel(Parcel in) {
                return new WorkshopSecurityVoBean(in);
            }

            @Override
            public WorkshopSecurityVoBean[] newArray(int size) {
                return new WorkshopSecurityVoBean[size];
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

        public List<SecurityBaseBean> getTSecurityBaseVo() {
            return tsecurityBaseVo;
        }

        public void setTSecurityBaseVo(List<SecurityBaseBean> tSecurityBaseVo) {
            this.tsecurityBaseVo = tSecurityBaseVo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeTypedList(tsecurityBaseVo);
        }
    }
}
