package com.guyuan.dear.focus.qc.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 11:25
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseMaterialQcReport implements Parcelable {
    private String materialName;
    private String materialId;
    /**
     * 规格
     */
    private String spec;
    private String qualityChecker;
    private long date;
    /**
     * 参考 {@link BaseProductQcReport#TAG_TYPE_PASS} {@link BaseProductQcReport#TAG_TYPE_REJECT}
     */
    private int tag;
    private boolean isNeedVerify;
    /**
     * 材质
     */
    private String materialType;
    /**
     * 表示审批状态，如：待审批，审批中，同意，拒绝等。
     * {@link BaseMaterialQcReport#REPORT_STATE_PASS},{@link BaseMaterialQcReport#REPORT_STATE_PENDING_FOR_APPROVAL},
     * {@link BaseMaterialQcReport#REPORT_STATE_REJECT}
     */
    private int state;
    /**
     * 通过
     */
    public static final int REPORT_STATE_PASS = 3;
    /**
     * 待审批
     */
    public static final int REPORT_STATE_PENDING_FOR_APPROVAL = 1;
    /**
     * 驳回
     */
    public static final int REPORT_STATE_REJECT = 2;


    public BaseMaterialQcReport() {
    }

    protected BaseMaterialQcReport(Parcel in) {
        materialName = in.readString();
        materialId = in.readString();
        spec = in.readString();
        qualityChecker = in.readString();
        date = in.readLong();
        tag = in.readInt();
        isNeedVerify = in.readByte()>0;
        materialType = in.readString();
        state = in.readInt();
    }

    public static final Creator<BaseMaterialQcReport> CREATOR = new Creator<BaseMaterialQcReport>() {
        @Override
        public BaseMaterialQcReport createFromParcel(Parcel in) {
            return new BaseMaterialQcReport(in);
        }

        @Override
        public BaseMaterialQcReport[] newArray(int size) {
            return new BaseMaterialQcReport[size];
        }
    };

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    public boolean isNeedVerify() {
        return isNeedVerify;
    }

    public void setNeedVerify(boolean needVerify) {
        isNeedVerify = needVerify;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMaterialType() {
        return materialType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(materialName);
        dest.writeString(materialId);
        dest.writeString(spec);
        dest.writeString(qualityChecker);
        dest.writeLong(date);
        dest.writeInt(tag);
        dest.writeByte((byte) (isNeedVerify?1:0));
        dest.writeString(materialType);
        dest.writeInt(state);
    }

    @Override
    public String toString() {
        return "BaseMaterialQcReport{" +
                "materialName='" + materialName + '\'' +
                ", materialId='" + materialId + '\'' +
                ", spec='" + spec + '\'' +
                ", qualityChecker='" + qualityChecker + '\'' +
                ", date=" + date +
                ", tag='" + tag + '\'' +
                '}';
    }

    public GenericQcReport toGenericQcReport(){
        GenericQcReport report = new GenericQcReport();
        report.setType(GenericQcReport.REPORT_TYPE_MATERIAL);
        report.setJson(new Gson().toJson(this));
        return report;
    }
}
