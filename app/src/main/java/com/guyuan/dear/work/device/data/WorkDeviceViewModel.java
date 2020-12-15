package com.guyuan.dear.work.device.data;

import androidx.hilt.lifecycle.ViewModelInject;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.login.data.bean.LoginBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.StringUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.device.api.WorkDeviceApiService;
import com.guyuan.dear.work.device.data.bean.MaintainCommitBody;
import com.guyuan.dear.work.device.data.bean.RepairCommitBody;
import com.guyuan.dear.work.device.data.bean.RepairFinishBody;
import com.guyuan.dear.work.device.data.bean.StartBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/30 11:04
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkDeviceViewModel extends BaseViewModel {
    private WorkDeviceApiService apiService;

    @ViewModelInject
    public WorkDeviceViewModel(WorkDeviceRepository repository) {
        this.apiService = repository.getApiService();
    }

    public void getEquipmentInfo(String number, String type) {
        RxJavaHelper.build(this, apiService.getEqiupmentInfo(number, type))
                .getHelper().flow();
    }

    public void getQRInfo(String qrCode) {
        RxJavaHelper.build(this, apiService.getQRInfo(qrCode))
                .getHelper().flow();
    }

    public void commitMaintainInfo(List<String> urlList, List<String> checkedList, int deviceID, boolean isChecked) {
        MaintainCommitBody body = new MaintainCommitBody();
        body.setEquipmentId((long) deviceID);
        StringBuilder checkBuilder = new StringBuilder();
        for (int i = 0; i < checkedList.size(); i++) {
            if (i < checkedList.size() - 1) {
                checkBuilder.append(checkedList.get(i));
                checkBuilder.append(",");
            } else {
                checkBuilder.append(checkedList.get(i));
            }
        }
        body.setDetail(checkBuilder.toString());
        body.setEquipmentStatus(isChecked ? 0 : 1);
        StringBuilder photoBuilder = new StringBuilder();
        for (int i = 0; i < urlList.size(); i++) {
            if (i < urlList.size() - 1) {
                photoBuilder.append(urlList.get(i));
                photoBuilder.append(",");
            } else {
                photoBuilder.append(urlList.get(i));
            }
        }

        if (checkedList.size() > 0) {
            body.setType(checkedList.get(0));
        }

        body.setGroupUrl(photoBuilder.toString());
        body.setImgUrl(photoBuilder.toString());
        LoginBean loginBean = CommonUtils.getLoginInfo();
        body.setCreateBy((long) loginBean.getUserId());
        RxJavaHelper.build(this,
                apiService.commitMaintainInfo(CommonUtils.getCommonRequestBody(body)))
                .getHelper().flow();
    }


    public void commitReportInfo(List<String> urlList, List<String> checkedList,
                                 List<StaffBean> copyList, int deviceID) {

        RepairCommitBody body = new RepairCommitBody();

        ArrayList<Long> userIDList = new ArrayList<>();
        for (StaffBean staffBean : copyList) {
            userIDList.add(staffBean.getId());
        }
        body.setSendUsers(userIDList);

        body.setEquipmentId((long) deviceID);
        StringBuilder checkBuilder = new StringBuilder();
        for (int i = 0; i < checkedList.size(); i++) {
            if (i < checkedList.size() - 1) {
                checkBuilder.append(checkedList.get(i));
                checkBuilder.append(",");
            } else {
                checkBuilder.append(checkedList.get(i));
            }
        }
        body.setErrorDetail(checkBuilder.toString());

        body.setErrorImgUrl(StringUtils.splicePhotoUrl(urlList));

        RxJavaHelper.build(this,
                apiService.commitReportInfo(CommonUtils.getCommonRequestBody(body)))
                .getHelper().flow();
    }


    public void upLoadPic(int type, Map<String, RequestBody> map) {
        RxJavaHelper.build(this, apiService.uploadPic(map))
                .getHelper().flow();
    }

    public void getRepairList(int pageIndex) {
        ListRequestBody listRequestBody = new ListRequestBody();
        listRequestBody.setPageNum(pageIndex);
        listRequestBody.setPageSize(ConstantValue.PAGE_SIZE);
        RxJavaHelper.build(this, apiService.getRepairList(
                CommonUtils.getCommonRequestBody(listRequestBody))).getHelper().flow();
    }

    public void getRepairDetail(Long id) {
        RxJavaHelper.build(this, apiService.getRepairDetail(id)).getHelper().flow();
    }


    public void startRepair(Long msgID) {
        StartBody body = new StartBody();
        body.setId(msgID);
        RxJavaHelper.build(this, apiService.startRepair(
                CommonUtils.getCommonRequestBody(body))).getHelper().flow();
    }

    public void finishRepair(List<String> urlList, List<String> checkList, Long deviceID, boolean isChecked) {
        RepairFinishBody body = new RepairFinishBody();
        body.setId(deviceID);
        body.setRepairResult(checkList.get(0));
        int isOpen = isChecked ? 1 : 2;
        body.setOpenBtn(isOpen);
        StringBuilder photoBuilder = new StringBuilder();
        for (int i = 0; i < urlList.size(); i++) {
            if (i < urlList.size() - 1) {
                photoBuilder.append(urlList.get(i));
                photoBuilder.append(",");
            } else {
                photoBuilder.append(urlList.get(i));
            }
        }
        body.setRepairImgUrl(photoBuilder.toString());

        RxJavaHelper.build(this, apiService.finishRepair(
                CommonUtils.getCommonRequestBody(body))).getHelper().flow();
    }

    public void getDeviceMaintainList() {
        RxJavaHelper.build(this, apiService.getDeviceMaintainList()).getHelper().flow();
    }

    public void getWorkDeviceDetail(long id) {
        RxJavaHelper.build(this, apiService.getWorkDeviceDetail(id)).getHelper().flow();
    }
}