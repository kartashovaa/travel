package com.kyd3snik.travel.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static Date getDate(int year, int month, int day) {
        return new GregorianCalendar(year, month - 1, day).getTime();
    }

    public static Date getToday() {
        return Calendar.getInstance().getTime();
    }

    //TODO: Реализовать метод
    public static int getPeriod(Date start, Date end) {
        return 3;
    }
}
