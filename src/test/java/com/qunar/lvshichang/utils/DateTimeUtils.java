package com.qunar.lvshichang.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

public class DateTimeUtils {


    @Test
    public void testBetweenDays() throws ParseException {
        Date date1 = DateUtils.parseDate("20170903 00:50:00", "yyyyMMdd HH:mm:ss");
        Date date2 = DateUtils.parseDate("20170902 00:50:00", "yyyyMMdd HH:mm:ss");

        int i = betweenDays(date1, date2);
        System.out.println(i);
    }


    public static int betweenDays(Date date1, Date date2) {
        LocalDate localDate1 = LocalDate.fromDateFields(date1);
        LocalDate localDate2 = LocalDate.fromDateFields(date2);
        Days days = Days.daysBetween(localDate1, localDate2);
        return days.getDays();
    }
}
