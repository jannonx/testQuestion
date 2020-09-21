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
import retrofit2.http.GET;
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
    String WORKSHOP = BASE + "tWorkshop/findPage";
    String FACTORY = BASE + "tFactory/findPage";
    String GET_CURRENT_EQUIPMENTS = BASE + "tFactory/getCurrentEquipments";
    String EQUIPMENT_EXCEPTION = BASE + "tEquipmentException/findPage";
    String GET_FACTORY_KIDS = BASE + "tFactory/getKids";
    String GET_EQUIPMENT_LINE = BASE + "tWorkshop/getLineEquipment";
    String GET_ITEM_DEVICE = BASE + "tEquipment/findByEquipmentDetail";

    //查询设备总数
    @GET(EQUIPMENT_SUM)
    Observable<ResultBean<DeviceNumberBean>> getDeviceNumber();

    //查询车间
    @POST(WORKSHOP)
    Observable<ResultBean<FactoryBean>> getWorkShopList(@Body RequestBody body);

    //查询厂房
    @POST(FACTORY)
    Observable<ResultBean<FactoryBean>> getFactoryList(@Body RequestBody body);

    //获取厂区车间和设备实时信息
    @GET(GET_CURRENT_EQUIPMENTS)
    Observable<ResultBean<FactoryRealTimeBean>> getFactoryRealTimeList(@Query(ID) Long id);

    //设备异常列表
    @POST(EQUIPMENT_EXCEPTION)
    Observable<ResultBean<DeviceExceptionBean>> getExceptionList(@Body RequestBody body);

    //获取厂区车间或流水线(1:车间 2:流水线)
    @GET(GET_FACTORY_KIDS)
    Observable<ResultBean<List<FarmBean>>> getFactoryFarms(@Query(TYPE) int type, @Query(ID) int id);

    @GET(GET_FACTORY_KIDS)
    Observable<ResultBean<List<FarmBean>>> getFarmLines(@Query(TYPE) int type, @Query(ID) int id);

    //获取流水线设备
    @GET(GET_EQUIPMENT_LINE)
    Observable<ResultBean<List<EquipmentBean>>> getEquipmentsFromLine(@Query(ID) int id);

    //获取设备概览具体设备
    @GET(GET_ITEM_DEVICE)
    Observable<ResultBean<List<EquipmentBean>>> getOverViewDevice(@Query(TYPE_ID) long id);

    //获取所有分类设备
    @GET(GET_ITEM_DEVICE)
    Observable<ResultBean<List<EquipmentBean>>> getTotalDevice();
}
