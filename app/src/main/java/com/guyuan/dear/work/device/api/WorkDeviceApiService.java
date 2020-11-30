package com.guyuan.dear.work.device.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.work.device.data.bean.ControlDeviceBean;
import com.guyuan.dear.work.device.data.bean.ControlRepairBean;
import com.guyuan.dear.work.device.data.bean.DeviceRepairBean;
import com.guyuan.dear.work.device.data.bean.MaintainListBean;
import com.guyuan.dear.work.device.data.bean.QRBean;
import com.guyuan.dear.work.device.data.bean.WorkDeviceDetailBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/30 10:48
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface WorkDeviceApiService extends BaseApiService {
    public static final String EQUIPMENT_MAINTANCE = "EQUIPMENT_MAINTANCE";//保养
    public static final String EQUIPMENT_FAULT = "EQUIPMENT_FAULT";//故障
    public static final String EQUIPMENT_DETAIL = "EQUIPMENT_DETAIL";//维修详情

    //设备保养/维修信息
   public static final String SCAN_EQUIPMENT_INFO= BASE + "tEquipmentMaintenance/scanEquipmentInfo";

    //设备二维码查询
    public static final String QR_CODE_INFO = BASE + "qrcode/getCodeInfo";

    //提交设备保养信息
    public static final String COMMIT_MAINTENANCE = BASE + "tEquipmentMaintenance/save";

    //设备报修
    public static final String REPORT_REPAIR = BASE + "tEquipmentRepair/reportRepair";

    //维修列表
    public static final String FIND_REPAIR_LIST = BASE + "tEquipmentRepair/findRepairList";

    //具体设备维修信息
    public static final String FIND_REPAIR = BASE + "tEquipmentRepair/findRepair";

    //开始维修
    public static final String START_REPAIR = BASE + "tEquipmentRepair/startRepair";

    //完成维修
    public static final String FINISH_REPAIR = BASE + "tEquipmentRepair/finishRepair";

    //获取设备保养列表
    public static final String GET_DEVICE_MAINTAIN_LIST = BASE + "tEquipmentMaintenance" +
            "/getEquipmentMaintenanceList";

    //设备详情
    public static final String GET_WORK_DEVICE_DETAIL = BASE + "tEquipmentRepair/findRepair";

    String EQUIPMENTCODE = "equipmentCode";
    String TYPE = "type";
    String STR = "str";
    String ID = "id";

    //设备保养/维修信息
    @GET(SCAN_EQUIPMENT_INFO)
    Observable<ResultBean<ControlDeviceBean>> getEqiupmentInfo(@Query(EQUIPMENTCODE) String equipmentCode,
                                                               @Query(TYPE) String type);

    //设备二维码查询
    @GET(QR_CODE_INFO)
    Observable<ResultBean<QRBean>> getQRInfo(@Query(STR) String str);


    //设备保养提交
    @POST(COMMIT_MAINTENANCE)
    Observable<ResultBean<Integer>> commitMaintainInfo(@Body RequestBody maintainBody);

    //设备维修提交
    @POST(REPORT_REPAIR)
    Observable<ResultBean<Integer>> commitReportInfo(@Body RequestBody fixBody);



    //获取维修列表
    @POST(FIND_REPAIR_LIST)
    Observable<ResultBean<ControlRepairBean>> getRepairList(@Body RequestBody requestBody);

    //获取设备维修信息
    @GET(FIND_REPAIR)
    Observable<ResultBean<DeviceRepairBean>> getRepairDetail(@Query(ID) Long id);

    //开始维修
    @POST(START_REPAIR)
    Observable<ResultBean<Integer>> startRepair(@Body RequestBody requestBody);

    //结束维修
    @POST(FINISH_REPAIR)
    Observable<ResultBean<Integer>> finishRepair(@Body RequestBody requestBody);

    //获取设备保养列表
    @GET(GET_DEVICE_MAINTAIN_LIST)
    Observable<ResultBean<List<MaintainListBean>>> getDeviceMaintainList();

    //获取设备详情
    @GET(GET_WORK_DEVICE_DETAIL)
    Observable<ResultBean<WorkDeviceDetailBean>> getWorkDeviceDetail(@Query(ID) long id);
}