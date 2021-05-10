package com.mercury.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static SimpleDateFormat sFormatter = new SimpleDateFormat();
    public final static String SHORT_TIME_FORMAT = "mm:ss";
    /**
     * 时间戳转对应格式日期
     */
    public static String longToStr(long m) {
        String dateString = null;
        synchronized (sFormatter) {
            sFormatter.applyPattern(SHORT_TIME_FORMAT);
            dateString = sFormatter.format(new Date(m));
        }
        return dateString;
    }

}
