package com.guyuan.dear.focus.contract.view.contractDetail;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.contract.bean.ContractComment;
import com.guyuan.dear.focus.contract.bean.DetailContractBean;
import com.guyuan.dear.focus.contract.repos.ContractDetailRepo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.utils.ToastUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 10:24
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractDetailViewModel extends BaseViewModel {
    private ContractDetailRepo repo = new ContractDetailRepo();
    private MutableLiveData<DetailContractBean> contractBean = new MutableLiveData<>();
    private DetailContractBean temp;
    private MutableLiveData<View.OnClickListener> onClickCheckProgress = new MutableLiveData<>();

    public MutableLiveData<DetailContractBean> getContractBean() {
        return contractBean;
    }

    public MutableLiveData<View.OnClickListener> getOnClickCheckProgress() {
        return onClickCheckProgress;
    }

    public void setOnClickCheckProgress(View.OnClickListener listener) {
        onClickCheckProgress.postValue(listener);
    }

    public Disposable loadContractDetail(int contractId) {
//        DetailContractBean bean = new DetailContractBean();
//        bean.setDate(System.currentTimeMillis());
//        bean.setBuyer("深圳固远智能机器人有限公司");
//        bean.setSalesPerson("兰特芳");
//        bean.setContractNum("DEAR-2020/10/10");
//        bean.setProductName("空气压缩机");
//        bean.setBuyerAddress("深圳南山科技园11栋1102");
//        bean.setReceivePerson("周全贵");
//        bean.setContactNumber("13726799842");
//        bean.setBuyerFirstCreateDate(
//                CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(System.currentTimeMillis(),36)
//        );
//        bean.setProductModel("空气压缩机TK028");
//        List<ProductComponent> componentList = new ArrayList<>();
//        for(int i=0;i<20;i++){
//            ProductComponent component = new ProductComponent();
//            component.setComponentName("部件"+(i+1));
//            component.setModelName("TK835");
//            component.setBluePrintId("TK835-"+(i+1));
//            component.setCount(1);
//            component.setUnit("台");
//            componentList.add(component);
//        }
//        List<ContractComment> commentList = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            ContractComment comment = new ContractComment();
//            comment.setDate(System.currentTimeMillis());
//            comment.setContent("老客户比较满意我们的产品。");
//            comment.setCommenterDept("销售部");
//            comment.setCommenter("戴金银");
//            comment.setSubComments(new ArrayList<>());
//            commentList.add(comment);
//        }
//        bean.setProductComponents(componentList);
//        bean.setCommentList(commentList);

        return repo.loadContractDetailById(contractId, callbackGetDetail);
    }

    private DearNetHelper.NetCallback<DetailContractBean> callbackGetDetail = new DearNetHelper.NetCallback<DetailContractBean>() {
        @Override
        public void onStart(Disposable disposable) {
            isShowLoading.postValue(true);

        }

        @Override
        public void onGetResult(DetailContractBean result) {
            //进一步获取评论列表
            temp = result;
            repo.getVerifyFlowByContractId((int) result.getContractId(), callbackGetComments);

        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

        }
    };

    private DearNetHelper.NetCallback<List<ContractComment>> callbackGetComments = new DearNetHelper.NetCallback<List<ContractComment>>() {
        @Override
        public void onStart(Disposable disposable) {

        }

        @Override
        public void onGetResult(List<ContractComment> result) {
            temp.setCommentList(result);
            contractBean.postValue(temp);
            isShowLoading.postValue(false);
        }

        @Override
        public void onError(Throwable error) {
            isShowLoading.postValue(false);
            ToastUtils.showShort(DearApplication.getInstance(), error.getMessage());

        }
    };
}
