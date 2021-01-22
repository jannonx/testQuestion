package com.guyuan.dear.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jannonx
 * on 2019/12/21
 */
public class DateUtil {

  /**
   * 描述：Date类型转化为String类型.
   *
   * @param date   the date
   * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
   * @return String String类型日期时间
   */
  public static String getStringByFormat(Date date, String format) {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
    String strDate = null;
    try {
      strDate = mSimpleDateFormat.format(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return strDate;
  }

  // 获得当天0点时间
  public static Date getTimesmorning() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }


  // 获得当天24点时间
  public static Date getTimesnight() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 24);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  //获取当前日期上个月日期
  public static Date getLastMonthDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 24);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
    return cal.getTime();
  }

  public static Date getLastYearDate(){
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 24);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 12);
    return cal.getTime();
  }



  //获取上年第一天日期
  public static Date getLastYearFirstDayTime() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
    cal.set(Calendar.MONTH, 0);
    cal.set(Calendar.DATE, 1);
    return cal.getTime();
  }


  //获取上年最后一天日期
  public static Date getLastYearLastDayTime() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
    cal.set(Calendar.MONTH, 11);
    cal.set(Calendar.DATE, 31);
    return cal.getTime();
  }


  //获取指定年份的第一天日期
  public static Date getYearFirstDayTime(int year) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, 0);
    cal.set(Calendar.DATE, 1);
    return cal.getTime();
  }


  //获取指定年份的最后一天日期
  public static Date getYearLastDayTime(int year) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.MILLISECOND, 0);
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, 11);
    cal.set(Calendar.DATE, 31);
    return cal.getTime();
  }


  // 获得本周一0点时间
  public static Date getTimesWeekmorning() {
    Calendar cal = Calendar.getInstance();
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0,
        0, 0);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return cal.getTime();
  }

  // 获得本周日24点时间
  public static Date getTimesWeeknight() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(getTimesWeekmorning());
    cal.add(Calendar.DAY_OF_WEEK, 7);
    return cal.getTime();
  }

  // 获得本月第一天0点时间
  public static Date getTimesMonthmorning() {
    Calendar cal = Calendar.getInstance();
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0,
        0, 0);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    return cal.getTime();
  }

  // 获得本月最后一天24点时间
  public static Date getTimesMonthnight() {
    Calendar cal = Calendar.getInstance();
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0,
        0, 0);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    cal.set(Calendar.HOUR_OF_DAY, 24);
    return cal.getTime();
  }

  /**
   * 得到某年某周的第一天
   *
   * @param year
   * @param week
   * @return
   */
  public static Date getFirstDayOfWeek(int year, int week) {
    week = week - 1;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, Calendar.JANUARY);
    calendar.set(Calendar.DATE, 1);
    Calendar cal = (Calendar) calendar.clone();
    cal.add(Calendar.DATE, week * 7);
    return getFirstDayOfWeek(cal.getTime());
  }

  /**
   * 得到某年某周的最后一天
   *
   * @param year
   * @param week
   * @return
   */
  public static Date getLastDayOfWeek(int year, int week) {
    week = week - 1;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, Calendar.JANUARY);
    calendar.set(Calendar.DATE, 1);
    Calendar cal = (Calendar) calendar.clone();
    cal.add(Calendar.DATE, week * 7);
    return getLastDayOfWeek(cal.getTime());
  }

  /**
   * 取得当前日期所在周的第一天
   *
   * @param date
   * @return
   */
  public static Date getFirstDayOfWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek(Calendar.SUNDAY);
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK,
        calendar.getFirstDayOfWeek()); // Sunday
    return calendar.getTime();
  }

  /**
   * 取得当前日期所在周的最后一天
   *
   * @param date
   * @return
   */
  public static Date getLastDayOfWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek(Calendar.SUNDAY);
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK,
        calendar.getFirstDayOfWeek() + 6); // Saturday
    return calendar.getTime();
  }

  /**
   * 取得当前日期所在周的前一周最后一天
   *
   * @param date
   * @return
   */
  public static Date getLastDayOfLastWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return getLastDayOfWeek(calendar.get(Calendar.YEAR),
        calendar.get(Calendar.WEEK_OF_YEAR) - 1);
  }

  /**
   * 返回指定日期的月的第一天
   *
   * @param year
   * @param month
   * @return
   */
  public static Date getFirstDayOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH), 1);
    return calendar.getTime();
  }

  /**
   * 返回指定年月的月的第一天
   *
   * @param year
   * @param month
   * @return
   */
  public static Date getFirstDayOfMonth(Integer year, Integer month) {
    Calendar calendar = Calendar.getInstance();
    if (year == null) {
      year = calendar.get(Calendar.YEAR);
    }
    if (month == null) {
      month = calendar.get(Calendar.MONTH);
    }
    calendar.set(year, month, 1);
    return calendar.getTime();
  }



  /**
   * 返回指定日期的月的最后一天
   *
   * @param year
   * @param month
   * @return
   */
  public static Date getLastDayOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH), 1);
    calendar.roll(Calendar.DATE, -1);
    return calendar.getTime();
  }

  /**
   * 返回指定年月的月的最后一天
   *
   * @param year
   * @param month
   * @return
   */
  public static Date getLastDayOfMonth(Integer year, Integer month) {
    Calendar calendar = Calendar.getInstance();
    if (year == null) {
      year = calendar.get(Calendar.YEAR);
    }
    if (month == null) {
      month = calendar.get(Calendar.MONTH);
    }
    calendar.set(year, month, 1);
    calendar.roll(Calendar.DATE, -1);
    return calendar.getTime();
  }

  /**
   * 返回指定日期的上个月的最后一天
   *
   * @param year
   * @param month
   * @return
   */
  public static Date getLastDayOfLastMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) - 1, 1);
    calendar.roll(Calendar.DATE, -1);
    return calendar.getTime();
  }

  /**
   * 返回指定日期的季的第一天
   *
   * @param year
   * @param quarter
   * @return
   */
  public static Date getFirstDayOfQuarter(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
        getQuarterOfYear(date));
  }

  /**
   * 返回指定年季的季的第一天
   *
   * @param year
   * @param quarter
   * @return
   */
  public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
    Calendar calendar = Calendar.getInstance();
    Integer month = Integer.valueOf(0);
    if (quarter == 1) {
      month = 1 - 1;
    } else if (quarter == 2) {
      month = 4 - 1;
    } else if (quarter == 3) {
      month = 7 - 1;
    } else if (quarter == 4) {
      month = 10 - 1;
    } else {
      month = calendar.get(Calendar.MONTH);
    }
    return getFirstDayOfMonth(year, month);
  }

  /**
   * 返回指定日期的季的最后一天
   *
   * @param year
   * @param quarter
   * @return
   */
  public static Date getLastDayOfQuarter(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
        getQuarterOfYear(date));
  }

  /**
   * 返回指定年季的季的最后一天
   *
   * @param year
   * @param quarter
   * @return
   */
  public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
    Calendar calendar = Calendar.getInstance();
    Integer month = Integer.valueOf(0);
    if (quarter == 1) {
      month = 3 - 1;
    } else if (quarter == 2) {
      month = 6 - 1;
    } else if (quarter == 3) {
      month = 9 - 1;
    } else if (quarter == 4) {
      month = 12 - 1;
    } else {
      month = calendar.get(Calendar.MONTH);
    }
    return getLastDayOfMonth(year, month);
  }

  /**
   * 返回指定日期的上一季的最后一天
   *
   * @param year
   * @param quarter
   * @return
   */
  public static Date getLastDayOfLastQuarter(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
        getQuarterOfYear(date));
  }

  /**
   * 返回指定年季的上一季的最后一天
   *
   * @param year
   * @param quarter
   * @return
   */
  public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
    Calendar calendar = Calendar.getInstance();
    Integer month = Integer.valueOf(0);
    if (quarter == 1) {
      month = 12 - 1;
    } else if (quarter == 2) {
      month = 3 - 1;
    } else if (quarter == 3) {
      month = 6 - 1;
    } else if (quarter == 4) {
      month = 9 - 1;
    } else {
      month = calendar.get(Calendar.MONTH);
    }
    return getLastDayOfMonth(year, month);
  }

  /**
   * 返回指定日期的季度
   *
   * @param date
   * @return
   */
  public static int getQuarterOfYear(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH) / 3 + 1;
  }


  //获取当前日期
  public static String getCurrentTime() {
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return dateFormat.format(date);
  }
}
