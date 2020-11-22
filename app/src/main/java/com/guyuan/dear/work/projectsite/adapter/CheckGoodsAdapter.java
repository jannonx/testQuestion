package com.guyuan.dear.work.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.customizeview.CheckedImageView;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.ProjectModuleType;
import com.guyuan.dear.R;
import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsAdapter extends BaseRecyclerAdapter<CheckGoodsBean> {
    /**
     * 清点是否异常，0正常，1异常
     */
    private static final int CHECK_RIGHT =0;
    private static final int CHECK_WRONG =1;
    private ProjectModuleType moduleType;


    public CheckGoodsAdapter(Context context, @NonNull List<CheckGoodsBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }
    public CheckGoodsAdapter(Context context, @NonNull List<CheckGoodsBean> listData, int layoutID, ProjectModuleType type) {
        super(context, listData, layoutID);
        this.moduleType=type;
    }
    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, CheckGoodsBean item,
                                  int position) {
        AppCompatImageView imageView = holder.getView(R.id.image_view);
        imageView.setVisibility(ProjectModuleType.TYPE_FOCUS==moduleType?View.VISIBLE:View.GONE);
        RelativeLayout rlConfirm = holder.getView(R.id.rl_confirm);
        rlConfirm.setVisibility(ProjectModuleType.TYPE_WORK==moduleType?View.VISIBLE:View.GONE);

        holder.setText(R.id.tv_good_name,item.getProjectName());
        holder.setText(R.id.tv_goods_code,item.getProjectCode());
        holder.setText(R.id.tv_goods_model,item.getModel());
        holder.setText(R.id.tv_remark,item.getRemark());
        holder.setText(R.id.tv_goods_number,item.getAmount()+item.getUnit());


        AppCompatImageView ivRightBtn = holder.getView(R.id.iv_right_btn);
        AppCompatImageView ivWrongBtn = holder.getView(R.id.iv_wrong_btn);

        ivRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setIsException(CHECK_RIGHT);
                ivRightBtn.setImageResource(R.mipmap.right);
                ivWrongBtn.setImageResource(R.mipmap.ic_wrong_gray_unselect);
            }
        });
        ivWrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setIsException(CHECK_WRONG);
                ivRightBtn.setImageResource(R.mipmap.ic_right_gray_unselect);
                ivWrongBtn.setImageResource(R.mipmap.wrong);
            }
        });
    }
}
