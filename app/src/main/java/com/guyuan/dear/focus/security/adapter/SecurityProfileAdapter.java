package com.guyuan.dear.focus.security.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.guyuan.dear.R;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.security.data.beans.DangerProfileBean;
import com.guyuan.dear.focus.security.data.beans.SecurityBaseBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/10 10:28
 * @company : 固远（深圳）信息技术有限公司
 **/
public class SecurityProfileAdapter extends BaseRecyclerAdapter<DangerProfileBean.WorkshopSecurityVoBean> {
    private ContentListener listener;

    public SecurityProfileAdapter(Context context,
                                              @NonNull List<DangerProfileBean.WorkshopSecurityVoBean> listData,
                                              int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder,
                                  DangerProfileBean.WorkshopSecurityVoBean item,
                                  int position) {
        LinearLayout farm_content_ll = holder.getView(R.id.farm_content_ll);
        holder.setText(R.id.farm_content_tv, item.getName());
//        farm_content_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, RealTimeSecurityProfileActivity.class);
//                intent.putExtra(RealTimeSecurityProfileActivity.CONTENT, item);
//                context.startActivity(intent);
//            }
//        });

        BaseRecyclerView recyclerView = holder.getView(R.id.farm_content_rv);
        SecurityContentAdapter detailAdapter = new SecurityContentAdapter(context,
                item.getTSecurityBaseVo(), R.layout.item_focus_content);

        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(detailAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (listener != null) {
                    listener.showDetail(item.getTSecurityBaseVo().get(position));
                }
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
    }

    public void setListener(ContentListener listener) {
        this.listener = listener;
    }

    public interface ContentListener {
        void showDetail(SecurityBaseBean detailBean);
    }
}
