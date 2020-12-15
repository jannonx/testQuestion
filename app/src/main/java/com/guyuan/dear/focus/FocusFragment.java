package com.guyuan.dear.focus;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentFocusBinding;
import com.guyuan.dear.focus.aftersale.activity.FocusAfterSaleActivity;
import com.guyuan.dear.focus.assess.ui.FocusAssessActivity;
import com.guyuan.dear.focus.client.activity.FocusClientActivity;
import com.guyuan.dear.focus.contract.view.home.ContractHomeActivity;
import com.guyuan.dear.focus.hr.view.home.HrHomeActivity;
import com.guyuan.dear.focus.produce.ui.FocusProduceActivity;
import com.guyuan.dear.focus.projectsite.activity.FocusProjectSiteActivity;
import com.guyuan.dear.focus.purchase.ui.FocusPurchaseActivity;
import com.guyuan.dear.focus.qc.views.home.QcHomeActivity;
import com.guyuan.dear.focus.security.ui.FocusSecurityActivity;
import com.guyuan.dear.focus.stock.ui.FocusStockActivity;
import com.guyuan.dear.focus.transport.ui.TransportActivity;
import com.guyuan.dear.login.data.bean.ChildrenBean;
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
public class FocusFragment extends BaseListFragment<ChildrenBean, FragmentFocusBinding, BaseViewModel> {

    public static final String TAG = "FocusFragment";

    public static FocusFragment newInstance(String title, ArrayList<ChildrenBean> menuList) {

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
            ArrayList<ChildrenBean> menuList =
                    arguments.getParcelableArrayList(ConstantValue.KEY_MENU);
            //hidden过滤，不显示
            ArrayList<ChildrenBean> temMenuList = new ArrayList<>();
            for (ChildrenBean childrenBean : menuList) {
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
                        LogUtils.showLog("url=" + url);
                        switch (url) {
                            case ConstantValue.FOCUS_STAFF://人员
                                HrHomeActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_SECURITY://安全
                                FocusSecurityActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_DEVICE://设备
                                showToastTip("正在研发中...");
                            //    FocusDeviceActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_SELL://销售

                                break;

                            case ConstantValue.FOCUS_ASSESS://评审
                                FocusAssessActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_PRODUCE://生产
                                FocusProduceActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_PURCHASE://采购
                                FocusPurchaseActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_QUALITY://质检
                                QcHomeActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_STOCK://库存
                                FocusStockActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_TRANSPORT://运输
                                TransportActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_PROJECT_SITE://工程现场
//                                DemoDetailActivity.start(getContext());
                                FocusProjectSiteActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_AFTER_SERVICE://售后服务
                                FocusAfterSaleActivity.start(getContext(), title);
                                break;

                            case ConstantValue.FOCUS_MORNING_MEETING://晨会

                                break;


                            case ConstantValue.FOCUS_SMART_GUARD://智能门卫

                                break;

                            case ConstantValue.FOCUS_CONSTRUCTION_MONITORING://施工现场

                                break;
                            case ConstantValue.FOCUS_CONTRACT://合同
                                ContractHomeActivity.start(getContext(), title);
                                break;
                            case ConstantValue.FOCUS_CUSTOMER://客户
                                FocusClientActivity.start(getContext(), title);

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

    @Override
    protected int getVariableId() {
        return 0;
    }
}
