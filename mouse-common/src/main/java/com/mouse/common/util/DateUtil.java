package com.mouse.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间工具类
 */
public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_DATE_FORMAT_MS = "yyyy-MM-dd HH:mm:ss sss";

    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final DateFormat BA_AUTH_DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 将 Date 转化为 String 类型 精确到毫秒
     * @param date
     * @return
     */
    public static String dateToStringMS(Date date) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_DATE_FORMAT_MS);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            log.error("Translate Date to String in ms catch an Exception.");
        }
        return result;
    }

    /**
     * 将 String 转化为 Date 类型 精确到毫秒
     * @param dateStr
     * @return
     */
    public static Date stringToDateMS(String dateStr) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_DATE_FORMAT_MS);
        try {
            date = formater.parse(dateStr);
        } catch (Exception e) {
            log.error("Translate String to Date catch an Exception.");
        }
        return date;
    }

    /**
     * 将 Date 转化为 String 类型
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            log.error("Translate Date to String catch an Exception.");
        }
        return result;
    }

    /**
     * 将 String 转化为 Date 类型
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            date = formater.parse(dateStr);
        } catch (Exception e) {
            log.error("Translate String to Date catch an Exception.");
        }
        return date;
    }

    /**
     * 将普通时间 转化 为 ISO 8601 时间
     * @param date
     * @return
     */
    public static String getISODate(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT);
        df.setTimeZone(tz);

        return df.format(date);
    }

    /**
     * 将 ISO 8601 时间转化为 普通时间
     * @param isoDate
     * @return
     */
    public static Date transISO2Date(String isoDate) {
        try {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT);
            df.setTimeZone(tz);
            return df.parse(isoDate);
        } catch ( ParseException e ) {
            return null;
        }
    }

    /**
     * 将 Date 转化为供 BA认证使用的字符串
     * @param date
     * @return
     */
    public static String getBasicAuthString(Date date) {
        return BA_AUTH_DATE_FORMAT.format(date);
    }

    /**
     * 将BA认证使用的字符串 转化为 Date
     * @param dateStr
     * @return
     */
    public static Date getBasicAuthDate(String dateStr) {
        try {
            return BA_AUTH_DATE_FORMAT.parse(dateStr);
        } catch ( ParseException e ) {
            return null;
        }
    }
}