package com.guyuan.dear.focus.contract.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.guyuan.dear.net.resultBeans.NetContractStatusDetail;
import com.guyuan.dear.net.resultBeans.NetVerifyFlowBean;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 针对某个销售合同的评论，首次使用在：我的关注-销售-合同概况-销售详情-跟进动态记录中
 * @since: 2020/10/10 15:25
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractComment implements Parcelable {
    /**
     * 评论人
     */
    private String commenter;
    /**
     * 评论人头像
     */
    private String imgUrl;
    /**
     * 评论日期
     */
    private long date;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论人部门
     */
    private String commenterDept;

    /**
     * 评论回覆
     */
    private List<ContractComment> subComments;

    public ContractComment() {
    }

    public ContractComment(NetVerifyFlowBean src){
        setCommenter(src.getCreateName());
        setImgUrl(src.getImgUrl());
        String createTime = src.getCreateTime();
        if(!TextUtils.isEmpty(createTime)){
            try {
                Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(createTime);
                setDate(date.getTime());
            }catch (Exception e){
                setDate(0);
            }
        }
        setContent(src.getContent());
        setCommenterDept(src.getDeptName().replace(",","-"));
        subComments = new ArrayList<>();
        List<NetVerifyFlowBean.BusinessDetailsBean> list = src.getBusinessDetails();
        if(list!=null&&!list.isEmpty()){
            for (NetVerifyFlowBean.BusinessDetailsBean bean : list) {
                ContractComment sub = new ContractComment(bean);
                subComments.add(sub);
            }
        }
    }

    public ContractComment(NetVerifyFlowBean.BusinessDetailsBean src) {
        setCommenter(src.getCreateName());
        setImgUrl(src.getImgUrl());
        setContent(src.getContent());
        setCommenterDept(src.getDeptName());
        String createTime = src.getCreateTime();
        if(!TextUtils.isEmpty(createTime)){
            try {
                Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(createTime);
                setDate(date.getTime());
            }catch (Exception e){
                setDate(0);
            }
        }

    }

    protected ContractComment(Parcel in) {
        commenter = in.readString();
        imgUrl = in.readString();
        date = in.readLong();
        content = in.readString();
        commenterDept = in.readString();
        subComments = in.createTypedArrayList(ContractComment.CREATOR);
    }

    public static final Creator<ContractComment> CREATOR = new Creator<ContractComment>() {
        @Override
        public ContractComment createFromParcel(Parcel in) {
            return new ContractComment(in);
        }

        @Override
        public ContractComment[] newArray(int size) {
            return new ContractComment[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(commenter);
        dest.writeString(imgUrl);
        dest.writeLong(date);
        dest.writeString(content);
        dest.writeString(commenterDept);
        dest.writeTypedList(subComments);
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenterDept() {
        return commenterDept;
    }

    public void setCommenterDept(String commenterDept) {
        this.commenterDept = commenterDept;
    }

    public List<ContractComment> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<ContractComment> subComments) {
        this.subComments = subComments;
    }
}
