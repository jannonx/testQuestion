package com.guyuan.dear.focus.contract.view.contractApplyDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 18:14
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractApplyDetailViewModel extends BaseViewModel {
    private MutableLiveData<DetailContractApplyBean> detailBean = new MutableLiveData<>();

    public MutableLiveData<DetailContractApplyBean> getDetailBean() {
        return detailBean;
    }

    public Disposable getContractDetailFromNet(long examineId) {
//        ContractExcptDetailBean bean = new ContractExcptDetailBean();
//        bean.setContractNum(examineId);
//        bean.setBuyer("北京天行健科技有限公司");
//        bean.setDetailCause("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//        bean.setExceptionTag("暂停");
//        bean.setApplier("兰特芳");
//        bean.setJudgement("国家政策暂停");
//        List<ContractApproveLog> commentList = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            ContractApproveLog comment = new ContractApproveLog();
//            comment.setName("丁大力");
//            comment.setDate(System.currentTimeMillis());
//            comment.setDept("销售部");
//            comment.setImgUrl("");
//            comment.setStaffId(123);
//            if(i%2==0){
//                comment.setResult(Vote.VOTE_RESULT_PASS);
//                comment.setComment("该项目技术已经成熟，可以尝试。");
//            }else {
//                comment.setResult(Vote.VOTE_RESULT_REJECT);
//                comment.setComment("该项目材料成本太高，不建议进行。");
//            }
//            commentList.add(comment);
//        }
//        bean.setComments(commentList);
//        detailBean.postValue(bean);
        return DearNetHelper.getInstance().getContractApplyDetail(
                (int) examineId,
                getDetailCallback);
    }

    private DearNetHelper.NetCallback<DetailContractApplyBean> getDetailCallback = new DearNetHelper.NetCallback<DetailContractApplyBean>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);

        }

        @Override
        public void onGetResult(DetailContractApplyBean result) {
            isShowLoading.postValue(false);
            detailBean.postValue(result);
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

        }
    };
}
