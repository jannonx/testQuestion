package com.guyuan.dear.focus.transport.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.ActivityTransportDetailBinding;
import com.guyuan.dear.focus.transport.data.TransportViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 20:09
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TransportDetailActivity extends BaseToolbarActivity<ActivityTransportDetailBinding,
        TransportViewModel> implements TabLayoutHelper.TabLayoutListener {

    private TransportInventoryFragment inventoryFragment;
    private TransportMessageFragment messageFragment;
    private TransportDocumentFragment documentFragment;

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTab();
    }

    private void setTab() {
        List<Fragment> fragmentList = new ArrayList<>();
        inventoryFragment = TransportInventoryFragment.newInstance();
        messageFragment = TransportMessageFragment.newInstance();
        documentFragment = TransportDocumentFragment.newInstance();
        fragmentList.add(inventoryFragment);
        fragmentList.add(messageFragment);
        fragmentList.add(documentFragment);

        new TabLayoutHelper(this, binding.transportDetailTl, binding.transportDetailVp,
                fragmentList, TabLayoutHelper.UNDERLINE).setTab().setListener(this).setCustomView();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_transport_detail;
    }

    @Override
    public void setCustomContent(View customView, int currentPosition) {
        TextView tv = customView.findViewById(R.id.tab_tv);
        switch (currentPosition) {
            case 0:
                tv.setText("货物清单");
                break;

            case 1:
                tv.setText("发货信息");
                break;

            case 2:
                tv.setText("电子单据");
                break;
        }
    }
}