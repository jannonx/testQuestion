package com.guyuan.dear.ezCloud.bindingAdapters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.ezCloud.adapters.EzPlaybackListAdapter;
import com.videogo.openapi.bean.EZDeviceRecordFile;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 17:19
 * @company: 固远（深圳）信息技术有限公司
 **/
public class EzPlaybackBindingAdapter {

    @BindingAdapter(value = {"showEzPlaybackList","setItemClickListener"})
    public static void showEzPlaybackList(BaseRecyclerView view, List<EZDeviceRecordFile> data,OnItemClickListener listener){
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL,false);
        EzPlaybackListAdapter adapter = new EzPlaybackListAdapter(data);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(false);
        view.setPullRefreshEnabled(false);
        wrapper.setOnItemClickListener(listener);
    }
}
