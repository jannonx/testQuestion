package com.guyuan.dear.focus.sales.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 10:39
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComponentStateBean {
    private String productionState;
    private String componentName;
    private String modelName;

    public String getProductionState() {
        return productionState;
    }

    public void setProductionState(String productionState) {
        this.productionState = productionState;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
