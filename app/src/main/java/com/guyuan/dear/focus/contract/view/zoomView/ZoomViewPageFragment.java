package com.guyuan.dear.focus.contract.view.zoomView;

import android.os.Bundle;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.bean.ImageSource;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/19 14:44
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ZoomViewPageFragment extends BaseFragment {
    private PhotoView mPhotoView;

    public static ZoomViewPageFragment getInstance(ImageSource src){
        ZoomViewPageFragment fragment = new ZoomViewPageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantValue.KEY_IMG_SRC,src);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_zoom_view_page;
    }

    @Override
    protected void initialization() {
        mPhotoView = rootView.findViewById(R.id.fragment_zoom_view_page_photo_view_content);
        mPhotoView.setEnabled(true);
        mPhotoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Bundle bundle = getArguments();
        ImageSource src = bundle.getParcelable(ConstantValue.KEY_IMG_SRC);
        mPhotoView.setImageResource(src.getSrc());
    }
}
