package com.kyd3snik.travel.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static Date getDate(int year, int month, int day) {
        return new GregorianCalendar(year, month - 1, day).getTime();
    }

    public static Date getToday() {
        return Calendar.getInstance().getTime();
    }


    public static long getPeriod(Date start, Date end) {
        long millisDiff = end.getTime() - start.getTime();
        return TimeUnit.DAYS.convert(millisDiff, TimeUnit.MILLISECONDS);
    }
}
