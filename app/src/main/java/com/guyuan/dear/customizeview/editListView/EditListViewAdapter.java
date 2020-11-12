package com.guyuan.dear.customizeview.editListView;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.dialog.TipDialogFragment;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/12 10:55
 * @company : 固远（深圳）信息技术有限公司
 **/

public class EditListViewAdapter extends BaseRecyclerAdapter<EditListViewBean> {

    private String itemTitle;
    private boolean editable = true;//默认可编辑
    private FragmentManager manager;

    public EditListViewAdapter(Context context, String itemTitle, @NonNull List<EditListViewBean> listData, int layoutID) {
        super(context, listData, layoutID);
        this.itemTitle = itemTitle;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, EditListViewBean item, int position) {
        MaterialButton elv_delete_mb = holder.getView(R.id.elv_delete_mb);
        TextView elv_number_tv = holder.getView(R.id.elv_number_tv);
        EditText editText = holder.getView(R.id.elv_et);
        String itemIndex = itemTitle + (position + 1);
        holder.setText(R.id.elv_item_title_tv, itemIndex);
        editText.setText(item.getContent());

        if (item.isEditable()) {
            elv_number_tv.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(item.getContent())) {
                elv_number_tv.setText(String.format(context.getResources().getString(R.string.textLength)
                        , item.getContent().length()));
            }
            elv_delete_mb.setVisibility(View.VISIBLE);
            editText.setEnabled(true);

            elv_delete_mb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TipDialogFragment tipDialogFragment = TipDialogFragment.newInstance("确定删除" + itemIndex, "");
                    tipDialogFragment.setOnSureListener(new TipDialogFragment.OnSure() {
                        @Override
                        public void sure() {
                            listData.remove(item);
                            notifyDataSetChanged();
                            tipDialogFragment.dismiss();
                        }
                    })
                            .setOnCancelListener(new TipDialogFragment.OnCancel() {
                                @Override
                                public void cancel() {
                                    tipDialogFragment.dismiss();
                                }
                            }).show(manager, TipDialogFragment.TAG);
                }
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    elv_number_tv.setText(String.format(context.getResources().getString(R.string.textLength), s.length()));
                    item.setContent(s.toString());
                }
            });
        } else {
            elv_number_tv.setVisibility(View.GONE);
            elv_delete_mb.setVisibility(View.GONE);
            editText.setEnabled(false);
        }

    }

    //是否开启编辑状态
    public void setEditable(boolean editable) {
        for (EditListViewBean bean : listData) {
            bean.setEditable(editable);
        }
        notifyDataSetChanged();
    }

    //增加一栏
    public void add() {
        EditListViewBean bean = new EditListViewBean();
        bean.setEditable(true);
        listData.add(bean);
        notifyItemChanged(listData.size() - 1);
    }

    public void setListData(List<EditListViewBean> dataList) {
        listData.clear();
        listData.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

}