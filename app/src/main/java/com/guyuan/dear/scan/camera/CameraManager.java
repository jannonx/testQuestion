package com.guyuan.dear.scan.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import com.guyuan.dear.scan.util.BitmapUtil;

import java.io.IOException;

/**
 * Created by tl on 2018-8-31
 * 管理camera的一些操作
 */
public class CameraManager {
  private int cameraNum;
  private Camera.CameraInfo frontCameraInfo;
  private Camera.CameraInfo backCameraInfo;
  private Camera camera;
  private int currentCamera = -1;
  private CameraConfigurationManager configurationManager;
  private boolean isInitParms = false;
  private CameraListener listener;

  public CameraManager(CameraListener listener) {
    configurationManager = new CameraConfigurationManager();
    this.listener = listener;
  }

  /**
   * 打开摄像头
   * @param cameraID id
   */
  public void openCamera(int cameraID) {
    cameraNum = Camera.getNumberOfCameras();
    if (cameraNum == 0) return;
    getCameraInfo();
    if (cameraID == Camera.CameraInfo.CAMERA_FACING_FRONT && frontCameraInfo != null) {
      camera = Camera.open(cameraID);
      currentCamera = cameraID;
    } else if (cameraID == Camera.CameraInfo.CAMERA_FACING_BACK && backCameraInfo != null) {
      camera = Camera.open(cameraID);
      currentCamera = cameraID;
    } else {
      Log.e("Camera", "this device has no such camera");
    }
  }

  /**
   * 关闭摄像头
   */
  public void closeCamera() {
    if (camera != null) {
      camera.release();
      camera = null;
    }
  }

  /**
   * 设置相机预览回调
   *
   * @param callback callback
   */
  public void setPreviewCallback(Camera.PreviewCallback callback) {
    if (camera != null) {
      camera.setOneShotPreviewCallback(callback);
    }
  }

  /**
   * 开启预览
   */
  public void startPreview(SurfaceHolder holder) {
    if (camera != null) {
      try {

        if (!isInitParms) {
          configurationManager.initFromCameraParameters(camera);//默认摄像头是横向的，需要替换屏幕宽高
        }

        configurationManager.setDesiredCameraParameters(camera, false);//配置预览尺寸
        camera.setPreviewDisplay(holder);
        camera.startPreview();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 关闭预览
   */
  public void stopPreview() {
    if (camera != null) {
      camera.stopPreview();
    }
  }

  /**
   * 获取当前开启的摄像头id
   *
   * @return id
   */
  public int getOpendCameraID() {
    return currentCamera;
  }

  /**
   * 打开闪光灯
   */
  public void openLight() {
    if (camera != null) {
      Camera.Parameters parameter = camera.getParameters();
      parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
      camera.setParameters(parameter);
    }
  }

  /**
   * 关闭闪光灯
   */
  public void offLight() {
    if (camera != null) {
      Camera.Parameters parameter = camera.getParameters();
      parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
      camera.setParameters(parameter);
    }
  }

  /**
   * 切换摄像头
   */
  public void changeCamera(SurfaceHolder holder) {
    closeCamera();
    stopPreview();
    if (currentCamera == Camera.CameraInfo.CAMERA_FACING_FRONT) {
      currentCamera = Camera.CameraInfo.CAMERA_FACING_BACK;
      openCamera(currentCamera);
    } else if (currentCamera == Camera.CameraInfo.CAMERA_FACING_BACK) {
      currentCamera = Camera.CameraInfo.CAMERA_FACING_FRONT;
      openCamera(currentCamera);
    }
    startPreview(holder);
  }

  /**
   * 获取相机分辨率
   *
   * @return
   */
  //  public Point getCameraResolution() {
  //    return configManager.getCameraResolution();
  //  }

  /**
   * 获取预览尺寸
   *
   * @return size
   */
  public Camera.Size getPreviewSize() {
    if (null != camera) {
      return camera.getParameters().getPreviewSize();
    }
    return null;
  }

  /**
   * 拍照
   */
  public void takePhoto() {
    if (camera != null) {
      camera.takePicture(null, null, new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
          try {
            Bitmap originBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap photoBitmap;
            if (currentCamera == Camera.CameraInfo.CAMERA_FACING_FRONT) {
              Bitmap mirrorBitmap = BitmapUtil.mirrorImg(originBitmap);
              photoBitmap = BitmapUtil.rotateBitmapByDegree(mirrorBitmap, 90);
            } else {
              photoBitmap = BitmapUtil.rotateBitmapByDegree(originBitmap, 90);
            }
            if (photoBitmap != null) {
              listener.showPhoto(photoBitmap);
            }
            camera.startPreview();

          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
    }
  }

  /**
   * 获取设备摄像头信息
   */
  private void getCameraInfo() {
    for (int i = 0; i < cameraNum; i++) {
      Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
      Camera.getCameraInfo(i, cameraInfo);
      if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
        frontCameraInfo = cameraInfo;
      } else {
        backCameraInfo = cameraInfo;
      }
    }
  }

  interface CameraListener {
    void showPhoto(Bitmap photo);
  }
}
