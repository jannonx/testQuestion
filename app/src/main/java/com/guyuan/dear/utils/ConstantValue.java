package com.guyuan.dear.utils;

/**
 * @author : tl
 * @description :常量池
 * @since: 2020/9/8
 * @company : 固远（深圳）信息技术有限公司
 **/
public class ConstantValue {


    //===============app菜单字段和版本跳转协议==========================start

    //首页入口权限字段
    public static final String SMART_CONTROL = "SmartControl";
    public static final String FOCUS = "RealTimeProgress";
    public static final String WORK = "ProcessControl";
    public static final String OFFICE = "MobileOffice";
    public static final String ANALYSE = "IntelligentAnalysis";


    public static final String KEY_EQUIPMENT_LIST = "KEY_EQUIPMENT_LIST";
    public static final String KEY_ANALYSIS_ASPECT = "KEY_ANALYSIS_ASPECT";
    public static final String KEY_IS_EXCLCUDE_SELF = "KEY_IS_EXCLCUDE_SELF";
    public static final String KEY_WORK_FLOW_TYPE = "KEY_WORK_FLOW_TYPE";
    public static final String KEY_PRE_SELECTED_STAFFS = "KEY_PRE_SELECTED_STAFFS";
    public static final String KEY_DISABLE_STAFFS = "KEY_DISABLE_STAFFS";
    public static final String KEY_EQUIPMENT = "KEY_EQUIPMENT";
    public static final String KEY_J_PUSH_ALIAST = "'KEY_J_PUSH_ALIAST";
    public static final String KEY_ATTENDANT = "KEY_ATTENDANT";
    public static final String KEY_MEETING_ROOM = "KEY_MEETING_ROOM";
    public static final String KEY_IS_FILTER = "KEY_IS_FILTER";
    public static final String KEY_BURIED_POINT_DATA = "KEY_BURIED_POINT_DATA";
    //埋点数据用户列表
    public static final String KEY_BURIED_POINT_USER_LIST = "KEY_BURIED_POINT_USER_LIST";
    public static final String KEY_KEY_WORD = "KEY_KEY_WORD";
    public static final boolean IS_DEBUG_MODE = true;
    public static final String IS_OPEN_VOICE = "isOpenVoice";
    public static final String QR_SETTING = "qr_setting";
    public static final String KEY_START_TIME = "start_time";
    public static final String KEY_END_TIME = "end_time";
    public static final String KEY_START_MODE = "key_start_mode";
    public static final String KEY_FILTER_TYPE = "key_filter_type";
    public static final String FIRST_OPEN = "first_open";//第一次打开app
    public static final String KEY_CURRENT_UMENG_ALIAS = "key_current_umeng_alias";
    public static final String KEY_VIDEO_TYPE = "key_video_type";
    public static final String IS_NEED_LOAD_FROM_NET = "is_need_load_from_net";
    public static final String KEY_PLAY_BACK_LIST = "key_play_back_list";
    public static final String KEY_PLAY_PROGRESS_PERCENT = "key_play_progress_percent";
    public static final String KEY_COMPANY_ID = "key_company_id";
    public static final String KEY_STATUS_TYPE = "key_status_type";
    public static final String KEY_STAFF_TABLE_LAST_UPDATE_TIME = "key_staff_table_last_update_time";
    public static final String KEY_VIDEO_URL = "KEY_VIDEO_URL";
    public static int isForground;   //app是否在前台
    public static boolean hasNewVersion = false;//是否有新版本

    //todo 条码对比离线数据
    public static String BAR_PWD = "bar_pwd";
    public static String BAR_PRE = "bar_pre";
    public static String BAR_END = "bar_end";
    public static String BAR_TYPE = "bar_type";
    public static String BAR_STATE = "bar_state";

    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String CAPTCHA = "captcha";
    public static final String ID_NUMBER = "id_number";
    public static final String TOKEN_EXPIRE_TIME = "token_expire_time";
    public static final String USER_JSON_STRING = "USER_JSON_STRING";
    public static final String FACTORY_LIST = "factory_list";//缓存工厂列表

