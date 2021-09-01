package com.jti.event.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    private Log log = LogFactory.getLog(DateUtil.class);


    public static String getDate(Date date, String format) {
        if ( date != null ) {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
        return null;
    }

    /**
     * 현재 날짜를 format에 따라
     *
     * @param String
     *            format
     * @return String
     */
    public static String getDate(String format){
        return getDate((new Date()), format);
    }

    public static String getSoapDate(Date date) {
        if ( date != null ) {
            TimeZone tz = TimeZone.getTimeZone("GMT+9");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(tz);
            return df.format(date);
        }
        return null;
    }

    /**
     * 날짜 객체를 입력으로 받아 문자열로 반환한다.
     *
     * @param date
     *            날짜 객체
     * @param delimiter
     *            구분자
     * @return 문자열의 날짜가 반환된다.
     */
    public static String getDateStr(Date date, String delimiter) {
        if (date != null) {
            DateFormat df = new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");
            return df.format(date);
        }

        return null;
    }

    /**
     * 날짜 객체를 입력으로 받아 문자열로 반환한다.
     *
     * @param date
     *            날짜 객체
     * @param delimiter
     *            구분자
     * @return 문자열의 날짜가 반환된다.
     */
    public static String getDateStr(Date date, char delimiter) {
        return getDateStr(date, delimiter + "");
    }

    /**
     * GMT의 시각 형태로 표현하기 위한 Formatter.
     *
     * @return DateFormat / GMT Time formatter
     */
    public static DateFormat getGMTDateFormat(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormat;
    }
    /**
     * yyyyMMddHHmmss의 문자열을 GMT의 Date형 객체로 반환하는 메소드.
     *
     * @param date
     *            the date
     * @return Date / GMT 시간으로 설정된 Date 객체
     */
    public static Date getGMTDate(String date) {
        DateFormat format = getGMTDateFormat("yyyyMMddHHmmss");
        try {
            return format.parse(date);
        }
        catch ( ParseException e ) {
            return null;
        }
    }

    /**
     * 현재 날짜(yyyyMMdd)
     * @return
     */
    public static String getCurrentDate(){
        Date date = new Date();
        return getDateString(date, "yyyyMMdd");
    }

    /**
     * 날짜 문자열 출력
     * @param date
     * @param format
     * @return
     */
    public static String getDateString(Date date, String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * String형 날짜로 몇일후의 날짜를 리턴.
     *
     * @param dateStr
     *            yyyyMMdd
     * @param days
     *            +5 or -5 로 가능
     * @param format
     *            yyyyMMdd
     * @return
     */
    public static String getPlusMinusDate(String dateStr, int days, String format){
        if (dateStr == null || dateStr.length() == 0){
            return "";
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(dateStr.substring(0, 4)),
                Integer.parseInt(dateStr.substring(4, 6)) - 1,
                Integer.parseInt(dateStr.substring(6, 8)));

        return getPlusMinusDate(cal.getTime(), days, format);
    }

    /**
     * 날짜의 몇일후의 날짜를 리턴.
     *
     * @param Date
     *            date
     * @param int days +5 or -5 로 가능
     * @param String
     *            format
     * @return String
     */
    public static String getPlusMinusDate(Date date, int days, String pFormat){
        String format = pFormat;
        Calendar calen = Calendar.getInstance(Locale.KOREAN);
        calen.setTime(date);
        calen.setTimeInMillis(calen.getTimeInMillis() + (60L * 60 * 24 * 1000 * days));
        if (format == null || format.length() == 0){
            format = "yyyyMMdd";
        }

        return getDate(calen.getTime(), format);
    }

    public static String getTimeZoneDate(Date date, String timeZoneID, String format) {
        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        return df.format(date);
    }
}
