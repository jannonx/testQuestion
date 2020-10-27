package com.guyuan.dear.focus.sales.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.sales.bean.ContractComment;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.List;
import java.util.Locale;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 15:56
 * @company: 固远（深圳）信息技术有限公司
 **/
public class CommentListAdapter extends BaseRecyclerAdapter<ContractComment> {
    private static final int VIEW_TYPE_TAIL = 1;
    private static final int VIEW_TYPE_NORMAL = 0;
    /**
     * 与客户首次建立联系的日期
     */
    private long firstCreateData;
    private boolean isHasMoreComments = true;

    /**
     * @param context
     * @param firstCreateData 与客户首次建立联系的日期
     * @param listData
     */
    public CommentListAdapter(Context context, long firstCreateData, @NonNull List<ContractComment> listData) {
        super(context, listData, R.layout.item_contract_comment);
        this.firstCreateData = firstCreateData;
    }

    @Override
    public int getItemCount() {
        if (isHasMoreComments) {
            return listData.size();
        } else {
            return listData.size() + 1;
        }
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TAIL) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_contract_comment_tail, parent, false);
            return new ViewHolder(context, view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    public void setHasMoreComments(boolean hasMoreComments) {
        isHasMoreComments = hasMoreComments;
    }

    public boolean isHasMoreComments() {
        return isHasMoreComments;
    }

    public void setFirstCreateData(long firstCreateData) {
        this.firstCreateData = firstCreateData;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_TAIL) {
            holder.setText(R.id.item_contract_comment_tail_tv_create_date,
                    "客户创建日期：" + CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(firstCreateData));
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ContractComment item, int position) {
        holder.setText(R.id.item_contract_comment_tv_name, item.getCommenter());
        holder.setText(R.id.item_contract_comment_tv_date,
                CalenderUtils.getInstance().toYearMonthDayHourMinuteFormat(item.getDate()));
        holder.setText(R.id.item_contract_comment_tv_comment, item.getContent());
        String dept = String.format(Locale.CHINA, "来自部门：%s", item.getCommenterDept());
        holder.setText(R.id.item_contract_comment_tv_dept, dept);

        List<ContractComment> subComments = item.getSubComments();
        if (subComments != null && !subComments.isEmpty()) {
            BaseRecyclerView recyclerView = holder.getView(R.id.item_contract_comment_recycler_view);
            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, true);
            SubCommentListAdapter adapter = new SubCommentListAdapter(context, subComments);
            BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(wrapper);
            recyclerView.setLoadMoreEnabled(false);
            recyclerView.setPullRefreshEnabled(false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!isHasMoreComments && position == listData.size()) {
            return VIEW_TYPE_TAIL;
        }
        return super.getItemViewType(position);
    }

    private static class ViewHolder extends BaseRecyclerViewHolder {
        private AppCompatTextView tvCreateDate;

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
            tvCreateDate = itemView.findViewById(R.id.item_contract_comment_tail_tv_create_date);
        }
    }


}
