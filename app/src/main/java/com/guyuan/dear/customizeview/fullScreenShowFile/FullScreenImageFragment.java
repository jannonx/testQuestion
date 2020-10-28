package com.guyuan.dear.customizeview.fullScreenShowFile;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFullScreenImageBinding;

/**
 * created by tl
 * created at 2020/6/17
 */
public class FullScreenImageFragment extends BaseDataBindingFragment<FragmentFullScreenImageBinding, BaseViewModel> {


    public static final String TAG = "FullScreenImageFragment";
    public static final String URL = "url";

    public static FullScreenImageFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(URL, url);
        FullScreenImageFragment fragment = new FullScreenImageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_full_screen_image;
    }

    @Override
    protected void initialization() {
        if (getArguments() != null) {
            String url = getArguments().getString(URL);
            Glide.with(this)
                    .load(url)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource,
                                                    @Nullable Transition<? super Drawable> transition) {
                            binding.fullScreenZv.setImageDrawable(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
