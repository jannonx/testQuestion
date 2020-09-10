package com.guyuan.dear.base.bean;

import java.io.Serializable;
import java.util.List;


/**
 * @description: 公司bean
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class CompanyOrganizationDataBean implements Serializable {

    /**
     * 公司ID
     */
    private int id;
    /**
     * 公司名称
     */
    private String companyOrganization;
    /**
     * 所属公司部门列表
     */
    private List<DepartmentDataBean> deptList;

    public String getCompanyOrganization() {
        return companyOrganization;
    }

    public void setCompanyOrganization(String companyOrganization) {
        this.companyOrganization = companyOrganization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DepartmentDataBean> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DepartmentDataBean> deptList) {
        this.deptList = deptList;
    }


}
