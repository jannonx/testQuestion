package com.guyuan.dear.focus.qc.bindingAdapters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;
import com.guyuan.dear.focus.qc.adapters.QcVerifyLogsAdapter;
import com.guyuan.dear.focus.qc.beans.verfifyLog.GenericQcLogBean;

import java.util.Collections;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/12 17:42
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcReportDetailBindingAdapter {

    @BindingAdapter("setQcReportVerifyResult")
    public static void setQcReportVerifyResult(AppCompatTextView tv, int result) {
        if (result == Vote.VOTE_RESULT_PASS) {
            tv.setText("通过");
        } else if (result == Vote.VOTE_RESULT_REJECT) {
            tv.setText("不通过");
        }
    }

    @BindingAdapter("setQcReportVerifyLogsData")
    public static void setQcReportVerifyLogsData(RecyclerView view, List<GenericQcLogBean> data) {
        if (data == null) {
            return;
        }
        Collections.reverse(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        QcVerifyLogsAdapter adapter = new QcVerifyLogsAdapter(data, view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
    }
}
