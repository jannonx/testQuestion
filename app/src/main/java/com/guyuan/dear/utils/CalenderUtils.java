package com.guyuan.dear.utils;

import android.text.TextUtils;

import com.example.mvvmlibrary.util.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * @description: 日历工具类
 * @author: 廖华凯
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class CalenderUtils {

    private SimpleDateFormat simpleDateFormatFull;
    private SimpleDateFormat simpleDateFormatByDay;
    private SimpleDateFormat simpleDateFormatByHour;
    private SimpleDateFormat simpleDateFormatByYear;
    private SimpleDateFormat simpleDateFormatByYearMonth;
    private SimpleDateFormat simpleDateFormatByChineseDay;
    private SimpleDateFormat simpleDateFormatByChineseMonthAndDay;
    private SimpleDateFormat simpleDateFormatByChineseHourAndMinute;
    private SimpleDateFormat simpleDateFormatByHourAndMinute;
    private SimpleDateFormat simpleDateFormatByChineseYearMonth;
    private SimpleDateFormat simpleDateFormatByNumericYearMonth;
    private SimpleDateFormat mSimpleDateFormatByYearMonthDayHourMin;
    private SimpleDateFormat mSimpleDateFormatByLongYearAndMonth;
    private SimpleDateFormat mSimplePointYearMonthDay;
    private static CalenderUtils instance;
    private String[] chineseMonths =
            new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月",};
    private String[] chineseWeekDays = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private final SimpleDateFormat stdHourMinSecFormat;

    //2019-11-28 08:56:36
    private CalenderUtils() {
        simpleDateFormatFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        simpleDateFormatByHour = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        simpleDateFormatByDay = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        simpleDateFormatByYear = new SimpleDateFormat("yyyy", Locale.CHINA);
        simpleDateFormatByChineseDay = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        simpleDateFormatByChineseMonthAndDay = new SimpleDateFormat("MM月dd日", Locale.CHINA);
        simpleDateFormatByChineseHourAndMinute = new SimpleDateFormat("HH时mm分", Locale.CHINA);
        simpleDateFormatByHourAndMinute = new SimpleDateFormat("HH:mm", Locale.CHINA);
        simpleDateFormatByChineseYearMonth = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
        simpleDateFormatByNumericYearMonth = new SimpleDateFormat("yy-MM", Locale.CHINA);
        simpleDateFormatByYearMonth = new SimpleDateFormat("yy-MM", Locale.CHINA);
        mSimpleDateFormatByYearMonthDayHourMin = new SimpleDateFormat("yyyy年MM月dd日HH时mm分", Locale.CHINA);
        mSimpleDateFormatByLongYearAndMonth = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        mSimplePointYearMonthDay = new SimpleDateFormat("yy.MM.dd", Locale.CHINA);
        stdHourMinSecFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    }

    /**
     * 时：分：秒 如 “12:30:45”
     *
     * @param timeMills
     * @return
     */
    public String toStandardHourMinSecFormat(long timeMills) {
        return stdHourMinSecFormat.format(new Date(timeMills));
    }

    /**
     * 解析 HH:mm:ss 的时间格式
     *
     * @param format
     * @return
     */
    public Date parseStandardHourMinSecFormat(String format) {
        try {
            return stdHourMinSecFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * yyyy-MM
     *
     * @param timeMillis
     * @return
     */
    public String toLongYearAndMonth(long timeMillis) {
        return mSimpleDateFormatByLongYearAndMonth.format(new Date(timeMillis));
    }

    /**
     * yyyy年MM月dd日
     *
     * @param timeMills
     * @return
     */
    public String toChineseYearMonthDayFormat(long timeMills) {
        return simpleDateFormatByChineseDay.format(new Date(timeMills));
    }

    public static CalenderUtils getInstance() {
        if (instance == null) {
            synchronized (CalenderUtils.class) {
                if (instance == null) {
                    instance = new CalenderUtils();
                }
            }
        }
        return instance;
    }

    public boolean checkIfTheSameDay(long day1, long day2) {
        Calendar left = Calendar.getInstance();
        left.setTimeInMillis(day1);

        Calendar right = Calendar.getInstance();
        right.setTimeInMillis(day2);

        if (left.get(Calendar.YEAR) == right.get(Calendar.YEAR)
                && left.get(Calendar.MONTH) == right.get(Calendar.MONTH)
                && left.get(Calendar.DAY_OF_MONTH) == right.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    /**
     * 根据数字月份获取相应的中文月份，数字从0开始代表一月。
     */
    public String getChineseMonth(int month) {
        month = month % chineseMonths.length;
        return chineseMonths[month];
    }

    public String getChineseDayOfWeek(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return chineseWeekDays[i - 1];
    }

    /**
     * xx时xx分
     *
     * @param timeMillis
     * @return
     */
    public String toChineseHourAndMinute(long timeMillis) {
        return simpleDateFormatByChineseHourAndMinute.format(new Date(timeMillis));
    }

    /**
     * 12:14
     *
     * @param timeMillis
     * @return
     */
    public String toHourAndMinute(long timeMillis) {
        return simpleDateFormatByHourAndMinute.format(new Date(timeMillis));
    }

    public String toChineseYearMonth(long timeMillis) {
        return simpleDateFormatByChineseYearMonth.format(new Date(timeMillis));
    }

    public String toNumericYearMonth(long timeMillis) {
        return simpleDateFormatByNumericYearMonth.format(new Date(timeMillis));
    }

    public String toYearMonth(long timeMillis) {
        return simpleDateFormatByYearMonth.format(new Date(timeMillis));
    }

    public String getChineseMonthAndDay(long timeMillis) {
        return simpleDateFormatByChineseMonthAndDay.format(new Date(timeMillis));
    }

    public String toSmartFactoryDateStringFormat(long time) {
        return simpleDateFormatFull.format(new Date(time));
    }

    public String toYearMonthDayHourMinuteFormat(long time) {
        return mSimpleDateFormatByYearMonthDayHourMin.format(new Date(time));
    }

    public String getCurrentYearByString() {
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public String getYearByDate(long time) {
        return simpleDateFormatByYear.format(new Date(time));
    }

    /**
     * 根据时间戳long,获取当天日期
     */
    public String getDateByDate(long time) {
        return simpleDateFormatByDay.format(new Date(time));
    }

    /**
     * 解析 yyyy-MM-dd HH:mm:ss的时间格式
     *
     * @param date
     * @return
     */
    public Date parseSmartFactoryDateStringFormat(String date) {
        try {
            return simpleDateFormatFull.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.showLog(e.getMessage());
        }
        return null;
    }


    public String parseSmartFactoryChineseDateStringFormat(String date) {
        try {
            Date simpleDate = simpleDateFormatFull.parse(date);
            if (simpleDate != null) {
                return simpleDateFormatByChineseDay.format(simpleDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.showLog(e.getMessage());
        }
        return null;
    }


    /**
     * 解析 yyyy-MM-dd的日期
     *
     * @param date
     * @return
     */
    public Date parseSmartFactoryDateFormatByDay(String date) {
        try {
            return simpleDateFormatByDay.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.showLog(e.getMessage());
        }
        return null;
    }

    public String toSmartFactoryDateFormatByFull(long time) {
        return mSimplePointYearMonthDay.format(new Date(time));
    }

    public String toSmartFactoryDateFormatByFull(Date date) {
        return mSimplePointYearMonthDay.format(date);
    }

    public String toSmartFactoryDateFormatByFull(String dateString) {
        if (TextUtils.isEmpty(dateString)) return "";
        Date date = null;
        try {
            date = simpleDateFormatByDay.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.showLog(e.getMessage());
        }
        if (date != null) {
            return mSimplePointYearMonthDay.format(date);
        } else {
            return "未知日期";
        }

    }

    /**
     * 把日期转成 yyyy-MM-dd 格式
     *
     * @param time
     * @return
     */
    public String toSmartFactoryDateFormatByDay(long time) {
        return simpleDateFormatByDay.format(new Date(time));
    }

    public String formatByHour(long time) {
        return simpleDateFormatByHour.format(new Date(time));
    }

    public String toChineseMonthAndDay(long time) {
        return simpleDateFormatByChineseMonthAndDay.format(new Date(time));
    }

    public String getHourAndMinuteDescription(long duration) {
        long hour = duration / 1000 / 60 / 60;
        long minute = (duration - hour * 1000 * 60 * 60) / 1000 / 60;
        if (hour > 0) {
            return String.format(Locale.CHINA, "%d小时%d分", hour, minute);
        } else if (minute >= 0) {
            return String.format(Locale.CHINA, "%d分", minute);
        } else {
            return "时间间隔小于0分，请检查";
        }
    }

    public String getDaysByDate(long duration) {
        double result;
        long days = duration / 1000 / 60 / 60 / 24;
        long hours = (duration - days * 1000 * 60 * 60 * 24) / 1000 / 60 / 60;
        if (hours > 4) {
            result = ++days;
        } else {
            result = days + 0.5;
        }
        return result + "";
    }

    public String getHoursByDate(long duration) {
        double result;
        long hours = duration / 1000 / 60 / 60;
        long minute = (duration - hours * 1000 * 60 * 60) / 1000 / 60;
        if (minute > 30) {
            result = hours + 0.5;
        } else {
            result = hours;
        }
        return result + "";
    }

//  public long getXMonthsLaterInYearMonthDayFormat(long date,int x){
//    Calendar startDate = Calendar.getInstance();
//    startDate.setTimeInMillis(date);
//
//
//  }

    public long getXMonthsAgoInYearMonthFormat(long date, int x) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        for (int i = 0; i < x; i++) {
            month--;
            if (month == 0) {
                month = 12;
                year--;
            }
        }
        month--;
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTimeInMillis();
    }

    public long getXDaysAgoInYearMonthFormat(long date, int x) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        for (int i = 0; i < x; i++) {
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            dayOfMonth -= 1;
            if (dayOfMonth == 0) {
                int month = calendar.get(Calendar.MONTH);
                month -= 1;
                if (month < 0) {
                    int year = calendar.get(Calendar.YEAR);
                    calendar.set(Calendar.YEAR, year - 1);
                    month = 11;
                }
                calendar.set(Calendar.MONTH, month);
                dayOfMonth = 30;
            }
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
        return calendar.getTimeInMillis();
    }

    //将字符串时间格式转为long形
    public long getTimeToLong(String DateTime) {
        Date date = null;
        long time = -1;
        try {
            date = simpleDateFormatFull.parse(DateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            time = date.getTime();
        }

        return time;
    }

    /**
     * 计算两段时间包含了多少个月。
     */
    public int getMonthCountByDates(long startTime, long endTime) {
        Calendar from = Calendar.getInstance();
        from.setTimeInMillis(startTime);
        Calendar to = Calendar.getInstance();
        to.setTimeInMillis(endTime);
        int monthCountByEnd = to.get(Calendar.YEAR) * 12 + (to.get(Calendar.MONTH) + 1);
        int monthCountFromStart = from.get(Calendar.YEAR) * 12 + (from.get(Calendar.MONTH) + 1);
        return monthCountByEnd - monthCountFromStart + 1;
    }

    /**
     * 设置小时、分钟、秒中
     */
    public Date getSettingDate(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获取当年的第一天
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    public static Date getPassSevenDay() {
        Calendar c = Calendar.getInstance();

        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        return c.getTime();
    }

    public static Date getPassOneMonth() {
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    public static Date getPassThreeMonth() {
        Calendar c = Calendar.getInstance();
        //过去三个月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        return c.getTime();
    }

    public static Date getPassOneYear() {
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        return c.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    private String[] weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 格式样板：2020-9-27 (星期日)
     *
     * @param startTime
     */
    public String toDateAndWeekDayFormat(long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String format = simpleDateFormatByDay.format(new Date(startTime));
        return format + " (" + weekDays[index] + ")";
    }
}
