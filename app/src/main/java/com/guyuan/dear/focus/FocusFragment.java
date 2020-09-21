package com.guyuan.dear.focus;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentFocusBinding;
import com.guyuan.dear.focus.assess.ui.FocusAssessActivity;
import com.guyuan.dear.focus.hr.view.HrHomeActivity;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.NetworkUtils;

import java.util.ArrayList;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/8 11:41
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusFragment extends BaseListFragment<LoginBean.AppMenusBean.ChildrenBean, FragmentFocusBinding> {

    public static final String TAG = "FocusFragment";

    public static FocusFragment newInstance(String title,
                                            ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList) {

        Bundle args = new Bundle();
        args.putString(ConstantValue.KEY_TITLE, title);
        args.putParcelableArrayList(ConstantValue.KEY_MENU, menuList);
        FocusFragment fragment = new FocusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_focus;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString(ConstantValue.KEY_TITLE,
                    getContext().getString(R.string.default_title_realtime_progress));
            binding.homeFocusTitleTv.setText(title);
            ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList =
                    arguments.getParcelableArrayList(ConstantValue.KEY_MENU);
            //hidden过滤，不显示
            ArrayList<LoginBean.AppMenusBean.ChildrenBean> temMenuList = new ArrayList<>();
            for (LoginBean.AppMenusBean.ChildrenBean childrenBean : menuList) {
                LogUtils.showLog("menu=" + childrenBean.getName());
                if (childrenBean.getHidden() == 1) temMenuList.add(childrenBean);
            }
            menuList.clear();
            menuList.addAll(temMenuList);
            temMenuList.clear();

            BaseMenuAdapter baseMenuAdapter = new BaseMenuAdapter(getContext(), menuList,
                    R.layout.item_fragment_menu);
            BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(baseMenuAdapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (NetworkUtils.isNetworkAvailable(getContext())) {
                        String url = menuList.get(position).getUrl();
                        String title = menuList.get(position).getTitle();
                        Bundle cBundle = new Bundle();
                        cBundle.putString(ConstantValue.KEY_TITLE, title);

                        switch (url) {
                            case ConstantValue.FOCUS_STAFF://人员
                                HrHomeActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_SECURITY://安全

                                break;

                            case ConstantValue.FOCUS_DEVICE://设备

                                break;

                            case ConstantValue.FOCUS_SELL://销售

                                break;

                            case ConstantValue.FOCUS_ASSESS://评审
                                FocusAssessActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_PRODUCE://生产

                                break;

                            case ConstantValue.FOCUS_PURCHASE://采购

                                break;

                            case ConstantValue.FOCUS_QUALITY://质检

                                break;

                            case ConstantValue.FOCUS_STOCK://库存

                                break;

                            case ConstantValue.FOCUS_TRANSPORT://运输

                                break;

                            case ConstantValue.FOCUS_PROJECT_SITE://工程现场

                                break;

                            case ConstantValue.FOCUS_AFTER_SERVICE://售后服务

                                break;

                            case ConstantValue.FOCUS_MORNING_MEETING://晨会

                                break;


                            case ConstantValue.FOCUS_SMART_GUARD://智能门卫

                                break;

                            case ConstantValue.FOCUS_CONSTRUCTION_MONITORING://施工现场

                                break;


                            default:
                                break;
                        }
                    } else {
                        showToastTip(ConstantValue.NO_INTERNET);
                    }
                }
            });

            GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
            binding.baseRecycleView.setLayoutManager(manager);
            binding.baseRecycleView.setAdapter(adapter);

        }

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }
}
