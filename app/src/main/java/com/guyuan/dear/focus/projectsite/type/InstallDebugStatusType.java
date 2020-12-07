package com.guyuan.dear.focus.projectsite.type;

import com.guyuan.dear.R;

import java.io.Serializable;

/**
 * @description: 安装调试施工动态状态
 * @author: 许建宁
 * @since: 2020/12/7 16:16
 * @company: 固远（深圳）信息技术有限公司
 */
public enum InstallDebugStatusType implements Serializable {
    /**
     * 工程现场安装调试状态枚举值
     */
    INSTALL_STATUS_IDEA(0, "指导意见", R.drawable.bg_blue_1890ff_round),
    INSTALL_STATUS_UPDATE_TIME(1, "时间调整", R.drawable.bg_blue_1890ff_round),
    INSTALL_STATUS_WAIT(10, "待安装", R.drawable.bg_blue_1890ff_round),
    INSTALL_STATUS_ING(20, "实际开始时间", R.drawable.bg_blue_1890ff_round),
    INSTALL_STATUS_CONTINUE(21, "继续安装", R.drawable.bg_blue_1890ff_round),
    INSTALL_STATUS_FINISH(30, "完工", R.drawable.bg_green_2fc25b_round),
    INSTALL_STATUS_PAUSE(40, "暂停安装", R.drawable.bg_red_f04864_round),
    INSTALL_STATUS_UNKNOWN(50, "未知状态", R.drawable.bg_red_f04864_round);

    private int code;

    private String message;

    private int ballBg;

    InstallDebugStatusType(int code, String message, int ballBg) {
        this.code = code;
        this.message = message;
        this.ballBg = ballBg;
    }


    /**
     * 通过code查询对应的描述值
     *
     * @param
     * @return
     * @author: dcq
     * @date: 2020/9/23 16:59
     */
    public static String getName(int code) {
        for (InstallDebugStatusType pm : InstallDebugStatusType.values()) {
            if (pm.code == code) {
                return pm.message;
            }
        }
        return "";
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static InstallDebugStatusType getEnum(Integer index) {
        for (InstallDebugStatusType c : InstallDebugStatusType.values()) {
            if (c.getCode() == index) {
                return c;
            }
        }
        return INSTALL_STATUS_UNKNOWN;
    }

    public static InstallDebugStatusType toType(String title) {
        for (InstallDebugStatusType c : InstallDebugStatusType.values()) {
            if (c.getMessage().equals(title)) {
                return c;
            }
        }
        return INSTALL_STATUS_UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getBallBg() {
        return ballBg;
    }
}
