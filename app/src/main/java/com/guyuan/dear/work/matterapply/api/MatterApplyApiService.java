package com.guyuan.dear.work.matterapply.api;

import androidx.room.RewriteQueriesToDropUnusedColumns;

import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.work.matterapply.data.bean.MatterApplyListBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterDetailBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterMaterialBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterProductBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterProjectBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterTypeBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 18:52
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface MatterApplyApiService extends BaseApiService {

    String PROJECT_LIST = BASE + "materialrequest/findAllProject";
    String PROJECT_TYPE_LIST = BASE + "materialrequest/findAllTaskType";
    String PRODUCT_LIST = BASE + "materialrequest/findAllProduct";
    String MATERIAL_LIST = BASE + "materialrequest/findAllMaterial";
    String APPLY_MATTER = BASE + "materialrequest/addRequest";
    String MATTER_APPLY_LIST = BASE + "materialrequest/findPage";
    String MATTER_DETAIL = BASE + "materialrequest/findById";

    String PROJECT_ID = "projectId";
    String TASK_TYPE = "taskType";//任务类型1.自制生产2.外购生产3.部分外协4.总体设计
    String PRODUCT_ID = "productId";
    String REQUEST_ID = "requestId";

    //获取物料项目列表
    @GET(PROJECT_LIST)
    Observable<ResultBean<List<MatterProjectBean>>> getMatterProjectList();

    //获取物料生产类型列表
    @GET(PROJECT_TYPE_LIST)
    Observable<ResultBean<List<MatterTypeBean>>> getMatterTypeList();

    //获取产品列表
    @GET(PRODUCT_LIST)
    Observable<ResultBean<List<MatterProductBean>>> getMatterProductList(@Query(PROJECT_ID) int projectID,
                                                                         @Query(TASK_TYPE) int taskType);

    //获取原材料列表
    @GET(MATERIAL_LIST)
    Observable<ResultBean<List<MatterMaterialBean>>> getMaterialList(@Query(PRODUCT_ID) int productID);

    //申请物料
    @POST(APPLY_MATTER)
    Observable<ResultBean<Integer>> applyMatter(@Body RequestBody body);


    //领料申请列表
    @POST(MATTER_APPLY_LIST)
    Observable<ResultBean<MatterApplyListBean>> getMatterApplyList(@Body RequestBody body);

    //领料详情
    @GET(MATTER_DETAIL)
    Observable<ResultBean<MatterDetailBean>> getMatterDetail(@Query(REQUEST_ID) int requestID);


}