package com.guyuan.dear.net.resultBeans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/18 15:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class NetServerParam {

    @SerializedName("JUDGE_CONDITION")
    private List<JudgeCondition> judgeConditions;


    public List<JudgeCondition> getJudgeConditions() {
        return judgeConditions;
    }

    public void setJudgeConditions(List<JudgeCondition> judgeConditions) {
        this.judgeConditions = judgeConditions;
    }

    /**
     * 判定维度
     */
    public static class JudgeCondition {
        /**
         * id : 40
         * key : 1001
         * value : 国家政策影响
         * type : JUDGE_CONDITION
         * description : 合同暂停原因
         * sort : 0
         * createBy : null
         * createTime : null
         * lastUpdateBy : null
         * lastUpdateTime : null
         * remarks : null
         * delFlag : 0
         */

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
