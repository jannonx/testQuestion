package com.guyuan.dear.work.device.ui.detail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.adapter.TagPhotoAdapter;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;
import com.guyuan.dear.databinding.FragmentWorkDeviceDetailBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.StringUtils;
import com.guyuan.dear.work.device.data.WorkDeviceViewModel;
import com.guyuan.dear.work.device.data.bean.WorkDeviceDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tl
 * created at 2020/7/13
 */
public class WorkDeviceDetailFragment extends BaseDataBindingFragment<FragmentWorkDeviceDetailBinding,
        WorkDeviceViewModel> implements BaseFileUploadActivity.PhotoSelectListener {

    public static final String TAG = "WorkDeviceDetailFragment";
    public static final int REPAIR = 1;
    public static final int OTHER = 2;
    private WorkDeviceDetailActivity workDeviceDetailActivity;
    private ArrayList<String> photoList = new ArrayList<>();
    private TagPhotoAdapter photoAdapter;
    private long deviceID;
    private int state;

    public static WorkDeviceDetailFragment newInstance(long deviceId, int state) {
        Bundle args = new Bundle();
        args.putLong(ConstantValue.KEY_ID, deviceId);
        args.putInt(WorkDeviceDetailActivity.DEVICE_CONTENT_STATE, state);
        WorkDeviceDetailFragment fragment = new WorkDeviceDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_device_detail;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        workDeviceDetailActivity = (WorkDeviceDetailActivity) context;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            deviceID = getArguments().getLong(ConstantValue.KEY_ID, 0);
            viewModel.getWorkDeviceDetail(deviceID);
            state = getArguments().getInt(WorkDeviceDetailActivity.DEVICE_CONTENT_STATE, 0);
            setState(state);
        }
    }

    private void setState(int state) {
        switch (state) {
            case REPAIR:
                binding.controlDetailSt.setVisibility(View.VISIBLE);
                binding.controlDetailRv.setVisibility(View.VISIBLE);
                binding.controlDetailCommit.setVisibility(View.VISIBLE);
                binding.controlUploadIv.setVisibility(View.VISIBLE);

                binding.controlSwitchTv.setVisibility(View.GONE);
                binding.controlRepairResultTv.setVisibility(View.GONE);

                break;

            case OTHER:

                break;
        }
    }


    public void setData(WorkDeviceDetailBean workDeviceDetailBean) {
        binding.controlDetailName.setText(workDeviceDetailBean.getEquipmentName());
        binding.controlDetailNumber.setText(workDeviceDetailBean.getEquipmentCode());
        binding.controlDetailType.setText(workDeviceDetailBean.getEquipmentModel());
        binding.controlDetailPosition.setText(workDeviceDetailBean.getWorkshopName());
        binding.controlDetailPerson.setText(workDeviceDetailBean.getUserName());
        binding.controlDetailPhone.setText(workDeviceDetailBean.getUserPhone());
        binding.controlDetailContent.setText(workDeviceDetailBean.getErrorDetail());
        binding.controlDetailTime.setText(workDeviceDetailBean.getLastMaintainTime());

        binding.controlDetailRv.setAdapter(new TagAdapter<String>(workDeviceDetailBean.getRepairResultOption()) {
            @Override
            public View getView(FlowLayout parent, int position, String content) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_checkbox,
                        binding.controlDetailRv, false);
                CheckBox cb = view.findViewById(R.id.item_cb);
                cb.setText(content);
                return view;
            }
        });
        binding.controlSwitchTv.setText(workDeviceDetailBean.getDeviceStatus());
        binding.controlRepairResultTv.setText(workDeviceDetailBean.getRepairResult());

        if (state == REPAIR) {
            binding.controlUploadIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    workDeviceDetailActivity.openAlbum(BaseFileUploadActivity.FIRST);
                }
            });

            binding.controlDetailCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> checkedList = binding.controlDetailRv.getSelected();
                    if (checkedList.size() > 0 && photoList != null && photoList.size() > 0) {
                        workDeviceDetailActivity.setCurrentPhotoType(BaseFileUploadActivity.FIRST);
                        workDeviceDetailActivity.checkPhotoAndFileUpLoad(photoList);
                    } else {
                        Toast.makeText(getContext(), "请选择故障类型和照片", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            photoAdapter = new TagPhotoAdapter(getContext(), StringUtils.splitPhotoUrl(workDeviceDetailBean.getErrorImgUrl()), false);
            binding.pictureTl.setAdapter(photoAdapter);
        }

    }

    public void finishRepair(List<String> urlList) {
        List<String> checkList = binding.controlDetailRv.getSelected();
        viewModel.finishRepair(urlList, checkList, deviceID, binding.controlDetailSt.isChecked());

    }


    @Override
    public ArrayList<String> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onPhotoSelected(ArrayList<String> dataList) {
        photoList.clear();
        photoList.addAll(dataList);
        if (photoAdapter == null) {
            photoAdapter = new TagPhotoAdapter(getContext(), photoList);
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
