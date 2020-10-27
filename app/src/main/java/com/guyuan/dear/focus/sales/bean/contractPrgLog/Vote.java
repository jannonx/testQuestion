package com.guyuan.dear.focus.sales.bean.contractPrgLog;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 18:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class Vote {
    private long staffId;
    private String name;
    private String imgUrl;
    private int result;
    public static final int VOTE_RESULT_PASS = 1;
    public static final int VOTE_RESULT_REJECT = 0;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
