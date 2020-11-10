package com.guyuan.dear.customizeview.itemDecorator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 10:42
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AddSendListItemDecorator extends RecyclerView.ItemDecoration {
    private Paint paint;

    public AddSendListItemDecorator() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (orientation == GridLayoutManager.VERTICAL) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(childCount);
                    int position = parent.getChildAdapterPosition(child);
                    position = position % spanCount;
                    if(position!=0&&position!=spanCount-1){
                        View pre = parent.getChildAt(i - 1);
                        View cur = parent.getChildAt(i);
                        if(pre!=null&&cur!=null){
                            Bitmap bitmap = BitmapFactory.decodeResource(parent.getResources(), R.drawable.ic_svg_right_arrow_for_send_list);
                            if(bitmap!=null){
                                int left = pre.getLeft()-((pre.getLeft()-cur.getRight())/2+bitmap.getWidth()/2);
                                int top = pre.getTop()+(pre.getHeight()/2-bitmap.getHeight()/2);
                                int right = pre.getLeft()-((pre.getLeft()-cur.getRight())/2-bitmap.getWidth()/2);
                                int bottom = pre.getBottom()-(pre.getHeight()/2-bitmap.getHeight()/2);
                                c.drawBitmap(bitmap,left,top,paint);
                            }
                        }
                    }
                }

            }
        }
    }
}
