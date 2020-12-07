package com.guyuan.dear.work.device.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.adapter.TagPhotoAdapter;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;
import com.guyuan.dear.databinding.FragmentControlMaintainBinding;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.work.device.data.WorkDeviceViewModel;
import com.guyuan.dear.work.device.data.bean.ControlDeviceBean;
import com.guyuan.dear.work.device.data.bean.MaintainCommitBody;
import com.sun.jna.platform.win32.OaIdl;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

public class ControlMaintainFragment extends BaseDataBindingFragment<FragmentControlMaintainBinding, WorkDeviceViewModel>
        implements BaseTabActivity.PhotoSelectListener {


    public static final String TAG = "ControlMaintainFragment";
    private WorkDeviceActivity controlDeviceActivity;
    private ControlDeviceBean controlDeviceBean;
    private ArrayList<Uri> photoList = new ArrayList<>();
    private TagPhotoAdapter photoAdapter;

    public static ControlMaintainFragment newInstance() {

        Bundle args = new Bundle();

        ControlMaintainFragment fragment = new ControlMaintainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controlDeviceActivity = (WorkDeviceActivity) context;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_control_maintain;
    }

    @Override
    public void initialization() {

    }

    public void setUI(ControlDeviceBean controlDeviceBean) {

        String reportName = CommonUtils.getLoginInfo().getUserInfo().getName();
        binding.controlMaintainReportPersonTv.setText(reportName);

        this.controlDeviceBean = controlDeviceBean;
        binding.controlMaintainName.setText(controlDeviceBean.getName());
        binding.controlMaintainNumber.setText(controlDeviceBean.getCode());
        binding.controlMaintainType.setText(controlDeviceBean.getEquipmentModel());
        binding.controlMaintainPosition.setText(controlDeviceBean.getWorkShopName());
        binding.controlMaintainTime.setText(controlDeviceBean.getLastMaintainTime());
        binding.controlMaintainPlanStartTime.setText(controlDeviceBean.getPlanMaintainTimeStart());
        binding.controlMaintainPlanEndTime.setText(controlDeviceBean.getPlanMaintainTimeEnd());

        List<String> maintainType = controlDeviceBean.getOptType();
        binding.maintainTypeTl.setAdapter(new TagAdapter<String>(maintainType) {
            @Override
            public View getView(FlowLayout parent, int position, String type) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_checkbox,
                        binding.maintainTypeTl, false);
                CheckBox cb = view.findViewById(R.id.item_cb);
                cb.setText(type);
                return view;
            }
        });

        List<String> maintainContent = controlDeviceBean.getContent();

        binding.controlMaintainTl.setAdapter(new TagAdapter<String>(maintainContent) {
            @Override
            public View getView(FlowLayout parent, int position, String content) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_checkbox,
                        binding.controlMaintainTl, false);
                CheckBox cb = view.findViewById(R.id.item_cb);
                cb.setText(content);
                return view;
            }
        });

        binding.controlMaintainCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.controlMaintainTl.getSelected().size() > 0
                        && photoList.size() > 0
                        && binding.maintainTypeTl.getSelected().size() > 0) {
                    controlDeviceActivity.setCurrentPhotoType(BaseTabActivity.FIRST);
                    controlDeviceActivity.checkPhotoAndFileUpLoad(CommonUtils.getFilePath(photoList));
                } else {
                    Toast.makeText(getContext(), "请选择保养类型,照片和保养类型", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.controlUploadIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDeviceActivity.openAlbum(BaseTabActivity.FIRST);
            }
        });

        binding.controlMaintainSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            controlDeviceActivity.checkToItem(1);
                        }
                    }
                });
    }

    public void commitMaintainInfo(List<String> urlList) {
        List<String> checkedList = binding.controlMaintainTl.getSelected();
        viewModel.commitMaintainInfo(urlList, checkedList,
                controlDeviceBean.getId(), binding.controlMaintainSwitch.isChecked());

    }

    public void clearData() {
        binding.controlMaintainTl.clearSelected();
        binding.maintainTypeTl.clearSelected();
        photoList.clear();
        photoAdapter.clearDataList();
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
