package com.example.weather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeRenderUtils {

    private static SimpleDateFormat formatBuilder;

    /**
     * 将带中文格式的时间字符串
     *
     * @return
     */
    public static String formatTime(String time, String format) {
        String t = "";
        formatBuilder = new SimpleDateFormat("MMM dd,yyyy", Locale.CHINA);
        try {
            Date date = formatBuilder.parse(time);
            t = TimeRenderUtils.getDate(format, date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    public static Date getTime(String format, String time) {
        Date date = null;
        formatBuilder = new SimpleDateFormat(format);
        try {
            date = formatBuilder.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDate(String format, long time) {
        return getDate(format, new Date(time));
    }

    public static String getDate(String format) {
        return getDate(format, new Date());
    }

    public static String getDate(String format, Date date) {
        formatBuilder = new SimpleDateFormat(format, Locale.getDefault());
        return formatBuilder.format(date);
    }

    public static int getDay() {
        return getDay(new Date());
    }

    public static int getDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("d", Locale.getDefault());
        return Integer.valueOf(formatter.format(date));
    }

    public static int getMonth() {
        return getMonth(new Date());
    }

    public static int getMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("M", Locale.getDefault());
        return Integer.valueOf(formatter.format(date));
    }

    public static int getYear() {
        return getYear(new Date());
    }

    public static int getYear(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.getDefault());
        return Integer.valueOf(formatter.format(date));
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    public static String friendlyTime(String sdate) {
        Date time = null;

        if (isInEasternEightZones()) {
            time = toDate(sdate);
        } else {
            time = transformTime(toDate(sdate), TimeZone.getTimeZone("GMT+08"),
                    TimeZone.getDefault());
        }

        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else {
            ftime = days + "天前";
        }
        return ftime;
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sDate
     * @return
     */
    public static Date toDate(String sDate) {
        return toDate(sDate, dateFormater.get());
    }

    public static Date toDate(String sDate, SimpleDateFormat dateFormater) {
        try {
            return dateFormater.parse(sDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
     *
     * @return
     */
    public static boolean isInEasternEightZones() {
        return TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08");
    }

    /**
     * 根据不同时区，转换时间 2014年7月31日
     */
    public static Date transformTime(Date date, TimeZone oldZone,
                                     TimeZone newZone) {
        Date finalDate = null;
        if (date != null) {
            int timeOffset = oldZone.getOffset(date.getTime())
                    - newZone.getOffset(date.getTime());
            finalDate = new Date(date.getTime() - timeOffset);
        }
        return finalDate;
    }

    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     */
    public static String getDate(String str, String format1, String format2) {
        SimpleDateFormat sf1 = new SimpleDateFormat(format1, Locale.getDefault());
        SimpleDateFormat sf2 = new SimpleDateFormat(format2, Locale.getDefault());
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sfstr;
    }

    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     */
    public static String getTime(long ms, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format, Locale.getDefault());
        return format1.format(ms);
    }


    //  String pTime = "2012-03-12";
    public static String getWeek(Date date) {
        String Week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }
        return Week;
    }

    /**
     * 获取时间差
     */
    public static int[] getDiff(Date date1, Date date2) {

        int[] ints = new int[2];

        long diff = date2.getTime() - date1.getTime();
        int days = (int) (diff / (1000 * 60 * 60 * 24));
        ints[0] = days;
        int hours = (int) ((diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        ints[1] = hours;
        return ints;
    }

    /**
     * * 日期差天数、小时、分钟、秒数组
     * * @param startDate
     * * @param endDate
     * * @return
     */
    public static long[] getDisTime(Date startDate, Date endDate) {
        long timesDis = Math.abs(startDate.getTime() - endDate.getTime());
        long day = timesDis / (1000 * 60 * 60 * 24);
        long hour = timesDis / (1000 * 60 * 60) - day * 24;
        long min = timesDis / (1000 * 60) - day * 24 * 60 - hour * 60;
        long sec = timesDis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        return new long[]{day, hour, min, sec};
    }


    /**
     * 获得与某日期相隔几天的日期
     *
     * @param date     指定的日期
     * @param addCount 相隔的天数
     * @return 返回的日期
     */
    public static Date addDay(Date date, int addCount) {
        // Calendar cal = new GregorianCalendar();
        // 最好用Calendar.getInstance();
        // 因为有的地方，不是使用GregorianCalendar的。
        Calendar calendar = Calendar.getInstance();
        // calendar.setTime(strToDate(dateToStr(date)));
        calendar.setTime(date);
        calendar.add(Calendar.DATE, addCount);
        return calendar.getTime();
    }

}
