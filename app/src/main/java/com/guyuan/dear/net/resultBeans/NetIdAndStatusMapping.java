package com.guyuan.dear.net.resultBeans;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class NetIdAndStatusMapping {

    /**
     * id : 4
     * name : 唐力
     * userPhone : 19925156029
     * status : 0
     */

    private int id;
    /**
     * 最新打卡状态：0，未到岗 1.正常上班 2.迟到 3.早退 4.请假
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
