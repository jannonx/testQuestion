package com.guyuan.dear.mine.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.databinding.FragmentUserInfoBinding;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.mine.activity.UserInfoActivity;
import com.guyuan.dear.mine.bean.MineRequestBody;
import com.guyuan.dear.mine.data.EventRefreshUserData;
import com.guyuan.dear.mine.data.MineViewModel;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import droidninja.filepicker.utils.ContentUriUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @description: 我的--个人中心
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class UserInfoFragment extends BaseDataBindingFragment<FragmentUserInfoBinding, MineViewModel>
        implements View.OnClickListener, BaseFileUploadActivity.PhotoSelectListener {
    // BaseTabActivity.PhotoSelectListener
    protected ArrayList<Uri> photoList = new ArrayList<>();
    public static final String TAG = "UserInfoFragment";
    private UserInfoActivity activity;
    private String avatarUrl;

    public static UserInfoFragment newInstance() {
        Bundle args = new Bundle();
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (UserInfoActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_user_info;
    }

    @Override
    protected void initialization() {
        LoginBean user = CommonUtils.getLoginInfo();
        if (user != null && user.getUserInfo() != null) {
            binding.tvDepartment.setText(user.getUserInfo().getDeptIdName());
            binding.tvPosition.setText(user.getUserInfo().getPointIdName());
            String imgUrl = user.getUserInfo().getImgUrl();
            GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(binding.ivAvatar, imgUrl);
        } else {
            showToastTip("用户信息为空/UserInfo为空。");
        }

        binding.rlAvatar.setOnClickListener(this);
        binding.tvLogout.setOnClickListener(this);


        initData();
    }

    private void initData() {
        viewModel.getUploadImageEvent().observe(this, new Observer<List<UploadBean>>() {
            @Override
            public void onChanged(List<UploadBean> dataList) {
                //获取图片url
//                List<UploadBean> data = dataRefreshBean.getData();
                if (dataList.isEmpty()) return;
                LogUtils.showLog("data=" + dataList.get(0).getUrl());
                postUserAvatar(avatarUrl = dataList.get(0).getUrl());
            }
        });


        viewModel.getUserAvatarEvent().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer resultBean) {
                LoginBean loginInfo = CommonUtils.getLoginInfo();
                loginInfo.getUserInfo().setImgUrl(avatarUrl);
                DearApplication.getInstance().saveCacheData(ConstantValue.USER_JSON_STRING,
                        new Gson().toJson(loginInfo));
                EventBus.getDefault().post(new EventRefreshUserData());
                ToastUtils.showShort(getContext(), "修改头像成功！");
                getActivity().finish();
            }
        });
    }

    private void postUserAvatar(String url) {
        MineRequestBody body = new MineRequestBody();
        body.setUrl(url);
        String str = GsonUtil.objectToString(body);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                "charset=utf-8"), str);
        viewModel.postUserAvatar(requestBody);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //选择头像
            case R.id.rl_avatar:
//                workReportCommitActivity.openAlbum(BaseTabActivity.FIRST);
                activity.openAlbum(BaseTabActivity.FIRST);
                break;
            //退出账号
            case R.id.tv_logout:
                CommonUtils.logout(getContext());


                break;
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }


    @Override
    public ArrayList<Uri> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onPhotoSelected(ArrayList<Uri> dataList) {
        photoList.clear();
        if (dataList.isEmpty()) return;
        List<String> tempListData = new ArrayList<>();
        try {
            String imgPath = ContentUriUtils.INSTANCE.getFilePath(DearApplication.getInstance(), dataList.get(0));
            tempListData.add(imgPath);
            activity.checkPhotoAndFileUpLoad(tempListData);
            GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(binding.ivAvatar, imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
