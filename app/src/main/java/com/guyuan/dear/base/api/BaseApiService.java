package com.guyuan.dear.base.api;

/**
 * @author : Jannonx
 * @description : 通用api
 * @since: 2020/9/7
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


    String WORKSHOP = BASE + "tWorkshop/findPage";
    String FACTORY = BASE + "tFactory/findPage";
    String UPLOAD = FILE + "file/uploadApp";
    String MEETING_ROOM = BASE + "tMeetingRoom/findPage";
    String CONTRACT_STATUS = BASE + "checkContract/checkStatus";



}
