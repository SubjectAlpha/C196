package com.c196.exam.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Locale;

public final class DateManager {
    private DateManager(){}

    public static String buildISODate(int year, int mm, int dd) {
        String month = String.valueOf(mm + 1);
        String day = String.valueOf(dd);

        if (month.length() < 2) {
            month = "0" + month;
        }

        if(day.length() < 2) {
            day = "0" + day;
        }

        return year + "-" + month + "-" + day;
    }

    public static long convertString(String s) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return df.parse(s).toInstant().toEpochMilli();
    }

    public static String convertLong(long l){
        Instant i = Instant.ofEpochMilli(l);
        OffsetDateTime odt = i.atOffset(OffsetDateTime.now().getOffset());
        return buildISODate(odt.getYear(), odt.getMonthValue(), odt.getDayOfMonth());
    }
}
