package com.guyuan.dear.umeng;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.utils.LogUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/31 16:19
 * @company: 固远（深圳）信息技术有限公司
 **/
public class UmengAliasManager {
    private String aliasType = null;
    private static UmengAliasManager aliasManager;
    private PushAgent pushAgent;

    public static UmengAliasManager getInstance() {
        if (aliasManager == null) {
            synchronized (UmengAliasManager.class) {
                if (aliasManager == null) {
                    aliasManager = new UmengAliasManager();
                }
            }
        }
        return aliasManager;
    }

    private UmengAliasManager() {
        pushAgent = PushAgent.getInstance(DearApplication.getInstance());
        String buildType = BuildConfig.BUILD_TYPE;
        boolean isDebug = BuildConfig.DEBUG;
        switch (buildType) {
            case "debug":
                if (isDebug) {
                    aliasType = "DEV_DEBUG";
                } else {
                    aliasType = "DEV_RELEASE";
                }
                break;
            case "check":
                if (isDebug) {
                    aliasType = "TEST_DEBUG";
                } else {
                    aliasType = "TEST_RELEASE";
                }
                break;
            case "release":
                if (isDebug) {
                    aliasType = "DEAR_DEBUG";
                } else {
                    aliasType = "DEAR_RELEASE";
                }
                break;
            default:
                aliasType = "UNKNOWN_VERSION";
                break;
        }
    }


    /**
     * 判断当前登录用户是否需要替代之前登录用户，重新在友盟服务器中绑定设备
     *
     * @param newUserId
     */
    public void updatePushAlias(@Nullable Long preUserId, @NonNull Long newUserId) {
        LogUtils.showLog("旧别名：" + genUserAlias(preUserId) + " vs 新别名：" + genUserAlias(newUserId));
        if (preUserId == null || preUserId <= 0) {
            bindNewAlias(genUserAlias(newUserId));
        } else {
            //解除上一个用户的绑定
            if (!preUserId.equals(newUserId)) {
                pushAgent.deleteAlias(genUserAlias(preUserId), aliasType, new UTrack.ICallBack() {
                    @Override
                    public void onMessage(boolean b, String s) {
                        if (b) {
                            LogUtils.showLog("解除绑定之前的用户成功：" + s);
                            bindNewAlias(genUserAlias(newUserId));
                        } else {
                            LogUtils.showLog("解除绑定之前的用户失败：" + s);
                        }
                    }
                });
            } else {
                LogUtils.showLog("用户重新登录，不需要更新绑定");
            }
        }
    }

    private void bindNewAlias(String newAlias) {
        LogUtils.showLog("开始绑定新用户...");
        pushAgent.addAlias(newAlias, aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                if (b) {
                    LogUtils.showLog("绑定新用户成功：" + s);
                } else {
                    LogUtils.showLog("绑定新用户失败：" + s);
                }
            }
        });
    }


    /**
     * 解除设备别名绑定，用户退出登陆时用
     */
    public void unregisterAlias(Long userId) {
        LogUtils.showLog("开始解除友盟绑定...");
        pushAgent.deleteAlias(genUserAlias(userId), aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                if (b) {
                    LogUtils.showLog("解除绑定友盟成功：" + s);
                } else {
                    LogUtils.showLog("解除绑定友盟失败：" + s);
                }
            }
        });
    }

    public String getAliasType() {
        return aliasType;
    }

    public String genUserAlias(@Nullable Long userId) {
        if (userId == null) {
            return null;
        }
        return aliasType + ":" + userId.toString();
    }
}
