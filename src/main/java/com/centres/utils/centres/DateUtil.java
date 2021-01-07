/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: DateUtil
 * Author:   CentreS
 * Date:     2019-06-21 16:02
 * Description: 时间工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.utility;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author CentreS
 * @Description: 时间工具
 * @create 2019-06-21
 */
public class DateUtil {

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static String getRecordId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        return ft.format(date);
    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(date);
    }

    public static Long getCurrentTimeLong() {
        Date date = new Date();
        return date.getTime();
    }

    public static String getDateTime(Long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(date);
    }

    /**
     * 新店报告
     *
     * @param timestamp
     * @return
     */
    public static String ecgDate(String timestamp) {
        try {
            if (StringUtils.isNullorEmpty(timestamp)) {
                return "";
            }
            SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            date = new Date(ft1.parse(timestamp).getTime());
            SimpleDateFormat ft = new SimpleDateFormat("MM-dd HH:mm:ss");
            return ft.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(ecgDate("2020-05-08 00:00:00"));
    }


    public static String getDatePoor(String startTime) {
        return getDatePoor(startTime, getCurrentTime());
    }

    public static String getDatePoor(String startTime, String endTime) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = ft.parse(startTime);
            endDate = ft.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return hour + "小时" + min + "分钟";
    }

    public static String getTwoMinutePast(String time) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = ft.parse(time);
            return ft.format(date.getTime() + 1000 * 60 * 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 改变时间
     *
     * @param dateTime
     * @param calendarType like Calendar.MONTH
     * @param num
     * @return
     */
    public static String modifyDateTime(String dateTime, Integer calendarType, Integer num) {
        if (StringUtils.isNullorEmpty(dateTime)) {
            return "";
        }
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(ft.parse(dateTime));
            cal.add(calendarType, num);
            return ft.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long modifyDateTime(Long dateTime, Integer calendarType, Integer num) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = ft.format(dateTime);
        return DateUtil.getDateTimeLong(modifyDateTime(format, calendarType, num));
    }

    /**
     * 比较时间
     *
     * @param dateTime1
     * @param dateTime2
     * @return
     */
    public static boolean compareDate(String dateTime1, String dateTime2) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date one = ft.parse(dateTime1);
            Date two = ft.parse(dateTime2);
            return one.getTime() > two.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String timeDifferent(String startTime) {
        return timeDifferent(startTime, getCurrentTime());
    }

    /**
     * 返回时间差
     *
     * @param startTime
     * @param endTime
     * @return 字符串
     */
    public static String timeDifferent(String startTime, String endTime) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            long between = end.getTime() - start.getTime();
            long day = between / (24 * 60 * 60 * 1000);
            long hour = (between / (60 * 60 * 1000) - day * 24);
            long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            day = day < 0 ? 0 : day;
            hour = hour < 0 ? 0 : hour;
            min = min < 0 ? 0 : min;
            s = s < 0 ? 0 : s;
            return day + "-" + hour + "-" + min;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static final int SECOND = 0;
    public static final int MIN = 1;
    public static final int HOUR = 2;
    public static final int DAY = 3;

    /**
     * 获取时间差(向上取整)
     *
     * @param startTime
     * @param endTime
     * @param unit      单位 DateUtil.SECOND
     * @return
     */
    public static Integer timeDifferent(String startTime, String endTime, int unit) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            long between = end.getTime() - start.getTime();
            Long resultDay = new BigDecimal(between).divide(new BigDecimal(24 * 60 * 60 * 1000), BigDecimal.ROUND_CEILING).longValue();
            Long resultHour = new BigDecimal(between).divide(new BigDecimal((60 * 60 * 1000)), BigDecimal.ROUND_CEILING).longValue();
            Long resultMin = new BigDecimal(between).divide(new BigDecimal((60 * 1000)), BigDecimal.ROUND_CEILING).longValue();
            Long resultSecond = new BigDecimal(between).divide(new BigDecimal(1000), BigDecimal.ROUND_CEILING).longValue();
            if (unit == DateUtil.SECOND) {
                return resultSecond.intValue();
            } else if (unit == DateUtil.MIN) {
                return resultMin.intValue();
            } else if (unit == DateUtil.HOUR) {
                return resultHour.intValue();
            } else if (unit == DateUtil.DAY) {
                return resultDay.intValue();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public static void main(String[] args) {
//        System.out.println(timeDifferent("2020-08-05 00:00:00","2020-08-06 00:00:01",DateUtil.DAY));
//    }

    public static Long timeDifferentLong(String startTime) {
        return timeDifferentLong(startTime, DateUtil.getCurrentTime());
    }

    /**
     * 返回时间差
     *
     * @param startTime
     * @param endTime
     * @return Long min
     */
    public static Long timeDifferentLong(String startTime, String endTime) {
        String temp = DateUtil.getCurrentTime();
        if (StringUtils.isNullorEmpty(startTime)) {
            startTime = temp;
        }
        if (StringUtils.isNullorEmpty(endTime)) {
            endTime = temp;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            long between = end.getTime() - start.getTime();
            long min = between / (60 * 1000);
            return min;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }


    public static Long getDateTimeLong(String startTime) {
        if (StringUtils.isNullorEmpty(startTime)) {
            return 0L;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(startTime);
            return start.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 策略---根据时间差获取筛选体温记录的间隔
     *
     * @return 时间间隔：分钟
     */
    public static Integer getInterval(Long min) {
        Long sixHour = 60 * 6L;
        Long twelveHour = 60 * 12L;
        if (min < sixHour) {
            return 30;
        } else if (min > sixHour && min < twelveHour) {
            return 60;
        } else if (min > twelveHour) {
            return 120;
        }
        return null;
    }

    /**
     * 半个小时为间隔，将时间向前取整
     *
     * @param times
     * @return
     */
    public static String integerForward(String times) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        SimpleDateFormat halfFormatter = new SimpleDateFormat("yyyy-MM-dd HH:30:00");
        SimpleDateFormat currentFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date time = currentFormat.parse(times);
            long between = time.getTime() - currentFormat.parse(formatter.format(time)).getTime();
            if (between >= 1000 * 60 * 30) {
                return halfFormatter.format(time);
            } else {
                return formatter.format(time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 半个小时为间隔，时间向后取整
     *
     * @param times
     * @return
     */
    public static String integerBack(String times) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        SimpleDateFormat halfFormatter = new SimpleDateFormat("yyyy-MM-dd HH:30:00");
        SimpleDateFormat currentFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date time = currentFormat.parse(times);
            long between = time.getTime() - currentFormat.parse(formatter.format(time)).getTime();
            if (between >= 1000 * 60 * 30) {
                time.setTime(time.getTime() + 1000 * 60 * 60);
                return formatter.format(time);
            } else {
                return halfFormatter.format(time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
