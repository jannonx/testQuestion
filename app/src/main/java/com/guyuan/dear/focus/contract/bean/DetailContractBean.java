package com.guyuan.dear.focus.contract.bean;

import android.text.TextUtils;

import com.guyuan.dear.net.resultBeans.NetContractDetailInfo;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-合同概况-合同列表-合同详情
 * @since: 2020/10/10 10:46
 * @company: 固远（深圳）信息技术有限公司
 **/
public class DetailContractBean extends BaseContractBean {

    /**
     * 买方地址
     */
    private String buyerAddress;
    /**
     * 收货人
     */
    private String receivePerson;
    /**
     * 收货人联系方式
     */
    private String contactNumber;


    /**
     * 零部件
     */
    private List<ProductComponent> productComponents;

    /**
     * 合同评论
     */
    private List<ContractComment> commentList;
    /**
     * 客户首次建立联系时间
     */
    private long buyerFirstCreateDate;

    public DetailContractBean() {
    }

    public DetailContractBean(NetContractDetailInfo src) {
        setContractId(src.getId());
        setContractNum(src.getContractNum());
        setBuyer(src.getCusName());
        String signTime = src.getSignTime();
        if(!TextUtils.isEmpty(signTime)){
            try {
                setDate(CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(signTime).getTime());
            }catch (Exception e){
                LogUtils.showLog(e.getMessage());
            }
        }
        setReceivePerson(src.getConsignee());
        setContactNumber(src.getContactInfo());
        setSalesPerson(src.getSalesman());
        setProductName(src.getEquipmentName());
        setProductModel(src.getEquipmentModel());
        setBuyerAddress(src.getDeliveryAddress());
        productComponents = new ArrayList<>();
        List<NetContractDetailInfo.TcontractPartsBean> parts = src.getTcontractParts();
        for (NetContractDetailInfo.TcontractPartsBean part : parts) {
            ProductComponent component = new ProductComponent(part);
            productComponents.add(component);
        }
    }


    public List<ProductComponent> getProductComponents() {
        return productComponents;
    }

    public void setProductComponents(List<ProductComponent> productComponents) {
        this.productComponents = productComponents;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public long getBuyerFirstCreateDate() {
        return buyerFirstCreateDate;
    }

    public void setBuyerFirstCreateDate(long buyerFirstCreateDate) {
        this.buyerFirstCreateDate = buyerFirstCreateDate;
    }

    public List<ContractComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ContractComment> commentList) {
        this.commentList = commentList;
    }
}
