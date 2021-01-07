package com.guyuan.dear.base.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.bean.ContractStatusBean;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.work.assess.data.bean.MeetingRoomBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @author : tl
 * @description : 通用api
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/
public interface BaseApiService {

    //接口统一起始字段
    String BASE = "base/";
    String MESSAGE = "message/";
    String FILE = "file/";
    String ANALYSIS = "analysis/";


    //接口参数
    String PAGE_INDEX = "pageNum";  //页码
    String PAGE_SIZE = "pageSize";  //每页数量
    String ID = "id";
    String CONTRACT_ID = "contractId";            //合同ID
    String CONTRACT_NUMBER = "contractNumber";    //合同编号
    String FLAG = "flag";                         //flag标识
    String PROJECT_ID = "projectId";              //项目ID

    String WORKSHOP = BASE + "tWorkshop/findPage";
    String FACTORY = BASE + "tFactory/findPage";
    String UPLOAD = FILE + "file/uploadApp";
    String MEETING_ROOM = BASE + "tMeetingRoom/findPage";
    String CONTRACT_STATUS = BASE + "/checkContract/checkStatus";


    //查询厂房
    @POST(FACTORY)
    Observable<ResultBean<FactoryBean>> getFactoryList(@Body RequestBody body);

    //上传图片
    @POST(UPLOAD)
    @Multipart
    Observable<ResultBean<List<UploadBean>>> uploadPic(@PartMap Map<String, RequestBody> map);

    //获取会议室
    @POST(MEETING_ROOM)
    Observable<ResultBean<MeetingRoomBean>> getMeetingRoomList(@Body RequestBody body);

    //查询合同状态
    @GET(CONTRACT_STATUS)
    Observable<ResultBean<ContractStatusBean>> getContractStatusByContractID(@Query(CONTRACT_ID) int contractID);

    @GET(CONTRACT_STATUS)
    Observable<ResultBean<ContractStatusBean>> getContractStatusByID(@Query(ID) int id);

    @GET(CONTRACT_STATUS)
    Observable<ResultBean<ContractStatusBean>> getContractStatusByContractNumber(@Query(CONTRACT_NUMBER) String contractNumber);

    @GET(CONTRACT_STATUS)
    Observable<ResultBean<ContractStatusBean>> getContractStatusByFlag(@Query(FLAG) int flag);

    @GET(CONTRACT_STATUS)
    Observable<ResultBean<ContractStatusBean>> getContractStatusByProjectID(@Query(PROJECT_ID) int projectID);
}
