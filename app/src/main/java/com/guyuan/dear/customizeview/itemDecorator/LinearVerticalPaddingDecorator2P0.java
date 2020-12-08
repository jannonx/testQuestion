package com.guyuan.dear.customizeview.itemDecorator;

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
public class LinearVerticalPaddingDecorator2P0 extends RecyclerView.ItemDecoration {
    private int outerLeft;
    private int outerTop;
    private int outerRight;
    private int outerBottom;
    private int gapBetween;

    /**
     * 2.0版本，设置外框以及内部item间的空间，以dp为单位。
     * @param outerLeft 外框左边的空间
     * @param outerTop 外框顶部的空间
     * @param outerRight 外框右边的空间
     * @param outerBottom 外框底部的空间
     * @param gapBetween item之间的空间
     */
    public LinearVerticalPaddingDecorator2P0(int outerLeft, int outerTop, int outerRight, int outerBottom,int gapBetween) {
        this.outerLeft = (int) DimensionUtils.dp2px(outerLeft);
        this.outerTop = (int) DimensionUtils.dp2px(outerTop);
        this.outerRight = (int) DimensionUtils.dp2px(outerRight);
        this.outerBottom = (int) DimensionUtils.dp2px(outerBottom);
        this.gapBetween = (int) DimensionUtils.dp2px(gapBetween);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager && ((LinearLayoutManager) layoutManager).getOrientation()==RecyclerView.VERTICAL){
            int position = parent.getChildAdapterPosition(view);
            if(position==0){
                //当前本项目引用的三方库base recycler view默认第一个item是一个header，这里对header不做任何改变。
                outRect.set(0,0,0,0);
            }else if(position==1){
                //实际上的第一个item
                outRect.set(outerLeft,outerTop,outerRight,0);
            }else if (position == parent.getAdapter().getItemCount()-1){
                //最后一个item
                outRect.set(outerLeft,gapBetween,outerRight,outerBottom);
            }else {
                //中间的item
                outRect.set(outerLeft,gapBetween,outerRight,0);
            }
        }else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
