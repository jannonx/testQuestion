package com.guyuan.dear.focus.hr.bindingAdapters;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.Date;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/27 17:53
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffMonthlyDetailBindingAdapter {

    /**
     * @param v
     * @param state 上午打卡状态：1.正常 2.迟到
     */
    @BindingAdapter(value = {"showAmAttendState", "isAttendDay"})
    public static void showAmAttendState(AppCompatImageView v, int state, int isAttendDay) {
        if (isAttendDay > 0) {
            switch (state) {
                case 2:
                    v.setImageResource(R.drawable.ic_svg_round_filled_orange_fa8c16_24dp);
                    break;
                case 0:
                    v.setImageResource(R.drawable.ic_svg_round_filled_red_f04864_24dp);
                    break;
                case 1:
                default:
                    v.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
                    break;
            }
        } else {
            v.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
        }

    }

    /**
     * @param v 下午打卡状态：1.正常 2.早退
     */
    @BindingAdapter(value = {"showPmAttendState", "isAttendDay"})
    public static void showPmAttendState(AppCompatImageView v, int state, int isAttendDay) {
        if (isAttendDay > 0) {
            switch (state) {
                case 2:
                    v.setImageResource(R.drawable.ic_svg_round_filled_purple_3436c7_24dp);
                    break;

                case 0:
                    v.setImageResource(R.drawable.ic_svg_round_filled_red_f04864_24dp);
                    break;
                case 1:
                default:
                    v.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
                    break;
            }
        } else {
            v.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
        }


    }

    @BindingAdapter("showChineseYearMonth")
    public static void showChineseYearMonth(AppCompatTextView view, long date) {
        String yearMonth = CalenderUtils.getInstance().toChineseYearMonth(date);
        view.setText(yearMonth);
    }

    @BindingAdapter("convertFullDateToHourMinute")
    public static void convertFullDateToHourMinute(AppCompatTextView view, String fullDate) {
        String data = "";
        if (fullDate != null) {
            try {
                Date format = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(fullDate);
                data = CalenderUtils.getInstance().toChineseHourAndMinute(format.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        view.setText(data);

    }

}
