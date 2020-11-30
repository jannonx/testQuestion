package com.guyuan.dear.base.api;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.focus.device.data.beans.FactoryBean;
import com.guyuan.dear.work.assess.data.bean.MeetingRoomBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

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

    String WORKSHOP = BASE + "tWorkshop/findPage";
    String FACTORY = BASE + "tFactory/findPage";
    String UPLOAD = FILE + "file/uploadApp";
    String MEETING_ROOM = BASE + "tMeetingRoom/findPage";

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
}
