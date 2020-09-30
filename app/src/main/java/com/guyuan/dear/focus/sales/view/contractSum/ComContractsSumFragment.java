package com.guyuan.dear.focus.sales.view.contractSum;

import android.view.View;

import androidx.databinding.library.baseAdapters.BR;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ComContractsBinding;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * 我的关注-销售首页-合同订单概况
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/29 15:49
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ComContractsSumFragment extends BaseMvvmFragment<ComContractsBinding, ComContractsSumViewModel> {

    public static ComContractsSumFragment getInstance() {
        return new ComContractsSumFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_contact_summary_vm;
    }

    @Override
    protected void initData() {
        getViewModel().setSelectDate(System.currentTimeMillis());

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().setOnClickSelectDate(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentDate = System.currentTimeMillis();
                long minDate = CalenderUtils.getInstance().getXMonthsAgoInYearMonthFormat(currentDate,60);
                long maxDate = currentDate;
                AlertDialogUtils.pickTime(
                        getParentFragmentManager(),
                        "请选择年份",
                        minDate,
                        maxDate,
                        currentDate,
                        Type.YEAR,
                        new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                getViewModel().setSelectDate(millseconds);
                            }
                        }
                );
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_company_contracts_summary;
    }
}
