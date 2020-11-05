package com.guyuan.dear.work.contractPause.views.applyWindow;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentPauseContractBinding;
import com.guyuan.dear.dialog.SimpleRecyclerViewDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.PauseContractBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 11:59
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PauseContractFragment extends BaseMvvmFragment<FragmentPauseContractBinding, PauseContractViewModel> {

    private static final int REQUEST_CODE_PICK_SEND_LIST = 123;
    private static final int REQUEST_CODE_PICK_COPY_LIST = 321;

    public static PauseContractFragment getInstance() {
        return new PauseContractFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_pause_contract_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        PauseContractViewModel viewModel = getViewModel();

        viewModel.setOnClickSelectBuyer(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPickBuyer();
            }
        });

        viewModel.setOnClickSelectContract(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSelectContract();

            }
        });

        viewModel.setOnClickSelectJudgement(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPickJudgement();

            }
        });


        viewModel.setOnClickAddSendList(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> sendList = getViewModel().getPreSelectSendList();
                PickStaffsActivity.startForResult(PauseContractFragment.this, REQUEST_CODE_PICK_SEND_LIST, "请选择审批人",
                        sendList, new ArrayList<StaffBean>(), 10);
            }
        });

        viewModel.setOnClickAddCopyList(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> copyList = getViewModel().getPreSelectCopyList();
                PickStaffsActivity.startForResult(PauseContractFragment.this, REQUEST_CODE_PICK_COPY_LIST, "请选择抄送人",
                        copyList, new ArrayList<StaffBean>(), 10);

            }
        });

        viewModel.setOnClickSubmit(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastTip("提交成功！");
            }
        });

        AppCompatEditText editText = getViewDataBinding().fragmentPauseContractEdtInputDetailReason;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MutableLiveData<PauseContractBean> liveData = viewModel.getPauseBean();
                liveData.getValue().setDetailPauseReason(s.toString());
                liveData.postValue(liveData.getValue());
            }
        });


    }

    private void showDialogPickJudgement() {
        List<String> buyers = new ArrayList<String>() {
            {
                add("国家政策维度");
                add("生产成本维度");
                add("安装难度维度");
                add("售后服务成本维度");

            }
        };
        SimpleRecyclerViewDialog.show(getContext(), buyers, new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
            @Override
            public void onItemClick(String bean, int position) {
                MutableLiveData<PauseContractBean> liveData = getViewModel().getPauseBean();
                liveData.getValue().setJudgement(bean);
                liveData.postValue(liveData.getValue());
            }
        });
    }

    private void showDialogSelectContract() {
        List<String> buyers = new ArrayList<String>() {
            {
                add("DEAR-SALES-CONTRACT-001");
                add("DEAR-SALES-CONTRACT-002");
                add("DEAR-SALES-CONTRACT-003");
                add("DEAR-SALES-CONTRACT-004");
                add("DEAR-SALES-CONTRACT-005");
                add("DEAR-SALES-CONTRACT-006");
                add("DEAR-SALES-CONTRACT-007");
                add("DEAR-SALES-CONTRACT-008");
            }
        };
        SimpleRecyclerViewDialog.show(getContext(), buyers, new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
            @Override
            public void onItemClick(String bean, int position) {
                MutableLiveData<PauseContractBean> liveData = getViewModel().getPauseBean();
                liveData.getValue().setContractId(bean);
                liveData.postValue(liveData.getValue());
            }
        });
    }

    private void showDialogPickBuyer() {
        List<String> buyers = new ArrayList<String>() {
            {
                add("北京天行健科技有限公司");
                add("东莞金大金五金塑料厂");
                add("绍兴鑫汇集装箱有限公司");
                add("东莞星河生物有限公司");
                add("东莞时脉特塑料有限公司");
            }
        };
        SimpleRecyclerViewDialog.show(getContext(), buyers, new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
            @Override
            public void onItemClick(String bean, int position) {
                MutableLiveData<PauseContractBean> liveData = getViewModel().getPauseBean();
                liveData.getValue().setBuyer(bean);
                liveData.getValue().setContractId("");
                liveData.postValue(liveData.getValue());
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pause_contract;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_PICK_SEND_LIST){
            if(resultCode==RESULT_OK){
                ArrayList<StaffBean> list = data.getParcelableArrayListExtra(ConstantValue.KEY_STAFF_LIST);
                getViewModel().updateSendList(list);
            }
        }else if(requestCode==REQUEST_CODE_PICK_COPY_LIST){
            if(resultCode==RESULT_OK){
                ArrayList<StaffBean> list = data.getParcelableArrayListExtra(ConstantValue.KEY_STAFF_LIST);
                getViewModel().updateCopyList(list);
            }
        }
    }
}
