package com.guyuan.dear.utils;

/**
 * @author : tl
 * @description :常量池
 * @since: 2020/9/8
 * @company : 固远（深圳）信息技术有限公司
 **/
public class ConstantValue {
    public static final String FIRST_OPEN = "first_open";//第一次打开app
    public static final String KEY_EXCEPTION = "KEY_EXCEPTION";    //异常列表标识
    public static final int STATE_EXCEPTION = 0x001;  //异常状态

    //===============app菜单字段和版本跳转协议==========================start

    //首页入口权限字段
    public static final String SMART_CONTROL = "SmartControl";
    public static final String FOCUS = "RealTimeProgress";
    public static final String WORK = "ProcessControl";
    public static final String OFFICE = "MobileOffice";
    public static final String ANALYSE = "IntelligentAnalysis";
    public static final String MINE = "Mine";


    //我的关注action集合
    public static final String[] FOCUS_ACTIONS = {};


    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_MENU = "KEY_MENU";

    //登录键
    public static final String KEY_USER_NAME = "KEY_USER_NAME";
    public static final String KEY_USER_PW = "KEY_USER_PW";
    public static final String USER_JSON_STRING = "USER_JSON_STRING";

    //提示语
    public static final String NO_INTERNET = "无网络,请检查网络连接！";

    public static boolean hasNewVersion = false;//是否有新版本

    public static final String KEY_CONTENT = "KEY_CONTENT";
    public static final String KEY_APPROVE_MENU = "KEY_APPROVE_MENU";

    //移动办公
    public static final String MOBILE_OFFICE_PENDING_FOR_MY_APPROVAL =
            "MobileOfficePendingForMyApproval";//待我审批
    public static final String MOBILEOFFICE_MEETING = "MobileOffice_Meeting";
    public static final String MOBILEOFFICE_NOTICE = "MobileOffice_Notice";
    public static final String MOBILEOFFICE_SIGNIN = "MobileOffice_SignIn";
    public static final String MOBILEOFFICE_SIGNOUT = "MobileOffice_SignOut";
    public static final String MOBILEOFFICE_UPLOAD = "MobileOffice_UpLoad";
    public static final String MOBILE_OFFICE_MY_APPLY = "MobileOfficeMyApply";//我的申请
    public static final String MOBILE_OFFICE_MY_APPROVAL_HISTORY = "MobileOfficeMyApprovalHistory";
    public static final String MOBILE_OFFICE_APPROVALS_BROWSER = "MobileOfficeApprovalsBrowser";//我的审批
    public static final String MOBILEOFFICE_REPORT = "MobileOffice_Report"; //工作报告
    public static final String MobileOffice_Sign = "MobileOffice_Sign"; //签到打卡
}