    //二维码对应类型编码
    public static final int FACTORY = 1001;//厂区
    public static final int WORKSHOP = 1002;//车间
    public static final int LINE = 1003;//流水线
    public static final int POINT = 1004;//工位
    public static final int EQUIPMENT = 1005;//设备
    public static final int SAFETY = 1006;//安全

    //===============app菜单字段和版本跳转协议==========================start

    //首页入口权限字段
    public static final String SMARTCONTROL = "SmartControl";
    public static final String REALTIMEPROGRESS = "RealTimeProgress";
    public static final String PROCESSCONTROL = "ProcessControl";
    public static final String MOBILEOFFICE = "MobileOffice";
    public static final String INTELLIGENTANALYSIS = "IntelligentAnalysis";
    public static final String MINE = "Mine";

    //实时进展
    public static final String REALTIMEPROGRESS_SAFE = "RealTimeProgress_safe";
    public static final String REALTIMEPROGRESS_PERSON = "RealTimeProgress_person";
    public static final String REALTIMEPROGRESS_DEVICE = "RealTimeProgress_device";
    public static final String REALTIMEPROGRESS_QUALITY = "RealTimeProgress_quality";
    public static final String REALTIMEPROGRESS_STOCK = "RealTimeProgress_stock";
    public static final String REALTIMEPROGRESS_PRODUCE = "RealTimeProgress_produce";
    public static final String REALTIMEPROGRESS_CONTRACT = "RealTimeProgress_contract";
    public static final String PROCESSCONTROL_MORNING_MEETINGS = "Process_Control_Morning_Meetings";
    public static final String PROCESSCONTROL_GATE_WATCHER = "Process_Control_Gate_Watcher";

    //摄像头界面
    public static final String SURVEILLANCE_ACTIVITY = "surveillanceActivity";

    //过程控制
    public static final String PROCESSCONTROL_SAFE = "ProcessControl_safe";
    public static final String PROCESSCONTROL_PERSON = "ProcessControl_person";
    public static final String PROCESSCONTROL_DEVICE = "ProcessControl_device";
    public static final String PROCESSCONTROL_QUALITY = "ProcessControl_quality";
    public static final String PROCESSCONTROL_STOCK = "ProcessControl_stock";
    public static final String PROCESSCONTROL_PRODUCE = "ProcessControl_produce";
    public static final String PROCESSCONTROL_CONTRACT = "ProcessControl_contract";
    public static final String PROCESSCONTROL_DEVICE_LIST = "ProcessControl_device_list";
    public static final String PROCESSCONTROL_SAFE_LIST = "ProcessControl_safe_list";
    public static final String PROCESSCONTROL_QRCODE_COMPARE = "ProcessControl_qrcode_compare";
    public static final String REAL_TIME_PROGRESS_FINANCIAL_MANAGEMENT =
            "REAL_TIME_PROGRESS_FINANCIAL_MANAGEMENT";


    //financial
    //智能分析
    public static final String INTELLIGENTANALYSIS_MAIN = "IntelligentAnalysis_main";//分析界面
    public static final String INTELLIGENTANALYSIS_PERSONNELEFFICIENCY =
            "IntelligentAnalysis_PersonnelEfficiency";//人员效率
    public static final String INTELLIGENTANALYSIS_DEVICEEFFICIENCY =
            "IntelligentAnalysis_DeviceEfficiency";//设备效率
    public static final String INTELLIGENTANALYSIS_PROCESSRATIONAL =
            "IntelligentAnalysis_ProcessRational";//流程合理性
    public static final String INTELLIGENTANALYSIS_STOCKRATIONAL =
            "IntelligentAnalysis_StockRational";//库存合理性
    public static final String INTELLIGENTANALYSIS_MANAGESUGGESTION =
            "IntelligentAnalysis_manageSuggestion";//改进建议
    public static final String INTELLIGENT_ANALYSIS_OPERATION_RETURN =
            "IntelligentAnalysisOperationReturn";//运营效益

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

