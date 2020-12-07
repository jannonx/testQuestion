package com.guyuan.dear.focus.projectsite.type;


import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 我的关注--工程现场--安全排查--单个事项评审结果
 * @author: 许建宁
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum SingleItemResultType implements Serializable {
    /**
     *  单个事项评审结果(0:未评审；1:通过；2:不通过),如果纯展示事项明细，就可忽略次字段。
     */
    /**
     * 未评审
     */
    TYPE_UNKNOWN(0, "未评审", R.mipmap.ic_empty_round),
    /**
     * 通过
     */
    TYPE_PASS(1, "通过", R.mipmap.right),
    /**
     * 不通过
     */
    TYPE_REJECT(2, "不通过", R.mipmap.wrong);


    private int code;
    private String des;
    private int imageView;

    SingleItemResultType(int code, String des, int imageView) {
        this.code = code;
        this.des = des;
        this.imageView = imageView;

    }

    /**
     * 根据枚举code获取实例，用于switch
     */
    public static SingleItemResultType toType(int index) {
        for (SingleItemResultType type : SingleItemResultType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }


    public static String toText(int type) {
        return SingleItemResultType.toType(type).getDes();

    }


    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }

    public int getImageView() {
        return imageView;
    }
}
