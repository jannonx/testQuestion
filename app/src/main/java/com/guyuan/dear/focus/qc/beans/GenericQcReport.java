package com.guyuan.dear.focus.qc.beans;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 15:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class GenericQcReport {
    /**
     * 报告类型-原材料报告
     */
    public static final int REPORT_TYPE_MATERIAL=0;
    /**
     * 报告类型-产品报告
     */
    public static final int REPORT_TYPE_PRODUCT=1;
    /**
     * 参考 {@link GenericQcReport#REPORT_TYPE_MATERIAL}、{@link GenericQcReport#REPORT_TYPE_PRODUCT}
     */
    private int type;
    private String json;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "GenericQcReport{" +
                "type=" + type +
                ", json='" + json + '\'' +
                '}';
    }
}