    //审批
    public static final String MOBILE_OFFICE_APPROVE_APPLY_FOR_LEAVE =
            "MobileOfficeApproveApplyForLeave";
    //请假
    public static final String MOBILE_OFFICE_APPROVE_BUSINESS_TRIP =
            "MobileOfficeApproveBusinessTrip";//出差
    public static final String MOBILE_OFFICE_APPROVE_PURCHASE = "MobileOfficeApprovePurchase";//采购
    public static final String MOBILE_OFFICE_APPROVE_OUT = "MobileOfficeApproveOut";//外出
    public static final String MOBILE_OFFICE_APPROVE_OVERTIME = "MobileOfficeApproveOvertime";//加班
    public static final String MOBILE_OFFICE_APPROVE_EMPLOY = "MobileOfficeApproveEmploy";//招聘
    public static final String MOBILE_OFFICE_APPROVE_LEAVE_OFFICE = "MobileOfficeApproveLeaveOffice";
    //离职
    public static final String MOBILE_OFFICE_APPROVE_EXPENSE_REIMBURSEMENT =
            "MobileOfficeApproveExpenseReimbursement";//报销
    public static final String MOBILE_OFFICE_APPROVE_PAY = "MobileOfficeApprovePay";//付款
    public static final String MOBILE_OFFICE_APPROVE_IMPREST = "MobileOfficeApproveImprest";//备用金
    public static final String MOBILE_OFFICE_APPROVE_SEAL = "MobileOfficeApproveSeal";//用印
    public static final String MOBILE_OFFICE_APPROVE_COMMON = "MobileOfficeApproveCommon";//通用审批

    //我的关注action集合
    public static final String[] FOCUS_ACTIONS = {REALTIMEPROGRESS_SAFE, REALTIMEPROGRESS_PERSON,
            REALTIMEPROGRESS_DEVICE, REALTIMEPROGRESS_QUALITY, REALTIMEPROGRESS_STOCK, REALTIMEPROGRESS_PRODUCE,
            REALTIMEPROGRESS_CONTRACT, PROCESSCONTROL_MORNING_MEETINGS, PROCESSCONTROL_GATE_WATCHER,
            SURVEILLANCE_ACTIVITY};

    //我的工作action集合
    public static final String[] WORK_ACTIONS = {PROCESSCONTROL_SAFE, PROCESSCONTROL_PERSON,
            PROCESSCONTROL_DEVICE, PROCESSCONTROL_QUALITY, PROCESSCONTROL_STOCK, PROCESSCONTROL_PRODUCE,
            PROCESSCONTROL_CONTRACT, PROCESSCONTROL_DEVICE_LIST, PROCESSCONTROL_SAFE_LIST,
            PROCESSCONTROL_QRCODE_COMPARE};

    //===============app菜单字段和版本跳转协议==========================end


    //产品
    public static final String CONTROL_STOCK_FINISH_PRODUCT = "成品";  //2
    public static final String CONTROL_STOCK_PART = "部件";            //1
    public static final String CONTROL_STOCK_SPARE = "零件";           //0
    public static final String CONTROL_STOCK_MATERIAL = "原材料";      //3

    //异常code
    public static final int HTTP_ERROR = 0x900;    //网络异常
    public static final int TIMEOUT_ERROR = 0x901;  //连接超时
    public static final int JSON_ERROR = 0x902;    //json解析异常
    public static final int SERVER_ERROR = 0x903;  //服务器异常
    public static final int CONNECT_ERROR = 0x904; //连接失败
    public static final int UNKNOW_ERROR = 0x905; //其他错误

