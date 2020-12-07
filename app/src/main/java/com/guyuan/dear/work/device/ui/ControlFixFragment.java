package com.guyuan.dear.work.device.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.adapter.TagPhotoAdapter;
import com.guyuan.dear.base.adapter.TagStaffAdapter;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;
import com.guyuan.dear.databinding.FragmentControlReportBinding;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.StringUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.device.data.WorkDeviceViewModel;
import com.guyuan.dear.work.device.data.bean.ControlDeviceBean;
import com.guyuan.dear.work.device.data.bean.RepairCommitBody;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

public class ControlFixFragment extends BaseDataBindingFragment<FragmentControlReportBinding,
        WorkDeviceViewModel> implements BaseTabActivity.PhotoSelectListener {

    public static final String TAG = "ControlFixFragment";
    public static final int REQUEST_APPROVE = 0x001;
    private WorkDeviceActivity controlDeviceActivity;
    private ControlDeviceBean controlDeviceBean;
    private ArrayList<StaffBean> copyList = new ArrayList<>();
    private TagAdapter<StaffBean> copyAdapter;
    private ArrayList<Uri> photoList = new ArrayList<>();
    private TagPhotoAdapter photoAdapter;
    private List<String> selectedContent = new ArrayList<>();

    public static ControlFixFragment newInstance() {
        Bundle args = new Bundle();
        ControlFixFragment fragment = new ControlFixFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_control_report;
    }

    @Override
    public void initialization() {

    }

    public void setUI(ControlDeviceBean bean) {
        String reportName = CommonUtils.getLoginInfo().getUserInfo().getName();
        binding.controlReportPersonTv.setText(reportName);

        if (bean != null) {
            controlDeviceBean = bean;
            binding.controlReportName.setText(bean.getName());
            binding.controlReportNumber.setText(bean.getCode());
            binding.controlReportType.setText(bean.getEquipmentModel());
            binding.controlReportPosition.setText(bean.getWorkShopName());
            binding.controlReportTime.setText(bean.getLastMaintainTime());

            List<String> reportContent = bean.getContent();
            binding.controlReportTl.setAdapter(new TagAdapter<String>(reportContent) {
                @Override
                public View getView(FlowLayout parent, int position, String content) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_checkbox,
                            binding.controlReportTl, false);
                    CheckBox cb = view.findViewById(R.id.item_cb);
                    cb.setText(content);
                    return view;
                }
            });

            binding.controlReportCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.controlReportTl.getSelected().size() > 0 && photoList != null &&
                            photoList.size() > 0 && copyList.size() > 0) {
                        controlDeviceActivity.setCurrentPhotoType(BaseTabActivity.SECOND);
                        controlDeviceActivity.checkPhotoAndFileUpLoad(CommonUtils.getFilePath(photoList));
                    } else {
                        Toast.makeText(getContext(), "请选择故障类型和照片及抄送人", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            binding.controlReportIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controlDeviceActivity.openAlbum(BaseTabActivity.SECOND);
                }
            });
        }

        binding.copyToTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickStaffsActivity.startForResult(ControlFixFragment.this, REQUEST_APPROVE,
                        "请选择抄送人", copyList, new ArrayList<StaffBean>(), new ArrayList<StaffBean>(), ConstantValue.CONST_MAX_STAFF_COUNT);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_APPROVE:
                if (data != null) {
                    List<StaffBean> extra = data.getParcelableArrayListExtra(ConstantValue.KEY_STAFF_LIST);
                    if (extra != null) {
                        setReportList(extra);
                    }
                }
                break;
        }
    }


    private void setReportList(List<StaffBean> extra) {
        copyList.clear();
        copyList.addAll(extra);
        if (copyAdapter == null) {
            copyAdapter = new TagStaffAdapter(getContext(), copyList);
            binding.copyTl.setAdapter(copyAdapter);
        } else {
            copyAdapter.notifyDataChanged();
        }
    }


    public void commitRepairInfo(List<String> urlList) {
        List<String> checkedList = binding.controlReportTl.getSelected();
        viewModel.commitReportInfo(urlList, checkedList, copyList, controlDeviceBean.getId());
    }

    public void clearData() {
        binding.controlReportTl.clearSelected();
        photoList.clear();
        photoAdapter.clearDataList();
        copyList.clear();
        copyAdapter.clearDataList();
    }

    @Override
    public ArrayList<Uri> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onPhotoSelected(ArrayList<Uri> selectedPhotoList) {
        photoList.clear();
        photoList.addAll(selectedPhotoList);
        if (photoAdapter == null) {
            photoAdapter = new TagPhotoAdapter(getContext(), CommonUtils.getFilePath(photoList));
            binding.pictureTl.setAdapter(photoAdapter);
        } else {
            photoAdapter.notifyDataChanged();
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
