package com.lskycity.support.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Simple DateUtils
 */
public class DateUtils {
    private static SimpleDateFormat FORMATER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static String getTimeString(long time) {
        return FORMATER.format(new Date(time));
    }

}
