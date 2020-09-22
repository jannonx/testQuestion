package com.guyuan.dear.focus.device.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.focus.device.data.beans.DeviceExceptionBean;
import com.guyuan.dear.focus.device.data.beans.DeviceNumberBean;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.focus.device.data.beans.FactoryRealTimeBean;
import com.guyuan.dear.focus.device.data.beans.FarmBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 12:06
 * @company : 固远（深圳）信息技术有限公司
 **/
public interface FocusDeviceApiService extends BaseApiService {
    String ID = "id";
    String TYPE = "type";
    String TYPE_ID = "typeId";

    String EQUIPMENT_SUM = BASE + "tEquipment/equipmentSum";
    String GET_CURRENT_EQUIPMENTS = BASE + "tFactory/getCurrentEquipments";
    String EQUIPMENT_EXCEPTION = BASE + "tEquipmentException/findPage";
    String GET_FACTORY_KIDS = BASE + "tFactory/getKids";
    String GET_EQUIPMENT_LINE = BASE + "tWorkshop/getLineEquipment";
    String GET_ITEM_DEVICE = BASE + "tEquipment/findByEquipmentDetail";

    //查询设备总数
    @GET(EQUIPMENT_SUM)
    Observable<ResultBean<DeviceNumberBean>> getDeviceNumber();


    //获取厂区车间和设备实时信息
    @GET(GET_CURRENT_EQUIPMENTS)
    Observable<ResultBean<FactoryRealTimeBean>> getFactoryRealTimeList(@Query(ID) Long id);

    //设备异常列表
    @POST(EQUIPMENT_EXCEPTION)
    @FormUrlEncoded
    Observable<ResultBean<DeviceExceptionBean>> getExceptionList(@Field(PAGE_INDEX) int pageIndex);

    //获取厂区车间或流水线(1:车间 2:流水线)的设备
    @GET(GET_FACTORY_KIDS)
    Observable<ResultBean<List<FarmBean>>> getDeviceByType(@Query(TYPE) int type, @Query(ID) int id);


    //获取设备概览分类设备
    @GET(GET_ITEM_DEVICE)
    Observable<ResultBean<List<EquipmentBean>>> getOverViewTypeDevice(@Query(TYPE_ID) long id);

    //获取设备概览所有分类设备
    @GET(GET_ITEM_DEVICE)
    Observable<ResultBean<List<EquipmentBean>>> getOverviewTotalDevice();
}
