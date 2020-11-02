package com.guyuan.dear.mine.data;


import com.example.httplibrary.bean.ResultBean;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.mine.api.MineApiService;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.PartMap;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class MineRepository {
    private MineApiService apiService;

    public MineRepository(MineApiService mineApiService) {
        this.apiService = mineApiService;
    }

    Observable<ResultBean<Integer>> editUserPassWord(String password, String newPassword) {
        return apiService.editUserPassWord(password, newPassword);
    }

    Observable<ResultBean<List<UploadBean>>> uploadPic(@PartMap Map<String, RequestBody> map) {
        return apiService.uploadPic(map);
    }

    Observable<ResultBean<Integer>> postFeedBack(RequestBody body) {
        return apiService.postFeedBack(body);
    }

    Observable<ResultBean<Integer>> postUserAvatar(RequestBody body) {
        return apiService.postUserAvatar(body);
    }
}
