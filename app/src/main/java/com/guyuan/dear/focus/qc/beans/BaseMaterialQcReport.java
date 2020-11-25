package com.guyuan.dear.focus.qc.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.guyuan.dear.net.resultBeans.NetBaseQcBean;
import com.guyuan.dear.utils.CalenderUtils;

import static com.guyuan.dear.focus.qc.beans.BaseProductQcReport.TAG_TYPE_CHECKING;
import static com.guyuan.dear.focus.qc.beans.BaseProductQcReport.TAG_TYPE_PASS;
import static com.guyuan.dear.focus.qc.beans.BaseProductQcReport.TAG_TYPE_REJECT;

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

    /**
     * 无需审批
     */
    public static final int REPORT_STATE_NO_NEED_TO_APPROVE = 4;
    private int reportId;


    public BaseMaterialQcReport() {
    }

    public BaseMaterialQcReport(NetBaseQcBean src) {
        setMaterialName(src.getProductName());
        setMaterialId(src.getProductCode());
        setMaterialType(src.getMaterial());
        this.reportId = src.getId();
        setSpec(src.getModel());
        try {
            setDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(src.getCreateTime()).getTime());
        } catch (Exception e) {
            setDate(0L);
        }
        setQualityChecker(src.getQualityName());
        int status = src.getApprovalStatus();
        //审批状态：1.无需审批，2.待审批，3.审批通过，4.审批被驳回
        setNeedVerify(true);
        switch (status) {
            case 2:
                setState(REPORT_STATE_PENDING_FOR_APPROVAL);
                break;
            case 3:
                setState(REPORT_STATE_PASS);
                break;
            case 4:
                setState(REPORT_STATE_REJECT);
                break;
            case 1:
            default:
                setState(REPORT_STATE_NO_NEED_TO_APPROVE);
                setNeedVerify(false);
                break;
        }
        //质检判断结果：0.未质检，1.合格，2.不合格
        int result = src.getQualityResult();
        switch (result){
            case 1:
                setTag(TAG_TYPE_PASS);
                break;
            case 2:
                setTag(TAG_TYPE_REJECT);
                break;
            case 0:
            default:
                setTag(TAG_TYPE_CHECKING);
                break;
        }


    }

    protected BaseMaterialQcReport(Parcel in) {
        materialName = in.readString();
        materialId = in.readString();
        spec = in.readString();
        qualityChecker = in.readString();
        date = in.readLong();
        tag = in.readInt();
        isNeedVerify = in.readByte() > 0;
        materialType = in.readString();
        state = in.readInt();
        reportId = in.readInt();
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

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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
        dest.writeByte((byte) (isNeedVerify ? 1 : 0));
        dest.writeString(materialType);
        dest.writeInt(state);
        dest.writeInt(reportId);
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

    public GenericQcReport toGenericQcReport() {
        GenericQcReport report = new GenericQcReport();
        report.setType(GenericQcReport.REPORT_TYPE_MATERIAL);
        report.setJson(new Gson().toJson(this));
        return report;
    }
}
