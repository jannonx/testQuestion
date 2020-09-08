package tl.com.easy_recycleview_library.util;

import androidx.recyclerview.widget.RecyclerView;



import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * RecyclerView设置Header/Footer所用到的工具类
 * @author Lzx
 * @created 2016/9/30 10:36
 *
 */
public class RecyclerViewUtils {

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getLayoutPosition()方法
     *
     * @param recyclerView
     * @param holder
     * @return
     */
    public static int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof BaseRecyclerViewAdapter) {

            int headerViewCounter = ((BaseRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getLayoutPosition() - (headerViewCounter + 1);
            }
        }

        return holder.getLayoutPosition();
    }

}