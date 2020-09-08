package com.example.mvvmlibrary.base.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mvvmlibrary.util.AlertDialogUtils;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * created by tl
 * created at 2020/8/12
 */
public abstract class BaseFragment extends Fragment {

    protected AlertDialog loadingDialog;
    protected View rootView;
    protected FragmentManager childFragmentManager;
    protected boolean isFirstLoad = true;
    protected boolean isUserVisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(getLayoutID(), container, false);
        }
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        childFragmentManager = getChildFragmentManager();
        initialization();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            initInViewPager();
            isFirstLoad = false;

        }
        reFreshUI();
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {//当可见的时候执行操作
            isUserVisible = true;
            onVisible();
        } else {//不可见时执行相应的操作
            isUserVisible = false;
            onInvisible();
        }
    }


    protected void onVisible() {

    }

    protected void onInvisible() {
    }


    //Viewpager中fragment可见时刷新数据
    protected void reFreshUI() {
    }

    //viewpager中fragment初始化
    protected void initInViewPager() {
    }


    protected abstract int getLayoutID();

    protected abstract void initialization();

    public void showToastTip(int resId) {
        String txt = getString(resId);
        showToastTip(txt);
    }

    public void showToastTip(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showSnackBarTip(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackBarTip(int resId) {
        Snackbar.make(rootView, resId, Snackbar.LENGTH_SHORT).show();
    }

    public void showLoading(FragmentManager manager) {
        hideLoading();
        loadingDialog = AlertDialogUtils.showLoading(getContext(), null, null);
    }

    public void showLoadingWithStatus(FragmentManager manager, String status) {
        hideLoading();
        loadingDialog = AlertDialogUtils.showLoading(getContext(), null, status);
    }

    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
