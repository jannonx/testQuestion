package com.guyuan.dear.focus.contract.bean;

import java.util.List;

/**
 * @author : 唐力
 * @description :合同异常或全部列表bean
 * @since: 2020/12/1 13:40
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ContractBean {

    /**
     * pageNum : 1
     * pageSize : 30
     * totalSize : 22
     * totalPages : 1
     * content : [{"id":8987,"cusName":"深圳市机器人信息技术有限公司","contractNum":"测试使用@#￥%\u2026\u2026&*）001","signTime":"2020-11-04 00:00:00","salesman":"王晨","state":0},{"id":8988,"cusName":"广州市汇聚热电子科技有限公司","contractNum":"20201127-001","signTime":"2020-11-22 00:00:00","salesman":"leo","state":0},{"id":8989,"cusName":"广州市汇聚热电子科技有限公司","contractNum":"TEST007","signTime":"2020-11-18 00:00:00","salesman":"唐力","state":0},{"id":8990,"cusName":"牛气冲天有限公司","contractNum":"测试007","signTime":"2020-11-27 00:00:00","salesman":"唐力","state":0},{"id":8991,"cusName":"深圳市超超有限公司","contractNum":"A2020-11-27","signTime":"2020-11-20 00:00:00","salesman":"李家辉","state":0},{"id":8992,"cusName":"深圳信息顾问有限公司","contractNum":"2020/11/27 14:11","signTime":"2020-11-27 00:00:00","salesman":"王晨","state":0},{"id":8993,"cusName":"深圳市爱咋咋滴科技有限公司","contractNum":"20201127","signTime":"2020-11-27 00:00:00","salesman":"王晨","state":0},{"id":8994,"cusName":"深圳市埃尔法","contractNum":"深圳市埃尔法","signTime":"2020-11-27 00:00:00","salesman":"王晨","state":0},{"id":8995,"cusName":"广东省深圳市","contractNum":"3622561-10","signTime":"2020-11-19 00:00:00","salesman":"王晨","state":0},{"id":8996,"cusName":"深圳市高科技氧气分离公司","contractNum":"haoji-200","signTime":"2020-11-23 00:00:00","salesman":"黄涛","state":0},{"id":8997,"cusName":"康佳集团股份有限公司","contractNum":"GY- KJ-20201130A-SZ","signTime":"2020-11-30 00:00:00","salesman":"高敏早","state":0},{"id":8998,"cusName":"牛气冲天有限公司","contractNum":"1001001001","signTime":"2020-11-30 00:00:00","salesman":"唐力","state":0},{"id":8999,"cusName":"深圳市超超有限公司","contractNum":"AAAAAA","signTime":"2020-11-24 00:00:00","salesman":"李家辉","state":0},{"id":9000,"cusName":"深圳市超超有限公司","contractNum":"AAAAAAA","signTime":"2020-11-24 00:00:00","salesman":"李家辉","state":0},{"id":9001,"cusName":"2020/11/30","contractNum":"SZ2020","signTime":"2020-11-30 00:00:00","salesman":"王晨","state":0},{"id":9002,"cusName":"广州鲲鹏热电能源有限公司","contractNum":"KP20201126-001","signTime":"2020-11-26 00:00:00","salesman":"刘恒曦","state":0},{"id":9003,"cusName":"深圳市书香科技公司","contractNum":"haoji300","signTime":"2020-11-17 00:00:00","salesman":"许建宁","state":0},{"id":9004,"cusName":"20201130深圳科技小熊猫","contractNum":"GY-SZ-WAN3006","signTime":"2020-11-30 00:00:00","salesman":"王晨","state":0},{"id":9005,"cusName":"深圳市迪尔分公司","contractNum":"haoji500","signTime":"2020-12-02 00:00:00","salesman":"许建宁","state":0},{"id":9006,"cusName":"深圳市书香科技公司","contractNum":"合同编号","signTime":"2020-12-28 00:00:00","salesman":"李家辉","state":0},{"id":9007,"cusName":"深圳大亨有限公司","contractNum":"YN20201201","signTime":"2020-12-01 00:00:00","salesman":"欧富华","state":0},{"id":9008,"cusName":"广州鲲鹏热电能源有限公司","contractNum":"KP20201201-001","signTime":"2020-08-31 00:00:00","salesman":"张洁","state":0}]
     * updateTime : null
     */

    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPages;
    private Object updateTime;
    /**
     * id : 8987
     * cusName : 深圳市机器人信息技术有限公司
     * contractNum : 测试使用@#￥%……&*）001
     * signTime : 2020-11-04 00:00:00
     * salesman : 王晨
     * state : 0
     */

    private List<ContentBean> content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        private int id;
        private String cusName;         //客户名称
        private String contractNum;     //合同编号
        private String signTime;        //签订时间
        private String salesman;        //销售人
        private int state;              //0正常执行,1异常执行,2验收合格

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

        public String getContractNum() {
            return contractNum;
        }

        public void setContractNum(String contractNum) {
            this.contractNum = contractNum;
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
    }
}