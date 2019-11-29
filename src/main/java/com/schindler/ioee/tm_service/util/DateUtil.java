package com.schindler.ioee.tm_service.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

/**
 * Created by litim on 2018/3/20.
 */
@Slf4j
public class DateUtil {
    private static final int OFFLINE_OFFSET_SECONDS = 60 * 60 * 24 * 3;//60 * 60 * 24 * 3;


    /********
     * get the equipment offline offset date
     * @return
     */
    public static Date getEquipmentOfflineOffsetDate() {
        Date offsetDate = Date.from(LocalDateTime.now().
                minusSeconds(OFFLINE_OFFSET_SECONDS).atZone(ZoneId.systemDefault()).toInstant());
        return offsetDate;
    }


    private static ThreadLocal<SimpleDateFormat> yyyyMMddHHmmss_DateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    public static Optional<String> yyyyMMddHHmmss_Format(Date date) {
        try {
            return Optional.of(yyyyMMddHHmmss_DateFormat.get().format(date));
        } catch (Exception e) {
            log.error(" Date format error, date={}, error={}", date, e.getMessage());
        }

        return Optional.empty();
    }


    public static Optional<Date> yyyyMMddHHmmss_Parse(String dateString) {


        try {
            return Optional.of(yyyyMMddHHmmss_DateFormat.get().parse(dateString));
        } catch (ParseException e) {
            log.error(" Date parse error, dateString={}, error={}", dateString, e.getMessage());

        }

        return Optional.empty();
    }

    private static ThreadLocal<SimpleDateFormat> yyyyMMddDate_Format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Optional<String> yyyyMMdd_Format(Date date) {
        try {
            return Optional.of(yyyyMMddDate_Format.get().format(date));
        } catch (Exception e) {
            log.error("Date format error, date={}, error={}", date, e.getMessage());
        }

        return Optional.empty();
    }


    public static Optional<Date> yyyyMMdd_Parse(String dateString) {
        try {
            return Optional.of(yyyyMMddDate_Format.get().parse(dateString));
        } catch (ParseException e) {
            log.error(" Date parse error, dateString={}, error={}", dateString, e.getMessage());
        }

        return Optional.empty();
    }

    private static ThreadLocal<SimpleDateFormat> yyyy_MM_dd_DateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static Optional<String> yyyy_MM_dd_Format(Date date) {
        try {
            return Optional.of(yyyy_MM_dd_DateFormat.get().format(date));
        } catch (Exception e) {
            log.error("Date format error, date={}, error={}", date, e.getMessage());
        }

        return Optional.empty();
    }


    public static Optional<Date> yyyy_MM_dd_Parse(String dateString) {
        try {
            return Optional.of(yyyy_MM_dd_DateFormat.get().parse(dateString));
        } catch (ParseException e) {
            log.error(" Date parse error, dateString={}, error={}", dateString, e.getMessage());
        }

        return Optional.empty();
    }

    private static ThreadLocal<SimpleDateFormat> yyyy_MM_ddHH_mm_ss_DateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Optional<String> yyyy_MM_ddHH_mm_ss_Format(Date date) {
        try {
            return Optional.of(yyyy_MM_ddHH_mm_ss_DateFormat.get().format(date));
        } catch (Exception e) {
            log.error("Date format error, date={}, error={}", date, e.getMessage());
        }

        return Optional.empty();
    }

    public static Optional<Date> yyyy_MM_ddHH_mm_ss_Parse(String dateString) {
        try {
            return Optional.of(yyyy_MM_ddHH_mm_ss_DateFormat.get().parse(dateString));
        } catch (ParseException e) {
            log.error(" Date parse error, dateString={}, error={}", dateString, e.getMessage());
        }

        return Optional.empty();
    }
}
