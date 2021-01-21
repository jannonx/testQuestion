package com.guyuan.dear.base.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.bean.ContractStatusBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    public static String CONTRACT_ID = "contractId";            //合同ID
    public static String CONTRACT_NUMBER = "contractNumber";    //合同编号
    public static String FLAG = "flag";                         //flag标识
    public static String PROJECT_ID = "projectId";              //项目ID

    String WORKSHOP = BASE + "tWorkshop/findPage";
    String FACTORY = BASE + "tFactory/findPage";
    String UPLOAD = FILE + "file/uploadApp";
    String MEETING_ROOM = BASE + "tMeetingRoom/findPage";
    String CONTRACT_STATUS = BASE + "checkContract/checkStatus";



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
