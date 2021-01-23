package com.jannonx.electric.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import com.jannonx.electric.base.app.DearApplication;
import com.jannonx.electric.utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * @author : Jannonx
 * @description : 开启后台下载服务
 * @since: 2020/11/25 17:39
 **/
public class BackService extends IntentService {
    private DearApplication mApplication;

    public BackService() {
        super("BackService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = DearApplication.getInstance();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            final Bundle bundle = intent.getExtras();
            if (BackTaskType.TYPE_WR_DB.getDes().equals(action)) {//读写数据库
                readAndWriteDataBase();
            }
        }
    }

    /**
     * 读取数据库
     */
    private void readAndWriteDataBase() {
        //getFilesDir()方法用于获取/data/data//files目录
        final File file = new File(getFilesDir(), "testQuestion.db");
        System.out.println("文件路径---->" + getFilesDir());
        if (file.exists()) {//文件存在了就不需要拷贝了
            LogUtils.showLog("数据库文件已经存在,不需要再拷贝");
            return;
        }
        LogUtils.showLog("进行数据库文件拷贝...");
        try {
            //获取资产目录管理器
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open("testQuestion.db");//输入流
            FileOutputStream fos = new FileOutputStream(file);//输出流
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LogUtils.showLog("完成数据库文件拷贝");
        }
    }


}

