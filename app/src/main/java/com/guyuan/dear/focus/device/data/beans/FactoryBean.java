package com.guyuan.dear.focus.device.data.beans;

import java.util.List;

/**
 * Created by admin
 * on 2019/11/25
 */
public class FactoryBean {

    /**
     * pageNum : 1
     * pageSize : 10
     * totalSize : 1
     * totalPages : 1
     * content : [{"id":1,"code":"33s","name":"一号厂房","description":"33","principalBy":null,
     * "createTime":null,"delFlag":0}]
     */

    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPages;
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

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean  {
        /**
         * id : 1
         * code : 33s
         * name : 一号厂房
         * description : 33
         * principalBy : null
         * createTime : null
         * delFlag : 0
         */

        private Long id;
        private String code;
        private String name;
        private String description;
        private Object principalBy;
        private Object createTime;
        private int delFlag;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getPrincipalBy() {
            return principalBy;
        }

        public void setPrincipalBy(Object principalBy) {
            this.principalBy = principalBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

    }
}
