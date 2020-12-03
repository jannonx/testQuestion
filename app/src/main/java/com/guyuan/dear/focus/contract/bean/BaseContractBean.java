package com.guyuan.dear.focus.contract.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.guyuan.dear.net.resultBeans.NetSearchContactInfo;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.Transient;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同列表
 * @since: 2020/10/12 10:46
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseContractBean implements Parcelable {
    /**
     * 合同编号，做展示时用
     */
    private String contractNum;
    /**
     * 合同ID,网络请求时用
     */
    private long contractId;

    /**
     * 销售人员
     */
    private String salesPerson;
    /**
     * 买方
     */
    private String buyer;
    private int buyerId;
    /**
     * 合同签订日期
     */
    private long date;
    /**
     * 设备名称
     */
    private String productName;
    /**
     * 设备型号
     */
    private String productModel;
    /**
     * 合同订单标签,如：生产中，生产完成，评审中....等
     */
    private List<String> tags;

    @Transient
    public static final int CONTRACT_TYPE_PRE_ANNUAL_DELIVERS = 1;
    @Transient
    public static final int CONTRACT_TYPE_NEW_CONTRACTS = 2;
    @Transient
    public static final int CONTRACT_TYPE_FINISHED_CONTRACTS = 3;
    @Transient
    public static final int CONTRACT_TYPE_EXECUTING_CONTRACTS = 4;
    @Transient
    public static final int CONTRACT_TYPE_UNFINISHED_CONTRACTS = 6;
    @Transient
    public static final int CONTRACT_TYPE_EXCEPTION_CONTRACTS = 5;

    private int executingState;


    public BaseContractBean() {
    }

    public BaseContractBean(NetSearchContactInfo info) {
        setContractNum(info.getContractNum());
        setBuyer(info.getCusName());
        setContractId(info.getId());
        setProductName(info.getEquipmentName());
        setProductModel(info.getEquipmentModel());
        setSalesPerson(info.getSalesman());
        setDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(info.getSignTime()).getTime());
        setTags(new ArrayList<String>());
        this.executingState = info.getState();
        //0 正常执行 1 质保金异常 2 验收合格 3 暂停
        String tag = null;
        if (executingState == 0) {
            tag = "正常执行";
        } else if (executingState == 1) {
            tag = "质保金异常";
        } else if (executingState == 2) {
            tag = "验收合格";
        }else if (executingState == 3) {
            tag = "合同暂停";
        }
        if (tag != null) {
            getTags().add(tag);
        }
    }

    protected BaseContractBean(Parcel in) {
        contractNum = in.readString();
        contractId = in.readLong();
        salesPerson = in.readString();
        buyer = in.readString();
        buyerId = in.readInt();
        date = in.readLong();
        productName = in.readString();
        productModel = in.readString();
        tags = in.createStringArrayList();
        executingState = in.readInt();
    }

    public static final Creator<BaseContractBean> CREATOR = new Creator<BaseContractBean>() {
        @Override
        public BaseContractBean createFromParcel(Parcel in) {
            return new BaseContractBean(in);
        }

        @Override
        public BaseContractBean[] newArray(int size) {
            return new BaseContractBean[size];
        }
    };

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public int getExecutingState() {
        return executingState;
    }

    public void setExecutingState(int executingState) {
        this.executingState = executingState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contractNum);
        dest.writeLong(contractId);
        dest.writeString(salesPerson);
        dest.writeString(buyer);
        dest.writeInt(buyerId);
        dest.writeLong(date);
        dest.writeString(productName);
        dest.writeString(productModel);
        dest.writeStringList(tags);
        dest.writeInt(executingState);
    }
}
