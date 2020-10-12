package com.guyuan.dear.scan.interfaces;

/**
 * Created by tl on 2018-8-31
 */
public interface CameraInterface {
  void qrMode();

  void photoMode();

  void photographyMode();

  void faceMode();

  void openAlbum();

  void takePhoto();

  void changeCamera();

  void openFlash(boolean isOpen);

}
