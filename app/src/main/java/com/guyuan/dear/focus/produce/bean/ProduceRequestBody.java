package com.guyuan.dear.focus.produce.bean;

/**
 * @description: 生产模块--请求体(参数配置)
 * @author: jannonx
 * @since: 2020/11/9 10:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProduceRequestBody {

    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品代号
     */
    private String code;

    /**
     * 创建生产计划的开始时间
     */
    private String startTime;
    /**
     * 创建生产计划的结束时间
     */
    private String endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
