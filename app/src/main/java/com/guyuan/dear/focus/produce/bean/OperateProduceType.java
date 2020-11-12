package com.guyuan.dear.focus.produce.bean;


/**
 * @description: 生产--申请状态
 * @author: Jannonx
 * @since: 2020/11/10 15:31
 * @company: 固远（深圳）信息技术有限公司
 */
public enum OperateProduceType {
    /**
     * 操作类型：1.点击开始，2.点击完成，3.点击暂停，4.点击激活
     */
    TYPE_EXECUTE_START(1, "点击开始"),
    TYPE_EXECUTE_ACTIVATE(2, "点击完成"),
    TYPE_EXECUTE_PAUSE(3, "点击暂停"),
    TYPE_EXECUTE_COMPLETE(4, "点击激活"),
    TYPE_UNKNOWN(5, "未知操作");

    private int code;
    private String des;

    OperateProduceType(int code, String des) {
        this.code = code;
        this.des = des;
    }


    /**
     * 根据枚举code获取实例，用于switch
     */
    public static OperateProduceType toType(int index) {
        for (OperateProduceType type : OperateProduceType.values()) {
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public static String toText(int type) {
        return OperateProduceType.toType(type).getDes();

    }

    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
