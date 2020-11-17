package com.guyuan.dear.focus.qc.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 11:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseProductQcReport implements Parcelable {
    /**
     * 出厂编号
     */
    private String batchId;
    /**
     * 成品名称
     */
    private String productName;
    /**
     * 成品ID
     */
    private String productId;
    /**
     * 质检人员
     */
    private String qualityChecker;
    /**
     * 质检日期
     */
    private long date;
    /**
     * 表示状况的标签，如合格，不合格，检查中等
     */
    private int tag;
    public static final int TAG_TYPE_PASS = 0;
    public static final int TAG_TYPE_REJECT=1;
    /**
     * 表示审批状态，如：待审批，审批中，同意，拒绝等。
     * {@link BaseMaterialQcReport#REPORT_STATE_PASS},{@link BaseMaterialQcReport#REPORT_STATE_PENDING_FOR_APPROVAL},
     * {@link BaseMaterialQcReport#REPORT_STATE_REJECT}
     */
    private int state;
    /**
     * 是否需要审批
     */
    private boolean isNeedVerify;

    public BaseProductQcReport() {
    }

    protected BaseProductQcReport(Parcel in) {
        batchId = in.readString();
        productName = in.readString();
        productId = in.readString();
        qualityChecker = in.readString();
        date = in.readLong();
        tag = in.readInt();
        state = in.readInt();
        isNeedVerify = in.readByte()>0;
    }

    public static final Creator<BaseProductQcReport> CREATOR = new Creator<BaseProductQcReport>() {
        @Override
        public BaseProductQcReport createFromParcel(Parcel in) {
            return new BaseProductQcReport(in);
        }

        @Override
        public BaseProductQcReport[] newArray(int size) {
            return new BaseProductQcReport[size];
        }
    };

    public boolean isNeedVerify() {
        return isNeedVerify;
    }

    public void setNeedVerify(boolean needVerify) {
        isNeedVerify = needVerify;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQualityChecker() {
        return qualityChecker;
    }

    public void setQualityChecker(String qualityChecker) {
        this.qualityChecker = qualityChecker;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(batchId);
        dest.writeString(productName);
        dest.writeString(productId);
        dest.writeString(qualityChecker);
        dest.writeLong(date);
        dest.writeInt(tag);
        dest.writeInt(state);
        dest.writeByte((byte) (isNeedVerify?1:0));
    }

    @Override
    public String toString() {
        return "BaseProductQcReport{" +
                "batchId='" + batchId + '\'' +
                ", productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", qualityChecker='" + qualityChecker + '\'' +
                ", date=" + date +
                ", tag='" + tag + '\'' +
                '}';
    }

    public GenericQcReport toGenericQcReport(){
        GenericQcReport report = new GenericQcReport();
        report.setType(GenericQcReport.REPORT_TYPE_PRODUCT);
        report.setJson(new Gson().toJson(this));
        return report;
    }
}
