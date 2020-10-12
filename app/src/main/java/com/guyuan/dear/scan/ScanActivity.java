package com.guyuan.dear.scan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.widget.Toolbar;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityScanBinding;
import com.guyuan.dear.scan.camera.CameraFragment;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import org.greenrobot.eventbus.EventBus;
import io.reactivex.disposables.Disposable;

/**
 * 二维码扫描界面
 */

public class ScanActivity extends BaseNoToolbarActivity<ActivityScanBinding, BaseViewModel> implements CameraFragment.CameraFragmentListener {


    public static final String QRCODE = "qr_code";
    private static final int START_MODE_SIGN_IN = 1;
    private static final int START_MODE_SIGN_OFF = 2;
    private static final int START_MODE_UPDATE_PERFORMANCE = 3;
    private CameraFragment cameraFragment;
    private String title;

    public static void starter(Context context,String title) {
        Intent starter = new Intent(context, ScanActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        if (!TextUtils.isEmpty(title)) {
            binding.titleCenter.setText("请扫描" + title + "二维码");

        }

        checkPermissions(Manifest.permission.CAMERA);

       binding.toolbar.setNavigationIcon(R.mipmap.back);
       binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cameraFragment != null) {
                    cameraFragment.openFlash(isChecked);
                }
            }
        });
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_scan;
    }

    @Override
    public void onAllPermissionsGranted() {
        super.onAllPermissionsGranted();
        cameraFragment = CameraFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fragmentManager, cameraFragment, R.id.container,
            CameraFragment.TAG);
    }


    @Override
    public void showCode(String qrCode) {
        if (qrCode == null) {
            Toast.makeText(this, "未发现二维码", Toast.LENGTH_SHORT).show();
        } else {
            if (qrCode.contains("|")) {
                String[] content = qrCode.split("\\|");
                if (CommonUtils.isNumber(content[0])) {
                    int type = Integer.parseInt(content[0]);
                    switch (type) {
                        case ConstantValue.FACTORY://工厂

                            break;

                        case ConstantValue.WORKSHOP://车间

                            break;

                        case ConstantValue.LINE://流水线

                        case ConstantValue.POINT://工位
                            return;

                        case ConstantValue.EQUIPMENT://设备
//                            Bundle deviceBundle = new Bundle();
//                            deviceBundle.putInt(ControlDeviceActivity.TYPE, ControlDeviceActivity.ALL);
//                            deviceBundle.putString(ScanActivity.QRCODE, qrCode);
//                            deviceBundle.putString(ControlDeviceActivity.TITLE, "设备");
//                            startOtherActivityByAction(ConstantValue.PROCESSCONTROL_DEVICE, deviceBundle);
                            break;

                        case ConstantValue.SAFETY://安全
//                            Bundle bundle = new Bundle();
//                            bundle.putInt(ControlSecurityActivity.TYPE, ControlSecurityActivity.ALL);
//                            bundle.putString(ScanActivity.QRCODE, qrCode);
//                            bundle.putString(ControlSecurityActivity.TITLE, "安全");
//                            startOtherActivityByAction(ConstantValue.PROCESSCONTROL_SAFE, bundle);
                            break;
                    }
                } else {
                    showToastTip("无效二维码");
                }
            } else {
                showToastTip("无效二维码");
            }
        }
        finish();
    }

    @Override
    public void showPhoto(Bitmap photo) {

    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}
