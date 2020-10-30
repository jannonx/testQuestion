package com.guyuan.dear.focus.device.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.databinding.ActivityFocusDeviceDetailBinding;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;
import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 18:00
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceDetailActivity extends BaseToolbarActivity<ActivityFocusDeviceDetailBinding, BaseViewModel> implements View.OnClickListener {

    public static final String CONTENT = "content";
    public static final String TYPE = "type";
    public static final int NORMAL = 0x0001;
    public static final int EXCEPTION = 0x0002;
    private EquipmentBean equipmentsBean;
    private DeviceExceptionBean.ContentBean contentBean;
    private String currentDvrSeriesNum;
    private String currentCameraChannel;
    private String url;

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        int type = getIntent().getIntExtra(TYPE, 0);
        if (type == NORMAL) {
            setTitleCenter("设备详情");
            equipmentsBean = getIntent().getParcelableExtra(CONTENT);
            if (equipmentsBean != null) {
                setData(equipmentsBean);
            }
        } else if (type == EXCEPTION) {
            setTitleCenter("设备异常");
            contentBean = getIntent().getParcelableExtra(CONTENT);
            if (contentBean != null) {
                setData(contentBean);
            }
        }
        binding.tvCreateTime.setText(type == NORMAL ? "安装时间" : "报修时间");
        binding.cameraTv.setOnClickListener(this);
        binding.deviceIv.setOnClickListener(this);
    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_focus_device_detail;
    }


    private void setData(EquipmentBean bean) {

        if (bean.getTcameraBase() != null) {
            currentDvrSeriesNum = bean.getTcameraBase().getDvrSeriesNum();
            currentCameraChannel = bean.getTcameraBase().getCameraChannel();
        }

        binding.deviceIdTv.setText(bean.getCode());
        binding.deviceNameTv.setText(bean.getName());
        binding.deviceTypeTv.setText(bean.getEquipmentModel());
        binding.deviceFactoryTv.setText(bean.getVfactoryId());
        binding.deviceWorkshopTv.setText(bean.getVworkshopId());
        binding.deviceTimeTv.setText(bean.getInstallation());
        binding.deviceFactoryNameTv.setText(bean.getSupplierName());
        if (bean.getTEquipmentInfo() != null) {
            EquipmentBean.TEquipmentInfoBean infoBean = bean.getTEquipmentInfo();
            binding.deviceFactoryOpenTimeTv.setText(infoBean.getStartTime());
            binding.deviceFactoryCloseTimeTv.setText(infoBean.getStopTime());
            int status = infoBean.getStatus();
            //status-(状态)：1.运行 2.停机 3.保养 4.维修 5.故障
            switch (status) {
                case 1:
                    binding.deviceStateTv.setText("状态正常");
                    binding.statusIv.setImageResource(R.mipmap.status_normal);
                    break;

                case 2:
                    binding.deviceStateTv.setText("停机状态");
                    binding.statusIv.setImageResource(R.mipmap.status_exception);
                    break;

                case 3:
                    binding.deviceStateTv.setText("保养状态");
                    binding.statusIv.setImageResource(R.mipmap.status_exception);
                    break;

                case 4:
                    binding.deviceStateTv.setText("维修状态");
                    binding.statusIv.setImageResource(R.mipmap.status_exception);
                    break;

                case 5:
                    binding.deviceStateTv.setText("故障状态");
                    binding.statusIv.setImageResource(R.mipmap.status_exception);
                    break;
            }

        }
        url = bean.getImg();
        List<String> imgUrl = StringUtils.splitPhotoUrl(url);
        if (imgUrl != null && imgUrl.size() > 0) {
            GlideUtils.getInstance().loadImageInRound(binding.deviceIv, imgUrl.get(0), 4);
        }

    }

    private void setData(DeviceExceptionBean.ContentBean bean) {

        if (bean.getTcameraBase() != null) {
            currentDvrSeriesNum = bean.getTcameraBase().getDvrSeriesNum();
            currentCameraChannel = bean.getTcameraBase().getCameraChannel();
        }

        binding.deviceIdTv.setText(bean.getEquipmentCode());
        binding.deviceNameTv.setText(bean.getVequipmentName());
        binding.deviceTypeTv.setText(bean.getEquipmentModel());
        binding.deviceFactoryTv.setText(bean.getVfactoryId());
        binding.deviceWorkshopTv.setText(bean.getVworkshopId());
        binding.deviceTimeTv.setText(bean.getExTime());
        binding.deviceFactoryNameTv.setText(bean.getSupplierName());
        int status = bean.getStatus();//1:异常,2:正常,3:维修中
        switch (status) {
            case 1:
                binding.deviceStateTv.setText("状态异常");//设备异常列表全是异常状态
                binding.statusIv.setImageResource(R.mipmap.status_exception);
                break;
            case 2:
                binding.deviceStateTv.setText("状态正常");//设备异常列表全是异常状态
                binding.statusIv.setImageResource(R.mipmap.status_normal);
                break;

            case 3:
                binding.deviceStateTv.setText("维修中");//设备异常列表全是异常状态
                binding.statusIv.setImageResource(R.mipmap.status_exception);
                break;
        }
        url = bean.getImgUrl();
        List<String> imgUrl = StringUtils.splitPhotoUrl(url);
        if (imgUrl != null && imgUrl.size() > 0) {
            GlideUtils.getInstance().loadImageInRound(binding.deviceIv, imgUrl.get(0), 4);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_tv:
                if (TextUtils.isEmpty(currentDvrSeriesNum) || TextUtils.isEmpty(currentCameraChannel)) {
                    showToastTip("设备未绑定摄像头");
                    return;
                }
                try {
                    long l = Long.parseLong(currentCameraChannel);
                    //    SurveillanceActivity.start(this, currentDvrSeriesNum, (int) l);
                } catch (NumberFormatException e) {
                    showToastTip("摄像头平道格式有误");
                }
                break;

            case R.id.device_iv:
                FullScreenShowActivity.start(FocusDeviceDetailActivity.this, url);
                break;
            default:
                break;
        }
    }
}
