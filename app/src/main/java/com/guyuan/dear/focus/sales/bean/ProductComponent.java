package com.guyuan.dear.focus.sales.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: 廖华凯
 * @description: 关键零部件，首次使用在销售-合同概况-销售产品信息上
 * @since: 2020/10/10 10:52
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductComponent implements Parcelable {
    /**
     * 部件名称
     */
    private String componentName;
    /**
     * 型号
     */
    private String modelName;
    /**
     * 部件图纸
     */
    private String bluePrintId;
    /**
     * 部件数量
     */
    private int count;
    /**
     * 单位
     */
    private String unit;

    public ProductComponent() {
    }

    protected ProductComponent(Parcel in) {
        componentName = in.readString();
        modelName = in.readString();
        bluePrintId = in.readString();
        count = in.readInt();
        unit = in.readString();
    }

    public static final Creator<ProductComponent> CREATOR = new Creator<ProductComponent>() {
        @Override
        public ProductComponent createFromParcel(Parcel in) {
            return new ProductComponent(in);
        }

        @Override
        public ProductComponent[] newArray(int size) {
            return new ProductComponent[size];
        }
    };

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBluePrintId() {
        return bluePrintId;
    }

    public void setBluePrintId(String bluePrintId) {
        this.bluePrintId = bluePrintId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(componentName);
        dest.writeString(modelName);
        dest.writeString(bluePrintId);
        dest.writeInt(count);
        dest.writeString(unit);
    }

    @Override
    public String toString() {
        return "ProductComponent{" +
                "componentName='" + componentName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", bluePrintId='" + bluePrintId + '\'' +
                ", count=" + count +
                ", unit='" + unit + '\'' +
                '}';
    }
}
