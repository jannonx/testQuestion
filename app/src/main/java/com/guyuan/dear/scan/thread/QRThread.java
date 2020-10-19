package com.guyuan.dear.scan.thread;

import android.os.Handler;
import android.os.Looper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.guyuan.dear.scan.base.BaseThread;
import com.guyuan.dear.scan.camera.CameraManager;
import com.guyuan.dear.scan.util.DecodeFormatManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;


/**
 * Created by tl on 2018-8-30
 * 处理照片的线程
 */
public class QRThread extends BaseThread {

  public static final String BARCODE_BITMAP = "barcode_bitmap";
  public static final int BARCODE_MODE = 0X900;
  public static final int QRCODE_MODE = 0X901;
  public static final int ALL_MODE = 0X902;

  private QRHandler QRHandler;
  private Map<DecodeHintType, Object> hints;

  public QRThread(CameraManager cameraManager, int decodeMode, Handler resultHandler) {

    hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);

    //设置支持扫描的条码
    Collection<BarcodeFormat> decodeFormats = new ArrayList<BarcodeFormat>(
        EnumSet.of(BarcodeFormat.AZTEC, BarcodeFormat.PDF_417, BarcodeFormat.CODABAR));


    switch (decodeMode) {
      case BARCODE_MODE:
        decodeFormats.addAll(DecodeFormatManager.getBarCodeFormats());
        break;

      case QRCODE_MODE:
        decodeFormats.addAll(DecodeFormatManager.getQrCodeFormats());
        break;

      case ALL_MODE:
        decodeFormats.addAll(DecodeFormatManager.getBarCodeFormats());
        decodeFormats.addAll(DecodeFormatManager.getQrCodeFormats());
        break;

      default:
        break;
    }

    hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);

    QRHandler = new QRHandler(cameraManager,hints,resultHandler);
  }


  public Map<DecodeHintType, Object> getHints() {
    return hints;
  }

  public QRHandler getQRHandler() {
    return QRHandler;
  }

  public void removeHandler() {
    QRHandler = null;
  }

  @Override
  public void run() {
    super.run();
    Looper.prepare();
    Looper.loop();
  }


}
