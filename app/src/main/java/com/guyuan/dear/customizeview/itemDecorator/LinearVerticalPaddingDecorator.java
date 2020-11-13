package com.guyuan.dear.customizeview.itemDecorator;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.utils.DimensionUtils;

/**
 * 本项目中最常用的item decorator
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 17:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class LinearVerticalPaddingDecorator extends RecyclerView.ItemDecoration {
    private int outerPadding;
    private int dividePadding;

    /**
     *
     * @param outerPadding item和外框之间的空间
     * @param dividePadding item 和 item之间的空间
     */
    public LinearVerticalPaddingDecorator(int outerPadding, int dividePadding) {
       this.outerPadding = (int) DimensionUtils.dp2px(outerPadding);
       this.dividePadding = (int) DimensionUtils.dp2px(dividePadding);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager && ((LinearLayoutManager) layoutManager).getOrientation()==RecyclerView.VERTICAL){
            int position = parent.getChildAdapterPosition(view);
            //第一个item
            if(position==0){
                outRect.set(outerPadding,outerPadding,outerPadding,0);
            }else if (position == parent.getAdapter().getItemCount()-1){
                //最后一个item
                outRect.set(outerPadding,dividePadding,outerPadding,outerPadding);
            }else {
                //中间的item
                outRect.set(outerPadding,dividePadding,outerPadding,0);
            }
        }else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