    public static final String KEY_LIVE_STREAM_START_MODE = "KEY_LIVE_STREAM_START_MODE";
    public static final int VALUE_LIVE_STREAM_MODE_HOST = 1;
    public static final int VALUE_LIVE_STREAM_MODE_GUEST = 0;
    public static final String KEY_DEVICE_SERIAL_NUMBER = "KEY_DEVICE_SERIAL_NUMBER";
    public static final String KEY_CAMERA_CHANNEL_ID = "KEY_CAMERA_CHANNEL_ID";
    public static final String KEY_USER_NAME = "KEY_USER_NAME";
    public static final String KEY_USER_PW = "KEY_USER_PW";
    public static final String KEY_CONTENT = "KEY_CONTENT";
    /**
     * 是否是审批人
     */
    public static final String KEY_IS_APPROVER = "KEY_IS_APPROVER";
    public static final String KEY_YING_SHI_TOKEN = "KEY_YING_SHI_TOKEN";
    public static final String KEY_MEETING_INIT_MESSAGE = "KEY_MEETING_INIT_MESSAGE";
    public static final String ACTION_SMART_FACTORY_MQTT_SERVICE_DESTROY =
            "ACTION_SMART_FACTORY_MQTT_SERVICE_DESTROY";
    public static final String ACTION_MQTT_DEAMON_SERVICE_DESTROY =
            "ACTION_MQTT_DEAMON_SERVICE_DESTROY";
    public static final String KEY_WORK_SHOP = "KEY_WORK_SHOP";
    public static final String KEY_STAFF = "KEY_STAFF";
    public static final String KEY_SCANNER_VIEW_ACTIVITY_START_MODE =
            "KEY_SCANNER_VIEW_ACTIVITY_START_MODE";
    public static final String KEY_MESSAGE = "KEY_MESSAGE";
    public static final String KEY_IMG_SRC = "KEY_IMG_SRC";
    public static final String KEY_DEFECT_REPORT = "KEY_DEFECT_REPORT";
    public static final String KEY_SELECTION = "KEY_SELECTION";
    public static final String KEY_REPORT_TYPE = "KEY_REPORT_TYPE";
    public static final int REQUEST_CODE_TAKE_PICTURE = 9527;
    public static final String KEY_QC_REPORT = "KEY_QC_REPORT";
    public static final String KEY_APPROVE_MENU = "KEY_APPROVE_MENU";
    public static final String KEY_IS_FQC = "KEY_IS_FQC";
    public static final String KEY_TYPE_QUALITY = "KEY_TYPE_QUALITY";
    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_EXCEPTION = "KEY_EXCEPTION";    //异常列表标识
    public static final int STATE_EXCEPTION = 0x001;  //异常状态
    public static final String KEY_STAFF_LIST = "KEY_STAFF_LIST";
    public static final String KEY_APPLY_FOR_APPROVE = "KEY_APPLY_FOR_APPROVE";
    public static final String KEY_STAFF_ID = "KEY_STAFF_ID";

    //选人activity回调code
    public static final int REQUEST_APPROVE = 0x001;  //审批人
    public static final int REQUEST_PARTNER = 0x002;  //同行人
    public static final int REQUEST_COPY = 0x003;     //抄送人

    public static final String KEY_MAX_SELECT_COUNT = "KEY_MAX_SELECT_COUNT";

    public static boolean IS_ENABLE_PULL_AND_REFRESH = false;
    public static boolean IS_ENABLE_TICK_AND_REFRESH = false;

    public static final String UMENG_USER_ALIAS_TYPE = "user_name_plus_id";
    public static final String TEXT_PLEASE_SELECT = "请选择";

    //提示语
    public static final String NO_INTERNET = "无网络,请检查网络连接！";


    public static final int PAGE_SIZE = 30;
    public static final int FIRST_PAGE = 1;
    public static final int CONST_MAX_STAFF_COUNT = 13;

    public static final int LOAD_MORE = 0X0100;
    public static final int REFRESH = 0X0101;


    //库存相关
    public static final String STOCK_FLOW = "stockFlow";
    public static final int STOCK_IN = 1;     //入库
    public static final int STOCK_OUT = 2;    //出库

    public static final String STOCK_TYPE = "stockType";
    public static final int STOCK_MATERIAL = 1;
    public static final int STOCK_PRODUCT = 2;
}
