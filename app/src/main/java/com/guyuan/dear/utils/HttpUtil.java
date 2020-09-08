package com.guyuan.dear.utils;

/**
 * @author : tl
 * @description :管理接口地址
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/
public class HttpUtil {

    private static final String BASE = "base/";

    private static final String MESSAGE = "message/";

    private static final String FILE = "file/";

    private static final String ANALYSIS = "analysis/";

    //登录
    public static final String LOGIN = BASE + "login";
    //查询所有设备信息
    public static final String DEVICE_INFO = BASE + "tEquipment/findPage";
    //设备详情
    public static final String DEVICE_DETAIL = BASE + "tEquipment/findById";


}
