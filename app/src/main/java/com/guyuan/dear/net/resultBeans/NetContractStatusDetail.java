package com.guyuan.dear.net.resultBeans;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/20 11:56
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetContractStatusDetail {

    /**
     * texamineVo : {"id":143,"arType":10002,"businessId":8977,"status":0,"createBy":3,"createName":"leo","createDept":"研发部,研发二部","createTime":"2020-11-18 18:48:55","imgUrl":"https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160385280403701030.jpg","remark":"","remark1":null,"remark2":null,"texamineFlows":[{"id":345,"mainId":143,"sort":1,"approveBy":6,"status":0,"approvalTime":null,"remarks":null,"approveName":"谢俊杰","departmentName":"研发部,研发一部","imgUrl":"https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160439727120079332.jpg"},{"id":346,"mainId":143,"sort":2,"approveBy":1755577,"status":-1,"approvalTime":null,"remarks":null,"approveName":"李家辉","departmentName":"研发部,研发一部","imgUrl":"https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160388473927849876.png"}]}
     * tcontractInfo : {"id":8977,"cusName":"深圳固远特种机器人","equipmentName":"AAAA","equipmentModel":"BBBB","contractNum":"AAA","totalAmount":111111,"qualityDeposit":2222,"receivingUnit":"AAAAA","deliveryAddress":"AAAAA","consignee":"AAAAA","contactInfo":"15000222111","createTime":"2020-11-17 06:42:31","signTime":"2020-11-08 16:00:00","tradeReceivables":null,"balance":null,"salesman":"李家辉","state":1,"approvedBy":null,"fileUrl":"","examinationTime":null,"tcontractParts":[{"id":8982,"name":"AAA","specifications":"AAA","model":"AAA","company":"AAA","num":111}]}
     */

    private TexamineVoBean texamineVo;
    private TcontractInfoBean tcontractInfo;

    public TexamineVoBean getTexamineVo() {
        return texamineVo;
    }

    public void setTexamineVo(TexamineVoBean texamineVo) {
        this.texamineVo = texamineVo;
    }

    public TcontractInfoBean getTcontractInfo() {
        return tcontractInfo;
    }

    public void setTcontractInfo(TcontractInfoBean tcontractInfo) {
        this.tcontractInfo = tcontractInfo;
    }

    public static class TexamineVoBean {
        /**
         * id : 143
         * arType : 10002
         * businessId : 8977
         * status : 0
         * createBy : 3
         * createName : leo
         * createDept : 研发部,研发二部
         * createTime : 2020-11-18 18:48:55
         * imgUrl : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160385280403701030.jpg
         * remark :
         * remark1 : null
         * remark2 : null
         * texamineFlows : [{"id":345,"mainId":143,"sort":1,"approveBy":6,"status":0,"approvalTime":null,"remarks":null,"approveName":"谢俊杰","departmentName":"研发部,研发一部","imgUrl":"https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160439727120079332.jpg"},{"id":346,"mainId":143,"sort":2,"approveBy":1755577,"status":-1,"approvalTime":null,"remarks":null,"approveName":"李家辉","departmentName":"研发部,研发一部","imgUrl":"https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160388473927849876.png"}]
         */

        private int id;
        private int arType;
        private int businessId;
        private int status;
        private int createBy;
        private String createName;
        private String createDept;
        private String createTime;
        private String imgUrl;
        private String remark;
        private String remark1;
        private String remark2;
        private List<TexamineFlowsBean> texamineFlows;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getArType() {
            return arType;
        }

        public void setArType(int arType) {
            this.arType = arType;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateDept() {
            return createDept;
        }

        public void setCreateDept(String createDept) {
            this.createDept = createDept;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark1() {
            return remark1;
        }

        public void setRemark1(String remark1) {
            this.remark1 = remark1;
        }

        public String getRemark2() {
            return remark2;
        }

        public void setRemark2(String remark2) {
            this.remark2 = remark2;
        }

        public List<TexamineFlowsBean> getTexamineFlows() {
            return texamineFlows;
        }

        public void setTexamineFlows(List<TexamineFlowsBean> texamineFlows) {
            this.texamineFlows = texamineFlows;
        }

        public static class TexamineFlowsBean {
            /**
             * id : 345
             * mainId : 143
             * sort : 1
             * approveBy : 6
             * status : 0
             * approvalTime : null
             * remarks : null
             * approveName : 谢俊杰
             * departmentName : 研发部,研发一部
             * imgUrl : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160439727120079332.jpg
             */

            private int id;
            private int mainId;
            private int sort;
            private int approveBy;
            private int status;
            private String approvalTime;
            private String remarks;
            private String approveName;
            private String departmentName;
            private String imgUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMainId() {
                return mainId;
            }

            public void setMainId(int mainId) {
                this.mainId = mainId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getApproveBy() {
                return approveBy;
            }

            public void setApproveBy(int approveBy) {
                this.approveBy = approveBy;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getApprovalTime() {
                return approvalTime;
            }

            public void setApprovalTime(String approvalTime) {
                this.approvalTime = approvalTime;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getApproveName() {
                return approveName;
            }

            public void setApproveName(String approveName) {
                this.approveName = approveName;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }

    public static class TcontractInfoBean {
        /**
         * id : 8977
         * cusName : 深圳固远特种机器人
         * equipmentName : AAAA
         * equipmentModel : BBBB
         * contractNum : AAA
         * totalAmount : 111111
         * qualityDeposit : 2222
         * receivingUnit : AAAAA
         * deliveryAddress : AAAAA
         * consignee : AAAAA
         * contactInfo : 15000222111
         * createTime : 2020-11-17 06:42:31
         * signTime : 2020-11-08 16:00:00
         * tradeReceivables : null
         * balance : null
         * salesman : 李家辉
         * state : 1
         * approvedBy : null
         * fileUrl :
         * examinationTime : null
         * tcontractParts : [{"id":8982,"name":"AAA","specifications":"AAA","model":"AAA","company":"AAA","num":111}]
         */

        private int id;
        private String cusName;
        private String equipmentName;
        private String equipmentModel;
        private String contractNum;
        private int totalAmount;
        private int qualityDeposit;
        private String receivingUnit;
        private String deliveryAddress;
        private String consignee;
        private String contactInfo;
        private String createTime;
        private String signTime;
        private String salesman;
        private int state;
        private int approvedBy;
        private String fileUrl;
        private String examinationTime;
        /**
         * 0 重启 1 暂停
         */
        private int stopStatus;
        private List<TcontractPartsBean> tcontractParts;

        public int getStopStatus() {
            return stopStatus;
        }

        public void setStopStatus(int stopStatus) {
            this.stopStatus = stopStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCusName() {
            return cusName;
        }

        public void setCusName(String cusName) {
            this.cusName = cusName;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public String getEquipmentModel() {
            return equipmentModel;
        }

        public void setEquipmentModel(String equipmentModel) {
            this.equipmentModel = equipmentModel;
        }

        public String getContractNum() {
            return contractNum;
        }

        public void setContractNum(String contractNum) {
            this.contractNum = contractNum;
        }

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getQualityDeposit() {
            return qualityDeposit;
        }

        public void setQualityDeposit(int qualityDeposit) {
            this.qualityDeposit = qualityDeposit;
        }

        public String getReceivingUnit() {
            return receivingUnit;
        }

        public void setReceivingUnit(String receivingUnit) {
            this.receivingUnit = receivingUnit;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public String getSalesman() {
            return salesman;
        }

        public void setSalesman(String salesman) {
            this.salesman = salesman;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(int approvedBy) {
            this.approvedBy = approvedBy;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getExaminationTime() {
            return examinationTime;
        }

        public void setExaminationTime(String examinationTime) {
            this.examinationTime = examinationTime;
        }

        public List<TcontractPartsBean> getTcontractParts() {
            return tcontractParts;
        }

        public void setTcontractParts(List<TcontractPartsBean> tcontractParts) {
            this.tcontractParts = tcontractParts;
        }

        public static class TcontractPartsBean {
            /**
             * id : 8982
             * name : AAA
             * specifications : AAA
             * model : AAA
             * company : AAA
             * num : 111
             */

            private int id;
            private String name;
            private String specifications;
            private String model;
            private String company;
            private int num;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSpecifications() {
                return specifications;
            }

            public void setSpecifications(String specifications) {
                this.specifications = specifications;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
