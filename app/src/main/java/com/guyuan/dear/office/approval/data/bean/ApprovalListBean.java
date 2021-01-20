package com.guyuan.dear.office.approval.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/26 10:17
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ApprovalListBean implements Parcelable{

    private int pageNum;
    private int pageSize;
    private int totalPages;
    private int totalSize;
    private String updateTime;

    private List<ContentBean> content;

    protected ApprovalListBean(Parcel in) {
        pageNum = in.readInt();
        pageSize = in.readInt();
        totalPages = in.readInt();
        totalSize = in.readInt();
        updateTime = in.readString();
        content = in.createTypedArrayList(ContentBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pageNum);
        dest.writeInt(pageSize);
        dest.writeInt(totalPages);
        dest.writeInt(totalSize);
        dest.writeString(updateTime);
        dest.writeTypedList(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ApprovalListBean> CREATOR = new Creator<ApprovalListBean>() {
        @Override
        public ApprovalListBean createFromParcel(Parcel in) {
            return new ApprovalListBean(in);
        }

        @Override
        public ApprovalListBean[] newArray(int size) {
            return new ApprovalListBean[size];
        }
    };

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Parcelable {
        private int arType;               //审批类型
        private int businessId;           //业务id
        private String businessName;      //业务名称
        private String createName;        //申请人名称
        private String createTime;        //申请时间
        private int id;                   //主键
        private int status;               //状态 0.审批中 1.已同意 2.已拒绝
        private boolean isApproval=false;       //是否是待审批列表

        protected ContentBean(Parcel in) {
            arType = in.readInt();
            businessId = in.readInt();
            businessName = in.readString();
            createName = in.readString();
            createTime = in.readString();
            id = in.readInt();
            status = in.readInt();
            isApproval = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(arType);
            dest.writeInt(businessId);
            dest.writeString(businessName);
            dest.writeString(createName);
            dest.writeString(createTime);
            dest.writeInt(id);
            dest.writeInt(status);
            dest.writeByte((byte) (isApproval ? 1 : 0));
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel in) {
                return new ContentBean(in);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };

        public boolean isApproval() {
            return isApproval;
        }

        public void setApproval(boolean approval) {
            isApproval = approval;
        }

        public int getArType() {
            return arType;
        }

        public void setArType(int arType) {
            this.arType = arType;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}