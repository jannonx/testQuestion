package com.jannonx.electric.test.bean;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2021/1/23 02:24
 * @company: 固远（深圳）信息技术有限公司
 */
public class ExamConditionBean implements Serializable {
    /**
     * 选中浏览Index
     */
    private int browsePosition;
    /**
     * 浏览类型
     */
    private ExamFunctionType functionType;

    /**
     * 试题bean
     */
    private TestQuestionBean testQuestionBean;

    public ExamConditionBean() {
    }

    public ExamConditionBean(int browsePosition, ExamFunctionType functionType) {
        this.browsePosition = browsePosition;
        this.functionType = functionType;
    }

    public int getBrowsePosition() {
        return browsePosition;
    }

    public void setBrowsePosition(int browsePosition) {
        this.browsePosition = browsePosition;
    }

    public ExamFunctionType getFunctionType() {
        return functionType;
    }

    public void setFunctionType(ExamFunctionType functionType) {
        this.functionType = functionType;
    }

    public TestQuestionBean getTestQuestionBean() {
        return testQuestionBean;
    }

    public void setTestQuestionBean(TestQuestionBean testQuestionBean) {
        this.testQuestionBean = testQuestionBean;
    }
}
