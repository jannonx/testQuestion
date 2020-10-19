package com.guyuan.dear.scan.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.Result;
import com.guyuan.dear.R;
import com.guyuan.dear.scan.interfaces.CameraInterface;
import com.guyuan.dear.scan.thread.QRHandler;
import com.guyuan.dear.scan.thread.QRThread;
import com.guyuan.dear.scan.util.ConstanceValues;


/**
 * Created by tl on 2018-8-30
 * camera1 API
 */
public class CameraFragment extends Fragment implements CameraInterface, CameraManager
    .CameraListener, Camera.PreviewCallback, Handler.Callback {

  public static final String TAG = "CameraFragment";

  private SurfaceView surfaceView;
  private CameraManager cameraManager;
  private boolean hasSurface = false;
  private CameraFragmentListener listener;
  private QRThread qrThread;
  private Handler resultHandler;
  private int screenWidth;
  private int screenHeight;

  public static CameraFragment newInstance() {

    Bundle args = new Bundle();

    CameraFragment fragment = new CameraFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_preview, container, false);
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    cameraManager = new CameraManager( this);
    resultHandler = new Handler(this);
    getPhoneWidthAndHeight();
    surfaceView = view.findViewById(R.id.camera_sv);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof CameraFragmentListener) {
      listener = (CameraFragmentListener) context;
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (hasSurface) {
      // The activity was paused but not stopped, so the surface still
      // exists. Therefore
      // surfaceCreated() won't be called, so init the camera here.
      //手机息屏时surface没有销毁,不会执行surfaceCreated()方法
      cameraManager.openCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
      cameraManager.startPreview(surfaceView.getHolder());
    } else {
      // Install the callback and wait for surfaceCreated() to init the
      // camera.
      surfaceView.getHolder().addCallback(callback);
    }
  }


  @Override
  public void onPause() {
    super.onPause();
    cameraManager.closeCamera();
    cameraManager.stopPreview();
    if (!hasSurface) {
      surfaceView.getHolder().removeCallback(callback);
    }
  }

  //识别二维码模式
  @Override
  public void qrMode() {
    if (qrThread == null) {
      qrThread = new QRThread(cameraManager, QRThread.ALL_MODE, resultHandler);
      qrThread.start();
    } else {
      qrThread.reStart();
    }
    cameraManager.setPreviewCallback(this);
  }

  //拍照模式
  @Override
  public void photoMode() {
    cameraManager.takePhoto();
  }

  //摄像模式
  @Override
  public void photographyMode() {

  }

  //人脸模式
  @Override
  public void faceMode() {

  }

  //打开相册
  @Override
  public void openAlbum() {

  }

  //相机模式操作按钮(拍照/摄像)
  @Override
  public void takePhoto() {
    cameraManager.takePhoto();
  }

  //切换摄像头
  @Override
  public void changeCamera() {
    cameraManager.changeCamera(surfaceView.getHolder());
  }

  //是否打开闪光灯
  @Override
  public void openFlash(boolean isOpen) {
    if (isOpen) {
      cameraManager.openLight();
    } else {
      cameraManager.offLight();
    }
  }

  SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {//需要在surface准备完成后再打开摄像头
      hasSurface = true;
      cameraManager.openCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
      cameraManager.startPreview(surfaceHolder);
      qrMode();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
      hasSurface = false;
    }
  };


  private void addFaceFrame() {

  }

  @Override
  public void showPhoto(Bitmap photo) {
    if (listener != null) {
      listener.showPhoto(photo);
    }
  }

  @Override
  public void onPreviewFrame(byte[] data, Camera camera) {
    //二维码识别
    if (qrThread != null && !qrThread.isPause()) {
      QRHandler qrHandler = qrThread.getQRHandler();
      if (qrHandler != null) {
        Message qrMsg = qrHandler.obtainMessage(ConstanceValues.QR_DETECH, data);
        qrHandler.sendMessage(qrMsg);

      }
    }
  }

  @Override
  public boolean handleMessage(Message msg) {

    switch (msg.what) {
      case ConstanceValues.QR_SUCCESS:
        Result result = (Result) msg.obj;
        //  Toast.makeText(getContext(), result.getText(), Toast.LENGTH_SHORT).show();
        if (listener != null) {
          listener.showCode(result.getText());
        }
        break;

      case ConstanceValues.QR_FAIL:
        // Toast.makeText(getContext(), "no qr", Toast.LENGTH_SHORT).show();
        cameraManager.setPreviewCallback(this);
        break;

      case ConstanceValues.FACE_SUCCESS:
        Toast.makeText(getContext(), "检测到人脸", Toast.LENGTH_SHORT).show();
        cameraManager.setPreviewCallback(this);
        break;

      case ConstanceValues.FACE_FAIL:
        //  Toast.makeText(getContext(), "未检测到人脸", Toast.LENGTH_SHORT).show();
        cameraManager.setPreviewCallback(this);
        break;

      case ConstanceValues.FACE_UNCLEAR:
        cameraManager.setPreviewCallback(this);
        break;

      case ConstanceValues.FACE_IMPERFECT:
        cameraManager.setPreviewCallback(this);
        break;

      case ConstanceValues.FACE_DRAW:
        break;

      case ConstanceValues.FACE_NO_PHOTO:
        Toast.makeText(getContext(), "未获取到图像！", Toast.LENGTH_SHORT).show();
        cameraManager.setPreviewCallback(this);
        break;
    }

    return false;
  }


  private void getPhoneWidthAndHeight() {
    DisplayMetrics dm = new DisplayMetrics();
    dm = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
    float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
    float densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
    screenWidth = dm.widthPixels; // 屏幕宽（dip，如：320dip）
    screenHeight = dm.heightPixels; // 屏幕高（dip，如：533dip）
    int screenWidthPX = (int) (screenWidth * density + 0.5f); // 屏幕宽（px，如：720px）
    int screenHeightPX = (int) (screenHeight * density + 0.5f); // 屏幕高（px，如：1280px）
  }

  public interface CameraFragmentListener {
    void showCode(String qrCode);

    void showPhoto(Bitmap photo);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if(listener!=null){
      listener=null;
    }

    if(surfaceView!=null){
      surfaceView=null;
    }
  }
}
