package com.guyuan.dear.focus.contract.view.contractRestartDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.contract.bean.ContractApproveLog;
import com.guyuan.dear.focus.contract.bean.ContractExcptDetailBean;
import com.guyuan.dear.focus.contract.bean.RestartedContractDetailBean;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/29 20:27
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartedContractDetailViewModel extends BaseViewModel {
    private MutableLiveData<RestartedContractDetailBean> detailBean = new MutableLiveData<>();

    public MutableLiveData<RestartedContractDetailBean> getDetailBean() {
        return detailBean;
    }

    public void setDetailBean(RestartedContractDetailBean detailBean) {
        this.detailBean.postValue(detailBean);
    }

    public void getRestartedContractDetailFromNet(String contractId){
        RestartedContractDetailBean bean = new RestartedContractDetailBean();
        bean.setContractId(contractId);
        bean.setBuyer("北京天行健科技有限公司");
        bean.setDetailCause("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        bean.setApplier("兰特芳");
        bean.setJudgement("国家政策暂停");
        List<ContractApproveLog> commentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ContractApproveLog comment = new ContractApproveLog();
            comment.setName("丁大力");
            comment.setDate(System.currentTimeMillis());
            comment.setDept("销售部");
            comment.setImgUrl("");
            comment.setStaffId(123);
            if(i%2==0){
                comment.setResult(Vote.VOTE_RESULT_PASS);
                comment.setComment("该项目技术已经成熟，可以尝试。");
            }else {
                comment.setResult(Vote.VOTE_RESULT_REJECT);
                comment.setComment("该项目材料成本太高，不建议进行。");
            }
            commentList.add(comment);
        }
        bean.setComments(commentList);
        detailBean.postValue(bean);
    }
}
