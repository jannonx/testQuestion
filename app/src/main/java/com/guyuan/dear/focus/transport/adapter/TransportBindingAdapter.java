package com.guyuan.dear.focus.transport.adapter;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.guyuan.dear.base.adapter.TabAdapter;
import com.guyuan.dear.focus.transport.data.bean.TransportDetailBean;
import com.guyuan.dear.focus.transport.ui.detail.TransportDocumentFragment;
import com.guyuan.dear.focus.transport.ui.detail.TransportInventoryFragment;
import com.guyuan.dear.focus.transport.ui.detail.TransportMessageFragment;
import com.guyuan.dear.utils.StringUtils;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 14:50
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportBindingAdapter {
    @BindingAdapter("detailData")
    public static void setViewPagerData(ViewPager2 vp, TransportDetailBean detailBean) {
        if (detailBean != null) {
            TabAdapter tabAdapter = (TabAdapter) vp.getAdapter();
            List<Fragment> fragmentList = tabAdapter.getFragmentList();
            TransportInventoryFragment inventoryFragment = (TransportInventoryFragment) fragmentList.get(0);
            TransportMessageFragment messageFragment = (TransportMessageFragment) fragmentList.get(1);
            TransportDocumentFragment documentFragment = (TransportDocumentFragment) fragmentList.get(2);
            inventoryFragment.setListData(detailBean.getTransportDetailVOList());
            List<String> imgList = StringUtils.splitPhotoUrl(detailBean.getFileUrl());
            if (imgList != null) {
                documentFragment.setListData(imgList);
            }
        }

    }

}