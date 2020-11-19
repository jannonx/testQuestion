package com.guyuan.dear.focus.aftersale.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.databinding.ActivityWithToolbarBinding;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.databinding.ActivityFocusAfterSaleDetailBinding;
import com.guyuan.dear.focus.aftersale.data.FocusAfterSaleViewModel;
import com.guyuan.dear.utils.ConstantValue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/19 14:43
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusAfterSaleDetailActivity extends BaseToolbarActivity<
        ActivityFocusAfterSaleDetailBinding, FocusAfterSaleViewModel> implements TabLayoutHelper.TabLayoutListener {

    public static void start(Context context, String title, int id) {
        Intent starter = new Intent(context, FocusAfterSaleDetailActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTab();

    }

    private void setTab() {
        List<Fragment> fragmentList = new ArrayList<>();

        new TabLayoutHelper(this, binding.focusSaleDetailTl, binding.focusSaleDetailVp,
                fragmentList, TabLayoutHelper.UNDERLINE).setTab().setListener(this).setCustomView();
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_focus_after_sale_detail;
    }

    @Override
    protected int getVariableId() {
        return BR.focusAfterSaleViewModel;
    }

    @Override
    public void setCustomContent(View customView, int currentPosition) {
        TextView tv = customView.findViewById(R.id.tab_tv);
        switch (currentPosition) {
            case 0:
                tv.setText("排查记录");
                break;

            case 1:
                tv.setText("问题描述");
                break;
        }
    }
}