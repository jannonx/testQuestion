package com.guyuan.dear.focus.contract.adapter.contractPrgLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.FirstCreateDate;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 18:40
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LogTailAdapter extends RecyclerView.Adapter<LogTailAdapter.ViewHolder> {
    private List<FirstCreateDate> mList;
    private Context mContext;

    public LogTailAdapter(Context context, List<FirstCreateDate> list) {
        this.mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contract_prg_log_tail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirstCreateDate date = mList.get(position);
        holder.tvDate.setText(CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(date.getDate()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate =itemView.findViewById(R.id.item_contract_prg_log_tail_tv_first_create_date);
        }
    }
}
