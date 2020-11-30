package com.guyuan.dear.message.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.message.api.MessageApiService;
import com.guyuan.dear.message.data.bean.MessageDetailBean;
import com.guyuan.dear.message.data.bean.MessageListBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

import retrofit2.http.PUT;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 14:01
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MessageViewModel extends BaseViewModel {
    private MessageApiService apiService;
    private MutableLiveData<MessageListBean> messageListMLD = new MutableLiveData<>();
    private MutableLiveData<MessageDetailBean> messageDetailMLD = new MutableLiveData<>();

    @ViewModelInject
    public MessageViewModel(MessageApiService apiService) {
        this.apiService = apiService;
    }


    public MutableLiveData<MessageListBean> getMessageListMLD() {
        return messageListMLD;
    }

    public MutableLiveData<MessageDetailBean> getMessageDetailMLD() {
        return messageDetailMLD;
    }

    public void getMessageList(int pageIndex, int msgType, String content) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(pageIndex);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setMsgType(msgType);//显示消息种类,1:显示警告消息、预警消息；2：正常消息、办公消息
        filtersBean.setQueryParams(content);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this, apiService.getMessageList(
                CommonUtils.getCommonRequestBody(requestBody)))
                .showLoading(false)
                .getHelper().flow(messageListMLD);
    }

    public void getMessageDetail(int id) {
        RxJavaHelper.build(this, apiService.getMessageDetail(id))
                .getHelper().flow(messageDetailMLD);
    }
}