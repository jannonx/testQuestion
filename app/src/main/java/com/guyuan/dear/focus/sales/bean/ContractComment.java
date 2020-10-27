package com.guyuan.dear.focus.sales.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Transient;

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
