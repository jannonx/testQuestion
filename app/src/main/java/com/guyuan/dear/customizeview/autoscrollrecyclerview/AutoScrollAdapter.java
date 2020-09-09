package com.guyuan.dear.customizeview.autoscrollrecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.AutoScrollTextView;

import java.util.List;

/**
 * created by tl
 * created at 2020/4/25
 */
public class AutoScrollAdapter extends RecyclerView.Adapter<AutoScrollAdapter.Holder> {
    public static final String NORMAL = "normal";
    private List<MessageBean> datas;
    private AutoScrollAdapterListener listener;
    private Context context;

    public AutoScrollAdapter(Context context, @NonNull List<MessageBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).inflate(R.layout.item_auto_scroll, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        MessageBean bean = datas.get(position % datas.size());
        String title = bean.getMsgTitle();
        String url = bean.getUrl();
        String urlName = bean.getUrlName();
        int status = bean.getLabel();
        Log.i("scroll", "position=" + position % datas.size());
        Log.i("scroll", "status=" + status);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && url != null) {
                    listener.onClick(url, urlName);
                }
            }
        });

        switch (status) {
            case 0:
                holder.iv.setImageResource(R.mipmap.control_green);
                holder.tv.setTextColor(context.getResources().getColor(R.color.control_text_green));
                break;

            case 1:
                holder.iv.setImageResource(R.mipmap.control_yellow);
                holder.tv.setTextColor(context.getResources().getColor(R.color.control_text_yellow));
                break;

            case 2:
                holder.iv.setImageResource(R.mipmap.control_red);
                holder.tv.setTextColor(context.getResources().getColor(R.color.control_text_red));
                break;
        }

        holder.tv.setText(bean.getUrlWarnContent());

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    class Holder extends RecyclerView.ViewHolder {
        AutoScrollTextView tv;
        ImageView iv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.auto_scroll_content_tv);
            iv = itemView.findViewById(R.id.auto_scroll_iv);
        }
    }

    public void setDatas(List<MessageBean> messageBeans) {
        datas.clear();
        datas.addAll(messageBeans);
        notifyDataSetChanged();
    }

    public List<MessageBean> getDatas() {
        return datas;
    }

    //获取指定位置message
    public MessageBean getMessageBeanByPosition(int position) {
        return datas.get(position % datas.size());
    }

    public void setListener(AutoScrollAdapterListener listener) {
        this.listener = listener;
    }

    public interface AutoScrollAdapterListener {
        void onClick(String url, String title);
    }


}
